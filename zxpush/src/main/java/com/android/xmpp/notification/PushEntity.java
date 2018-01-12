package com.android.xmpp.notification;

public class PushEntity {

	private String notificationId = "";
	private String notificationApiKey = "";
	private String notificationTitle = "";
	private String notificationMessage = "";
	private String notificationUri = "";
	private String notificationFrom = "";
	private String packetId = "";

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationApiKey() {
		return notificationApiKey;
	}

	public void setNotificationApiKey(String notificationApiKey) {
		this.notificationApiKey = notificationApiKey;
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public String getNotificationMessage() {
		return notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	public String getNotificationUri() {
		return notificationUri;
	}

	public void setNotificationUri(String notificationUri) {
		this.notificationUri = notificationUri;
	}

	public String getNotificationFrom() {
		return notificationFrom;
	}

	public void setNotificationFrom(String notificationFrom) {
		this.notificationFrom = notificationFrom;
	}

	public String getPacketId() {
		return packetId;
	}

	public void setPacketId(String packetId) {
		this.packetId = packetId;
	}

}
