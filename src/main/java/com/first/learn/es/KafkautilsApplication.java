package com.first.learn.es;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.first.learn.ist.es.data.events.APPlicationProperties;
import com.first.learn.ist.es.data.events.ESDataKafkaConsumer;

@SpringBootApplication
public class KafkautilsApplication {

	final static Properties props = new Properties();

	@Autowired
	public void setGlobal(final APPlicationProperties appProperties) {
		props.put("bootstrap.servers", appProperties.getBootstrapServers());
		props.put("key.deserializer", appProperties.getKeyDeserializer());
		props.put("value.deserializer", appProperties.getValueDeserializer());
		props.put("group.id", appProperties.getGroupId());
		props.put("ssl.keystore.location", appProperties.getSslKeystoreLocation());
		props.put("ssl.keystore.password", appProperties.getSslKeystorePassword());
		props.put("ssl.truststore.location", appProperties.getSslTruststoreLocation());
		props.put("ssl.truststore.password", appProperties.getSslTruststorePassword());
		props.put("security.protocol", appProperties.getSecurityProtocol());
		props.put("sasl.mechanism", appProperties.getSaslMechanism());
		props.put("auto.offset.reset", appProperties.getAutoOffsetReset());
		props.put("topicNames", appProperties.getSubscribeTopicNames());
		props.put("messagesLocation", appProperties.getMessagesLocation());
	}

	public static void main(final String[] args) {
		SpringApplication.run(KafkautilsApplication.class, args);
		ESDataKafkaConsumer.consume(props);
		//ConsumersCheck.consume(props);
	}
}
