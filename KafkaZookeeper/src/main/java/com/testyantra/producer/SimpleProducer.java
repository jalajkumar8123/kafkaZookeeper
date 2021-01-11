package com.testyantra.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {

	public static void main(String[] args) {

		try {
			String topicName = "testyantra";
			Properties props = new Properties();
			props.put("bootstrap.servers", "localhost:9092");
			props.put("acks", "all");

			// If the request fails, the producer can automatically retry,
			props.put("retries", 0);

			// Specify buffer size in config
			props.put("batch.size", 16384);

			// Reduce the no of requests less than 0
			props.put("linger.ms", 1);

			// The buffer.memory controls the total amount of memory available to the
			// producer for buffering.
			props.put("buffer.memory", 33554432);

			props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

			props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

			Producer<String, String> producer = new KafkaProducer<String, String>(props);

			for (int i = 0; i < 10; i++) {
				producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), Integer.toString(i)));
				System.out.println("Message sent successfully");
			Thread.sleep(5000);
			}
			producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}