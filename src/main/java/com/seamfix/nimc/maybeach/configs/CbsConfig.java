package com.seamfix.nimc.maybeach.configs;

import lombok.extern.slf4j.Slf4j;
import org.keyczar.Crypter;
import org.keyczar.exceptions.KeyczarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import sfx.crypto.CryptoReader;

import java.io.File;
import java.io.IOException;

@Configuration
@Slf4j
public class CbsConfig {
    @Autowired
    private ApplicationContext appContext;

    @Bean
    public Crypter crypter() {
        Crypter crypter = null;
        Resource resource = appContext.getResource("classpath:/ncc");

        try {
            File file = resource.getFile();
            crypter = new Crypter(new CryptoReader(file.getAbsolutePath()));
        } catch (KeyczarException e) {
            log.error("KeyczarException ", e);
        } catch (IOException e) {
            log.error("IOException ", e);
        }
        return crypter;
    }

}
