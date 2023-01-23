package com.app.ERS.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.ERS.models.Ticket;
import com.app.ERS.models.TicketStatus;
import com.app.ERS.models.TicketType;
import com.app.ERS.models.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

  List<Ticket> getTicketsBySubmitter(User u);

  List<Ticket> getTicketsBySubmitterAndType(User u, TicketType t);

  List<Ticket> getTicketsBySubmitterAndStatus(User u, TicketStatus s);

  List<Ticket> getTicketsByStatus(TicketStatus s);

}
