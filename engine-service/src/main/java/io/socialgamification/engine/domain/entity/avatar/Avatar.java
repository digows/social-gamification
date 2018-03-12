//package io.socialgamification.engine.domain.entity.avatar;
//
//import java.io.Serializable;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//
//import io.socialgamification.engine.domain.entity.global.integration.ConnectorUser;
//import io.socialgamification.engine.domain.entity.AbstractEntity;
//import io.socialgamification.engine.domain.entity.account.User;
//
//import org.directwebremoting.annotations.DataTransferObject;
//import org.hibernate.annotations.Formula;
//
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.Setter;
//import lombok.ToString;
//
///**
// * 
// * @author rodrigo.p.fraga
// */
//@Data
//@Entity
//@DataTransferObject(javascript = "Avatar")
//@ToString(exclude = {"connectorUsers"})
//@Table(uniqueConstraints={
//	@UniqueConstraint(columnNames={"team_id", "user_id", "role_id"})
//})
//@EqualsAndHashCode(callSuper = true, exclude = {"connectorUsers"})
//public class Avatar extends AbstractEntity implements Serializable
//{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 6951607827275898504L;
//
//	/*-------------------------------------------------------------------
//	 *				 		     ATTRIBUTES
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 */
//	@NotNull
//	private Boolean lastSeen;
//	
//	/**
//	 * 
//	 */
//	@NotNull
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	private Team team;
//	
//	/**
//	 * 
//	 */
//	@NotNull
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	private User user;
//	
//	/**
//	 * 
//	 */
//	@NotNull
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	private AvatarRole role;
//	
//	/**
//	 * 
//	 */
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "connector_user_avatar", joinColumns = {
//			@JoinColumn(name = "avatar_id", nullable = false, updatable = false) 
//		}, 
//		inverseJoinColumns = {
//			@JoinColumn(name = "connector_user_id", nullable = false, updatable = false) 
//	})
//	private Set<ConnectorUser> connectorUsers = new HashSet<>();
//
//	//------------
//	/**
//	 *
//	 */
//	@Setter(value=AccessLevel.NONE)
//    @Formula(
//    		"(SELECT COALESCE(SUM(reward.amount), 0) "
//    		 + "FROM reward AS reward "
//   + "INNER JOIN rule_expression AS rule_expression ON reward.expression_id = rule_expression.id "
//    	    + "WHERE reward.avatar_id = id AND reward.type = 'Earning' AND rule_expression.earning_type = 'POINT')"
//    	)
//    private int points;
//    
//	/**
//	 *
//	 */
//	@Setter(value=AccessLevel.NONE)
//    @Formula(
//    		"(SELECT COALESCE(SUM(reward.amount), 0) "
//    		 + "FROM reward AS reward "
//   + "INNER JOIN rule_expression AS rule_expression ON reward.expression_id = rule_expression.id "
//    	    + "WHERE reward.avatar_id = id AND reward.type = 'Earning' AND rule_expression.earning_type = 'XP')"
//    	)
//    private int XPs;
//    
//	/**
//	 *
//	 */ 
//	@Setter(value=AccessLevel.NONE)
//    @Formula(
//    		"(SELECT COALESCE(SUM(reward.amount), 0) "
//    		 + "FROM reward AS reward "
//   + "INNER JOIN rule_expression AS rule_expression ON reward.expression_id = rule_expression.id "
//    	    + "WHERE reward.avatar_id = id AND reward.type = 'Earning' AND rule_expression.earning_type = 'GOLD')"
//    	)
//    private int golds;
//	
//	/**
//	 * 
//	 */
//	@Setter(value=AccessLevel.NONE)
//    @Formula(
//    		"(SELECT MAX(rule_expression.index) "
//    		 + "FROM reward AS reward "
//   + "INNER JOIN rule_expression AS rule_expression ON reward.expression_id = rule_expression.id "
//    	    + "WHERE reward.avatar_id = id AND reward.type = 'Level')"
//    	)
//    private Short level;
//	
//	/**
//	 * FIXME The @Formula is bugging when we use SUBSELECT, so the fix were create a view named avatar_ranking.  
//	 */
//	@Setter(value=AccessLevel.NONE)
//	@Formula(
//		"(SELECT avatar_ranking.ranking "
//		 + "FROM avatar_ranking AS avatar_ranking "
//		+ "WHERE avatar_ranking.avatar_id = id)"
//	)
//	private Short ranking;
//
//	/*-------------------------------------------------------------------
//	 * 		 					CONSTRUCTORS
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 */
//	public Avatar()
//	{
//	}
//
//	/**
//	 * 
//	 * @param id
//	 */
//	public Avatar( long id )
//	{
//		super( id );
//	}
//
//	/*-------------------------------------------------------------------
//	 *							BEHAVIORS
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 * @return
//	 */
//	public String getName()
//	{
//		if ( this.getUser() != null )
//		{
//			return this.getUser().getName();
//		}
//		
//		return null;
//	}
//}