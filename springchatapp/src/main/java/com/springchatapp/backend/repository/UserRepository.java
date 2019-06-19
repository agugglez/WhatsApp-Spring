package com.springchatapp.backend.repository;

import com.springchatapp.backend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.swing.text.html.Option;
import java.util.Optional;
//import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByPhoneString(String phoneString);
}
