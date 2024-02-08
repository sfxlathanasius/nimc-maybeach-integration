package com.seamfix.nimc.maybeach.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CenterPojo {
    private String code;

    private String latitude;

    private String name;

    private boolean mobile;

    private String status;

    private String longitude;

    private String geofencingRadiusInMeters;
}
