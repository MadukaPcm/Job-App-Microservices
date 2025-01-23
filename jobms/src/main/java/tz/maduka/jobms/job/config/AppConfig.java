package tz.maduka.jobms.job.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean   // Bean means spring-boot is going to take care of managing this.
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
