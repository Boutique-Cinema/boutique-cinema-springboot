package com.cinema.dto.member;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberJoinDTO {
  private String id;
  private String password;
  private String email;
  private String name;
  private String birth;
  private String phone;
  private int years14More;
  private int useTermsAgree;
  private int personalInfoAgree;
  private Date joinDate;
}
