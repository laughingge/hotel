package com.example.hotel.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

@EnableWebSecurity
@Configuration
public class MyWebSecuriteConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService userDetails(){
        return new MyUserDetailsService();
    }


    /**
     * 没有passwordEncoder会抛java.lang.IllegalArgumentException:
     *      There is no PasswordEncoder mapped for the id "null"
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {

       // http.authorizeRequests()//拦截
             //   .antMatchers( "/js/**","/css/**","/img/**","/plugins/**/**/**","/plugins/**/**/**/**","/uploads/**","/ionicons/**/**,"/login").permitAll()//允许/、/home的访问
             //   .antMatchers("/Floor/**").hasAnyRole("USER")//用户USER角色的用户访问有关/user下面的所有
             //   .antMatchers("/User/**","/Emp/**","/Floor/**").hasAnyRole("ADMIN")//同上
                //.anyRequest().authenticated()//其它所有访问都拦截
             //   .and()
             //   .formLogin()//添加登陆
               // .loginPage("/login").permitAll()//登陆页面“/login"允许访问
             //   .and()
             //   .logout().logoutUrl("/logout")
             //   .logoutSuccessUrl("/login")
             //   .permitAll();//同上
     //   http.csrf().disable();
        http.authorizeRequests()//配置安全策略
                .antMatchers("/login","/uploads/**","/encode").permitAll()//定义/请求不需要验证
                //.antMatchers("/Emp/**").hasAnyRole("USER")
                .antMatchers("/Emp/**","/chart").hasAnyRole("ADMIN")
                .anyRequest().authenticated()//其余的所有请求都需要验证r
                .and()
                .formLogin()
               // .loginPage("/login").permitAll()
                .and()
                .logout()
                .permitAll();//定义logout不需要验证


        http.csrf().disable();


    }

    //@Override
   // public void configure(WebSecurity web) throws Exception {
//解决静态资源被拦截的问题
      //  web.ignoring().antMatchers("/plugins/**/**/**");
   // }

}