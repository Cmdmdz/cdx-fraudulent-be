package com.cdx.cdxlearningmaterials.repository;

import com.cdx.cdxlearningmaterials.repository.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsernameAndPassword(String username,String password);
    Boolean existsByUsername(String username);
    User findByUsername(String username);
    User findByUserId(Long userId);

    List<User> findAllByRole(String role);

    List<User> findAllByName(String name);


}
