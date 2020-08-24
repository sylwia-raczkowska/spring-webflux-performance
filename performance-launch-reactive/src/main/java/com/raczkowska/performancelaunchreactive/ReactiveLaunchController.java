package com.raczkowska.performancelaunchreactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j

class ReactiveLaunchController {

    private final ReactiveLaunchService reactiveLaunchService;

    ReactiveLaunchController(ReactiveLaunchService reactiveLaunchService) {
        this.reactiveLaunchService = reactiveLaunchService;
    }

    @GetMapping("/launches-reactive")
    public Flux<ReactiveLaunch> getLaunches(final @RequestParam(name = "page") int page,
                                            final @RequestParam(name = "size") int size) {
        return reactiveLaunchService.findAll(PageRequest.of(page, size));
    }

    @PostMapping("/launch-reactive")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<ReactiveLaunch> addLaunch(@RequestBody ReactiveLaunch reactiveLaunch) {
        return reactiveLaunchService.add(reactiveLaunch);
    }

}
