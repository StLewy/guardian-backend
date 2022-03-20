package com.sl.guardianbackend.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GuardianDTO {
  private String name;
  private String surname;
  private String phone;
  private String email;
  private String responseStatus; //E- exist C - create;
}
