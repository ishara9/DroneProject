package com.musala.droneproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.droneproject.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>
{
  Optional<List<Medication>> findAllMedicationByDroneId(Long droneId);
}
