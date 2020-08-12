package com.raczkowska.springperformance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BlockingLaunchController {
    private static final int DELAY_PER_ITEM_MS = 100;

    private final BlockingLaunchRepository blockingLaunchRepository;

    BlockingLaunchController(final BlockingLaunchRepository blockingLaunchRepository) {
        this.blockingLaunchRepository = blockingLaunchRepository;
    }

    @GetMapping("/launches-blocking")
    public Page<LaunchBlock> getQuotesBlocking(final @RequestParam(name = "page") int page,
                                               final @RequestParam(name = "size") int size) throws Exception {
        Thread.sleep(DELAY_PER_ITEM_MS * size);
        return blockingLaunchRepository.findAll(PageRequest.of(page, size));
    }

}
