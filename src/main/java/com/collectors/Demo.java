package com.collectors;

import com.collectors.customspliterator.EmployeeSpliterator;
import com.collectors.model.Employee;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Demo {
    public static void main(String[] args) {
        Path path = Paths.get("G://Intellij Workspace/Custom-spliterator-02/src/main/resources/employee.log");

        try(Stream<String> lines = Files.lines(path)){
            Stream<String> words = lines.flatMap(line -> Arrays.stream(line.split(",")));
            EmployeeSpliterator employeeSpliterator = new EmployeeSpliterator(words.spliterator());
            List<Employee> employeeList = StreamSupport.stream(employeeSpliterator, false).collect(Collectors.toList());

            System.out.println("\n --- Employees --");
            employeeList.stream().map(employee -> employee.getName()).collect(Collectors.toList())
                    .forEach(System.out::println);

            // To get Designation
            System.out.println("\n --- Designation --");
            employeeList.stream().map(employee -> employee.getDesignation())
                    .collect(Collectors.toSet())
                    .forEach(System.out::println);

            // Sort the Employee
            System.out.println("\n --- Sorted Employees --");
            TreeSet<Employee> treeSet = employeeList.stream()
                    .collect(Collectors.toCollection((TreeSet::new)));
            treeSet.forEach(System.out::println);

            // Key Value Pair
            System.out.println("\n --- Map Employee Id to Employee Name --");
            Map<Integer, String> employeesMap = employeeList.stream()
                    .collect(Collectors.toMap(Employee::getId,Employee::getName));
            System.out.println(employeesMap);

            // Separate List of Male and Females (Suitable for 2 types)
            System.out.println("\n --- Separate M & F --");
            Map<Boolean,List<Employee>> booleanListMap = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getGender() == 'M'));
            System.out.println(booleanListMap);

            // Group By
            System.out.println("\n --- GroupBy Designation --");
            Map<String,List<Employee>> designationMap = employeeList.stream()
                    .collect(Collectors
                            .groupingBy(Employee::getDesignation));
            System.out.println(designationMap);

            System.out.println("\n --- Getting all employee name in a single string separated by ,");
            String employeesStr = employeeList.stream()
                    .map(Employee::getName)
                    .collect(Collectors.joining(", "));
            System.out.println(employeesStr);

            System.out.println("\n --- Get Number of Employees based on Designation ---");
            Map<String,Long> designationCountMap = employeeList.stream()
                    .collect(Collectors
                            .groupingBy(Employee::getDesignation, Collectors.counting()));
            System.out.println(designationCountMap);

            System.out.println("\n --- Get Total Fund based on Designation ---");
            Map<String,Double> fundBasedOnDesignation = employeeList.stream()
                    .collect(Collectors
                            .groupingBy(Employee::getDesignation, Collectors.summingDouble(Employee::getSalary)));
            System.out.println(fundBasedOnDesignation);

            System.out.println("\n --- Get Maximum Salaried Employee in designation group ---");
            Map<String,Optional<Employee>> maxSalaryInDesignation = employeeList.stream()
                    .collect(Collectors.groupingBy(Employee::getDesignation, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
            System.out.println(maxSalaryInDesignation);

            System.out.println("\n --- Get Maximum Salary in designation group ---");
            Map<String,Optional<Double>> maxSalaries = employeeList.stream()
                    .collect(
                            Collectors.groupingBy(
                                    Employee::getDesignation,
                                    Collectors.mapping(
                                            Employee::getSalary,
                                            Collectors.maxBy(Comparator.comparing(Function.identity()))
                                    )
                            )
                    );
            System.out.println(maxSalaries);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}

