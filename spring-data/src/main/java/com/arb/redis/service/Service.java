package com.arb.redis.service;

import com.arb.redis.model.Cacheable;

public interface Service<V extends Cacheable> {

	public void put(V obj);

	public V get(V key);
	
	public void delete(V key);

}