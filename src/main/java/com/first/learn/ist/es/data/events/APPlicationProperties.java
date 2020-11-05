package com.first.learn.ist.es.data.events;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties
public class APPlicationProperties {

	private String bootstrapServers;
	private String keyDeserializer;
	private String valueDeserializer;
	private String groupId;
	private String sslKeystoreLocation;
	private String sslKeystorePassword;
	private String sslTruststoreLocation;
	private String sslTruststorePassword;
	private String securityProtocol;
	private String saslMechanism;
	private String autoOffsetReset;
	private String subscribeTopicNames;
	private String messagesLocation;

	private String tcpBootstrapServers;
	private String tcpGroupId;
	private String tcpAutoOffsetReset;
	private String tcpSubscribeTopicNames;
	private String tcpMessagesLocation;

	public String getBootstrapServers() {
		return bootstrapServers;
	}

	public void setBootstrapServers(final String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}

	public String getKeyDeserializer() {
		return keyDeserializer;
	}

	public void setKeyDeserializer(final String keyDeserializer) {
		this.keyDeserializer = keyDeserializer;
	}

	public String getValueDeserializer() {
		return valueDeserializer;
	}

	public void setValueDeserializer(final String valueDeserializer) {
		this.valueDeserializer = valueDeserializer;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}

	public String getSslKeystoreLocation() {
		return sslKeystoreLocation;
	}

	public void setSslKeystoreLocation(final String sslKeystoreLocation) {
		this.sslKeystoreLocation = sslKeystoreLocation;
	}

	public String getSslKeystorePassword() {
		return sslKeystorePassword;
	}

	public void setSslKeystorePassword(final String sslKeystorePassword) {
		this.sslKeystorePassword = sslKeystorePassword;
	}

	public String getSslTruststoreLocation() {
		return sslTruststoreLocation;
	}

	public void setSslTruststoreLocation(final String sslTruststoreLocation) {
		this.sslTruststoreLocation = sslTruststoreLocation;
	}

	public String getSslTruststorePassword() {
		return sslTruststorePassword;
	}

	public void setSslTruststorePassword(final String sslTruststorePassword) {
		this.sslTruststorePassword = sslTruststorePassword;
	}

	public String getSecurityProtocol() {
		return securityProtocol;
	}

	public void setSecurityProtocol(final String securityProtocol) {
		this.securityProtocol = securityProtocol;
	}

	public String getSaslMechanism() {
		return saslMechanism;
	}

	public void setSaslMechanism(final String saslMechanism) {
		this.saslMechanism = saslMechanism;
	}

	public String getAutoOffsetReset() {
		return autoOffsetReset;
	}

	public void setAutoOffsetReset(final String autoOffsetReset) {
		this.autoOffsetReset = autoOffsetReset;
	}

	public String getSubscribeTopicNames() {
		return subscribeTopicNames;
	}

	public void setSubscribeTopicNames(final String subscribeTopicNames) {
		this.subscribeTopicNames = subscribeTopicNames;
	}

	public String getMessagesLocation() {
		return messagesLocation;
	}

	public void setMessagesLocation(final String messagesLocation) {
		this.messagesLocation = messagesLocation;
	}

	public String getTcpBootstrapServers() {
		return tcpBootstrapServers;
	}

	public void setTcpBootstrapServers(final String tcpBootstrapServers) {
		this.tcpBootstrapServers = tcpBootstrapServers;
	}

	public String getTcpGroupId() {
		return tcpGroupId;
	}

	public void setTcpGroupId(final String tcpGroupId) {
		this.tcpGroupId = tcpGroupId;
	}

	public String getTcpAutoOffsetReset() {
		return tcpAutoOffsetReset;
	}

	public void setTcpAutoOffsetReset(final String tcpAutoOffsetReset) {
		this.tcpAutoOffsetReset = tcpAutoOffsetReset;
	}

	public String getTcpSubscribeTopicNames() {
		return tcpSubscribeTopicNames;
	}

	public void setTcpSubscribeTopicNames(final String tcpSubscribeTopicNames) {
		this.tcpSubscribeTopicNames = tcpSubscribeTopicNames;
	}

	public String getTcpMessagesLocation() {
		return tcpMessagesLocation;
	}

	public void setTcpMessagesLocation(final String tcpMessagesLocation) {
		this.tcpMessagesLocation = tcpMessagesLocation;
	}
}
