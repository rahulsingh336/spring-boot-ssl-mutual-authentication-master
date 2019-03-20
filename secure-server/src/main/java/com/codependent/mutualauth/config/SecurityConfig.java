package com.codependent.mutualauth.config;

import com.codependent.mutualauth.AbstractPreAuthenticatedProcessingFilterTemp;
import com.codependent.mutualauth.CustomDNExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.x509.X509AuthenticationFilter;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomAuthProvider customAuthProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth.authenticationProvider(customAuthProvider));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.addFilterBefore(customAuthFilter(), X509AuthenticationFilter.class)
			.authorizeRequests()
				.anyRequest().authenticated()
			/*.and()
				.x509()
					.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
					.userDetailsService(userDetailsService())*/
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			;
	}

	public AbstractPreAuthenticatedProcessingFilterTemp customAuthFilter(){
		AbstractPreAuthenticatedProcessingFilterTemp abstractPreAuthenticatedProcessingFilterTemp = new AbstractPreAuthenticatedProcessingFilterTemp();
		abstractPreAuthenticatedProcessingFilterTemp.setAuthenticationManager(authenticationManager);
		return abstractPreAuthenticatedProcessingFilterTemp;
	}
	
	/*@Bean
    public UserDetailsService userDetailsService() {
        return (username -> {
        	if (username.equals("codependent-client1") || username.equals("codependent-client2")) {
                return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
            }else{
            	return null;
            }
        });
    }*/

	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			System.out.println("see username");
			System.out.println(username);
			if (username != null) {
        return new User("username going from here", "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
      }
      return null;
    };
	}

	/*@Primary
	@Bean
	public X509PrincipalExtractor getCustomDNExtract(){
		return new CustomDNExtract();
	}*/
	
}
