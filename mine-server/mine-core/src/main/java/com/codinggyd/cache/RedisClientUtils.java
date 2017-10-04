package com.codinggyd.cache;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.codinggyd.util.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * 
 * 
 * @Title:  RedisClient.java
 * @Package: com.codinggyd.cache
 * @Description: Redis 客户端 配置和初始化
 *
 * @author: guoyd
 * @Date: 2017年10月4日 下午2:34:59
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class RedisClientUtils {
	
	//redis 主机IP
	private static String HOST;
	//redis 端口
	private static Integer PORT;
	//redis 密码
	private static String AUTH;
	//redis 可用连接实例的最大数目，默认为8；
    //如果赋值为-1，则表示不限制，如果pool已经分配了MAX_TOTAL个jedis实例，则此时pool的状态为exhausted(耗尽)
	private static Integer MAX_TOTAL;
	//redis 控制一个pool最多有多少个状态为idle(空闲)的jedis实例，默认值是8
	private static Integer MAX_IDLE;
	//redis 等待可用连接的最大时间，单位是毫秒，默认值为-1，表示永不超时。
    //如果超过等待时间，则直接抛出JedisConnectionException
	private static Integer MAX_WAIT_MILLIS;
	//redis 在borrow(用)一个jedis实例时，是否提前进行validate(验证)操作；
    //如果为true，则得到的jedis实例均是可用的
	private static boolean TEST_ON_BORROW;
	//redis连接池
	private static JedisPool jedisPool;
	
	private RedisClientUtils() {}
	
	public static JedisPool getInstance() {
		if (null == jedisPool ) {
			
			synchronized (RedisClientUtils.class) {
				if (null == jedisPool) {
					JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
					jedisPoolConfig.setMaxIdle(MAX_IDLE);
					jedisPoolConfig.setMaxTotal(MAX_TOTAL);
					jedisPoolConfig.setMaxWaitMillis(MAX_WAIT_MILLIS);
					jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
					
					jedisPool = new JedisPool(jedisPoolConfig,HOST,PORT,100000,AUTH);
				}
			}
		}
		return jedisPool;
	}
	
	public static void cache(String key,Object value) {
		try {
			Jedis jedis = jedisPool.getResource();
			Transaction transaction = jedis.multi();
			transaction.append(key.getBytes(),SerializeUtils.serialize(value));
			transaction.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static <T extends Serializable> void cache2(String tableName,ICacheKey<T> cacheKey,List<T> list) {
		try {
			Jedis jedis = getInstance().getResource();
			Transaction transaction = jedis.multi();
			for (T t : list) {
				transaction.append(SerializeUtils.serialize(tableName+":"+cacheKey.getCacheKey(t)), SerializeUtils.serialize(t));
			}
			transaction.exec();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getFromCache(String key) {
		byte[] bytes = getInstance().getResource().get(key.getBytes());
		if (null == bytes) {
			return null;
		}
		return SerializeUtils.unserialize(bytes);
	}
	
	public static void release(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
			jedis = null;
		}
	}
	
	@Value("${spring.redis.host}")
	public void setHOST(String hOST) {
		HOST = hOST;
	}
	
	@Value("${spring.redis.port}")
	public void setPORT(Integer pORT) {
		PORT = pORT;
	}
	@Value("${spring.redis.auth}")
	public void setAUTH(String aUTH) {
		AUTH = aUTH;
	}
	@Value("${spring.redis.maxTotal}")
	public void setMAX_TOTAL(Integer mAX_TOTAL) {
		MAX_TOTAL = mAX_TOTAL;
	}
	@Value("${spring.redis.maxIdle}")
	public void setMAX_IDLE(Integer mAX_IDLE) {
		MAX_IDLE = mAX_IDLE;
	}
	@Value("${spring.redis.maxWaitMillis}")
	public void setMAX_WAIT_MILLIS(Integer mAX_WAIT_MILLIS) {
		MAX_WAIT_MILLIS = mAX_WAIT_MILLIS;
	}
	@Value("${spring.redis.testOnBorrow}")
	public void setTEST_ON_BORROW(boolean tEST_ON_BORROW) {
		TEST_ON_BORROW = tEST_ON_BORROW;
	} 
	
	
	
}
