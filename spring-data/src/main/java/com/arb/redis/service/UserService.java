package com.arb.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.arb.redis.model.Cacheable;
import com.arb.redis.model.User;


@Service("userService")
public class UserService implements com.arb.redis.service.Service<User> {

	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;

	
	public void put(User user) {
		redisTemplate.opsForHash().put(user.getObjectKey(), user.getKey(), user);
	}

	
	public void delete(User key) {
		redisTemplate.opsForHash().delete(key.getObjectKey(), key.getKey());
	}

	
	public User get(User key) {
		return (User) redisTemplate.opsForHash().get(key.getObjectKey(), key.getKey());
	}
}