package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.springboot.config.ApplicationUserRole.*;
import static com.springboot.config.ApplicationUserPermission.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
//			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//			.and()
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/students/**").hasRole(STUDENT.name())
//			.antMatchers(HttpMethod.DELETE,"/manage/students/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.POST,"/manage/students/**").hasAuthority(COURSE_WRITE.getPermission())
//			.antMatchers(HttpMethod.PUT,"/manage/students/**").hasAuthority(COURSE_WRITE.getPermission())			
//			.antMatchers("/manage/students/**").hasAnyRole(ADMIN.name(),ADMIN_TRAINEE.name())
			.anyRequest()
			.authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll(); // communication exchange with server happens via Jsessionid
			//.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {

		UserDetails studentUser = User.builder()
				.username("venkat")
				.password(passwordEncoder.encode("venkat"))
//				.roles(ApplicationUserRole.STUDENT.name())//ROLE_STUDENT
				.authorities(STUDENT.getGrantedAuthorities())
				.build();

		UserDetails adminUser = User.builder()
				.username("ashi")
				.password(passwordEncoder.encode("ashi"))
//				.roles(ApplicationUserRole.ADMIN.name())//ROLE_STUDENT
				.authorities(ADMIN.getGrantedAuthorities())
				.build();
		
		UserDetails adminTraineeUser = User.builder()
				.username("vamsi")
				.password(passwordEncoder.encode("vamsi"))
//				.roles(ApplicationUserRole.ADMIN_TRAINEE.name())//ROLE_STUDENT
				.authorities(ADMIN_TRAINEE.getGrantedAuthorities())
				.build();
		
		return new InMemoryUserDetailsManager(studentUser,adminUser,adminTraineeUser);
	}

}
