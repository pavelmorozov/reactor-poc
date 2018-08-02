package com.springreactive.poc.controller;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springreactive.poc.domain.BanquetHall;
import com.springreactive.poc.repository.BanquetHallRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/BanquetHall")
public class BanquetHallController {
	
	@Autowired
	BanquetHallRepository banquetHallRepository;

	@GetMapping("/BanquetHall")
	Flux<BanquetHall> list() {
		return banquetHallRepository.findAll();
	}
	
	@GetMapping("/BanquetHall/{name}")
	Mono<BanquetHall> findByName(@PathVariable String name) {
		return banquetHallRepository.findByName(name);
	}
	
	@PostMapping("/BanquetHall")
	Mono<Void> create(@RequestBody Publisher<BanquetHall> banquetHallStream) {
		return banquetHallRepository.insert(banquetHallStream).then();
	}
}
