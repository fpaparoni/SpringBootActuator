package com.javastaff.spring.boot.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;

@Component
public class MyCustomServiceHealthIndicator implements HealthIndicator {
	
	private static final Logger LOG = LoggerFactory.getLogger(MyCustomServiceHealthIndicator.class);
	
    @Override
    public Health health() {
        int status = redisCheck();
        if (status == 0) {
            return Health.down().build();
        } else {
        	String lastItem=redisLastItem();
            return Health.up().withDetail("lastItem", lastItem).build();
        }
    }
    
    private String redisLastItem() {
    	JedisShardInfo shardInfo = new JedisShardInfo("localhost", Protocol.DEFAULT_PORT);
        Jedis jedis = new Jedis(shardInfo);
        String lastItem=jedis.randomKey();
        jedis.close();
		return lastItem;
	}

	public int redisCheck() {
    	LOG.info("Checking REDIS...");
    	int status=0;
    	try {
    		JedisShardInfo shardInfo = new JedisShardInfo("localhost", Protocol.DEFAULT_PORT);
            Jedis jedis = new Jedis(shardInfo);
            String pong=jedis.ping();
            if (pong.equals("PONG"))
            	status=1;
            jedis.close();
        } 
    	catch (Exception ex) {
    		LOG.error(ex.getMessage());
    	}
    	
    	return status;
    }
}
