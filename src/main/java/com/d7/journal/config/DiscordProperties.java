package com.d7.journal.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Validated
@Setter
@Getter
@ConfigurationProperties(prefix = "discord")
public class DiscordProperties {

    @NotEmpty(message = "required discord token")
    private String token;

    @Valid
    @NotNull(message = "required discord webhook info")
    private Webhook webhook;

    @Setter
    @Getter
    public static class Webhook {

        @NotNull(message = "required discord webhook id")
        private Long id;

        @NotEmpty(message = "required discord webhook token")
        private String token;
    }
}
