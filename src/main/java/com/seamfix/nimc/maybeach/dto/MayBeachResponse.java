package com.seamfix.nimc.maybeach.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MayBeachResponse {

    private ApiData data;
    private int code;

    @Data
    public static class ApiData {
        @JsonProperty("addEnrolment_metadata")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private List<AddEnrolmentMetadata> addEnrolmentMetadata;
        @JsonProperty("enrolment_metadataID")
        private EnrolmentMetadataId enrolmentMetadataId;
    }

    @Data
    public static class AddEnrolmentMetadata {
        private String id;
    }
    @Data
    public static class EnrolmentMetadataId {
        private String status;
    }

}


