package com.raczkowska.springperformance;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
class ReactiveLaunchController {
    private static final int DELAY_PER_ITEM_MS = 100;


    private final ReactiveLaunchRepository reactiveLaunchRepository;

    ReactiveLaunchController(ReactiveLaunchRepository bookRepository) {
        this.reactiveLaunchRepository = bookRepository;
    }

    @GetMapping("/launches-reactive")
    public Flux<LaunchReactive> getQuoteFlux(final @RequestParam(name = "page") int page,
                                          final @RequestParam(name = "size") int size) {
        return reactiveLaunchRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }
}
