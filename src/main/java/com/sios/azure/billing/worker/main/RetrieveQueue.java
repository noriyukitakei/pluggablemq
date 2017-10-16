package com.sios.azure.billing.worker.main;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import com.microsoft.azure.storage.queue.CloudQueueMessage;
import com.sios.azure.billing.worker.json.JsonBase;

@Service
public class RetrieveQueue {

	private static final Logger logger = LoggerFactory.getLogger(RetrieveQueue.class);
	private static final String connection = "XXXXXX";
	private static final String queueName = "mail-sender";
	private static ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private ApplicationContext applicationContext;

	@Async
	public CompletableFuture<String> exec() {

		try {
			// CONNECTION STRINGの情報をもとにキューの入れ物に接続する
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(connection);

			// キューのクライアントを生成する
			CloudQueueClient queueClient = storageAccount.createCloudQueueClient();

			// キュー名をセットする
			CloudQueue queue = queueClient.getQueueReference(queueName);

			// 無限ループによりキューを監視する
			while (true) {

				// キューからメッセージを取得する
				CloudQueueMessage retrievedMessage = queue.retrieveMessage();

				if (retrievedMessage != null) {
					// JSONからサービスIDを取得して、Javaオブジェクトにマップする
					JsonBase result = objectMapper.readValue(retrievedMessage.getMessageContentAsString(),
							JsonBase.class);
					
					// サービスIDに対応したBeanをApplication Contextから取得する
					com.sios.azure.billing.worker.service.Service service = (com.sios.azure.billing.worker.service.Service) applicationContext
							.getBean(result.getServiceId());
					
					// サービスに定義された処理を実行する
					service.exec(retrievedMessage.getMessageContentAsString());
					
					// キューを削除する。
					queue.deleteMessage(retrievedMessage);
				}
			}
		} catch (Exception e) {
			logger.error("Exception occurred while thread is running...", e);
		}

		return CompletableFuture.completedFuture("Thread is done!!");
	}
}
