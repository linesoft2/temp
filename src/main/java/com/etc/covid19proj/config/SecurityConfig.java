package com.etc.covid19proj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurity的配置类，创建密码的解密对象，设置静态资源的放行路径，定义用户登录的操作过程
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 1.返回一个需要Spring管理的密码编码对象
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决对静态资源的拦截，静态资源的根路径是/resources/static
     * @param web spring security的web对象
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/faceLog.html");
        web.ignoring().antMatchers("/faceLogin");
    }

    /**
     * authorizeRequests()
     * .antMatchers("/login.html")
     * .permitAll()
     * .anyRequest()
     * .authenticated()
     * 配置登录请求的页面地址，对所有的地址请求都需要认证
     * formLogin()
     *  .loginPage("/login.html")
     * .loginProcessingUrl("/login")
     * .defaultSuccessUrl("/main.html")
     * .permitAll()
     * 配置登录操作的控制器和页面地址，登录成功后访问的默认页面地址
     * logout().logoutUrl("/logout").logoutSuccessUrl("/login.html")
     * 注销动作访问的控制器，注销成功后返回到的页面地址
     * @param http
     * @throws
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/main.html")
                .permitAll();
        http.headers().frameOptions().sameOrigin();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html");
    }
}
