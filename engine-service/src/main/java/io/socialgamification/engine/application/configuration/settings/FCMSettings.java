package io.socialgamification.engine.application.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import lombok.Data;

/**
 * 
 * @author rodrigo.p.fraga
 */
@Data
@Component
@ConfigurationProperties(prefix = "fcm")
public class FCMSettings implements IFcmClientSettings
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	private String apiKey;
	/**
	 * 
	 */
	private String url;

	/*-------------------------------------------------------------------
	 *				 		   GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see de.bytefish.fcmjava.http.options.IFcmClientSettings#getApiKey()
	 */
	@Override
	public String getApiKey()
	{
		return this.apiKey;
	}

	/*
	 * (non-Javadoc)
	 * @see de.bytefish.fcmjava.http.options.IFcmClientSettings#getFcmUrl()
	 */
	@Override
	public String getFcmUrl()
	{
		return this.url;
	}
}