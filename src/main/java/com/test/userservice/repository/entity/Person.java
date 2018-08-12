package com.test.userservice.repository.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person {

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id
  private Long id;

  @Column(name = "person_id")
  private String personId;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "birth_date")
  LocalDate birthDate;

  @Column(name = "email", unique = true)
  String email;


}
