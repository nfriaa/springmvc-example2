package net.isetjb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // to allow connect to h2-console (bug page blanche après login !!) :
        http.csrf().disable();
        http.headers().frameOptions().disable();

        // webjars :
        //http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        // resources :
        //http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
        // espace publique :
        //http.authorizeRequests().antMatchers("/", "/index").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").permitAll();

        // espace sécurisé :
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .inMemoryAuthentication()
                .withUser("demo").password("demo").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");

    }

    /**
     * permet quelques urls et les autres auth
     */
    protected void configure_version1(HttpSecurity http) throws Exception
    {

        // to allow connect to h2-console (bug page blanche après login !!) :
        http.csrf().disable();
        http.headers().frameOptions().disable();

        // webjars :
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        // resources :
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();

        // espace publique :
        http.authorizeRequests().antMatchers("/", "/index").permitAll();

        // espace sécurisé :
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

    }
}
