package com.d7.journal.api;

import com.d7.journal.db.mapper.HelloMapper;
import discord4j.core.object.entity.Webhook;
import discord4j.core.spec.WebhookExecuteSpec;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/hello")
@RestController
public class HelloController {

    private final HelloMapper helloMapper;
    private final Webhook webhook;

    @GetMapping
    public ResponseEntity<?> hello() {

        var res = helloMapper.findStr();

        webhook.execute(WebhookExecuteSpec.builder()
                .content("dkdk")
                .build())
                .block();

        return ResponseEntity.ok(Map.of("name", "아아아"));
    }
}
