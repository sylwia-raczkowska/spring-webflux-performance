package com.raczkowska.performancelaunchreactive;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
class ReactiveLaunchService {
    private static final int DELAY_PER_ITEM_MS = 100;

    private final ReactiveLaunchRepository reactiveLaunchRepository;

    ReactiveLaunchService(ReactiveLaunchRepository reactiveLaunchRepository) {
        this.reactiveLaunchRepository = reactiveLaunchRepository;
    }

    public Flux<ReactiveLaunch> findAll(Pageable pageable) {
        return reactiveLaunchRepository.findAllByNameNotNullAndDescriptionNotNull(pageable)
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    Mono<ReactiveLaunch> add(ReactiveLaunch reactiveLaunch) {
        return reactiveLaunchRepository.save(reactiveLaunch).delayElement(Duration.ofMillis(3000));
    }
}
