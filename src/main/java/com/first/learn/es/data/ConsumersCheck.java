package com.first.learn.es.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListConsumerGroupOffsetsResult;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class ConsumersCheck {

	public static void consume(final Properties properties) {

		final KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

		final AdminClient adminClient = AdminClient.create(properties);

		final ListConsumerGroupOffsetsResult offsets = adminClient
				.listConsumerGroupOffsets("person-bifurcation-group-uat-new");
		new HashMap<>();
		try {
			offsets.partitionsToOffsetAndMetadata().get().entrySet().forEach(entry -> {
				// offsetMap.put(entry.getKey().topic().concat("-").concat(String.valueOf(entry.getKey().partition())),entry.getValue().offset());
				final String topic = entry.getKey().topic();
				final int partition = entry.getKey().partition();
				final long currentOffset = entry.getValue().offset();
				final long endOffset = getLogEndOffset(new TopicPartition(topic, partition), kafkaConsumer);

				System.out.println(topic + "#" + partition + "#" + currentOffset + "#" + endOffset + "#"
						+ (currentOffset - endOffset));
			});
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static long getLogEndOffset(final TopicPartition tp, final KafkaConsumer<String, String> kafkaConsumer) {

		Collections.singletonList(tp);
		kafkaConsumer.assign(Collections.singletonList(tp));
		kafkaConsumer.seekToEnd(Collections.singletonList(tp));
		return kafkaConsumer.position(tp);
	}
}
