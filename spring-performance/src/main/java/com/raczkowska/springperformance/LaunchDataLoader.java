package com.raczkowska.springperformance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.Supplier;

@Component
@Slf4j
class LaunchDataLoader implements ApplicationRunner {

    private final ReactiveLaunchRepository reactiveLaunchRepository;

    LaunchDataLoader(ReactiveLaunchRepository reactiveLaunchRepository) {
        this.reactiveLaunchRepository = reactiveLaunchRepository;
    }

    @Override
    public void run(final ApplicationArguments args) {
        reactiveLaunchRepository.save(new Launch("1", "fff", "333"));
        reactiveLaunchRepository.save(new Launch("2", "fff", "333"));
        reactiveLaunchRepository.save(new Launch("3", "fff", "333"));
        reactiveLaunchRepository.save(new Launch("4", "fff", "333"));
    }

    private Supplier<String> getIdSequenceSupplier() {
        return new Supplier<>() {
            Long l = 0L;

            @Override
            public String get() {
                return String.format("%05d", l++);
            }
        };
    }
}
