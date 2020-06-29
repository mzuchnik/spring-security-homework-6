package pl.mzuchnik.springsecurityhomework6.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.mzuchnik.springsecurityhomework6.config.filter.JwtFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/cars").hasAnyRole("USER", "ADMIN")
                .and()
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable();
    }
}
