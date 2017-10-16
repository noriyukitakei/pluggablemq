package com.sios.azure.billing.worker.main;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sios.azure.billing.worker.config.WorkerServiceConfig;

/**
 * メインクラス
 */
@SpringBootApplication
@EnableAsync
@Import(WorkerServiceConfig.class)
public class AzureBillingManagerWorkerApplication extends AsyncConfigurerSupport {

	public static void main(String[] args) {
		SpringApplication.run(AzureBillingManagerWorkerApplication.class, args);
	}

	@Override
	public Executor getAsyncExecutor() {
		// スレッドプールの設定を記載する
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("Thread-");
		executor.initialize();
		return executor;
	}
}
