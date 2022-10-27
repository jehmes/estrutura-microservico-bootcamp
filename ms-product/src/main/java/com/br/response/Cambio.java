package com.br.response;

import java.io.Serializable;

public class Cambio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String from;
	private String to;
	private Float conversionFactor;
	private Float convertedValue;
	private String enviroment;

	public Cambio() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}


	public Float getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Float conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	public Float getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Float convertedValue) {
		this.convertedValue = convertedValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		this.enviroment = enviroment;
	}
}
