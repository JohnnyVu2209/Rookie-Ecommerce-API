package com.musical.instrument.ecommerce.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	private String successCode;
	private Object data;
	private String errorCode;

	public String getSuccessCode() {
		return successCode;
	}

	public void setSuccessCode(String successCode) {
		this.successCode = successCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
