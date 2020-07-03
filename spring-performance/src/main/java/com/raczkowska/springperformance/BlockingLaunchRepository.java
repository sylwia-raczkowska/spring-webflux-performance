package com.raczkowska.springperformance;

import org.springframework.data.mongodb.repository.MongoRepository;

interface BlockingLaunchRepository extends MongoRepository<LaunchBlock, String> {

}
