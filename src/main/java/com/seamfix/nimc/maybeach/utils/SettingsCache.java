package com.seamfix.nimc.maybeach.utils;

import com.seamfix.kyc.microservices.utilities.cache.AbstractBaseBioCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class SettingsCache extends AbstractBaseBioCache {

    private final AppProperties appProperties;

    public SettingsCache(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @PostConstruct
    public void init() {
        log.debug("Initializing Cache Mechanism");
        addressList = appProperties.getSettingsCacheServerList();
        connect();
    }

    @Override
    protected String getCachePrefix() {
        return null;
    }

    public String getAddress() {
        return addressList;
    }
}
