package com.arb.redis.core;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import com.arb.redis.model.Bid;
import com.arb.redis.model.Cacheable;
import com.arb.redis.model.User;
import com.arb.redis.service.BidService;

/**
 * This program prints top k bids
 * 
 * Time Complexity: O(log(N)+M) with N being the number of elements in the sorted set and M the number of elements returned.
 * 
 * @author anmol
 *
 */
public class TopBidsMain {
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        @SuppressWarnings("unchecked")
        BidService bidService = (BidService)context.getBean("bidService");
        
        bidService.deleteAll();
        
        for(int i=0; i<100; i++){
        	User user = new User(""+i,""+i);
        	BigDecimal bidAmount = new BigDecimal(getBidAmount());
        	Bid bid = new Bid(""+i, bidAmount, user);
        	
//        	bidService.
        	bidService.put(bid);
        	
//        	Bid gBid = bidService.get(bid);
//        	if(gBid!=null){
//        		System.out.println("Bid stored"+gBid.getBidAmount());
//        	}
        	
//        	bidService.delete(bid);
        	
        	if(i!=0 && i%20==0){
//        		bidService.doAFunction();
//        		print data
        		Set<TypedTuple<Cacheable>> topBidsWithScore = bidService.getTopmostKBids(15);
        		if(null!=topBidsWithScore){
        			for(TypedTuple tuple: topBidsWithScore){
        				Bid tBid = (Bid)tuple.getValue();
        				Double score = tuple.getScore();
        				System.out.println("Bid Id:"+tBid.getKey()+", bidAmount: "+tBid.getBidAmount());
        			}
        			System.out.println("\n");
        		}
        	}
        }
        
        System.out.println("Program ends");
        bidService.deleteAll();
	}

	private static Double getBidAmount() {
		return Math.random()*100000;
	}
	

}
