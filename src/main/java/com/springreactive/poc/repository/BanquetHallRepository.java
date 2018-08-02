package com.springreactive.poc.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springreactive.poc.domain.BanquetHall;

import reactor.core.publisher.Mono;

public interface BanquetHallRepository extends ReactiveMongoRepository<BanquetHall, String> {
	Mono<BanquetHall> findByName(String name);
}
