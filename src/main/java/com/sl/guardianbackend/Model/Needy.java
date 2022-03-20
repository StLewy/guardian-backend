package com.sl.guardianbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Needy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String bankAccount;
  private String cityUA;
  private String cityPL;
  private String description;
  private String email;
  @OneToOne
  private Guardian guardian;
  private String status;
  private LocalDate creation;
  private LocalDate update;
}
