package io.socialgamification.engine.domain.entity;

import java.io.Serializable;

/**
 * 
 * @author rodrigo.p.fraga
 */
public interface IEntity<ID extends Serializable> extends Serializable
{
	/*-------------------------------------------------------------------
	 * 		 				GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	public ID getId();

	/**
	 * 
	 * @param id
	 */
	public void setId( ID id );
}