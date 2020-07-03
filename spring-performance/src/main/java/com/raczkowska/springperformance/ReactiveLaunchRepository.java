package com.raczkowska.springperformance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

interface ReactiveLaunchRepository extends ReactiveMongoRepository<LaunchReactive, String> {

    Flux<LaunchReactive> findAllByIdNotNullOrderByIdAsc(final Pageable page);
}
