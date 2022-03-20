package com.sl.guardianbackend.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NeedyDTO {

  private String name;
  private String bankAccount;
  private String cityUA;
  private String cityPL;
  private String description;
  private String email;
  private String generateCode;
  private String statusResponese; //D - dodano konto, C - kod został już wykorzystany, K-konto zostało już wcześniej dodane
}
