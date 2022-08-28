package com.musala.droneproject.model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Medication
{
  @Id
  @SequenceGenerator(
      name = "medication_sequence",
      sequenceName = "medication_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "medication_sequence"
  )
  @Column( updatable = false )
  private Long id;

  private String name;

  private Double weight;

  private String code;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "medication_id", referencedColumnName = "id")
  @JsonIgnore
  private Drone drone;

  @Lob
  @Column
  private byte[] image;

  public Medication()
  {
  }

  public Medication(Long id, String name, Double weight, String code, byte[] image)
  {
    this.id = id;
    this.name = name;
    this.weight = weight;
    this.code = code;
    this.image = image;
  }

  public Medication(String name, Double weight, String code, byte[] image)
  {
    this.name = name;
    this.weight = weight;
    this.code = code;
    this.image = image;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Double getWeight()
  {
    return weight;
  }

  public void setWeight(Double weight)
  {
    this.weight = weight;
  }

  public String getCode()
  {
    return code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public byte[] getImage()
  {
    return image;
  }

  public void setImage(byte[] image)
  {
    this.image = image;
  }

  public Drone getDrone()
  {
    return drone;
  }

  public void setDrone(Drone drone)
  {
    this.drone = drone;
  }

  @Override
  public String toString()
  {
    return "Medication{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", weight=" + weight + ", code='" + code
        + '\'' + ", image=" + Arrays.toString(image) + '}';
  }

}
