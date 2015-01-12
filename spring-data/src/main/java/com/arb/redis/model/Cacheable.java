package com.arb.redis.model;

import java.io.Serializable;

public interface Cacheable extends Serializable {
	
	public String getKey();
	
	public String getObjectKey();
	
}