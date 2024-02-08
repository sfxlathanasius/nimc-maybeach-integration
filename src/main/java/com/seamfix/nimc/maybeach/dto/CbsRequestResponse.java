/**
 * 
 */
package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nneoma
 *
 */
@Getter
@Setter
public class CbsRequestResponse extends MayBeachResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5088453035314880845L;

	
	private Object data;

	public CbsRequestResponse() {
	}

	public CbsRequestResponse(int code, String message) {
		super(code, message);
	}
}
