package io.socialgamification.engine.domain.repository.account;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.socialgamification.engine.domain.entity.account.User;

/**
 * 
 * @author rodrigo@eits.com.br 
 * @since 22/04/2014
 * @version 1.0
 * @category Repository
 */
public interface IUserRepository extends JpaRepository<User, Long>
{
	/**
	 * @param username
	 * @return
	 */
	public Optional<User> findByEmail(String email);
	
	/**
	 * @param filter
	 * @param pageable
	 * @return
	 */
	@Query(value="FROM User user " +
				  "WHERE ( FILTER(user.id, :filter) = TRUE "
				  	 + "OR FILTER(user.name, :filter) = TRUE "
				  	 + "OR FILTER(user.email, :filter) = TRUE )" )
	public Page<User> listByFilters( @Param("filter") String filter, Pageable pageable );

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Override
	@CacheEvict(cacheNames="authentications", key="#p0.email")
	public <S extends User> S save( S entity );
}
