package com.raczkowska.performancelaunchreactive;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReactiveLaunchRepository extends ReactiveMongoRepository<ReactiveLaunch, String> {

    Flux<ReactiveLaunch> findAllByNameNotNullAndDescriptionNotNull(final Pageable page);
}
