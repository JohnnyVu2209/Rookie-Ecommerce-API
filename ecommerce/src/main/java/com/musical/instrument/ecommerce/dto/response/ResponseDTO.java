package com.musical.instrument.ecommerce.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO {
	private String successCode;
	private Object data;
	private String errorCode;
}
