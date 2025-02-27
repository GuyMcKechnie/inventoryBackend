package com.booklidio.booklidio_spring_backend.Users;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long USERID;

  private String FIRSTNAME;
  private String LASTNAME;
  private String EMAIL;
  private String CELLPHONE;
  private int MARKETING;

  @JsonPropertyOrder(
    { "USERID", "FIRSTNAME", "LASTNAME", "EMAIL", "CELLPHONE", "MARKETING" }
  )
  public User() {}

  @JsonPropertyOrder(
    { "USERID", "FIRSTNAME", "LASTNAME", "EMAIL", "CELLPHONE", "MARKETING" }
  )
  public User(
    String first_name,
    String last_name,
    String email,
    String cellphone,
    int marketing
  ) {
    this.FIRSTNAME = first_name;
    this.LASTNAME = last_name;
    this.EMAIL = email;
    this.CELLPHONE = cellphone;
    this.MARKETING = marketing;
  }

  public String getFIRSTNAME() {
    return FIRSTNAME;
  }

  public void setFIRSTNAME(String first_name) {
    this.FIRSTNAME = first_name;
  }

  public String getLASTNAME() {
    return LASTNAME;
  }

  public void setLASTNAME(String last_name) {
    this.LASTNAME = last_name;
  }

  public String getEMAIL() {
    return EMAIL;
  }

  public void setEMAIL(String email) {
    this.EMAIL = email;
  }

  public String getCELLPHONE() {
    return CELLPHONE;
  }

  public void setCELLPHONE(String cellphone) {
    this.CELLPHONE = cellphone;
  }

  public int getMARKETING() {
    return MARKETING;
  }

  public void setMARKETING(int marketing) {
    this.MARKETING = marketing;
  }

  public Long getUSERID() {
    return USERID;
  }

  public void setUSERID(Long uSERID) {
    USERID = uSERID;
  }
}
