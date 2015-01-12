package com.arb.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.collections.DefaultRedisMap;
import org.springframework.stereotype.Service;

import com.arb.domain.*;
import com.arb.redis.model.Cacheable;
import com.arb.redis.model.User;



@Service("userMapService")
public class UserMapService implements com.arb.redis.service.Service<User> {
	
	@Autowired
	DefaultRedisMap<String, Cacheable> userRedisMap;
	

	public void put(User user) {
		userRedisMap.put(user.getKey(), user);
	}


	public void delete(User key) {
		userRedisMap.remove(key.getKey());
	}


	public User get(User key) {
		return (User) userRedisMap.get(key.getKey());
	}
}