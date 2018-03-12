package io.socialgamification.engine;

import javax.validation.Validator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import br.com.eits.common.application.i18n.ResourceBundleMessageSource;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.http.client.IFcmClient;
import io.socialgamification.engine.application.configuration.settings.FCMSettings;

/**
 * 
 * @author rodrigo.p.fraga
 */
@EnableAsync
//@EnableCaching
@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args )
	{
		SpringApplication.run( Application.class, args );
	}
	
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------
	 *				 		    BEHAVIORS
	 *-------------------------------------------------------------------*/

	/*-------------------------------------------------------------------
	 *							OVERRIDES
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.web.servlet.support.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application )
	{
		return application.sources( Application.class );
	}
	
	/*-------------------------------------------------------------------
	 *				 		     BEANS
	 *-------------------------------------------------------------------*/
	
	/**
	 * 
	 * @return
	 */
    @Bean
    public MessageSource messageSource() 
    {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setAlwaysUseMessageFormat( true );
        messageSource.setDefaultEncoding( "UTF-8" );
        messageSource.setBasenames( "classpath:i18n/labels", "classpath:i18n/messages" );
        return messageSource;
    }
    
    /**
     * 
     * @return
     */
    @Bean
    public Validator validator() 
    {
    		return new LocalValidatorFactoryBean();
    }
    
    /**
     * 
     * @param settings
     * @return
     */
    @Bean
    public IFcmClient fcmClient( FCMSettings settings ) 
    {
    		return new FcmClient(settings);
    }
}