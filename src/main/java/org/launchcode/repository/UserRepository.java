package org.launchcode.repository;

import org.launchcode.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUserName(String username);

}
