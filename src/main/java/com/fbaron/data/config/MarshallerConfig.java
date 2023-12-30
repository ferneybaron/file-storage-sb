package com.fbaron.data.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author Ferney Estupinan Baron
 * @since 12/24/2023
 */

@Configuration
@RequiredArgsConstructor
public class MarshallerConfig {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("com.thd.core.model");
        return jaxb2Marshaller;
    }

}
