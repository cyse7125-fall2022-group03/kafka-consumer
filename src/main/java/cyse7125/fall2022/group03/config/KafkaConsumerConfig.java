package cyse7125.fall2022.group03.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import cyse7125.fall2022.group03.model.Task;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBroker;

    private static final String GROUP_ID = "group2";
    private static final String GROUP_ID_3 = "group3";

	// config for String plain text
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

	// @Bean
	// public ConsumerFactory<String, String> stringConsumerFactory() {
	// 	LOGGER.debug(String.format("csye7125: stringConsumerFactory() called  "+ kafkaBroker));
	// 	Map<String, Object> configs = new HashMap<>();
	// 	configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
	// 	configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	// 	configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	// 	configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
	// 	return new DefaultKafkaConsumerFactory<>(configs);
	// }

	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, String> stringKafkaListenerContainerFactory() {
	// 	LOGGER.debug(String.format("csye7125: stringKafkaListenerContainerFactory() called  "));
	// 	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
	// 	factory.setConsumerFactory(stringConsumerFactory());
	// 	return factory;
	// }
	

	// config for json data
	 @Bean
	 public ConsumerFactory<String, Task> userConsumerFactory() {
	 	LOGGER.debug(String.format("csye7125: userConsumerFactory() called  "+ kafkaBroker));
	 	Map<String, Object> configs = new HashMap<>();
	 	configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
	 	configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	 	configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	 	configs.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_3);
	 	return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(Task.class));
	 }

	 @Bean
	 public ConcurrentKafkaListenerContainerFactory<String, Task> userKafkaListenerContainerFactory() {
	 	LOGGER.debug(String.format("csye7125: userKafkaListenerContainerFactory() called  "));
	 	ConcurrentKafkaListenerContainerFactory<String, Task> factory = new ConcurrentKafkaListenerContainerFactory<String, Task>();
	 	factory.setConsumerFactory(userConsumerFactory());
	 	return factory;
	 }

}
