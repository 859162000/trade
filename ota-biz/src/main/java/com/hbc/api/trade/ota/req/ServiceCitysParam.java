package com.hbc.api.trade.ota.req;

import org.hibernate.validator.constraints.NotBlank;

public class ServiceCitysParam {

	private Integer servicePartner ;
	@NotBlank private String sign ;

	/**
	 * @return the sign
	 */
	public String getSign() {
		return sign;
	}

	/**
	 * @param sign the sign to set
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getServicePartner() {
		return servicePartner ;
	}

	public void setServicePartner(Integer servicePartner) {
		this.servicePartner = servicePartner ;
	}
}
