package com.seamfix.nimc.maybeach.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CbsHeartBeatsRequest implements Serializable {

    private static final long serialVersionUID = -3459356338177237750L;

    @NotNull(message = "Please provide the longitude of your current location")
    private Double currentLocationLongitude;

    @NotNull(message = "Please provide the latitude of your current location")
    private Double currentLocationLatitude;

    @NotBlank(message = "Please provide the device ID")
    private String deviceId;

    private String deviceTime;

    @NotBlank(message = "Please provide the the login ID")
    private String clientAppUserLoginIdentifier;

    @NotBlank(message = "Please provide the esa code")
    private String esaCode;

    @NotBlank(message = "Please provide the client app version")
    private String clientAppVersion;

    @NotBlank(message = "Please provide the device type")
    private String deviceType;

    @NotBlank(message = "Please provide the os type")
    private String osType;

    @NotBlank(message = "Please provide the os version")
    private String osVersion;

    @NotBlank(message = "Please provide the provider code")
    private String providerCode;

    @NotBlank(message = "Please provide the device description")
    private String deviceDescription;

    @NotBlank(message = "Please provide the agent nin")
    private String agentNin;

    @NotNull(message = "Please provide the count of all time enrolments")
    private Integer countOfAllTimeEnrolments;

    @NotNull(message = "Please provide the count of all time enrolments to nimc backend")
    private Integer countOfAllTimeEnrolmentsSentToNimcBackend;

    @NotBlank(message = "Please provide the last tracking id")
    private String lastTrackingId;

    @NotNull(message = "Please provide the count of all time incomplete enrollments")
    private Integer countOfAllTimeIncompleteEnrollments;

    private String lastEnrollmentSyncTimeToNimcBackend;

    private Boolean vpnConnectionStatus;

    private Integer countOfAllTimeOutlierEnrollments;

    @NotNull(message = "Please provide the count of all time enrollment notifications")
    private Integer countOfAllTimeEnrollmentNotificationsSentToCbs;

    @NotEmpty(message = "Please provide the connected devices")
    private List<@Valid DeviceInfo> connectedDevices;
}
