package com.sios.azure.billing.worker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sios.azure.billing.worker.service.SendMailService;
import com.sios.azure.billing.worker.service.Service;

/**
 * Serviceインターフェースを実装したクラスのDIの定義をするJava Config
 */
@Configuration
public class WorkerServiceConfig {

	// Bean名には、メッセージのJSONの「serviceId」にある値と同じものを指定する
	@Bean(name = "SendMailService")
	public Service getSendMailService() {

		return new SendMailService();

	}
}
