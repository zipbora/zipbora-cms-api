package com.zipbom.zipbom.Auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpDto {
	private String jwtToken;

	public SignUpDto(String jwtToken) {
		this.jwtToken = jwtToken;
	}
}
