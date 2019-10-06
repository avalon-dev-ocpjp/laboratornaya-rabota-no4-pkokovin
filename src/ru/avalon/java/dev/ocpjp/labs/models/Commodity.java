package ru.avalon.java.dev.ocpjp.labs.models;

import ru.avalon.java.dev.ocpjp.labs.core.Builder;
import ru.avalon.java.dev.ocpjp.labs.core.io.RandomFileReader;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Абстрактное представление о товаре.
 */
public interface Commodity extends Comparable<Commodity>{
    @Override
    default int compareTo(Commodity o) {
        if (this.getPrice() < o.getPrice()) return -1;
        if (this.getPrice() > o.getPrice()) return 1;
        return 0;
    }

    /**
     * Возвращает код товара.
     *
     * @return код товара в виде строки.
     */
    String getCode();

    /**
     * Возвращает артикул товара
     *
     * @return артикул товара в виде строки.
     */
    String getVendorCode();

    /**
     * Возвращает наименование товара.
     *
     * @return наименование товара в виде строки.
     */
    String getName();

    /**
     * Возвращает стоимость товара.
     *
     * @return стоимость товара.
     */
    double getPrice();

    /**
     * Возвращает остаток товара на складе.
     *
     * @return остаток товара.
     */
    int getResidue();

    /**
     * Абстрактное представление о реализации эталона
     * проектирования "Строитель" для типа данных {@link Commodity}.
     */
    interface CommodityBuilder extends Builder<Commodity> {
        /**
         * Устанавливает код товара.
         *
         * @param code код товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder code(String code);

        /**
         * Устанавливает артикул товара.
         *
         * @param vendorCode артикул товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder vendorCode(String vendorCode);

        /**
         * Устанавливает наименование товара.
         *
         * @param name наименование товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder name(String name);

        /**
         * Устанавливает стоимость товара.
         *
         * @param price стоимость товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder price(double price);

        /**
         * Устанавливает остаток товара на складе.
         *
         * @param residue остаток товара
         * @return экземпляр типа {@link CommodityBuilder}
         */
        CommodityBuilder residue(int residue);

        /**
         * Возвращает экземпляр типа {@link Commodity}
         * проинициализированный согласно заданной
         * конфигурации.
         *
         * @return экземпляр типа {@link Commodity}
         */
        @Override
        Commodity build();
    }

    /**
     * Возвращает "Строитель", с использованием которого
     * можно создавать экземпляры типа {@link Commodity}.
     *
     * @return экземпляр типа {@link CommodityBuilder}
     */
    static CommodityBuilder builder() {
        return new CommodityImpl.CommodityBuilderImpl();
    }

    /**
     * Выполняет создание заданного количества случайных
     * товаров.
     *
     * @param limit количество товаров в результирующей
     *              коллекции
     * @return коллекцию экземпляров {@link Commodity}
     * @throws IOException в случае ошибки ввода-вывода
     */
    static Collection<Commodity> random(int limit) throws IOException {
        try (RandomFileReader reader = RandomFileReader.fromSystemResource("resources/household.csv")) {
            /*
             * Реализовано создание случайных объектов типа 'Commodity'
             * 1. Для создания коллекции следует использовать метод 'generate()' класса 'Stream'
             * 2. Для получения коллекции следует использовать метод 'collect()' класса 'Stream'
             */
            return Stream.generate(reader::readLine)
                    .limit(limit)
                    .map(Commodity::valueOf)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Выполняет создание экземпляра типа {@link Commodity}
     * из строки.
     *
     * @param string строка, содержащая данные о товаре
     * @return экземпляр типа {@link Commodity}
     */
    static Commodity valueOf(String string) {
        /*
         * Реализован метод 'parse()' класса 'Commodity'
         * Реализация метода должна быть основана на формате
         * файла 'resources/household.csv'.
         */
        String[] commodityContents = string.split(";");
        String code = commodityContents[0];
        String vendorCode = commodityContents[1];
        String name = commodityContents[2];
        int resuide;
        if (isInteger(commodityContents[3])) {
            resuide = Integer.valueOf(commodityContents[3]);
        } else return null;
        double price;
        if (isDouble(commodityContents[4])) {
            price = Double.valueOf(commodityContents[4]);
        } else return null;

        return Commodity.builder()
                .code(code)
                .vendorCode(vendorCode)
                .name(name)
                .residue(resuide)
                .price(price)
                .build();
    }
    private static boolean isInteger(String val) {
        boolean flag = true;
        try {
            Integer.parseInt(val);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }
    private static boolean isDouble(String val) {
        boolean flag = true;
        try {
            Double.parseDouble(val);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }
}
