//package io.socialgamification.engine.domain.entity.post;
//
//import java.io.Serializable;
//
//import javax.persistence.Cacheable;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotNull;
//
//import io.socialgamification.engine.domain.entity.customer.notification.NotificationConfiguration;
//import io.socialgamification.engine.domain.entity.customer.player.Avatar;
//import io.socialgamification.engine.domain.entity.customer.player.reward.Asset;
//import io.socialgamification.engine.domain.entity.customer.player.reward.Earning;
//import io.socialgamification.engine.domain.entity.AbstractEntity;
//import io.socialgamification.engine.domain.entity.account.User;
//import io.socialgamification.engine.domain.entity.customer.notification.INotificable;
//import io.socialgamification.engine.domain.entity.customer.player.reward.AbstractReward;
//import io.socialgamification.engine.domain.entity.customer.player.reward.Level;
//
//import org.directwebremoting.annotations.DataTransferObject;
//import org.hibernate.annotations.Any;
//import org.hibernate.annotations.AnyMetaDef;
//import org.hibernate.annotations.Formula;
//import org.hibernate.annotations.MetaValue;
//import org.hibernate.validator.constraints.NotEmpty;
//
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.Setter;
//
///**
// * 
// * @author rodrigo.p.fraga
// */
//@Data
//@Entity
//@Cacheable
//@EqualsAndHashCode( callSuper = true )
//@DataTransferObject(javascript = "Post")
//public class Post extends AbstractEntity implements Serializable, INotificable, IReactable
//{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 2340075212020037013L;
//
//	/*-------------------------------------------------------------------
//	 *				 		     ATTRIBUTES
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 */
//	@NotEmpty
//	@Column(nullable=false, length=500)
//	private String description;
//	
//	/**
//	 * 
//	 */
//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	private Avatar avatar;
//	
//	/**
//	 * Maps one column (type and id) to any entities that implements IPostable.
//	 */
//	@NotNull
//    @JoinColumn(name = "postable_id", nullable=false)
//	@Any(metaColumn=@Column(name = "postable_type"), fetch=FetchType.LAZY, optional=false)
//	@AnyMetaDef(idType="long", metaType="string", metaValues={
//		@MetaValue(value = "ASSET", targetEntity = Asset.class),
//		@MetaValue(value = "EARNING", targetEntity = Earning.class),
//		@MetaValue(value = "LEVEL", targetEntity = Level.class)
//	})
//	private IPostable postable;
//	
//	/**
//	 * 
//	 */
//	@Setter(value=AccessLevel.NONE)
//	@Column(name = "postable_id", insertable=false, updatable=false)
//	private Long postableId;
//	
//	/**
//	 * 
//	 */
//	@Setter(value=AccessLevel.NONE)
//	@Column(name = "postable_type", insertable=false, updatable=false)
//	private String postableType;
//	
//	/**
//	 * 
//	 */
//	@Setter(value=AccessLevel.NONE)
//    @Formula(
//    		"(SELECT COUNT(comment.id) "
//    	   + "FROM comment AS comment "
//    	   + "LEFT OUTER JOIN post AS post ON post.id = comment.post_id "
//    	   + "WHERE post.id = id)"
//    	)
//    private int commentsCount;
//	
//	/**
//	 * 
//	 */
//	@Setter(value=AccessLevel.NONE)
//    @Formula(
//    		"(SELECT COUNT(reaction.id) "
//    	   + "FROM reaction AS reaction "
//    	   + "LEFT OUTER JOIN post AS post ON post.id = reaction.reactable_id "
//    	   + "WHERE reaction.reactable_type = 'POST' AND post.id = id)"
//    	)
//    private int reactionsCount;
//	
//	/**
//	 * Count of shares maintained by the app 
//	 */
//    private Integer sharesCount;
//	
//	/*-------------------------------------------------------------------
//	 * 		 					CONSTRUCTORS
//	 *-------------------------------------------------------------------*/
//	/**
//	 * 
//	 */
//	public Post()
//	{
//	}
//
//	/**
//	 * 
//	 * @param id
//	 */
//	public Post( long id )
//	{
//		super( id );
//	}
//	
//	/**
//	 * 
//	 * @param reward
//	 * @return
//	 */
//	public static Post of( AbstractReward<?> reward )//should be a IPostable?
//	{
//		final Post post = new Post();
//		post.setAvatar( reward.getAvatar() );
//		post.setPostable( reward );
//		post.setDescription( reward.getPostDescription() );
//		return post;
//	}
//
//	/*-------------------------------------------------------------------
//	 *							 BEHAVIORS
//	 *-------------------------------------------------------------------*/
//	/*
//	 * (non-Javadoc)
//	 * @see INotificable#getNotificationTitle()
//	 */
//	@Override
//	public String getNotificationTitle()
//	{
//		if ( this.getPostable() != null )
//		{
//			return this.getPostable().getNotificationTitle();
//		}
//		
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see INotificable#getNotificationBody()
//	 */
//	@Override
//	public String getNotificationBody()
//	{
//		if ( this.getPostable() != null )
//		{
//			return this.getPostable().getNotificationBody();
//		}
//		
//		return null;
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 * @see INotificable#getNotificationType()
//	 */
//	@Override
//	public String getNotificationType()
//	{
//		if ( this.getPostable() != null )
//		{
//			return this.getClass().getSimpleName().toLowerCase()+"."+this.getPostable().getNotificationType();
//		}
//		else
//		{
//			return this.getClass().getSimpleName().toLowerCase();
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see INotificable#getNotificationConfiguration()
//	 */
//	@Override
//	public NotificationConfiguration getNotificationConfiguration()
//	{
//		if ( this.getPostable() != null )
//		{
//			return this.getPostable().getNotificationConfiguration();
//		}
//		
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * @see INotificable#getOwner()
//	 */
//	@Override
//	public User getOwner()
//	{
//		if ( this.getAvatar() != null )
//		{
//			return this.getAvatar().getUser();
//		}
//		
//		return null;
//	}
//}