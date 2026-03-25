package com.fipe.api2.infrastructure.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VehicleBatchRepository {

  private final EntityManager entityManager;

  @Transactional
  public void batchInsertIgnoreConflict(List<VehicleEntity> vehicles) {

    if (vehicles.isEmpty()) return;

    StringBuilder sql = new StringBuilder("""
        INSERT INTO vehicles (fipe_brand_id, fipe_model_id, brand, model, category)
        VALUES
    """);

    for (int i = 0; i < vehicles.size(); i++) {
      sql.append("(?, ?, ?, ?, ?)");
      if (i < vehicles.size() - 1) {
        sql.append(", ");
      }
    }

    sql.append("""
        ON CONFLICT ON CONSTRAINT uk_vehicle_fipe_unique DO NOTHING
    """);

    Query query = entityManager.createNativeQuery(sql.toString());

    int index = 1;

    for (VehicleEntity v : vehicles) {
      query.setParameter(index++, v.getFipeBrandId());
      query.setParameter(index++, v.getFipeModelId());
      query.setParameter(index++, v.getBrand());
      query.setParameter(index++, v.getModel());
      query.setParameter(index++, v.getCategory().name());
    }

    query.executeUpdate();
  }
}
