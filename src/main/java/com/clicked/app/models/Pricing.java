package com.clicked.app.models;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pricing")
public class Pricing {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "pricing", cascade = CascadeType.ALL)
  private List<Service> services;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "btnName")
  private String btnName;

  @Column(name = "color")
  private String color;
}
