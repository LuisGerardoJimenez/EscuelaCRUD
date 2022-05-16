package mx.escuela.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("security")
public class YamlConfig {

	@Value("${security.client-id}")
	private String clientId;

	@Value("${security.client-secret}")
	private String clientSecret;

	@Value("${security.grant-type}")
	private String grantType;

	@Value("${security.scope-read}")
	private String scopeRead;

	@Value("${security.scope-write}")
	private String scopeWrite;

	@Value("${security.resource-ids}")
	private String resourceIds;

	@Value("${security.signing-key}")
	private String signingKey;

	@Value("${security.security-realm}")
	private String securityRealm;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getScopeRead() {
		return scopeRead;
	}
	public void setScopeRead(String scopeRead) {
		this.scopeRead = scopeRead;
	}
	public String getScopeWrite() {
		return scopeWrite;
	}
	public void setScopeWrite(String scopeWrite) {
		this.scopeWrite = scopeWrite;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public String getSigningKey() {
		return signingKey;
	}
	public void setSigningKey(String signingKey) {
		this.signingKey = signingKey;
	}
	public String getSecurityRealm() {
		return securityRealm;
	}
	public void setSecurityRealm(String securityRealm) {
		this.securityRealm = securityRealm;
	}

}
