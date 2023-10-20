package com.kgisl.maven1;

import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class App2 {
    public static void main(String[] args) throws IOException, CsvException {

        String fileName = "D:\\sneha\\java\\mvnprojects\\maven1\\Players.csv";

        CSVReader reader1 = new CSVReader(new FileReader(fileName));
        List<String[]> r1 = reader1.readAll();
        r1.forEach(x -> System.out.println(Arrays.toString(x)));
        try (CSVReader csvReader1 = new CSVReader(new FileReader(fileName))) {
            String[] header = csvReader1.readNext();
            System.out.println("Try block");
            if (header != null) {
                for (String columnName : header) {
                    System.out.print(columnName.toUpperCase() + "\t");
                }
                System.out.println();
            }
            String[] record;
            while ((record = csvReader1.readNext()) != null) {
                for (String value : record) {
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
            try (CSVReader csvReader2 = new CSVReader(
                    new FileReader("D:\\sneha\\java\\mvnprojects\\maven1\\Players.csv"))) {
                System.out.println("\nTRY SECOND BLOCK");
                if (header != null) {
                    for (String columnName : header) {
                        System.out.print(columnName.toUpperCase() + "\t");
                    }
                    System.out.println();
                }
                String[] nextRecord;
                while ((nextRecord = csvReader2.readNext()) != null) {
                    System.out.println("Name : " + nextRecord[0]);
                    System.out.println("Email : " + nextRecord[1]);
                    System.out.println("Phone : " + nextRecord[2]);
                    System.out.println("Country : " + nextRecord[3]);
                    System.out.println("**********");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    // BindByName & BindByPosition
        List<Players> beans = new CsvToBeanBuilder<Players>(new FileReader(fileName))
                .withSkipLines(1)
                .withType(Players.class)
                .build()
                .parse();
                beans.forEach(System.out::println);
                System.out.println();

        // Customer Interface -for Each
        Consumer<Players> makeUC = new Consumer<Players>() {
            @Override
            public void accept(Players p) {
                System.out.println(p.getName().toUpperCase());
            }
        };
        beans.forEach(makeUC);

        //Custom CSV Parser
        CSVParser csvParser2 = new CSVParserBuilder().withSeparator(';').build(); 
        try (CSVReader reader2 = new CSVReaderBuilder(

                new FileReader("D:\\sneha\\java\\mvnprojects\\maven1\\users.csv\\"))
                .withCSVParser(csvParser2)
                .withSkipLines(1)
                .build()) {
            System.out.println("\ncsv parser custom\n");
            List<String[]> r2 = reader2.readAll();
            r2.forEach(x -> System.out.println(Arrays.toString(x)));
        }
    }
}
