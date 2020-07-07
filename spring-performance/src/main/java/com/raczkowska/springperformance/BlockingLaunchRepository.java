package com.raczkowska.springperformance;

import org.springframework.data.repository.PagingAndSortingRepository;

interface BlockingLaunchRepository extends PagingAndSortingRepository<LaunchBlock, String> {

}
