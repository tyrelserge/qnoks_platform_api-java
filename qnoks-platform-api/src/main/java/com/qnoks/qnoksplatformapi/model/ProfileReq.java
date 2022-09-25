package com.qnoks.qnoksplatformapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileReq {
  private Integer userId;
  private String profileName;
  private String profileLevel;
}
