package com.sios.azure.billing.worker.service;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sios.azure.billing.worker.json.MailJson;

/**
 * Serviceインターフェースを実装するクラスで、メール送信処理を行う
 */
public class SendMailService implements Service {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void exec(String message) {
		try {
			// ここでは、JSONの内容を標準出力に出しているだけだが、実際にはメール送信処理等、ロジックを記載する
			MailJson result = objectMapper.readValue(message,MailJson.class);
			System.out.println("ServiceId:" + result.getServiceId());
			System.out.println("Subject:" + result.getData().getSubject());
			System.out.println("From:" + result.getData().getFrom());
			System.out.println("To:" + result.getData().getTo());
			System.out.println("Body:" + result.getData().getBody());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
