package com.arb.redis.core;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arb.redis.model.Bid;
import com.arb.redis.model.Cacheable;
import com.arb.redis.model.User;
import com.arb.redis.service.BidService;

public class LatestBidsMain {

	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        @SuppressWarnings("unchecked")
        BidService bidService = (BidService)context.getBean("bidService");
        
        bidService.deleteAll();
        
        for(int i=0; i<=20; i++){
        	User user = new User(""+i,""+i);
        	BigDecimal bidAmount = new BigDecimal(getBidAmount());
        	Bid bid = new Bid(""+i, bidAmount, user);
        	
//        	bidService.
        	bidService.putLatest(bid);
        	
//        	Bid gBid = bidService.get(bid);
//        	if(gBid!=null){
//        		System.out.println("Bid stored"+gBid.getBidAmount());
//        	}
        	
//        	bidService.delete(bid);
        	
        	if(i>=9){
//        		bidService.doAFunction();
//        		print data
        		List<Cacheable> topBidsWithScore = bidService.getLatestKBids(10);
        		if(null!=topBidsWithScore){
        			for(Cacheable tBid: topBidsWithScore){
        				System.out.println("Bid Id:"+tBid.getKey());
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
