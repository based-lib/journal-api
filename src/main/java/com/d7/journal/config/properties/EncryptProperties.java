package com.d7.journal.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Validated
@ConfigurationProperties(prefix = "encrypt")
public class EncryptProperties {

    @NotEmpty(message = "required encrypt password!!!")
    private String password;
}
