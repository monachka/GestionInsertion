package com.dame.uasz.Repository;


import java.util.List;
import java.util.Optional;

import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Notification;
import com.dame.uasz.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);

	@Query("SELECT n FROM Notification n WHERE n.user = ?1 ")
	List<Notification> findByUser1(User user);

	@Query("SELECT d FROM Demande d WHERE d.user = ?1 ")
	List<Demande> findByUser2(User user);
}


