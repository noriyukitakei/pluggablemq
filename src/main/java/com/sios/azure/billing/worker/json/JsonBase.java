package com.sios.azure.billing.worker.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * キューに入るJSONの中で、serivceIdの部分だけを定義しているクラス
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonBase {
	
	private String serviceId;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
