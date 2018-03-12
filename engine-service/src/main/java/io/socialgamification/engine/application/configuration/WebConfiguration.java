package io.socialgamification.engine.application.configuration;

import java.util.Arrays;
import java.util.Locale;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.spring.DwrClassPathBeanDefinitionScanner;
import org.directwebremoting.spring.DwrSpringServlet;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import br.com.eits.common.application.dwr.DwrAnnotationPostProcessor;
import io.socialgamification.engine.application.configuration.settings.DWRSettings;

/**
 * 
 * @author rodrigo
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer
{
	/*-------------------------------------------------------------------
	 * 		 						BEANS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	@Bean
    public FilterRegistrationBean<ForwardedHeaderFilter> forwardedHeaderFilter() 
	{
        final FilterRegistrationBean<ForwardedHeaderFilter> filterRegBean = new FilterRegistrationBean<>();
        filterRegBean.setFilter(new ForwardedHeaderFilter());
        filterRegBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegBean;
    }

	//---------
	// Locale
	//---------
	/**
	 * 
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver()
	{
		final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale( new Locale( "pt", "BR" ) );
		localeResolver.setCookieMaxAge( 604800 ); //1 month
		return localeResolver;
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName( "lang" );
		return localeChangeInterceptor;
	}
	
	//---------
	// DWR
	//---------
	/**
	 * Process all spring beans with @RemoteProxy
	 * @return
	 */
	@Bean
	public DwrAnnotationPostProcessor dwrAnnotationPostProcessor( ApplicationContext applicationContext )
	{
		final BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
		final ClassPathBeanDefinitionScanner scanner = new DwrClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(DataTransferObject.class));
        scanner.scan("io.socialgamification.engine.domain.entity.**");
        
		return new DwrAnnotationPostProcessor();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean<DwrSpringServlet> dwrSpringServletRegistration( DWRSettings dwrSettings )
	{
		final ServletRegistrationBean<DwrSpringServlet> registration = new ServletRegistrationBean<>( new DwrSpringServlet(), "/broker/*" );
		registration.setName( "dwrSpringServlet" );
		registration.addInitParameter( "debug", dwrSettings.getDebug() );
		registration.addInitParameter( "scriptCompressed", dwrSettings.getScriptCompressed() );
		registration.addInitParameter( "generateDtoClasses", dwrSettings.getGenerateDtoClasses() );
		
		//cross-domain
		registration.addInitParameter( "crossDomainSessionSecurity", dwrSettings.getCrossDomainSessionSecurity() );
		registration.addInitParameter( "overridePath", dwrSettings.getOverridePath() );
		registration.addInitParameter( "allowGetForSafariButMakeForgeryEasier", dwrSettings.getAllowGetForSafariButMakeForgeryEasier() );
		registration.addInitParameter( "allowScriptTagRemoting", dwrSettings.getAllowScriptTagRemoting() );
		
		return registration;
	}
	
	/**
	 * DWR CORS filter
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> dwrCorsFilter( ServletRegistrationBean<DwrSpringServlet> dwrServletBean ) 
	{
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials( true );
	    config.addAllowedOrigin( CorsConfiguration.ALL );
	    config.addAllowedHeader( CorsConfiguration.ALL );
	    config.addAllowedMethod( CorsConfiguration.ALL );
	    
	    final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);
	    
	    final FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>();
	    filter.setFilter( new CorsFilter(urlBasedCorsConfigurationSource) );
	    filter.setServletRegistrationBeans( Arrays.asList(dwrServletBean) );
	    return filter;
	}

	/*-------------------------------------------------------------------
	 * 		 						OVERRIDES
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors( InterceptorRegistry registry )
	{
		registry.addInterceptor( this.localeChangeInterceptor() );
	}

	/*
	 * Global CORS filter
	 * 
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	public void addCorsMappings( CorsRegistry cors ) 
	{
		cors.addMapping("/**")
			.allowCredentials( true )
			.allowedHeaders( CorsConfiguration.ALL )
			.allowedMethods( CorsConfiguration.ALL )
			.allowedOrigins( CorsConfiguration.ALL );
	}
}