package com.seamfix.nimc.maybeach.services.device;

import com.seamfix.nimc.maybeach.dto.CbsDeviceCertificationRequest;
import com.seamfix.nimc.maybeach.dto.CbsDeviceUserLoginRequest;
import com.seamfix.nimc.maybeach.dto.CbsHeartBeatsRequest;
import com.seamfix.nimc.maybeach.dto.DeviceActivationDataPojo;
import com.seamfix.nimc.maybeach.enums.RequestTypeEnum;
import com.seamfix.nimc.maybeach.enums.ResponseCodeEnum;
import com.seamfix.nimc.maybeach.utils.EncryptionKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.seamfix.nimc.maybeach.dto.CbsDeviceActivationRequest;
import com.seamfix.nimc.maybeach.dto.CbsRequestResponse;
import com.seamfix.nimc.maybeach.dto.CbsResponse;
import com.seamfix.nimc.maybeach.services.payment.CbsService;
import org.springframework.web.client.HttpStatusCodeException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author nnwachukwu
 *
 */
@Slf4j
@Service
@SuppressWarnings("PMD.GuardLogStatement")
public class CbsDeviceService extends CbsService {

	@Autowired
	EncryptionKeyUtil encryptionKeyUtil;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public CbsResponse sendDeviceActivationRequest(CbsDeviceActivationRequest cbsDeviceActivationRequest) {
		if(!appConfig.isCbsIntegrationEnabled()) {
			return getMockResponse();
		}
		return callDeviceActivationService(cbsDeviceActivationRequest);
		
	}

	public CbsResponse sendDeviceCertificationRequest(CbsDeviceCertificationRequest cbsDeviceCertificationRequest) {
		if(!appConfig.isCbsIntegrationEnabled()) {
			return getMockResponse();
		}
		return callDeviceCertificationService(cbsDeviceCertificationRequest);

	}

	public CbsResponse sendFetchActivationDataRequest(String deviceId, String requestId) {
		if(!appConfig.isCbsIntegrationEnabled()) {
			return getMockResponse();
		}
		return callFetchActivationDataService(deviceId, requestId);

	}

	public CbsResponse sendDeviceUserLoginRequest(CbsDeviceUserLoginRequest userLoginRequest) {
		if(!appConfig.isCbsIntegrationEnabled()) {
			return getMockResponse();
		}
		return callDeviceUserLoginService(userLoginRequest);
	}

	public CbsResponse sendHeartBeats(CbsHeartBeatsRequest userLoginRequest) {
		if(!appConfig.isCbsIntegrationEnabled()) {
			return getMockResponse();
		}
		return callHeartBeats(userLoginRequest);
	}

