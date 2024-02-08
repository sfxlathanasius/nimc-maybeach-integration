package com.seamfix.nimc.maybeach.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties
@RefreshScope
public class AppProperties {

    private String saltKey;
    private String userServiceCreateUserEndpoint;
    private boolean skipHeaderAuthentication;
    private String heartbeatRate;
    private int apiTimeout;
    private String settingsCacheServerList;
    private String clientAuditFileLocationPath;
    private String googleMapApiKey;
    private String openStreetMapsUrl;
    private int settingsCacheTime;
}
