package cyse7125.fall2022.group03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cyse7125.fall2022.group03.model.Task;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class KafkaConsumerApplication {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerApplication.class);

	List<String> messages = new ArrayList<>();
	List<Task> taskMessages = new ArrayList<>();

//	private static final String GROUP_ID = "group2";
	private static final String GROUP_ID_3 = "group3";
	private static final String topicString = "topic01-team3";
	
//	@GetMapping("/consumeStringMessage")
//	public List<String> consumeMsg() {
//		LOGGER.debug(String.format("csye7125: consumeMsg called " + messages.toString()));
//		return messages;
//	}
	
//	@KafkaListener(groupId = GROUP_ID, topics = topicString, containerFactory = "stringKafkaListenerContainerFactory")
//	public List<String> getMsgFromTopic(String data) {
//		LOGGER.debug("csye7125: getMsgFromTopic called received in string" + data);
//		messages.add(data);
//		return messages;
//	}
	

	@GetMapping("/consumeJsonMessage")
	public List<Task> consumeJsonMessage() {
		LOGGER.debug(String.format("csye7125: consumeJsonMessage called "));
		if(taskMessages!=null) {
			LOGGER.debug(taskMessages.toString());
		}
		return taskMessages;
	}
	
	

	@KafkaListener(groupId = GROUP_ID_3, topics = topicString, containerFactory = "userKafkaListenerContainerFactory")
	public List<Task> getJsonMsgFromTopic(Task task) {
		LOGGER.debug(String.format("csye7125: Task received in json -> "));
		if(task!=null) {
			LOGGER.debug(task.toString());
			
			createElasticClient(task);
		}
		taskMessages.add(task);
		return taskMessages;
	}

	public static void main(String[] args) {
		LOGGER.debug(String.format("csye7125: main method called "));
		
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}


	public static void createElasticClient(Task task) {
		LOGGER.debug(String.format("csye7125: createElasticClient method called "));
		
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("elasticsearch-master", 9200, "http")));

		CreateIndexRequest request = new CreateIndexRequest("taskindex");
		request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 2));
		try {
			if(!client.indices().exists(new GetIndexRequest("taskindex"), RequestOptions.DEFAULT)) {
				CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
				System.out.println("response id: " + createIndexResponse.index());
			}
		} catch (IOException e) {
			LOGGER.debug(String.format("got 1 exception ***"));
			e.printStackTrace();
		}

		IndexRequest indexRequest = new IndexRequest("taskindex");
		indexRequest.id(task.getTaskId());
		try {
			indexRequest.source(new ObjectMapper().findAndRegisterModules().writeValueAsString(task), XContentType.JSON);
		} catch (JsonProcessingException e) {
			LOGGER.debug(String.format("got 2 exception ***"));
			e.printStackTrace();
		}

		IndexResponse indexResponse = null;

		try {
			indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			LOGGER.debug(String.format("got 3 exception ***"));
			e.printStackTrace();
		}
		
		if (indexResponse != null) {
			LOGGER.debug(String.format("csye7125: Elastic search index response -> " + indexResponse.getId()));
			LOGGER.debug(String.format("csye7125: Elastic search index response -> " +indexResponse.getResult().name()));
		} else {
			LOGGER.debug(String.format("-----indexResponse is null------"));
		}

	}

}
