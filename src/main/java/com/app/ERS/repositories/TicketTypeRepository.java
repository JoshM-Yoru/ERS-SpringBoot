package com.app.ERS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.ERS.models.TicketType;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Integer> {
}
