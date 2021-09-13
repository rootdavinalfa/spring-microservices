package xyz.dvnlabs.serviceorder

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableDiscoveryClient
open class ServiceOrderApplication
fun main(args: Array<String>) {
    runApplication<ServiceOrderApplication>(*args)
}

@Bean
@LoadBalanced
fun restTemplate(): RestTemplate {
    return RestTemplate()
}