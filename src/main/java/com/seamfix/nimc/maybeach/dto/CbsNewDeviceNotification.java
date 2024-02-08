package com.seamfix.nimc.maybeach.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CbsNewDeviceNotification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -946668276177892735L;

	private String currentLocationLongitude;

	private String activatedByFirstName;

	@NotBlank(message = "Please provide the esaCode")
	@NotNull(message = "Please provide the esaCode")
	private String esaCode;

	@NotBlank(message = "Please provide the machineTag")
	@NotNull(message = "Please provide the machineTag")
	private String machineTag;

	private String authorizedByLastName;
	
	private String authorizedByFirstName;

	private String activatorEmail;

	@NotBlank(message = "Please provide the esaName")
	@NotNull(message = "Please provide the esaName")
	private String esaName;

	@NotBlank(message = "Please provide the providerDeviceIdentifier")
	@NotNull(message = "Please provide the providerDeviceIdentifier")
	private String providerDeviceIdentifier;

	private String providerIdentifier;

	private String currentLocationLatitude;

	private String activatedByLastName;

	@NotBlank(message = "Please provide the requestId")
	@NotNull(message = "Please provide the requestId")
	private String requestId;

	private String centerCode;

	private String activatorNin;

	private String dateActivated;

	private String location;

	@NotBlank(message = "Please provide the centerName")
	@NotNull(message = "Please provide the centerName")
	private String centerName;

	private String activatorPhone;

	public String getCurrentLocationLongitude() {
		return currentLocationLongitude;
	}

	public void setCurrentLocationLongitude(String currentLocationLongitude) {
		this.currentLocationLongitude = currentLocationLongitude;
	}

	public String getActivatedByFirstName() {
		return activatedByFirstName;
	}

	public void setActivatedByFirstName(String activatedByFirstName) {
		this.activatedByFirstName = activatedByFirstName;
	}

	public String getEsaCode() {
		return esaCode;
	}

	public void setEsaCode(String esaCode) {
		this.esaCode = esaCode;
	}

	public String getMachineTag() {
		return machineTag;
	}

	public void setMachineTag(String machineTag) {
		this.machineTag = machineTag;
	}

	public String getAuthorizedByLastName() {
		return authorizedByLastName;
	}

	public void setAuthorizedByLastName(String authorizedByLastName) {
		this.authorizedByLastName = authorizedByLastName;
	}

	public String getAuthorizedByFirstName() {
		return authorizedByFirstName;
	}

	public void setAuthorizedByFirstName(String authorizedByFirstName) {
		this.authorizedByFirstName = authorizedByFirstName;
	}

	public String getActivatorEmail() {
		return activatorEmail;
	}

	public void setActivatorEmail(String activatorEmail) {
		this.activatorEmail = activatorEmail;
	}

	public String getEsaName() {
		return esaName;
	}

	public void setEsaName(String esaName) {
		this.esaName = esaName;
	}

	public String getProviderDeviceIdentifier() {
		return providerDeviceIdentifier;
	}

	public void setProviderDeviceIdentifier(String providerDeviceIdentifier) {
		this.providerDeviceIdentifier = providerDeviceIdentifier;
	}

	public String getProviderIdentifier() {
		return providerIdentifier;
	}

	public void setProviderIdentifier(String providerIdentifier) {
		this.providerIdentifier = providerIdentifier;
	}

	public String getCurrentLocationLatitude() {
		return currentLocationLatitude;
	}

	public void setCurrentLocationLatitude(String currentLocationLatitude) {
		this.currentLocationLatitude = currentLocationLatitude;
	}

	public String getActivatedByLastName() {
		return activatedByLastName;
	}

	public void setActivatedByLastName(String activatedByLastName) {
		this.activatedByLastName = activatedByLastName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getActivatorNin() {
		return activatorNin;
	}

	public void setActivatorNin(String activatorNin) {
		this.activatorNin = activatorNin;
	}

	public String getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(String dateActivated) {
		this.dateActivated = dateActivated;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getActivatorPhone() {
		return activatorPhone;
	}

	public void setActivatorPhone(String activatorPhone) {
		this.activatorPhone = activatorPhone;
	}

	@Override
	public String toString() {
		return "ClassPojo [currentLocationLongitude = " + currentLocationLongitude + ", activatedByFirstName = "
				+ activatedByFirstName + ", esaCode = " + esaCode + ", machineTag = " + machineTag
				+ ", authorizedByLastName = " + authorizedByLastName + ", activatorEmail = " + activatorEmail
				+ ", esaName = " + esaName + ", providerDeivceIdentifier = " + providerDeviceIdentifier
				+ ", providerIdentifier = " + providerIdentifier + ", currentLocationLatitude = "
				+ currentLocationLatitude + ", activatedByLastName = " + activatedByLastName + ", requestId = "
				+ requestId + ", centerCode = " + centerCode + ", activatorNin = " + activatorNin + ", dateActivated = "
				+ dateActivated + ", location = " + location + ", centerName = " + centerName + ", activatorPhone = "
				+ activatorPhone + "]";
	}

}
