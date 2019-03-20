package com.codependent.mutualauth.config;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by e076103 on 21-08-2018.
 */

@Component
public class CustomAuthenticationUserDetailsService implements AuthenticationUserDetailsService {

  @Override
  public UserDetails loadUserDetails(Authentication token)
      throws UsernameNotFoundException {

    return this.createUserDetails(token, token.getAuthorities());
  }

  protected UserDetails createUserDetails(Authentication token, Collection<? extends GrantedAuthority> authorities) {
    return new User(token.getName(), "N/A", true, true, true, true, authorities);
  }
}