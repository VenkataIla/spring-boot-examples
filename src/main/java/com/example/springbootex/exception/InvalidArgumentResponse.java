package com.example.springbootex.exception;

import com.example.springbootex.modal.BaseResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class InvalidArgumentResponse extends BaseResponse {
    Map<String, String> errorMessage;
}