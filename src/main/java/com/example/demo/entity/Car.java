package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_license_number", unique = true)
    private String carLicenseNumber;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "base_price")
    private double basePrice;

    @Column(name = "pph")
    private double pph;

    @Column(name = "security_deposit")
    private double securityDeposit;
    
   

	public Car() 
	{
		
	}

	public Car(Long id, String carLicenseNumber, String manufacturer, String model, double basePrice, double pph,
			double securityDeposit) {
		this.id = id;
		this.carLicenseNumber = carLicenseNumber;
		this.manufacturer = manufacturer;
		this.model = model;
		this.basePrice = basePrice;
		this.pph = pph;
		this.securityDeposit = securityDeposit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarLicenseNumber() {
		return carLicenseNumber;
	}

	public void setCarLicenseNumber(String carLicenseNumber) {
		this.carLicenseNumber = carLicenseNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getPph() {
		return pph;
	}

	public void setPph(double pph) {
		this.pph = pph;
	}

	public double getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", carLicenseNumber=" + carLicenseNumber + ", manufacturer=" + manufacturer
				+ ", model=" + model + ", basePrice=" + basePrice + ", pph=" + pph + ", securityDeposit="
				+ securityDeposit + "]";
	}


}


