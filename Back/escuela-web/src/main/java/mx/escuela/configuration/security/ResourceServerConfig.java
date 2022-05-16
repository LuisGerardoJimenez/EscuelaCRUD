package mx.escuela.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import mx.escuela.configuration.YamlConfig;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private YamlConfig config;

	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.resourceId(config.getResourceIds())
			.tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.exceptionHandling().authenticationEntryPoint(new AuthException())
			.and()
			.requestMatchers()
			.and()
			.authorizeRequests()
			.antMatchers("/**" ).permitAll()
			.antMatchers(HttpMethod.OPTIONS,"/oauth/token").permitAll();
	}
}
