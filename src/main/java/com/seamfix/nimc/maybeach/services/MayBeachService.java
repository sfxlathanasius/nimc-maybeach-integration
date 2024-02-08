package com.seamfix.nimc.maybeach.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seamfix.nimc.maybeach.dto.MayBeachResponse;
import com.seamfix.nimc.maybeach.enums.SettingsEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.PersistenceException;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class MayBeachService {

    private final SettingService settingsService;
    private final RestTemplate restTemplate;
    private static final String NWP_TOKEN = "Nwp-Token";
    private final ObjectMapper objectMapper;

    private HttpHeaders generateMayBeachHeaders(HttpHeaders headers, boolean isProdEnv){
        headers.set(HttpHeaders.AUTHORIZATION, settingsService.getSettingValue(isProdEnv?
                SettingsEnum.MAYBEACH_PROD_AUTHORIZATION :
                SettingsEnum.MAYBEACH_TEST_AUTHORIZATION));
        headers.set(NWP_TOKEN, settingsService.getSettingValue(isProdEnv?
                SettingsEnum.MAYBEACH_PROD_TOKEN :
                SettingsEnum.MAYBEACH_TEST_TOKEN));
        return headers;
    }

    public void login(Map payload, SettingsEnum settingsEnum) throws IOException, GeneralSecurityException {

        boolean isProdEnv = settingsEnum.getName().equals(SettingsEnum.MAYBEACH_PROD_URL.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers = generateMayBeachHeaders(headers, isProdEnv);

        String query = objectMapper.writeValueAsString(payload);

        HttpEntity<Map<String, Object>> requestEntity = getMapHttpEntity(query, headers);
        String url = settingsService.getSettingValue(settingsEnum);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        log.info("Response from MayBeach: {}", requestEntity);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            MayBeachResponse graphQLResponse = objectMapper.readValue(responseBody, MayBeachResponse.class);
            graphQLResponse.setCode(responseEntity.getStatusCode().value());
        }

    }

    @NotNull
    private static HttpEntity<Map<String, Object>> getMapHttpEntity(String query, HttpHeaders headers) {
        String mutation = "mutation ($metadata: String) {\n" +
                "  addEnrolment_metadata(input: [{\n" +
                "    metadata: $metadata\n" +
                "  }]) {\n" +
                "    id\n" +
                "  }\n" +
                "}";

        // Create a Map to represent the GraphQL request

        Map<String, Object> variables = new ConcurrentHashMap<>();
        variables.put("metadata", query);

        Map<String, Object> requestBody = new ConcurrentHashMap<>();
        requestBody.put("query", mutation);
        requestBody.put("variables", variables);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        return requestEntity;
    }

}
