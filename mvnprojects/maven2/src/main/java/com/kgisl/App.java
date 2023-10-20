package com.kgisl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException, CsvException {
        System.out.println("Hello World!");

        String fileName = "D:\\sneha\\java\\mvnprojects\\maven2\\EQUITY_L.csv";
        CSVReader reader = new CSVReader(new FileReader(fileName));

        List<String[]> eqList = reader.readAll();

        ArrayList<String[]> eqArrayList = new ArrayList<>(eqList);

        Stream<String[]> equityStream = eqArrayList.stream();
        // equityStream.forEach(x -> System.out.println(Arrays.toString(x)));

        if (equityStream instanceof Stream) {
            System.out.println("The output is a Stream<String[]>.");
        } else if (eqArrayList instanceof ArrayList) {
            System.out.println("The output is an ArrayList<String[]>.");
        } else if (eqList instanceof List) {
            System.out.println("The output is of an unknown type.");
        }
        // Count no.of companies in series EQ
        System.out.println("COMPANIES IN SERIES -EQ ");
        long count = equityStream
                .skip(1)
                .filter(c -> {
                    String series = c[2];
                    return series.startsWith("EQ");
                })
                .count();
        System.out.println(count);

        // Date of listing between 1995 & 2000

        // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        // LocalDate lowerBound = LocalDate.of(1995, 1, 1);
        // LocalDate upperBound = LocalDate.of(2001, 1, 1);
        // List<String[]> dateOfListing = eqArrayList.stream().skip(1).filter(data -> {
        //     LocalDate dol = LocalDate.parse(data[3], dateFormatter);
        //     return dol.isAfter(lowerBound) && dol.isBefore(upperBound);
        // }).collect(Collectors.toList());
        // dateOfListing.forEach(data -> {
        //     for (String value : data) {
        //         System.out.println(value + "\t");
        //     }
        // });
        // ---------------------------------
        // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        // String lowerBoundStr = "01-Jan-1995";
        // String upperBoundStr = "01-Jan-2001";

        // List<String[]> dateOfListing = eqArrayList.stream().skip(1).filter(data -> {
        // LocalDate dol = LocalDate.parse(data[3], dateFormatter);
        // LocalDate lowerBound = LocalDate.parse(lowerBoundStr, dateFormatter);
        // LocalDate upperBound = LocalDate.parse(upperBoundStr, dateFormatter);
        // return dol.isAfter(lowerBound) && dol.isBefore(upperBound);
        // }).collect(Collectors.toList());

        // dateOfListing.forEach(data -> {
        // for (String value : data) {
        // System.out.println(value + "\t");
        // }
        // });
        // --------------------------
        // String lowerBound = "01-JAN-1995";
        // String upperBound = "01-JAN-2001";

        // List<String[]> dateOfListing = eqArrayList.stream()
        // .skip(1) // Skip the header row
        // .filter(data -> {
        // String dol = data[3];
        // return dol.compareTo(lowerBound) >= 0 && dol.compareTo(upperBound) <= 0;
        // })
        // .collect(Collectors.toList());

        // dateOfListing.forEach(data -> {
        // for (String value : data) {
        // System.out.print(value + "\t");
        // }
        // System.out.println();
        // });
        // --------------------------------------------------
        // Distinct paid up value
        System.out.println("DISTINCT PAID UP VALUE ");
        List<String> distinctPaidUpValue = eqArrayList.stream()
                .skip(1)
                .map(data -> data[4])
                .distinct()
                .collect(Collectors.toList());
        System.out.println("DIstinct Paid Up Value= " + distinctPaidUpValue);

        // Sort by isin number
        System.out.println("ISIN NUMBER SORTED ");
        List<String[]> sortedList = eqArrayList.stream()
                .skip(1)
                .sorted(Comparator.comparing(data -> data[6]))
                .collect(Collectors.toList());

        System.out.println("SORTED LIST: ");
        sortedList.forEach(data -> {
            for (String isin : data) {
                System.out.println(isin);
            }
            System.out.println();
        });

        // Only ISIN number in sorted order
        // List<String> sortedList=eqArrayList.stream()
        // .skip(1)
        // .map(data->data[6])
        // .sorted()
        // .collect(Collectors.toList());
        // for (String isin : sortedList) {
        // System.out.println(isin);
        // }

        // Filter Indian organization
        System.out.println("ONLY INDIAN ORG");
        List<String[]> indianOrg = eqArrayList.stream()
                .skip(1)
                .filter(data -> {
                    String company = data[1];
                    return company.contains("India");
                })
                .sorted(Comparator.comparing(data -> data[6]))
                .collect(Collectors.toList());
        indianOrg.forEach(data -> {
            for (String value : data) {
                System.out.println(value + "\t");
            }
            System.out.println();
        });

        // 19% in Date of Listing
        System.out.println("YEAR 19%");
        List<String[]> year19 = eqArrayList.stream()
                .skip(1)
                .filter(data -> {
                    String yr19 = data[3];
                    int yr = Integer.parseInt(yr19.substring(yr19.lastIndexOf('-') + 1));
                    return String.valueOf(yr).startsWith("19");
                })
                .collect(Collectors.toList());
        year19.forEach(data -> {
            for (String value : data) {
                System.out.println(String.join(value + "," + "\t"));
            }
            System.out.println();
        });

        // Date is sorted and year only 19%
        // eqArrayList.stream()
        // .map(data -> data[3])
        // .filter(dateOfListing -> dateOfListing.matches(".*-19.*"))
        // .sorted()
        // .sorted(Comparator.comparing(date -> Integer.parseInt(date.substring(7))))
        // ->For sorting the year from 19*
        // .forEach(System.out::println);

        // Paid up value >5
        List<String> valueGrtThan5 = eqArrayList.stream()
                .skip(1)
                .filter(data -> Double.parseDouble(data[4]) > 5.0)
                .map(data -> data[4])
                .distinct()
                .collect(Collectors.toList());

        valueGrtThan5.forEach(data -> System.out.println(String.join(",", data)));

        // Group By paid up value
        Map<String, List<String[]>> groupedByPaidUpCode = eqArrayList.stream()
                .skip(1)
                .collect(Collectors.groupingBy(data -> data[4]));
        groupedByPaidUpCode.forEach((paidUpCode, group) -> {
            System.out.println("Paid Up value-> " + paidUpCode);
            System.out.println("Count-> " + group.size());
            System.out.println("--------");

        });

    }

}
