package com.lec.sts19_rest.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeVO {
	private Integer id;
	private String name;
	private int age;
	private int [] score;
	private double point;
	
	
	
	public Integer getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public int getAge() {
		return age;
	}



	public int[] getScore() {
		return score;
	}



	public double getPoint() {
		return point;
	}



	public EmployeeVO() {}



	public EmployeeVO(Integer id, String name, int age, int[] score, double point) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.score = score;
		this.point = point;
	}
	
	
}
