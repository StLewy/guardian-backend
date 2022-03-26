package com.sl.guardianbackend.model;

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
public class Pairing {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int period;
  private double amount;
  @OneToOne
  private Needy needy;
  @OneToOne
  private Sponsor sponsor;
  private LocalDate creation;
  private LocalDate update;
}
