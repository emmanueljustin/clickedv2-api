package com.clicked.app.models;

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
@Table(name = "service")
public class Service {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "serviceName")
  private String serviceName;

  @Column(name = "included")
  private boolean included;

  @ManyToOne
  @JoinColumn(name = "pricing_id")
  private Pricing pricing;
}
