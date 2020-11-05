package com.first.learn.es.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Demo {

	public static void consume(final Properties properties) {

		final KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

//		kafkaConsumer.listTopics().entrySet().forEach(entry -> {
//			final String key = entry.getKey();
//			final List<PartitionInfo> partitionInfo = entry.getValue();
//			for (final PartitionInfo p : partitionInfo) {
//				System.out.println(key + "|" + p.partition());
//			}
//		});

		final List<String> topicNames = new ArrayList<>();
		kafkaConsumer.listTopics().keySet().forEach(key -> {
			// System.out.println(kafkaConsumer.partitionsFor(key));
			if (key.contains("-maint")) {
				topicNames.add(key);
			}
		});
		topicNames.stream().sorted().forEach(topic -> {
			System.out.println(topic);
		});
	}
}
