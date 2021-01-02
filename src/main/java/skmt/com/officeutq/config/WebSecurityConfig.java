package skmt.com.officeutq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		// セキュリティ設定を無視するリクエスト設定
		// 静的リソースに対するアクセスはセキュリティ設定を無視する
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				// アクセス権限の設定
				// アクセス制限の無いURL
				.antMatchers("/", "/login", "/loginFailure", "/error").permitAll()
				// その他は認証済みであること
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				// ログイン画面
				.loginPage("/login")
				// 認証処理
				.loginProcessingUrl("/authenticate")
				// ログイン成功
				.defaultSuccessUrl("/menu", true)
				// ログイン失敗
				.failureUrl("/loginFailure")
				// usernameのパラメータ名
				.usernameParameter("username")
				// passwordのパラメータ名
				.passwordParameter("password")
				.and()
			.logout()
				// ログアウト処理
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				// ログアウト成功時の遷移先
				.logoutSuccessUrl("/login")
				// ログアウト時に削除するクッキー名
                .deleteCookies("JSESSIONID")
                // ログアウト時のセッション破棄を有効化
                .invalidateHttpSession(true)
                .permitAll()
                .and()
            .sessionManagement()
            	// セッションが無効な時の遷移先
                .invalidSessionUrl("/invalidSession");
	}
	
    @Autowired
    protected void config(AuthenticationManagerBuilder auth) throws Exception {
    	
        // パスワード
        String userPass = passwordEncoder().encode("user");
        String adminPass = passwordEncoder().encode("d");

        // インメモリの認証を行うための設定
        auth
        	.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("user").password(userPass).roles("USER")
        		.and()
//            .inMemoryAuthentication()
            	.passwordEncoder(passwordEncoder())
            	.withUser("d").password(adminPass).roles("ADMIN", "USER");

//        auth
//            .inMemoryAuthentication()
//                .withUser(
//                        User.withDefaultPasswordEncoder()
//                        .username("foo").password("foopass").roles("USER").build())
//                        .and()
//            .inMemoryAuthentication()
//                .withUser(
//                        User.withDefaultPasswordEncoder()
//                        .username("bar").password("barpass").roles("ADMIN", "USER").build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
