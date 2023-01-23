package com.app.ERS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.ERS.models.TicketStatus;

@Repository
public interface TicketStatusRepository extends JpaRepository<TicketStatus, Integer> {
}
