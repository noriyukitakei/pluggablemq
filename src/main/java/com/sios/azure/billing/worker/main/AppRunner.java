package com.sios.azure.billing.worker.main;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * スレッドを生成するクラス
 */
@Component
public class AppRunner implements CommandLineRunner {

	private final RetrieveQueue retrieveQueue;

	public AppRunner(RetrieveQueue retrieveQueue) {
		this.retrieveQueue = retrieveQueue;
	}

	@Override
	public void run(String... arg0) throws Exception {
		// 2つのスレッドを生成している
		CompletableFuture<String> f1 = retrieveQueue.exec();
		CompletableFuture<String> f2 = retrieveQueue.exec();
		
		// スレッドが全て終了するまでメイン処理を終了しない
		while (!(f1.isDone() && f2.isDone())) {
			Thread.sleep(10);
		}
	}
}
