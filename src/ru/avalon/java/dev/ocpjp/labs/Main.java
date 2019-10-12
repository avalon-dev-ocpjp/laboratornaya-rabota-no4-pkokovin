package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        final Collection<Commodity> commodities = Commodity.random(100);
//      1.
        Commodity maxPriceItem = commodities
                .stream()
                .max(Comparator.comparing(Commodity::getPrice))
                .orElse(null);
//      2.
        Map<Integer, List<Commodity>> minResuideItems = commodities
                .stream()
                .collect(Collectors.groupingBy(Commodity::getResidue, TreeMap::new, Collectors.toList()));
//      3.
        commodities
                .stream()
                .filter(x -> x.getResidue() < 5)
                .map(Commodity::getName)
                .forEach(System.out::println);
//      4.
        double sumPrice = commodities
                .stream()
                .mapToDouble(Commodity::getPrice)
                .sum();
//      5.
        System.out.println(commodities
                .stream()
                .map(Commodity::getName)
                .max(Comparator.comparing(String::length))
                .get());
//      6.
        List<Commodity> sortedCommodityList = commodities
                .stream()
                .sorted()
                .collect(Collectors.toList());
//      7.
        double averagePrice = commodities
                .stream()
                .mapToDouble(Commodity::getPrice)
                .average()
                .getAsDouble();
//      8.


       double median = findMedian(commodities
               .stream()
                .map(Commodity::getPrice)
                .collect(Collectors.toList()));


        /*
         * С использованием Java Stream API выполнены задачи из списка:
         * 1. Выполнить поиск самого дорого товара
         * 2. Найти товары с минимальным остатком
         * 3. Найти товары с остатком меньшим 5 и вывести в консоль их названия
         * 4. Подсчитать общую стоимость товаров с учётом их остатка
         * 5. Найти товар, с самым длинным названием и вывести его на экран
         * 6. Выполнить сортировку коллекции commodities
         * 7. Найти среднюю стоимость товаров
         * 8. Найти моду (медиану) стоимости товаров
         */
    }
    private static double findMedian(List<Double> lst) {
        double median = 0.;
        if (lst.size() % 2 == 0) {
            Collections.sort(lst);
            median = (lst.get(lst.size()/2-1) + lst.get(lst.size()/2))/2;
        }
        else median = lst.get(lst.size()/2);
        return median;
    }
}
