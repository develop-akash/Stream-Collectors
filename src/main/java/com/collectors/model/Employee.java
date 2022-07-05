package com.collectors.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Comparable<Employee>{
    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joiningDate;
    private double salary;

    @Override
    public int compareTo(Employee o) {
        if (id < o.getId()) {
            return -1;
        } else if (id > o.getId()) {
            return 1;
        } else {
            return 0;
        }
    }
}
