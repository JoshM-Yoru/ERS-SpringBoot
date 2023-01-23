package com.app.ERS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.ERS.models.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
