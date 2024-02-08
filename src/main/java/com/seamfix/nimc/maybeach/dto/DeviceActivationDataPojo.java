package com.seamfix.nimc.maybeach.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceActivationDataPojo {
    private FepPojo fep;

    private CenterPojo center;

    private DevicePojo device;

    private Object testBackendConfig;

    private Object prodBackendConfig;

    private Object testBackendCredential;

    private Object prodBackendCredential;
}
