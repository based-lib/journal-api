package com.d7.journal.config;

import com.d7.journal.config.properties.EncryptProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleGCMConfig;
import com.ulisesbocchio.jasyptspringboot.encryptor.SimpleGCMStringEncryptor;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableEncryptableProperties
@RequiredArgsConstructor
@EnableConfigurationProperties(EncryptProperties.class)
@Configuration
public class EncryptConfiguration {

    private final EncryptProperties encryptProperties;

    @Bean(name="jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        SimpleGCMConfig config = new SimpleGCMConfig();
        config.setSecretKeyPassword(encryptProperties.getPassword());
        return new SimpleGCMStringEncryptor(config);
    }
}
