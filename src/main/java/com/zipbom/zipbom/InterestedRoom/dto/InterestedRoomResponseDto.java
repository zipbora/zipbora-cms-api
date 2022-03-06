package com.zipbom.zipbom.InterestedRoom.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InterestedRoomResponseDto {

	private Long id;

	public InterestedRoomResponseDto(Long id) {
		this.id = id;
	}
}
