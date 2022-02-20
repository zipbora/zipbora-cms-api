package com.zipbom.zipbom.Util.response;

import com.zipbom.zipbom.Global.exception.ErrorResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FailResponseDto {
	private boolean success;
	private ErrorResponse error;
}
