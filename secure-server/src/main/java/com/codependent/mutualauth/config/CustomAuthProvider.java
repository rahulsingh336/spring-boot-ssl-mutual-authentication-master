package com.codependent.mutualauth.config;

import com.codependent.mutualauth.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Created by e076103 on 21-08-2018.
 */
@Component
public class CustomAuthProvider implements AuthenticationProvider {

  @Autowired
  CustomAuthenticationUserDetailsService preAuthenticatedUserDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    //if(true)
      //throw new BadCredentialsException("No pre-authenticated principal found in request.");

    UserDetails ud = this.preAuthenticatedUserDetailsService.loadUserDetails(authentication);
    PreAuthenticatedAuthenticationToken result = new PreAuthenticatedAuthenticationToken(ud, authentication.getCredentials(), ud.getAuthorities());
    result.setDetails(authentication.getDetails());
    return result;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}
