package com.raczkowska.performancelaunchreactive;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
class MongoDbInitializerConfig {

    @Value("${db.populatebyinitdata}")
    private boolean populateByInitData;

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean init(ObjectMapper mapper) {

        Jackson2RepositoryPopulatorFactoryBean factory =
                new Jackson2RepositoryPopulatorFactoryBean();
        factory.setMapper(mapper);

        if (populateByInitData) {
            Resource sourceData = new ClassPathResource("data.json");
            factory.setResources(new Resource[]{sourceData});
        }

        return factory;
    }
}
