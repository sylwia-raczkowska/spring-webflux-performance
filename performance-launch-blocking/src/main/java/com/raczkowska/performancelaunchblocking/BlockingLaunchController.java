package com.raczkowska.performancelaunchblocking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BlockingLaunchController {
    private static final int DELAY_PER_ITEM_MS = 100;

    private final LaunchBlockService launchBlockService;

    BlockingLaunchController(LaunchBlockService launchBlockService) {
        this.launchBlockService = launchBlockService;
    }

    @GetMapping("/launches-blocking")
    public Page<LaunchBlock> getQuotesBlocking(final @RequestParam(name = "page") int page,
                                               final @RequestParam(name = "size") int size) throws Exception {
        Thread.sleep(DELAY_PER_ITEM_MS * size);
        return launchBlockService.findAll(PageRequest.of(page, size));
    }

    @PostMapping("/launch-blocking")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public LaunchBlock saveProduct(@RequestBody LaunchBlock launchBlock) {
        return launchBlockService.save(launchBlock);
    }

}
