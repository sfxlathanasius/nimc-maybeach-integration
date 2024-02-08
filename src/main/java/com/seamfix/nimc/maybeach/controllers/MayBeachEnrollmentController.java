package com.seamfix.nimc.maybeach.controllers;

import com.seamfix.nimc.maybeach.dto.CbsDeviceUpdateNotification;
import com.seamfix.nimc.maybeach.dto.CbsEnrollmentNotificationRequest;
import com.seamfix.nimc.maybeach.dto.CbsNewDeviceNotification;
import com.seamfix.nimc.maybeach.dto.CbsPreEnrollmentCheckRequest;
import com.seamfix.nimc.maybeach.dto.MayBeachResponse;
import com.seamfix.nimc.maybeach.services.enrollment.MayBeachEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/enrollment")
public class MayBeachEnrollmentController {
	
	@Autowired
	private MayBeachEnrollmentService cbsEnrollmentService;

	@PostMapping("/notification")
	public MayBeachResponse enrollmentNotification(@RequestBody CbsEnrollmentNotificationRequest cbsEnrollmentNotificationRequest) {
		return cbsEnrollmentService.sendEnrollmentNotificationService(cbsEnrollmentNotificationRequest);
	}

	@PostMapping("/new-device/notification")
	public MayBeachResponse newDeviceNotification(@Valid @RequestBody CbsNewDeviceNotification cbsNewDeviceNotification) {
		return cbsEnrollmentService.sendNewDeviceNotification(cbsNewDeviceNotification);
	}

	@PostMapping("/device-update/notification/{deviceId}")
	public MayBeachResponse deviceUpdateNotification(@Valid @RequestBody CbsDeviceUpdateNotification cbsDeviceUpdateNotification, @PathVariable("deviceId") String deviceId) {
		cbsDeviceUpdateNotification.setDateModified(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date()));
		return cbsEnrollmentService.sendDeviceUpdateNotification(cbsDeviceUpdateNotification,deviceId);
	}

	@GetMapping("/{deviceId}/{fepCode}/centers")
	public MayBeachResponse getCentersForFep(@PathVariable("deviceId") String deviceId, @PathVariable("fepCode") String fepCode) {
		return cbsEnrollmentService.fetchEnrollmentCenters(deviceId, fepCode);
	}

	@GetMapping("/entity/status/{entityType}/{entityIdentifier}/{deviceId}")
	public MayBeachResponse getEntityStatus(@PathVariable("entityType") String entityType, @PathVariable("entityIdentifier") String entityIdentifier, @PathVariable("deviceId") String deviceId){
		return cbsEnrollmentService.getEntityStatus(entityType, entityIdentifier, deviceId);
	}

	@PostMapping("/nin-status")
	public MayBeachResponse doPreEnrollmentCheck(@Valid @RequestBody CbsPreEnrollmentCheckRequest preEnrollmentCheckRequest){
		preEnrollmentCheckRequest.setRequestTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date()));
		return cbsEnrollmentService.doPreEnrollmentCheck(preEnrollmentCheckRequest);
	}

	@PostMapping("/verification/phone")
	public MayBeachResponse doPhonePreEnrollmentCheck(@RequestBody CbsPreEnrollmentCheckRequest preEnrollmentCheckRequest){
		preEnrollmentCheckRequest.setRequestTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date()));
		return cbsEnrollmentService.doPhonePreEnrollmentCheck(preEnrollmentCheckRequest);
	}

	@PostMapping("/verification/demographic")
	public MayBeachResponse doDemographicPreEnrollmentCheck(@RequestBody CbsPreEnrollmentCheckRequest preEnrollmentCheckRequest){
		preEnrollmentCheckRequest.setRequestTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date()));
		return cbsEnrollmentService.doDemographicPreEnrollmentCheck(preEnrollmentCheckRequest);
	}

}
