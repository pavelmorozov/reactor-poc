package com.springreactive.poc.controller;

import java.time.Duration;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springreactive.poc.domain.BanquetHall;
import com.springreactive.poc.repository.BanquetHallRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/BanquetHall")
public class BanquetHallController {
	
	private static final Logger logger = LoggerFactory.getLogger(BanquetHallController.class);
	
	@Autowired
	BanquetHallRepository banquetHallRepository;

	@GetMapping("/BanquetHall")
	Flux<BanquetHall> list() {
		return banquetHallRepository.findAll();
	}
	
	@GetMapping("/BanquetHall/{name}")
	Mono<BanquetHall> findByName(@PathVariable String name) {
		String testVar = name;
		logger.info("hello "+name);
		return banquetHallRepository.findByName(name);
	}
	
	@PostMapping("/BanquetHall")
	Mono<Void> create(@RequestBody Publisher<BanquetHall> banquetHallStream) {
		
		/*Example to see the request body data*/
//		Subscriber<BanquetHall> s = new Subscriber<BanquetHall>() {
//			@Override
//			public void onSubscribe(Subscription s) {
//				logger.info("Argument: "+s.toString());
//				s.request(1);
//			}
//			@Override
//			public void onNext(BanquetHall t) {
//				logger.info("Argument: "+t.name);
//			}
//			@Override
//			public void onError(Throwable t) {
//				logger.info("Argument: "+t.toString());
//			}
//			@Override
//			public void onComplete() {
//				logger.info("Complete! ");
//			}
//		};
//		banquetHallStream.subscribe(s);

		/*Example to see the request body data (a simpler way)*/
		//banquetHallStream.subscribe(v -> {logger.info("value: "+v.name);});
		
		return banquetHallRepository.insert(banquetHallStream).then();
		
		/*used in case of custom subscriber, and method should return anything*/
		//return Mono.empty().delaySubscription(Duration.ofMillis(1000)).then();
	}
}
