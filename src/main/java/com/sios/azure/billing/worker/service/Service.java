package com.sios.azure.billing.worker.service;

/**
 * メッセージを処理するクラスのインターフェース
 */
public interface Service {
	
	/**
	 * メッセージを処理するメソッドで、引数にはメッセージの内容(JSON)を指定する
	 * @param message メッセージ
	 */
	public void exec(String message);
}
