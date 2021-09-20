package com.d7.journal.config;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Webhook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(DiscordProperties.class)
@Configuration
public class DiscordConfiguration {

    private final DiscordProperties discordProperties;

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(discordProperties.getToken())
                .build()
                .gateway()
                .login()
                .block();
    }

    @Bean
    public Webhook webhook(GatewayDiscordClient client) {
        return client.getWebhookByIdWithToken(Snowflake.of(discordProperties.getWebhook().getId()), discordProperties.getWebhook().getToken())
                .block();
    }
}
