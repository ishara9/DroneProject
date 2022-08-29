package com.musala.droneproject.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
    uniqueConstraints =
        {
            @UniqueConstraint(name = "serial_number_unique", columnNames = "serialNumber")
        }
)
public class Drone
{
  @Id
  @SequenceGenerator(
      name = "drone_sequence",
      sequenceName = "drone_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "drone_sequence"
  )
  @Column( updatable = false )
  private Long id;

  @Column(columnDefinition = "VARCHAR(100) NOT NULL")
  private String serialNumber;

  @Enumerated(EnumType.ORDINAL)
  private DroneModel model;

  private Double weightLimit;

  private Integer batteryCapacity;

  @Enumerated(EnumType.ORDINAL)
  private DroneState state;

  @OneToMany(mappedBy = "drone")
  private Set<Medication> medications;

  public Drone()
  {
  }

  public Drone(Long id,
               String serialNumber,
               DroneModel model,
               Double weightLimit,
               Integer batteryCapacity,
               DroneState state)
  {
    this.id = id;
    this.serialNumber = serialNumber;
    this.model = model;
    this.weightLimit = weightLimit;
    this.batteryCapacity = batteryCapacity;
    this.state = state;
  }

  public Drone(String serialNumber,
               DroneModel model,
               Double weightLimit,
               Integer batteryCapacity,
               DroneState state)
  {
    this.serialNumber = serialNumber;
    this.model = model;
    this.weightLimit = weightLimit;
    this.batteryCapacity = batteryCapacity;
    this.state = state;
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getSerialNumber()
  {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber)
  {
    this.serialNumber = serialNumber;
  }

  public DroneModel getModel()
  {
    return model;
  }

  public void setModel(DroneModel model)
  {
    this.model = model;
  }

  public Double getWeightLimit()
  {
    return weightLimit;
  }

  public void setWeightLimit(Double weightLimit)
  {
    this.weightLimit = weightLimit;
  }

  public Integer getBatteryCapacity()
  {
    return batteryCapacity;
  }

  public void setBatteryCapacity(Integer batteryCapacity)
  {
    this.batteryCapacity = batteryCapacity;
  }

  public DroneState getState()
  {
    return state;
  }

  public void setState(DroneState state)
  {
    this.state = state;
  }

  public Set<Medication> getMedications()
  {
    return medications;
  }

  public void setMedications(Set<Medication> medications)
  {
    this.medications = medications;
  }

  @Override
  public String toString()
  {
    return "Drone{" + "id=" + id + ", serialNumber='" + serialNumber + '\'' + ", model=" + model + ", weightLimit="
        + weightLimit + ", batteryCapacity=" + batteryCapacity + ", state=" + state +'}';
  }
}
