package com.collectors.customcollectors;

import com.collectors.model.Employee;

import java.util.*;
import java.util.stream.Collector;

public class CustomCollectors {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(2,6,8,9,0,1,52,5,61,8,9,96,0,18,23);


        Collector<Integer,List<Integer>,List<Integer>> toList = Collector.of(
                ArrayList::new, // Supplier
                List::add, // Accumulator
                (l1, l2) -> { l1.addAll(l2); return l1;}, // Combiner
                Collector.Characteristics.IDENTITY_FINISH); // Characteristics

        List<Integer> list = numbers.stream().collect(toList);
        list.forEach(System.out::println);

        System.out.println("Sorted List");
        Collector<Integer,List<Integer>,List<Integer>> toSortedListCollector = Collector.of(
                ArrayList::new, // Supplier
                List::add, // Accumulator
                (l1, l2) -> { l1.addAll(l2); return l1;}, // Combiner
                l -> {
                    Collections.sort(l);
                    return l;
                }, // Finisher
                Collector.Characteristics.UNORDERED); // Characteristics

        List<Integer> sortedList = numbers.stream().collect(toSortedListCollector);
        sortedList.forEach(System.out::println);









    }
}
