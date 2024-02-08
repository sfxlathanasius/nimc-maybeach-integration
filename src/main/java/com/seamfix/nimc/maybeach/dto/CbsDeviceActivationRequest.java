package com.seamfix.nimc.maybeach.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author nnwachukwu
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CbsDeviceActivationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5773819040019695219L;

	@NotBlank(message = "Please provide the machine tag")
	private String machineTag;

	@NotBlank(message = "Please provide the provider device identifier")
	private String providerDeviceIdentifier;

	@NotBlank(message = "Please provide the activation location latitude")
	private String activationLocationLatitude;

	@NotBlank(message = "Please provide the activation location longitude")
	private String activationLocationLongitude;

	@NotBlank(message = "Please provide the requester email")
	private String requesterEmail;

	@NotBlank(message = "Please provide the requester lastname")
	private String requesterLastname;

	@NotBlank(message = "Please provide the requester firstname")
	private String requesterFirstname;

	@NotBlank(message = "Please provide the requester phone number")
	private String requesterPhoneNumber;

	@NotBlank(message = "Please provide the requester NIN")
	private String requesterNin;

	@NotBlank(message = "Please provide the ESA code")
	private String esaCode;

	@NotBlank(message = "Please provide the ESA name")
	private String esaName;

	@NotBlank(message = "Please provide the request ID")
	private String requestId;

	@NotBlank(message = "Please provide the location")
	private String location;

}
