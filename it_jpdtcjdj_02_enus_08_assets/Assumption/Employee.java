package com.skillsoft.junit;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.List;

public class Employee {

    String firstName;
    String lastName;
    Integer id;
    Character gender;
    String role;
    Double salary;
    String type;
    ArrayList<String> projects;

    public Employee(String firstName, String lastName, int id,
                    char gender, String role, double salary, String type) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.gender = gender;
        this.role = role;
        this.salary = salary;
        this.type = type;
        this.projects = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getId() {
        return id;
    }

    public Character getGender() {
        return gender;
    }

    public String getRole() {
        return role;
    }

    public Double getSalary() {
        return salary;
    }

    public String getType() {
        return type;
    }

    public void adjustSalary(double adjAmount) {
        salary = salary + adjAmount;
    }

    public void validateLastName() throws Exception{

        if(!this.lastName.matches("^[a-zA-Z]*$")){
            throw new InvalidNameException("The last name can only contain alphabets");
        }
    }

    public void addProject(String projectName){
        projects.add(projectName);
    }

    public List<String> getProjects(){
        return projects;
    }
}