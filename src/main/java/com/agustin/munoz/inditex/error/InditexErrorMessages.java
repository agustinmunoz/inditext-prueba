package com.agustin.munoz.inditex.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author agust
 *
 */

@AllArgsConstructor
@Getter
public enum InditexErrorMessages {

	ERROR_TERCEROS_SERVICIOS("INDITEX 01", "Error de terceros servicios"), BAD_REQUEST("INDITEX 02", "Bad Request"),
	NOT_FOUND("INDITEX 03", "Not Found"), ERROR_INTERNO("INDITEX 04", "Error Interno"),
	DATA_EXCEPTION("INDITEX 05", "Error data exception"), PARSER_EXCEPTION("INDITEX 06", "Error de parseo");

	private String code;
	private String description;

}
