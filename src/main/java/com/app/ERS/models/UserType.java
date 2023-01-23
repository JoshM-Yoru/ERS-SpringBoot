package com.app.ERS.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserType {

  @Id
  @Column(name = "role_id")
  private Integer roleId;

  @Column(unique = true)
  private String role;
}
