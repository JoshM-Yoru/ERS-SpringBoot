package com.app.ERS.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.ERS.exceptions.InvalidAccess;
import com.app.ERS.models.Ticket;
import com.app.ERS.models.TicketStatus;
import com.app.ERS.models.TicketType;
import com.app.ERS.models.User;
import com.app.ERS.repositories.TicketRepository;
import com.app.ERS.repositories.TicketStatusRepository;
import com.app.ERS.repositories.TicketTypeRepository;
import com.app.ERS.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TicketService {

  private TicketRepository tRepo;

  private TicketStatusRepository tsRepo;

  private TicketTypeRepository ttRepo;

  private UserRepository uRepo;

  public Ticket createTicket(TicketType type, String description, Double amount, String email,
      String date) {

    TicketType t = ttRepo.findById(type.getTypeId()).get();
    TicketStatus ts = tsRepo.findById(1).get();
    User u = uRepo.getByEmail(email).get();

    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    LocalDate time = LocalDate.parse(date, format);

    Ticket ticket = new Ticket(t, ts, description, amount, u, time);

    return tRepo.save(ticket);

  }

  public Ticket approveDenyTicket(int manager, int id, boolean approved) {
    User approver = uRepo.findById(manager).get();

    if (approver.getType().get(0).getType().equals("MANAGER")) {

      Ticket t = tRepo.findById(id).get();

      if (!t.getStatus().get(0).getStatus().equals("PENDING")) {
        return t;
      }

      TicketStatus ts = approved ? tsRepo.findById(2).get() : tsRepo.findById(3).get();
      List<TicketStatus> status = t.getStatus();
      t.setReviewer(approver);
      t.setReviewDate(LocalDate.now());
      status.set(0, ts);

      return tRepo.save(t);

    }

    throw new InvalidAccess();

  }

  public List<Ticket> getPendingTickets(int manager) {

    User u = uRepo.findById(manager).get();

    if (u.getType().get(0).getType().equals("MANAGER")) {
      TicketStatus ts = tsRepo.findById(1).get();
      return tRepo.getTicketsByStatus(ts);
    }

    throw new InvalidAccess();

  }

  public List<Ticket> getEmployeeTickets(int id) {
    User u = uRepo.findById(id).get();

    return tRepo.getTicketsBySubmitter(u);
  }

}
