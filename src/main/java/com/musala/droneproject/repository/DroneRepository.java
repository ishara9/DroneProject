package com.musala.droneproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.droneproject.model.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>
{
   Optional<Drone> findDroneBySerialNumber(String serialNumber);
}
