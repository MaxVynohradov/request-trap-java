package com.vynohradov.requesttrap.configuration;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Validated
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "info.app")
public class ApplicationProperties {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private String version;

    @NotEmpty
    @Getter
    @Setter
    public static class Java {

        @NotEmpty
        private String version;
    }
}
