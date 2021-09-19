package com.devsuper.users.dto;

import com.devsuper.users.entities.User;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ClientDTO implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String cpf;
  private Double income;
  private Instant birthDate;
  private Integer children;

  public ClientDTO() {}

  public ClientDTO(User _user) {
    this.setBirthDate(_user.getBirthDate());
    this.setChildren(_user.getChildren());
    this.setCpf(_user.getCpf());
    this.setIncome(_user.getIncome());
    this.setName(_user.getName());
  }

  public ClientDTO(String name, String cpf, Double income, Instant birthDate, Integer children) {
    this.name = name;
    this.cpf = cpf;
    this.income = income;
    this.birthDate = birthDate;
    this.children = children;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Double getIncome() {
    return income;
  }

  public void setIncome(Double income) {
    this.income = income;
  }

  public Instant getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Instant birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getChildren() {
    return children;
  }

  public void setChildren(Integer children) {
    this.children = children;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClientDTO ClientDTO = (ClientDTO) o;
    return Objects.equals(id, ClientDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
