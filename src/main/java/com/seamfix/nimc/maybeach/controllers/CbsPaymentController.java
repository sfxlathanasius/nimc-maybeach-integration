package com.seamfix.nimc.maybeach.controllers;

import com.seamfix.nimc.maybeach.dto.CbsPaymentRequest;
import com.seamfix.nimc.maybeach.dto.CbsPaymentStatusRequest;
import com.seamfix.nimc.maybeach.dto.CbsPaymentStatusResponse;
import com.seamfix.nimc.maybeach.dto.CbsResponse;
import com.seamfix.nimc.maybeach.dto.CbsResponseData;
import com.seamfix.nimc.maybeach.exceptions.GeneralException;
import com.seamfix.nimc.maybeach.services.payment.CbsPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.SocketTimeoutException;

@RestController
@RequestMapping("/payments")
public class CbsPaymentController {
	
	private static final String SIGNATURE_STR = "Signature";
	@Autowired
	CbsPaymentService cbsPaymentService;

	@RequestMapping(value = "/consume", method = RequestMethod.POST)
	public CbsResponseData paymentConsumption(@RequestBody CbsPaymentRequest cbsPaymentRequest, @RequestHeader("X-ACCOUNT-ID") String accountId, @RequestHeader(SIGNATURE_STR) String signature) throws SocketTimeoutException, GeneralException{
		
		cbsPaymentRequest.getMappedHeaders().put("accountId", accountId);
		cbsPaymentRequest.getMappedHeaders().put(SIGNATURE_STR, signature);
		
		return cbsPaymentService.consumePayment(cbsPaymentRequest);
		
	}

	@RequestMapping(value = "/status/{esaCode}/{paymentReference}/{deviceId}", method = RequestMethod.GET)
	public CbsPaymentStatusResponse paymentStatus(@RequestHeader("X-ACCOUNT-ID") String accountId, @RequestHeader(SIGNATURE_STR) String signature, @PathVariable("esaCode") String esaCode, @PathVariable("paymentReference") String paymentReference,@PathVariable("deviceId") String deviceId) throws SocketTimeoutException, GeneralException{
		return cbsPaymentService.getPaymentStatus(esaCode, paymentReference,deviceId);
	}

	@RequestMapping(value = "/status", method = RequestMethod.POST)
	public CbsPaymentStatusResponse paymentStatus(@RequestBody CbsPaymentStatusRequest request) throws SocketTimeoutException, GeneralException{
		return cbsPaymentService.getPaymentStatus(request);
	}

	@RequestMapping(value = "/status/v2", method = RequestMethod.POST)
	public CbsResponse paymentStatusV2(@RequestBody CbsPaymentStatusRequest request){
		return cbsPaymentService.getPaymentStatusV2(request);
	}

	/**
	 * at the moment, the filter is only applied to this enpoint alone
	 * Go to @{@link com.seamfix.nimc.maybeach.filters.PaymentStatusFilter}
	 * and add more endpoints when the need arise
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/status/v3", method = RequestMethod.POST)
	public CbsResponse paymentStatusV3(@RequestBody CbsPaymentStatusRequest request){
		return cbsPaymentService.getPaymentStatusV3(request);
	}

}
