package sorting.util;

import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerFactory {

    public static Scanner createScanner(String inputFileName) {
        if (Strings.isEmpty(inputFileName)) {
            return new Scanner(System.in);
        }

        File inputFile = new File(inputFileName);
        try {
            return new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