	public CbsResponse callDeviceActivationService(CbsDeviceActivationRequest cbsDeviceActivationRequest) {
		Date requestTime = new Date();

		String url = appConfig.getCbsDeviceActivationUri();
		log.debug("Device Activation Url: {}", url);

		CbsResponse cbsResponse;

		Date responseTime;

		String validationError = validateRequestParams(cbsDeviceActivationRequest);
		if (validationError != null && !validationError.isEmpty()) {
			cbsResponse = new CbsResponse(HttpStatus.BAD_REQUEST.value(), validationError);
			responseTime = new Date();
			doPayloadBackup(cbsDeviceActivationRequest.getProviderDeviceIdentifier(), RequestTypeEnum.DEVICE_ACTIVATION.name(), requestTime, responseTime, url, cbsDeviceActivationRequest , cbsResponse);
			return cbsResponse;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = mapToJsonString(convertObjectToMap(cbsDeviceActivationRequest));
		log.debug("Device Activation Request: {}", requestJson);
		setAccountIdSignatureHeaderParams(headers, requestJson);

		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

		ResponseEntity<String> response;
		try{
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			responseTime = new Date();
		}catch (HttpStatusCodeException ex){
			responseTime = new Date();
			cbsResponse = handleJsonParseException(ex);
			doPayloadBackup(cbsDeviceActivationRequest.getProviderDeviceIdentifier(), RequestTypeEnum.DEVICE_ACTIVATION.name(), requestTime, responseTime, url, cbsDeviceActivationRequest , cbsResponse);
			if(!getCodes().contains(String.valueOf(ex.getRawStatusCode()))){
				cbsResponse.setMessage(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getDescription());
			}
			return  cbsResponse;
		}
		cbsResponse = gson.fromJson(response.getBody(), CbsRequestResponse.class);
		doPayloadBackup(cbsDeviceActivationRequest.getProviderDeviceIdentifier(), RequestTypeEnum.DEVICE_ACTIVATION.name(), requestTime, responseTime, url, cbsDeviceActivationRequest , cbsResponse);
		return cbsResponse;
	}

	public CbsResponse callDeviceCertificationService(CbsDeviceCertificationRequest deviceCertificationRequest) {
		Date requestTime = new Date();

		String url = appConfig.getCbsDeviceCertificationUri().replace("{deviceId}", safeString(deviceCertificationRequest.getDeviceId()));
		log.debug("Device Certification Url: {}", url);

		CbsResponse cbsResponse;
		Date responseTime;

		String validationError = validateRequestParams(deviceCertificationRequest);
		if (validationError != null && !validationError.isEmpty()) {
			cbsResponse = new CbsResponse(HttpStatus.BAD_REQUEST.value(), validationError);
			responseTime = new Date();
			doPayloadBackup(deviceCertificationRequest.getDeviceId(), RequestTypeEnum.DEVICE_CERTIFICATION.name(), requestTime, responseTime, url, deviceCertificationRequest , cbsResponse);
			return cbsResponse;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = mapToJsonString(convertObjectToMap(deviceCertificationRequest));
		log.debug("Device Certification Request: {}", requestJson);
		setAccountIdDeviceIdSignatureHeaderParams(headers, deviceCertificationRequest.getDeviceId(), requestJson);

		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

		ResponseEntity<String> response;
		try{
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			responseTime = new Date();
		}catch (HttpStatusCodeException ex){
			log.error("Error calling callDeviceCertificationService", ex);
			responseTime = new Date();
			cbsResponse = handleJsonParseException(ex);
			doPayloadBackup(deviceCertificationRequest.getDeviceId(), RequestTypeEnum.DEVICE_CERTIFICATION.name(), requestTime, responseTime, url, deviceCertificationRequest , cbsResponse);
			if(!getCodes().contains(String.valueOf(ex.getStatusCode().value()))){
				cbsResponse.setMessage(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getDescription());
			}
			return  cbsResponse;
		}
		cbsResponse = gson.fromJson(response.getBody(), CbsRequestResponse.class);
		doPayloadBackup(deviceCertificationRequest.getDeviceId(), RequestTypeEnum.DEVICE_CERTIFICATION.name(), requestTime, responseTime, url, deviceCertificationRequest , cbsResponse);

		return cbsResponse;
	}

	public CbsResponse callFetchActivationDataService(String deviceId, String requestId) {
		Date requestTime = new Date();
		String url = appConfig.getCbsFetchActivationUri().replace("{requestId}", safeString(requestId));
		log.debug("Fetch Activation Data Url: {}", url);

		CbsRequestResponse cbsResponse;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		setAccountIdDeviceIdSignatureHeaderParams(headers, deviceId, null);

		HttpEntity<String> entity = new HttpEntity<>(headers);

		Date responseTime;

		ResponseEntity<String> response;
		try{
			response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			responseTime = new Date();
		}catch (HttpStatusCodeException ex){
			log.debug("Fetch Activation Data Response Body: {}", ex.getResponseBodyAsString());
			responseTime = new Date();
			cbsResponse = handleJsonParseException(ex);
			doPayloadBackup(deviceId, RequestTypeEnum.FETCH_ACTIVATION_DATA.name(), requestTime, responseTime, url, null , cbsResponse);
			if(!getCodes().contains(String.valueOf(ex.getRawStatusCode()))){
				cbsResponse.setMessage(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getDescription());
			}
			return  cbsResponse;
		}
		log.debug("Fetch Activation Data Response Body: {}", response.getBody());
		cbsResponse = gson.fromJson(response.getBody(), CbsRequestResponse.class);

		Map<String, Object> cbsResponseData = null;

		if(cbsResponse.getCode() == HttpStatus.OK.value()) {
			cbsResponseData = (Map<String, Object>) cbsResponse.getData();
			String encryptedData = (String) cbsResponseData.get("data");
			String algorithm = (String) cbsResponseData.get("algorithm");

			DeviceActivationDataPojo decryptedData = gson.fromJson(encryptionKeyUtil.decrypt(appConfig.getCbsApiKey(), encryptedData, algorithm), DeviceActivationDataPojo.class);

			cbsResponse.setData(decryptedData);
		}

		doPayloadBackup(deviceId, RequestTypeEnum.FETCH_ACTIVATION_DATA.name(), requestTime, responseTime, url, cbsResponseData , cbsResponse);
		return cbsResponse;
	}

	@SuppressWarnings({"PMD.NcssCount", "PMD.AvoidCatchingGenericException"})
	public CbsResponse callDeviceUserLoginService(CbsDeviceUserLoginRequest userLoginRequest) {
		Date requestTime = new Date();
		CbsResponse cbsResponse;

		String url = appConfig.getCbsLoginUri();
		log.debug("Device User Login Url: {}", url);

		Date responseTime;
		String validationError = validateRequestParams(userLoginRequest);
		if (validationError != null && !validationError.isEmpty()) {
			cbsResponse = new CbsResponse(HttpStatus.BAD_REQUEST.value(), validationError);
			responseTime = new Date();
			doPayloadBackup(userLoginRequest.getDeviceId(), RequestTypeEnum.DEVICE_USER_LOGIN.name(), requestTime, responseTime, url, userLoginRequest , cbsResponse);
			return cbsResponse;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestJson = mapToJsonString(convertObjectToMap(userLoginRequest));
		setAccountIdDeviceIdSignatureHeaderParams(headers, userLoginRequest.getDeviceId(), requestJson);

		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

		ResponseEntity<String> response;
		try{
			response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			responseTime = new Date();
		}catch (HttpStatusCodeException ex){
			log.error("Error calling CBS DeviceUserLoginService", ex);
			responseTime = new Date();
			cbsResponse = handleJsonParseException(ex);
			doPayloadBackup(userLoginRequest.getDeviceId(), RequestTypeEnum.DEVICE_USER_LOGIN.name(), requestTime, responseTime, url, userLoginRequest , cbsResponse);
			if(!getCodes().contains(String.valueOf(ex.getRawStatusCode()))){
				cbsResponse.setMessage(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getDescription());
				cbsResponse.setCode(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getCode());
			}
			return cbsResponse;
		}catch (Exception e){
			log.error("Error calling CBS DeviceUserLoginService", e);
			cbsResponse = new CbsRequestResponse();
			cbsResponse.setMessage(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getDescription());
			cbsResponse.setCode(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getCode());
			return cbsResponse;
		}
		cbsResponse = gson.fromJson(response.getBody(), CbsRequestResponse.class);
		doPayloadBackup(userLoginRequest.getDeviceId(), RequestTypeEnum.DEVICE_USER_LOGIN.name(), requestTime, responseTime, url, userLoginRequest , cbsResponse);

		return cbsResponse;
	}

	@SuppressWarnings("PMD.AvoidCatchingGenericException")
	public CbsResponse callHeartBeats(CbsHeartBeatsRequest heartBeatsRequest) {
		Date requestTime = new Date();
		CbsResponse cbsResponse;

		String url = appConfig.getCbsHeartbeatsUri();
		log.debug("Heartbeats Url: {}", url);

		Date responseTime;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		if(StringUtils.isBlank(heartBeatsRequest.getDeviceTime())) {
			heartBeatsRequest.setDeviceTime(dateFormat.format(requestTime));
		}
		String requestJson = mapToJsonString(convertObjectToMap(heartBeatsRequest));
		setAccountIdDeviceIdSignatureHeaderParams(headers, heartBeatsRequest.getDeviceId(), requestJson);

		HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

		ResponseEntity<String> response;
		try{
			response = restTemplate.postForEntity(url, entity, String.class);
			responseTime = new Date();
			String responseBody = response.getBody();
			int responseCode = response.getStatusCodeValue();
			log.debug("cbs heartbeats response : {}. response code: {}", responseBody, responseCode);
			cbsResponse = new CbsResponse(responseCode, responseBody);
			cbsResponse.setCode(responseCode);
		}catch (HttpStatusCodeException ex){
			log.error("Error calling CBS Heartbeats Service", ex);
			responseTime = new Date();
			cbsResponse = gson.fromJson(ex.getResponseBodyAsString(), CbsRequestResponse.class);
		}catch (Exception e) {
			log.error("Error calling CBS Heartbeats Service", e);
			responseTime = new Date();
			cbsResponse = new CbsResponse();
			cbsResponse.setMessage(e.getMessage());
			doPayloadBackup(heartBeatsRequest.getDeviceId(), RequestTypeEnum.HEARTBEAT.name(), requestTime, responseTime, url, heartBeatsRequest , cbsResponse);
			cbsResponse.setMessage(ResponseCodeEnum.UNABLE_TO_REACH_CBS.getDescription());
			return cbsResponse;
		}
		doPayloadBackup(heartBeatsRequest.getDeviceId(), RequestTypeEnum.HEARTBEAT.name(), requestTime, responseTime, url, heartBeatsRequest , cbsResponse);
		return cbsResponse;
	}
}
