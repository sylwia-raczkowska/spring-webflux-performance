package com.raczkowska.springperformance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

interface ReactiveLaunchRepository extends ReactiveMongoRepository<Launch, String> {

    Flux<Launch> findAllByIdNotNullOrderByIdAsc(final Pageable page);
}