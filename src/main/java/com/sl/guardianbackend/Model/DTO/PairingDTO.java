package com.sl.guardianbackend.Model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PairingDTO {
  private int period;
  private double amount;
  private Long idNeed;
  private String statusResponse;
}
