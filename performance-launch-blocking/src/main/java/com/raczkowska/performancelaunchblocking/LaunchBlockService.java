package com.raczkowska.performancelaunchblocking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
class LaunchBlockService {
    private final BlockingLaunchRepository blockingLaunchRepository;

    LaunchBlockService(BlockingLaunchRepository blockingLaunchRepository) {
        this.blockingLaunchRepository = blockingLaunchRepository;
    }

    Page<LaunchBlock> findAll(PageRequest pageRequest) {
        return blockingLaunchRepository.findAll(pageRequest);
    }

    LaunchBlock save(LaunchBlock launchBlock) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return blockingLaunchRepository.save(launchBlock);
    }
}
