package com.proyecto.web.application;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by jonat on 18/08/2019.
 */
@Configuration
@ComponentScan(basePackages = {"com.proyecto.web.dao", "com.proyecto.web.service"})
@Import(DataConfig.class)
public class ComponentConfig {
}
