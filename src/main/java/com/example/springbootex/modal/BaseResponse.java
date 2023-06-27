package com.example.springbootex.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;



@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse {

    //Spring boot style response params
    @JsonIgnore //see getStatus()
    @Builder.Default
    protected HttpStatus status = HttpStatus.OK;

    protected String path;

    protected Map<String, String> responseTime;

    protected List<String> fulfilledBy;

    @Builder.Default
    protected String message = "";

    @JsonProperty
    public int getStatus() { //return status code instead of enum name
        return this.status.value();
    }

    private String traceId;

    @JsonIgnore
    protected HttpHeaders httpHeaders;


}