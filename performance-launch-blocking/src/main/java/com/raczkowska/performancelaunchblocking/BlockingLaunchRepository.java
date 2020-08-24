package com.raczkowska.performancelaunchblocking;

import org.springframework.data.repository.PagingAndSortingRepository;

interface BlockingLaunchRepository extends PagingAndSortingRepository<LaunchBlock, String> {

}
