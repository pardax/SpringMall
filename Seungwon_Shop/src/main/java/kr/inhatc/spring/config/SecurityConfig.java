package kr.inhatc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		//http.csrf().disable();
		http.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/")
			.usernameParameter("email")
			//.passwordParameter("pw") 
			//만약 내가 폼에서 네임 값을 pw로 했을 경우, 파라미터를 전달 할 필요가 있음
			.failureUrl("/member/login/error")
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/");
		
		
		http.authorizeRequests()
			.mvcMatchers("/css/**", "/js/**").permitAll()
			.mvcMatchers("/", "/member/**", "/item/**").permitAll()
			.mvcMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated();
		
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomEntryPoint());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
