//package com.example.backniznes;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.http.HttpServletResponse;
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Value("${cors.enabled:true}")
//    private boolean corsEnabled;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        applyCors(httpSecurity)
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedResponse())
//                .and()
//                .authorizeRequests()
//                .anyRequest().permitAll();
//    }
//
//    private HttpSecurity applyCors(HttpSecurity httpSecurity) throws Exception {
//        if (corsEnabled) {
//            return httpSecurity.cors().and();
//        } else {
//            return httpSecurity;
//        }
//    }
//
//    private AuthenticationEntryPoint unauthorizedResponse() {
//        return (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//    }
//}
package com.example.backniznes.Application.Configuration;

import com.example.backniznes.Application.JwtFilter;
import com.example.backniznes.Domain.Models.AccountEntity;
import com.example.backniznes.Infrastructure.DataAccessLayer.Impl.AccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate", "/ad").permitAll()
                .antMatchers(HttpMethod.GET,"/ad/{id}").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Service
    public static class MyUserDetailsService implements UserDetailsService {

        @Autowired
        private AccountDaoImpl accountDao;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AccountEntity user = accountDao.findByLogin(username);
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
        }
    }
}