package com.gqshop.kiosk.endtoend;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.gqshop.kiosk.configuration"})
public class ApplicationConfigurationForEndToEndTests {

}
