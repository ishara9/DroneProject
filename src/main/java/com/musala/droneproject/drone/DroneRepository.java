package com.musala.droneproject.drone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musala.droneproject.drone.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>
{
}
