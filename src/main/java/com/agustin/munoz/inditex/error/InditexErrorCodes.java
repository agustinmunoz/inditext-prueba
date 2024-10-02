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
public enum InditexErrorCodes {

	ERROR_500(500), ERROR_400(400), ERROR_404(404);

	private int code;

}
