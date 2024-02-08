package com.seamfix.nimc.maybeach.services.jms;

import com.google.gson.Gson;
import com.seamfix.nimc.maybeach.configs.AppConfig;
import com.seamfix.nimc.maybeach.dto.MayBeachResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class JmsSender {

	@Autowired
	JmsTemplate jmsTemplate;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Gson gson;

	@Autowired
	private AppConfig appConfig;

	@PostConstruct
	public void init() {
		gson = new Gson();
		log.info("==Initializing JMS Sender...");
	}

	public boolean callPayloadBackup(String deviceId, String requestType, Date requestTime, Date responseTime, String requestPath, Object request, MayBeachResponse response) {
		try {
			if(!appConfig.isEnablePayload()){
				log.info("Payload backup is disabled");
				return true;
			}
			Map<String, Object> mapMessage = new ConcurrentHashMap<>();
			mapMessage.put("msisdn", deviceId);
			if(response != null){
				mapMessage.put("responseCode", response.getCode() != 0 ? response.getCode() : response.getStatus());
				mapMessage.put("responseDescription", response.getMessage());
				mapMessage.put("response", gson.toJson(response));
			}
			mapMessage.put("requestType", requestType);
			mapMessage.put("requestTime", dateFormat.format(requestTime));
			mapMessage.put("responseTime", dateFormat.format(responseTime));
			if(request != null){
				mapMessage.put("request", gson.toJson(request));
			}
			mapMessage.put("requestPath", requestPath);
			jmsTemplate.convertAndSend("jms.queue.PayloadBackupQueue", mapMessage);
			return true;
		} catch (JmsException ex) {
			log.error("Something went wrong while pushing to queue", ex);
			return false;
		}
	}

}
