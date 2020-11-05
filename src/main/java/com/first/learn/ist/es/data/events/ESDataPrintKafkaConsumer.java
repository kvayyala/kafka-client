package com.first.learn.ist.es.data.events;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public final class ESDataPrintKafkaConsumer {

	private ESDataPrintKafkaConsumer() {
	}

	public static void consume(final Properties properties) {
		final String topicName = properties.getProperty("topicName");

		final KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
		final TopicPartition topicPartition = new TopicPartition(topicName, 0);
		final List<TopicPartition> topics = Arrays.asList(topicPartition);
		kafkaConsumer.assign(topics);

		try {
			while (true) {
				final ConsumerRecords<String, String> recs = kafkaConsumer.poll(100);
				for (final ConsumerRecord<String, String> rec : recs) {
					// System.out.println(rec.topic() + "#" + rec.offset() + "#" + rec.key() + "#" +
					// rec.value());
					System.out.printf("offset = %d, key = %s, value = %s%n", rec.offset(), rec.key(), rec.value());
				}
			}
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
			kafkaConsumer.close();
		}

	}
}
