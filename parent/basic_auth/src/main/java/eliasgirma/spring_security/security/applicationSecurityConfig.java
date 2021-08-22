package eliasgirma.spring_security.security;

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

import static eliasgirma.spring_security.security.ApplicationUserRole.*;
import static eliasgirma.spring_security.security.applicationUserPermission.COURSE_WRITE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class applicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public applicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //i want to secure the api/v1/student resource only the student have this resources

        http    .csrf().disable()
                .authorizeRequests()
                .antMatchers("/","index")//this one is white listed not requierd authentication
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE,"/managment/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST,"/managment/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT,"/managment/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers("/managment/api/**").hasAnyRole(ADMIN.name(),ADMINTRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails elias= User.builder()
                .username("elias")
                .password(passwordEncoder.encode("password"))
               // .roles(STUDENT.name())
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails admin=User.builder()
                .username("girma")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMIN.name() )
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails admintrainee=User.builder()
                .username("teshome")
                .password(passwordEncoder.encode("password"))
                //.roles(ADMINTRAINEE.name() )
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                elias,admin,admintrainee
        );
    }
}
