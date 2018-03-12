package io.socialgamification.engine.domain.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

/**
 * 
 * @author rodrigo@eits.com.br
 * @since 22/11/2012
 * @version 1.0
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity implements IEntity<Long>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3875941859616104733L;

	/*-------------------------------------------------------------------
	 * 		 					ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	/**
	 * 
	 */
	@Column(nullable = false, updatable = false)
	protected Calendar created;
	/**
	 * 
	 */
	protected Calendar updated;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public AbstractEntity()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public AbstractEntity( long id )
	{
		this.setId( id );
	}

	/*-------------------------------------------------------------------
	 * 		 					BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@PrePersist
	protected void refreshCreated()
	{
		if ( this.getCreated() == null )
		{
			this.setCreated( Calendar.getInstance() );
		}
	}

	/**
	 * 
	 */
	@PreUpdate
	protected void refreshUpdated()
	{
		this.refreshCreated();
		this.setUpdated( Calendar.getInstance() );
	}

	/*-------------------------------------------------------------------
	 *				 	    GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	public Long getId()
	{
		return id;
	}

	/**
	 * 
	 */
	@Override
	public void setId( Long id )
	{
		this.id = id;
	}
}