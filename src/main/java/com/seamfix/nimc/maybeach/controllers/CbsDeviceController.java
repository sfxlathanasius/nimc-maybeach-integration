package com.seamfix.nimc.maybeach.controllers;

import com.seamfix.nimc.maybeach.dto.CbsDeviceActivationRequest;
import com.seamfix.nimc.maybeach.dto.CbsDeviceCertificationRequest;
import com.seamfix.nimc.maybeach.dto.CbsDeviceUserLoginRequest;
import com.seamfix.nimc.maybeach.dto.CbsHeartBeatsRequest;
import com.seamfix.nimc.maybeach.dto.CbsResponse;
import com.seamfix.nimc.maybeach.services.device.CbsDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author nnwachukwu
 *
 */
@RestController
@RequestMapping("/device")
public class CbsDeviceController {
	
	@Autowired
	private CbsDeviceService cbsDeviceService;

	@PostMapping("/request-activation")
	public CbsResponse deviceActivationRequest(@Valid @RequestBody CbsDeviceActivationRequest cbsDeviceActivationRequest){
		
		return cbsDeviceService.sendDeviceActivationRequest(cbsDeviceActivationRequest);

	}

	@PostMapping("/request-certification")
	public CbsResponse deviceCertificationRequest(@Valid @RequestBody CbsDeviceCertificationRequest deviceCertificationRequest){

		return cbsDeviceService.sendDeviceCertificationRequest(deviceCertificationRequest) ;

	}

	@GetMapping("/activation-data/{deviceId}/{requestId}")
	public CbsResponse fetchActivationDataRequest(@PathVariable("deviceId") String deviceId, @PathVariable("requestId") String requestId){

		return cbsDeviceService.sendFetchActivationDataRequest(deviceId, requestId) ;

	}

	@PostMapping("/login")
	public CbsResponse deviceUserLoginRequest(@Valid @RequestBody CbsDeviceUserLoginRequest userLoginRequest){

		return cbsDeviceService.sendDeviceUserLoginRequest(userLoginRequest) ;

	}

	@GetMapping("/ping")
	public String ping() {
		return "CBS middleware service is up and running!";
	}

	@PostMapping("/heartbeats")
	public CbsResponse sendHeartBeats(@Valid @RequestBody CbsHeartBeatsRequest heartBeatsRequest){
		return cbsDeviceService.sendHeartBeats(heartBeatsRequest) ;
	}
}
