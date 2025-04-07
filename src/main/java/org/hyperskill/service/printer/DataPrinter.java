package org.hyperskill.service.printer;

import org.apache.logging.log4j.util.Strings;

import java.io.FileWriter;
import java.io.IOException;

public class DataPrinter {

    private final String outputFile;

    public DataPrinter(String outputFile) {
        this.outputFile = outputFile;
    }

    public void printData(String data) {
        if (Strings.isEmpty(outputFile)) {
            print(data);
        } else {
            print(data, outputFile);
        }
    }

    private void print(String data) {
        System.out.println(data);
    }

    private void print(String data, String fileName) {

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
