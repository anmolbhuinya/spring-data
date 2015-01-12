package com.arb.redis.model;

import java.math.BigDecimal;

/**
 * 
 * @author anmol
 *
 */
public class Bid implements Cacheable {
	
	public static final String OBJECT_KEY="BID";
	
	private String id;
	private BigDecimal bidAmount;
	
	
	public Bid(String id, BigDecimal bidAmount, User user){
		this.id = id;
		this.bidAmount = bidAmount;
	}

	@Override
	public String getKey() {
		return id;
	}

	@Override
	public String getObjectKey() {
		return OBJECT_KEY;
	}

	public BigDecimal getBidAmount() {
		return bidAmount;
	}


}
