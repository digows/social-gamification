package io.socialgamification.engine.domain.entity.account;

import java.util.HashSet;
import java.util.Set;

import org.directwebremoting.annotations.DataTransferObject;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author rodrigo@eits.com.br
 * @since 24/06/2016
 * @version 1.0
 */
@DataTransferObject(type = "enum")
public enum UserRole implements GrantedAuthority
{
	/*-------------------------------------------------------------------
	 *				 		     ENUMS
	 *-------------------------------------------------------------------*/
	SUPER_ADMINISTRATOR( UserRole.SUPER_ADMINISTRATOR_VALUE ),
	PLAYER( UserRole.PLAYER_VALUE ); 	

	public static final String SUPER_ADMINISTRATOR_VALUE 	= "SUPER_ADMINISTRATOR";
	public static final String PLAYER_VALUE 				= "PLAYER";

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public final String value;
	
	/**
	 * 
	 * @param value
	 */
	private UserRole( String value )
	{
		this.value = value;
	}
	
	/*-------------------------------------------------------------------
	 *				 		     BEHAVIORS
	 *-------------------------------------------------------------------*/
	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 */
	public String getAuthority()
	{
		return this.name();
	}

	/**
	 * @return
	 */
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		authorities.add( this );

		if ( this.equals( UserRole.SUPER_ADMINISTRATOR ) )
		{
			authorities.add( UserRole.PLAYER );

		}
		
		return authorities;
	}
}