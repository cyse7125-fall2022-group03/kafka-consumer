package cyse7125.fall2022.group03;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import cyse7125.fall2022.group03.model.Task;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class KafkaConsumerApplication {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerApplication.class);

	//Task taskFromTopic = null;
	List<String> messages = new ArrayList<>();
	List<Task> taskMessages = new ArrayList<>();

	private static final String GROUP_ID = "group2";
	private static final String GROUP_ID_3 = "group3";
	private static final String topicString = "topic02-team3";
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg() {
		LOGGER.debug(String.format("csye7125: consumeMsg called " + messages.toString()));
		return messages;
	}
	
	@KafkaListener(groupId = GROUP_ID, topics = topicString, containerFactory = "stringKafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		LOGGER.debug("csye7125: getMsgFromTopic called received in string" + data);
		messages.add(data);
		return messages;
	}
	

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
			initializeElasticSearch(task);
		}
		taskMessages.add(task);
		return taskMessages;
	}

	public static void main(String[] args) {
		LOGGER.debug(String.format("csye7125: main method called "));
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	public static void initializeElasticSearch(Task task){
		LOGGER.debug(String.format("csye7125: Elastic search received -> "));
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("elasticsearch-master", 9200, "http")));
		IndexRequest indexRequest = new IndexRequest("sampleIndex");
		indexRequest.id("003");
		try {
			LOGGER.debug(String.format("csye7125: Elastic search inside try index -> "));
			indexRequest.source(new ObjectMapper().writeValueAsString(task), XContentType.JSON);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		IndexResponse indexResponse = null;
		try {
			indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
			LOGGER.debug(String.format("csye7125: Elastic search index response -> "));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
