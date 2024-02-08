package com.seamfix.nimc.maybeach.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo implements Serializable {
    private static final long serialVersionUID = 2316612078121051847L;

    @NotBlank(message = "device type is required")
    private String deviceType;
    private Boolean connected;
    private String lastDisconnectionTime;
    private String description;
    private Integer id;

    public DeviceInfo(String deviceType) {
        this.deviceType = deviceType;
    }
}
