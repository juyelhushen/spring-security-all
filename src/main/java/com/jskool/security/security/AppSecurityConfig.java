package com.jskool.security.security;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.jskool.security.security.UserPermission.COURSE_WRITE;
import static com.jskool.security.security.UserPermission.STUDENT_WRITE;
import static com.jskool.security.security.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails semaUser = User.builder()
                .username("sema")
                .password(passwordEncoder.encode("password"))
                //.roles(STUDENT.name())
                .authorities(STUDENT.getSimpleGrantedAuthorities())
                .build();

        UserDetails juyelUser = User.builder()
                .username("juyel")
                .password(passwordEncoder.encode("pass123"))
                //.roles(ADMIN.name())
                .authorities(ADMIN.getSimpleGrantedAuthorities())
                .build();

        UserDetails adminTrainee = User.builder()
                .username("sakil")
                .password(passwordEncoder.encode("pass123"))
               // .roles(ADMINTRAINEE.name())
                .authorities(ADMINTRAINEE.getSimpleGrantedAuthorities())
                .build();



        return new InMemoryUserDetailsManager(semaUser,juyelUser,adminTrainee);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("/","index","/css/**")
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.POST,"/management/api").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/management/api").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET,"/management/api").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated().and()
                .formLogin()
                   .loginPage("/login").permitAll()
                   .defaultSuccessUrl("/couarses",true)
                .and()
                .rememberMe()
                   .tokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(20))
                   .key("secured")
                .and()
                .logout()
                   .logoutUrl("/logout")
                   .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                   .clearAuthentication(true)
                   .invalidateHttpSession(true)
                   .deleteCookies("JSESSIONID","remember-me")
                   .logoutSuccessUrl("/login");

    }
}
