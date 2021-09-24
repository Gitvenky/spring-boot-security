package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				.anyRequest().authenticated().and().httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails studentUser = User.builder()
				.username("venkat")
				.password(passwordEncoder.encode("venkat"))
				.roles(ApplicationUserRole.STUDENT.name())
				.build();

		UserDetails adminUser = User.builder()
				.username("ashi")
				.password(passwordEncoder.encode("ashi123"))
				.roles(ApplicationUserRole.ADMIN.name())
				.build();
		
		return new InMemoryUserDetailsManager(studentUser,adminUser);
	}

}
