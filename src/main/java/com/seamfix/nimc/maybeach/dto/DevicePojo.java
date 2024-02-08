package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevicePojo {
    private String providerIdentifier;

    private String machineTag;

    private String certificationStatus;

    private String status;
}
