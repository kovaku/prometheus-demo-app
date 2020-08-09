package com.github.kovaku.controller;

import com.github.kovaku.domain.RandomResponse;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.function.Predicate;

@RestController
public class RandomController {

    private Counter evenCounter;
    private Counter oddCounter;
    private Random random = new Random();
    private Predicate<Integer> parityPredicate = (number) -> number % 2 == 0;

    public RandomController(MeterRegistry meterRegistry) {
        evenCounter = Counter.builder("random.metric").tags("parity", "even").register(meterRegistry);
        oddCounter = Counter.builder("random.metric").tags("parity", "odd").register(meterRegistry);
    }

    @GetMapping(path = "/random")
    public RandomResponse getCounter() {
        Integer currentRandom = random.nextInt(10);
        Boolean parity = parityPredicate.test(currentRandom);
        RandomResponse response = new RandomResponse(currentRandom, parity);
        if (parity) {
            evenCounter.increment();
        } else {
            oddCounter.increment();
        }
        return response;
    }

}
