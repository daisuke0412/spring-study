package com.example.demo.domain.user.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="m_department")
public class Department {
	@Id
	public Integer departmentId;
	public String departmentName;
}
