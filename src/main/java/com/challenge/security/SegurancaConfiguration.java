package com.challenge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/*informa ao Spring que usaremos o Spring Security para fornecer mecanismos de segurança na web*/
@EnableWebSecurity
@EnableAuthorizationServer
/*
 * é uma anotação conveniente que permite autenticação de solicitação por meio
 * de tokens do OAuth 2.0. Normalmente, você forneceria um
 * ResourceServerConfigurer bean, mas o iniciador do Okta's Spring Boot
 * convenientemente fornece um para você
 */
@EnableResourceServer
public class SegurancaConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

   
	@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth
	                .inMemoryAuthentication()
	                 .withUser("bruno@gmail.com").password("123456").roles("ADMIN");
	    }

	 @Override
	    public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/user");
	    }
	 
	//@Override
	//public void configure(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
	//}
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		security.allowFormAuthenticationForClients();
	}
	@Bean
	/* Não criptografar no momento */
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
}