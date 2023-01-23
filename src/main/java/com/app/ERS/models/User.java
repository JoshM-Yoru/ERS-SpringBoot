package com.app.ERS.models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int userId;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_type_junction", joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "type")})
  private List<UserType> type;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(unique = true)
  private String email;

  private String password;

  @OneToMany(mappedBy = "submitter", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Ticket> reimbursements;

  @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Ticket> reviewedReimbursements;

  @Override
  public String toString() {
    return "Employee [userId=" + userId + ", user type=" + type.get(0) + ", firstName=" + firstName
        + ", lastName=" + lastName + ", email=" + email + ", password=" + password
        + ", reimbursements=" + reimbursements.size() + ", reviewedReimbursements="
        + reviewedReimbursements.size() + "]";
  }

}
