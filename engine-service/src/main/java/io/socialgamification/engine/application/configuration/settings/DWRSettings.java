package io.socialgamification.engine.application.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 
 * @author rodrigo
 */
@Data
@Component
@ConfigurationProperties(prefix="dwr")
public class DWRSettings 
{
	/*-------------------------------------------------------------------
	 * 		 					SETTINGS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
    private String debug = "false";
    /**
     * 
     */
    private String scriptCompressed = "true";
    /**
     * 
     */
    private String crossDomainSessionSecurity = "true";
    /**
     * 
     */
    private String overridePath = null;
    /**
     * 
     */
    private String generateDtoClasses = "dtoall";
    /**
     * 
     */
    private String allowGetForSafariButMakeForgeryEasier = "false";
    /**
     * 
     */
    private String allowScriptTagRemoting = "false";
}