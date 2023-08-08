package com.zhn.system.config;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "es")  // 支持热部署
@Data
@Log4j2
public class EsConfig {
    private String host;
    private int port;
    private String scheme;

    /**
     * 初始化一个 RestHighLevelClient
     */
    @Bean
    public RestHighLevelClient init() {
        log.info("获取的三要数是host:{},port:{},scheme:{}", host, port, scheme);
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, scheme)
                )
        );
    }
}
