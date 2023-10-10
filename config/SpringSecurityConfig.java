package com.example.todo_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//this class becomes spring java based configuration within this class we can define the spring bean
public class SpringSecurityConfig {
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    to authorize all the incoming http request
//    we use authorizeHttpRequest t authorize all the http request
//    **********Here we have configured the spring security in such a way that we have only enabled the http basic authentication*****************/
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.csrf((csrf)->csrf.disable()).authorizeHttpRequests((authorize) ->authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
                return http.build();
    }
    //Here lets create a spring bean within that we create a multiple users and store them in a in-memory object
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails niraj= User.builder()
                .username("niraj")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin=User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
//        let us store these 2 objects in a spring security provided in-memory object
        return new InMemoryUserDetailsManager(niraj,admin);
    }
}
