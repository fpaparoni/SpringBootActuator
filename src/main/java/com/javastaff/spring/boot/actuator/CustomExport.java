package com.javastaff.spring.boot.actuator;

import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.export.MetricExportProperties;
import org.springframework.boot.actuate.metrics.repository.redis.RedisMetricRepository;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;

@Configuration
public class CustomExport {
	@Bean
	@ExportMetricWriter
	MetricWriter metricWriter(MetricExportProperties export) {
		JedisShardInfo shardInfo = new JedisShardInfo("localhost", Protocol.DEFAULT_PORT);
		JedisConnectionFactory factory=new JedisConnectionFactory(shardInfo);
	    return new RedisMetricRepository(factory,
	        export.getRedis().getPrefix(), export.getRedis().getKey());
	}
}
