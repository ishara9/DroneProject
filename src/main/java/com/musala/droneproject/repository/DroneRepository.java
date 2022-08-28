package com.musala.droneproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.droneproject.model.Drone;
import com.musala.droneproject.model.DroneState;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>
{
  Optional<Drone> findDroneBySerialNumber(String serialNumber);

  Optional<List<Drone>> findAllDronesByStateIn(List<DroneState> droneStates);
}
