package com.raczkowska.springperformance;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

interface BlockingLaunchRepository extends PagingAndSortingRepository<Launch, String> {
    List<Launch> findAllByIdNotNullOrderByIdAsc(final Pageable page);

}
