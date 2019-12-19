package com.scrotifybanking.payeemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Login response dto.
 */
@Setter
@Getter
@NoArgsConstructor
public class LoginResponseDto {

	private Integer statusCode;
	private String message;
}
