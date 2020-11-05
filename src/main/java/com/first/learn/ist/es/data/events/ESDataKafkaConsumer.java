package com.first.learn.ist.es.data.events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ESDataKafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ESDataKafkaConsumer.class);
	private static final String FILE_EXT = ".log";
	private static final JSONParser parser = new JSONParser();

	private ESDataKafkaConsumer() {
	}

	public static void consume(final Properties properties) {
		final String topicName = properties.getProperty("topicNames");
		final String[] topicNames = topicName.split(",");
		final List<String> topicNamesList = Arrays.asList(topicNames);

		final KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
		final Path path = Paths.get(properties.getProperty("messagesLocation", "../dataMessages"));
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		topicNamesList.forEach(kafkatopicName -> {
			final String fileName = path.toString().concat("/").concat(kafkatopicName.concat(FILE_EXT));
			if (Files.notExists(Paths.get(fileName))) {
				try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
					writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					LOGGER.info("== Exception occurred :: Unable to create txt file for topciName {}" + topicName);
				}
			}
		});

		kafkaConsumer.subscribe(topicNamesList);
		try {
			final Map<String, List<String>> esDataMessages = new HashMap<>();
			while (true) {
				final ConsumerRecords<String, String> recs = kafkaConsumer.poll(100);
				for (final ConsumerRecord<String, String> rec : recs) {
					if (!esDataMessages.containsKey(rec.topic())) {
						final List<String> eventMessageList = new ArrayList<>();
						if (rec.topic().contains("person-bifurcation-error")) {
							final JSONObject recValue = (JSONObject) parser.parse(new String(rec.value()));
							recValue.put("dsid", rec.key());
							recValue.put("timestamp", rec.timestamp());
							eventMessageList.add(recValue.toJSONString());
						} else {
							eventMessageList.add(rec.value());
						}
						esDataMessages.put(rec.topic(), eventMessageList);
					} else {
						if (rec.topic().contains("person-bifurcation-error")) {
							final JSONObject recValue = (JSONObject) parser.parse(new String(rec.value()));
							recValue.put("dsid", rec.key());
							recValue.put("timestamp", rec.timestamp());
							esDataMessages.get(rec.topic()).add(recValue.toJSONString());
						} else {
							esDataMessages.get(rec.topic()).add(rec.value());
						}
					}
				}
				writeSmallTextFile(esDataMessages, path);
				esDataMessages.clear();
			}
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
			kafkaConsumer.close();
		}

	}

	private static void writeSmallTextFile(final Map<String, List<String>> esDataMessages, final Path path)
			throws IOException {
		esDataMessages.keySet().forEach(topicName -> {
			final Path filePath = Paths.get(path.toString().concat("/").concat(topicName.concat(FILE_EXT)));
			try {
				Files.write(filePath, esDataMessages.get(topicName), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
			} catch (final IOException e) {
				e.printStackTrace();
			}
		});
	}
}
