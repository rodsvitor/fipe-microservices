package com.fipe.api2.infrastructure.persistence;


import com.fipe.api2.domain.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "vehicles",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_vehicle_fipe_unique",
            columnNames = {"fipe_brand_id", "fipe_model_id", "category"})
    }
)
public class VehicleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long fipeBrandId;

  private Long fipeModelId;

  private String brand;

  private String code;

  private String model;

  private String observations;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

}
