package com.react.meteo.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;



@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

      @Autowired
	private DataSource datasource;
      
  
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        /*   
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
        */ 

            auth.jdbcAuthentication().dataSource(datasource)
		.usersByUsernameQuery("select user_name as principal, password as credential, active from users where user_name=?")
		.authoritiesByUsernameQuery("select user_name as principal, role as role from userrole where user_name=?")
                .passwordEncoder(passwordEncoder())
		.rolePrefix("ROLE_")
                 ;
   
    }
//---------------------------------------------------------------    
  
@SuppressWarnings("deprecation")
@Bean
public static NoOpPasswordEncoder passwordEncoder() {
return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
}
//---------------------------------------------------------------
    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/weather/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/weather/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/weather").hasRole("ADMIN")
                //.antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                //.antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
               // .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        //ok for demo
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }*/

}
