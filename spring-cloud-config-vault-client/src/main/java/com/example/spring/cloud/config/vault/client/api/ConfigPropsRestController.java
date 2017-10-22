package com.example.spring.cloud.config.vault.client.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping(path = "/props", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ConfigPropsRestController {

    @Value("${msg:'n/a'}")
    private String message;

    @Value("${mysecret:'n/a'}")
    private String mysecret;

    @GetMapping
    public ResponseEntity<ConfigProps> retrieveCfgProps() {
        return ResponseEntity.ok(new ConfigProps(message, mysecret));
    }

}
