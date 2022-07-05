package com.collectors.customspliterator;

import com.collectors.model.Employee;

import java.sql.Date;
import java.util.Spliterator;
import java.util.function.Consumer;

public class EmployeeSpliterator implements Spliterator<Employee> {

    private int id;
    private String name;
    private char gender;
    private Date dob;
    private String city;
    private String designation;
    private Date joiningDate;
    private double salary;
    private Spliterator<String> textSpiSpliterator;

    public EmployeeSpliterator(Spliterator<String> textSpiSpliterator) {
        this.textSpiSpliterator = textSpiSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Employee> action) {
        if (textSpiSpliterator.tryAdvance(id -> this.id = Integer.parseInt(id)) &&
                textSpiSpliterator.tryAdvance(name -> this.name = name) &&
                textSpiSpliterator.tryAdvance(gender -> this.gender = gender.charAt(0)) &&
                textSpiSpliterator.tryAdvance(dob -> this.dob = Date.valueOf(dob)) &&
                textSpiSpliterator.tryAdvance(city -> this.city = city) &&
                textSpiSpliterator.tryAdvance(designation -> this.designation = designation) &&
                textSpiSpliterator.tryAdvance(joiningDate -> this.joiningDate = Date.valueOf(joiningDate)) &&
                textSpiSpliterator.tryAdvance(salary -> this.salary = Double.parseDouble(salary))) {
            action.accept(Employee.builder()
                    .id(id)
                    .name(name)
                    .gender(gender)
                    .dob(dob)
                    .city(city)
                    .designation(designation)
                    .joiningDate(joiningDate)
                    .salary(salary)
                    .build());
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Employee> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return textSpiSpliterator.estimateSize()/8;
    }

    @Override
    public int characteristics() {
        return textSpiSpliterator.characteristics();
    }
}
