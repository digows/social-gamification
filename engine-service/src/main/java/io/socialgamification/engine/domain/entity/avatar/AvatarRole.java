//package io.socialgamification.engine.domain.entity.avatar;
//
//import java.io.Serializable;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.OneToMany;
//
//import org.directwebremoting.annotations.DataTransferObject;
//import org.hibernate.validator.constraints.NotEmpty;
//
//import io.socialgamification.engine.domain.entity.AbstractEntity;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.ToString;
//
///**
// * 
// * @author rodrigo.p.fraga
// */
//@Data
//@Entity
//@ToString(exclude = {"avatars"})
//@DataTransferObject(javascript = "AvatarRole")
//@EqualsAndHashCode( callSuper = true, exclude={"avatars"} )
//public class AvatarRole extends AbstractEntity implements Serializable
//{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -310722075300073987L;
//	
//	/*-------------------------------------------------------------------
//	 *				 		     ATTRIBUTES
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 */
//	@NotEmpty
//	@Column(nullable = false, length = 100)
//	private String name;
//	
//	/**
//	 * 
//	 */
//    @OneToMany(mappedBy="role", fetch=FetchType.LAZY)
//    private Set<Avatar> avatars;
//
//	/*-------------------------------------------------------------------
//	 * 		 					CONSTRUCTORS
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 */
//	public AvatarRole()
//	{
//	}
//
//	/**
//	 * 
//	 * @param id
//	 */
//	public AvatarRole( long id )
//	{
//		super( id );
//	}
//
//	/*-------------------------------------------------------------------
//	 *						GETTERS AND SETTERS
//	 *-------------------------------------------------------------------*/
//}