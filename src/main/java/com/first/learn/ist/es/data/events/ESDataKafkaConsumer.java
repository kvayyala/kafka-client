package com.first.learn.ist.es.data.events;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class ESDataKafkaConsumer {

	private static final JSONParser parser = new JSONParser();

	private ESDataKafkaConsumer() {
	}

	public static void consume(final Properties properties) {
		final String topicName = properties.getProperty("topicNames");
		final String[] topicNames = topicName.split(",");
		final List<String> topicNamesList = Arrays.asList(topicNames);

		final KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

		kafkaConsumer.subscribe(topicNamesList);
		try {
			while (true) {
				final ConsumerRecords<String, String> recs = kafkaConsumer.poll(Duration.ofMillis(1000));
				for (final ConsumerRecord<String, String> rec : recs) {
					System.out.println(rec.value());
					//final JSONObject recValue = (JSONObject) parser.parse(new String(rec.value()));
					//System.out.println(recValue);
				}
			}
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
			kafkaConsumer.close();
		}

	}

}
