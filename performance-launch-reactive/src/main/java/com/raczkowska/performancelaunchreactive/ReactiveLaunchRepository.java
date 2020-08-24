package com.raczkowska.performancelaunchreactive;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveLaunchRepository extends ReactiveMongoRepository<ReactiveLaunch, String> {

    Flux<ReactiveLaunch> findAllByNameNotNullOrderByNameAsc(final Pageable page);
}
