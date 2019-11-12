package com.yrx.datasourcemanager.manager.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * Created by r.x on 2019/11/10.
 */
@Configuration
// @EnableElasticsearchRepositories(basePackages = "com.yrx.datasourcemanager.manager.pojo.es")
// @EnableJpaRepositories(basePackages = "com.yrx.datasourcemanager.manager.service.es")
public class ElasticsearchConfig {

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
