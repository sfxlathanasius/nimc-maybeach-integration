package com.seamfix.nimc.maybeach.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seamfix.nimc.maybeach.enums.SettingsEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class GraphQLUtility {

    private final SettingService settingsService;
    private final RestTemplate restTemplate;
    private static final String NWP_TOKEN = "Nwp-Token";
    private final ObjectMapper objectMapper;

    public HttpHeaders generateMayBeachHeaders(HttpHeaders headers){
        headers.set(HttpHeaders.AUTHORIZATION, settingsService.getSettingValue(SettingsEnum.MAYBEACH_AUTHORIZATION));
        headers.set(NWP_TOKEN, settingsService.getSettingValue(SettingsEnum.MAYBEACH_TOKEN));
        return headers;
    }

    public Map login(String query, String url) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers = generateMayBeachHeaders(headers);
        Map response = new ConcurrentHashMap();

        HttpEntity<Map<String, Object>> requestEntity = getMapHttpEntity(query, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        log.info("Response from MayBeach: {}", requestEntity);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            response = objectMapper.readValue(responseBody, Map.class);
//            MayBeachResponse graphQLResponse = objectMapper.readValue(responseBody, MayBeachResponse.class);
        }
        return response;

    }

    @NotNull
    private static HttpEntity<Map<String, Object>> getMapHttpEntity(String query, HttpHeaders headers) {
        String mutation = "mutation ($metadata: String) {\n" +
                "  deviceUserLogin(input: [{\n" +
                "    metadata: $metadata\n" +
                "  }]) {\n" +
                "    id\n" +
                "    agentfirstname\n" +
                "    agentlastname\n" +
                "    agentemail\n" +
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
