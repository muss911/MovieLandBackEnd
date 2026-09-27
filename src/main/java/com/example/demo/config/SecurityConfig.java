package com.example.demo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
@Configuration
@EnableWebSecurity
public class SecurityConfig {	
		@Autowired
		private UserDetailsService userDetailsService;
		@Bean
		public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception
		{/*
			http.csrf(customizer ->customizer.disable());
			http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
			//http.formLogin(Customizer.withDefaults());
			http.httpBasic(Customizer.withDefaults());
			http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));*/
			/*Customizer<CsrfConfigurer<HttpSecurity>> csrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {

				public void customize(CsrfConfigurer<HttpSecurity> costumizer) {
					// TODO Auto-generated method stub
					costumizer.disable();
					
				}

				
			};*/
			return http
			.csrf(customizer ->customizer.disable())
			.authorizeHttpRequests(request -> request
					.requestMatchers("register","login")// autorizzate a passare
					.permitAll()
					.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.build();
		}
		// questa puoi cancellarla
		@Bean
		public UserDetailsService userDetailService()
		{
			UserDetails user1 = User
					.withDefaultPasswordEncoder()
					.username("pippo")
					.password("pippo")
					.roles("USER")
					.build();
			UserDetails user2 = User
					.withDefaultPasswordEncoder()
					.username("cane")
					.password("pippo")
					.roles("ADMIN")
					.build();
			
			return new InMemoryUserDetailsManager(user1,user2);
		}
		// fino a qui
		@Bean
		public AuthenticationProvider authenticationProvider()
		{
		    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		    provider.setUserDetailsService(userDetailsService);
		    return provider;
		}
		@Bean
		public AuthenticationManager authenticationManager (AuthenticationConfiguration config) throws Exception
		{
			return config.getAuthenticationManager();
		}


	}

	
}
