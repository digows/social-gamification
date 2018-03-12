package io.socialgamification.engine.application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.socialgamification.engine.application.security.AuthenticationFailureHandler;
import io.socialgamification.engine.application.security.AuthenticationSuccessHandler;

/**
 * @author rodrigo
 *
 */
@Configuration
public class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter
{
	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
    @Autowired
    private UserDetailsService userDetailsService;
    
	/*-------------------------------------------------------------------
	 * 		 						BEANS
	 *-------------------------------------------------------------------*/	
	//---------
	// Beans
	//---------
    /**
	 * 
	 * @return
	 */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() 
    {
        return new AuthenticationFailureHandler();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() 
    {
    		return new AuthenticationSuccessHandler();
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() 
    {
    		return new BCryptPasswordEncoder();
    }
    
	/*-------------------------------------------------------------------
	 * 		 					 OVERRIDES
	 *-------------------------------------------------------------------*/
	//---------
	// Authentication Manager
	//---------
	/**
	 * 
	 */
	@Override
	public void init( AuthenticationManagerBuilder builder ) throws Exception
	{
		builder
			.userDetailsService( this.userDetailsService )
			.passwordEncoder( this.passwordEncoder() );
	}
}