package com.sabre.api.sacs.errors;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Class indicating that objects in this module should be managed
 * by Spring Context.
 */
@Configuration
@ComponentScan
@EnableAsync
@EnableScheduling
public class ErrorHandlerConfiguration {

}
