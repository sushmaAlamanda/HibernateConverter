
/**
 * Created For 
 * Testing Hibernate
 */


package com.ramersoft.pos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity(name="people")
@Table(name="people")
public class Student {
     
    @Id
    @GeneratedValue
    private Integer id;     
    private String first_name;
    private String last_name;
    private Integer age;
    //private GenderEnum gender;
    
    public Student() {};
     
   
    public Student(Integer id, String firstName,String lastName, Integer age) {
        this.id = id;
       // this.first_name = firstName;
        //this.last_name = lastName;
        this.age = age;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
     

}