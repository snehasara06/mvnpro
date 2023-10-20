package com.kgisl.maven1;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class App {

    public static void main(String[] args) throws CsvException, IllegalStateException, FileNotFoundException {
        String fileName = "D:\\sneha\\java\\mvnprojects\\maven1\\EQUITY_SAMPLE.csv";

        List<Equity> beans = new CsvToBeanBuilder<Equity>(new FileReader(fileName))
                .withType(Equity.class)
                .build()
                .parse();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

        List<Equity> filteredEquities = beans.stream()
                .filter(equity -> {

                    LocalDate listingDate = LocalDate.parse(equity.getListingdate(), formatter);
                    int year = listingDate.getYear();
                    return year >= 1995 && year <= 2000;

                })
                // .sorted(Comparator.comparing(data->data[3]))
                .collect(Collectors.toList());
        filteredEquities.sort((eq1, eq2) -> {
            LocalDate date1 = LocalDate.parse(eq1.getListingdate(), formatter);
            LocalDate date2 = LocalDate.parse(eq2.getListingdate(), formatter);
            return date1.compareTo(date2);
        });
        Map<Integer, List<Equity>> groupedEquities = filteredEquities.stream()
                .collect(
                        Collectors.groupingBy(equity -> LocalDate.parse(equity.getListingdate(), formatter)
                                .getYear()));

        Map<Integer, Long> companiesPerYear = beans.stream()
                .collect(Collectors.groupingBy(equity ->
                LocalDate.parse(equity.getListingdate(), formatter)
                .getYear(),
                Collectors.counting()));

        // companiesPerYear.entrySet().stream()
        //         .sorted(Comparator.comparing(Map.Entry::getKey))
        //         .forEach(entry -> {
        //             System.out.println("Year: " + entry.getKey() + ", Count of Companies: " + entry.getValue());
        //         });

        //         Map<String, Long> companiesPerYearAndMonth = beans.stream()
        //         .collect(Collectors.groupingBy(equity -> {
        //             LocalDate date = LocalDate.parse(equity.getListingdate(), formatter);
        //             return date.getYear() + "-" + date.getMonthValue();
        //         }, Collectors.counting()));

        // companiesPerYearAndMonth.entrySet().stream()
        //         .sorted(Comparator.comparing(Map.Entry::getKey))
        //         .forEach(entry -> {
        //             String[] yearAndMonth = entry.getKey().split("-");
        //             int year = Integer.parseInt(yearAndMonth[0]);
        //             int month = Integer.parseInt(yearAndMonth[1]);
        //             String monthName = getMonthName(month);
        //             System.out.println("Year: " + year + ", Month: " + monthName + ", Count of Companies: " + entry.getValue());
        //         });       
        groupedEquities.forEach((year, equities) -> {
        System.out.println("Year: " + year);
        equities.forEach(equity -> {
        // System.out.println("Symbol: " + equity.getSymbol());
        // System.out.println("Company Name: " + equity.getCompany());
        // System.out.println("Series: " + equity.getSeries());
        System.out.println("Date of Listing: " + equity.getListingdate());
        // System.out.println("Paid Up Value: " + equity.getPaid());
        // System.out.println("Market Lot: " + equity.getMktlot());
        // System.out.println("ISIN Number: " + equity.getIsin());
        // System.out.println("Face Value: " + equity.getFacevalue());
        // System.out.println("----------------------------");
        });
        });
    }
    private static String getMonthName(int month) {
    return Month.of(month).name();
}

}
