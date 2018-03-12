package io.socialgamification.engine.domain.entity.account;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.Param;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.socialgamification.engine.domain.entity.AbstractEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author rodrigo.p.fraga
 */
@Data
@Entity
@ToString
@Table(name="\"USER\"")
@EqualsAndHashCode( callSuper = true )
@DataTransferObject(javascript = "User", params = @Param(name="exclude", value="enabled") )
public class User extends AbstractEntity implements Serializable, UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4052986759552589018L;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String name;
	
	/**
	 * 
	 */
	@Column(nullable = true, length = 200)
	private String deviceToken;
	
	/**
	 * 
	 */
	@Email
	@NotNull
	@Column(nullable = false, length = 150, unique = true)
	private String email;
	
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean disabled;
	
	/**
	 * 
	 */
	@NotBlank
	@Length(min = 8)
	@Column(nullable = false, length = 100)
	private String password;
	
	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;
	
	/**
	 * 
	 */
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] image;
    
	/**
	 * 
	 */
	@Basic
	@Setter(value=AccessLevel.NONE)
	private String recoveryToken;
	
	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public User()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public User( long id )
	{
		super( id );
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param enabled
	 * @param role
	 * @param password
	 */
	public User( Long id, String name, String email, Boolean disabled, UserRole role, String password )
	{
		super( id );
		this.email = email;
		this.name = name;
		this.disabled = disabled;
		this.password = password;
		this.role = role;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public void generateRecoveryToken()
	{
		this.recoveryToken = UUID.randomUUID().toString();
	}
	
	/**
	 * 
	 */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( this.role == null )
		{
			return null;
		}
		
		authorities.addAll( this.role.getAuthorities() );

		return authorities;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isEnabled()
	{
		return !this.disabled;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	@Transient
	public String getPassword()
	{
		return this.password;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	@Transient
	public String getUsername()
	{
		return this.email;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
}