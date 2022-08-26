package com.musala.droneproject.drone;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>
{
   Optional<Drone> findDroneBySerialNumber(String serialNumber);
}
