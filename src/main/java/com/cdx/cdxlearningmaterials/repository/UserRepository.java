package com.cdx.cdxlearningmaterials.repository;

import com.cdx.cdxlearningmaterials.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsernameAndPassword(String username,String password);
    Boolean existsByUsername(String username);
}
