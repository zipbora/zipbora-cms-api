package com.zipbom.zipbom.Util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SuccessResponseDto<T> {
	private boolean success;
	private T data;
}
