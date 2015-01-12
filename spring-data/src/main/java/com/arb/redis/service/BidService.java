package com.arb.redis.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Service;

import com.arb.redis.model.Bid;
import com.arb.redis.model.Cacheable;

/**
 * 
 * @author anmol
 *
 */
@Service("bidService")
public class BidService implements com.arb.redis.service.Service<Bid> {
	
	
	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;
	

	@Override
	public void put(Bid bid) {
		redisTemplate.opsForZSet().add(bid.getObjectKey(), bid, bid.getBidAmount().doubleValue());
		
	}

	@Override
	public Bid get(Bid key) {
		return null;
	}
	
	public Set<TypedTuple<Cacheable>> getTopmostKBids(int maxCount){
		return redisTemplate.opsForZSet().reverseRangeWithScores(Bid.OBJECT_KEY, 0, maxCount-1);
	}
	
	public void putLatest(Bid bid) {
		redisTemplate.opsForList().leftPush(Bid.OBJECT_KEY, bid);
		
	}
	
	
	public List<Cacheable>  getLatestKBids(int maxCount){
		return redisTemplate.opsForList().range(Bid.OBJECT_KEY, 0, maxCount-1);
	}
	
	
	
	public void doAFunction(){
//		test1
/*		{
			Set<String> strList = redisTemplate.opsForZSet().reverseRange(Bid.OBJECT_KEY, 0, 15);
			
			if(strList!=null){
				for(String str:strList){
					System.out.println(str);
				}
			}			
		}*/
		
		
//		test2
		/*{
			Set<TypedTuple<String>> strList = redisTemplate.opsForZSet().reverseRangeWithScores(Bid.OBJECT_KEY, 0, 15);
			
			if(strList!=null){
				for(TypedTuple str:strList){
					System.out.println("HashKey:"+str.getValue()+", Score:"+ str.getScore());
				}
			}			
		
		}*/

		
	}
	

	@Override
	public void delete(Bid key) {
		
		
	}
	
	public void deleteAll(){
		redisTemplate.delete(Bid.OBJECT_KEY);
	}

}
