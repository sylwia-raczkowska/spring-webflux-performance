package com.raczkowska.springperformance;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlockingLaunchRepository extends PagingAndSortingRepository<Launch, String> {

}
