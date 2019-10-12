package ru.avalon.java.dev.ocpjp.labs.core.io;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;


/**
 * Абстрактное представление о читателе файла.
 */
public interface FileReader extends Closeable {

    /**
     * Возвращает выбранную строку файла.
     *
     * @param line номер строки
     * @return экземпляр типа {@link String}
     * @throws IOException в случае ошибки ввода-вывода.
     */
    String readLine(int line) throws IOException;

    /**
     * Возвращает общее количество строк файла.
     *
     * @return количество строк файла в виде числа.
     */
    int getLinesCount();

    /**
     * фабричный метод, возвращающий реализацию читателя
     * файла на основе переданного описателя файла.
     *
     * @param file описатель файла
     * @return экземпляр типа {@link FileReader}
     * @throws IOException в случае ошибки ввода-вывода
     */
    static FileReader getInstance(File file) throws IOException {
        return new FileReaderImpl(file);
    }
}
