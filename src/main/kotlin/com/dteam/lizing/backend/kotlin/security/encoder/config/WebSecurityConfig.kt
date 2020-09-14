package com.dteam.lizing.backend.kotlin.security.encoder.config

import com.dteam.lizing.backend.kotlin.security.service.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

//https://habr.com/ru/company/otus/blog/488418/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    internal var userDetailsService: UserDetailsServiceImpl? = null

//    @Autowired
//    private val unauthorizedHandler: JwtAuthEntryPoint? = null

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

//    @Bean
//    fun authenticationJwtTokenFilter(): JwtAuthTokenFilter {
//        return JwtAuthTokenFilter()
//    }

    @Throws(Exception::class)
    override fun configure(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
// Don't need for API CSRF Protection
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/v1/roles/**").permitAll()
//                Some of User or Admin role
                .antMatchers("/api/v1/roles/user/**").hasAnyRole("USER", "ADMIN")
//                Only Admin role
                .antMatchers("/api/v1/roles/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/authority/read/**").hasAuthority("READ_PRIVILEGE")
//                Any Roles
                .antMatchers("/api/v1/roles/**").permitAll()
// All requests need to be authenticated
                .anyRequest().authenticated()
//                Use only basic auth
                .and().httpBasic()
//                Don't save info about users for api
                .and().sessionManagement().disable();
//        http
//            .authorizeRequests()
//            .antMatchers("/home").permitAll()
//            .anyRequest().authenticated();
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}
