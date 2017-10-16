package com.sios.azure.billing.worker.json;

/**
 * JsonBaseを継承し、Serviceが処理するJSONのマッピングクラス
 */
public class MailJson extends JsonBase {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		private String subject; // メールのタイトル
		private String to; // メールの宛先
		private String from; // メールの送信元
		private String body; // メールの本文

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

	}
}
