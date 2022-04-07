package com.tienda;

import com.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userDetarilsService;
    
    @Bean 
    public BCryptPasswordEncoder psswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    //El siguiente metodo para hcer autenticacion del usuario
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception{
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //}
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123")
                .roles("ADMIN", "VENDEDOR", "USER")
                .and()
                .withUser("vendedor")
                .password("{noop}123")
                .roles("ADMIN", "VENDEDOR", "USER")
                .and()
                .withUser("user")
                .password("{noop}123")
                .roles("USER");
    
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("crear")
                .hasRole("ADMIN")
                .antMatchers("/articulo/listado", "/categoria/listado", "cliente/listado")
                .hasAnyRole("ADMIN, VENDEDOR")
                .antMatchers("/")
                .hasAnyRole("USER", "VENDEDOR", "ADMIN")
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
    }
    
}
