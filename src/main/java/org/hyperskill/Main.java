package org.hyperskill;

import org.hyperskill.config.ConfigFactory;
import org.hyperskill.service.SortingToolFactory;
import sorting.util.ScannerFactory;

public class Main {
    public static void main(final String[] args) {
        try {
            var config = ConfigFactory.getConfig(args);
            var sortingTool = SortingToolFactory.createSortingTool(config);
            var scanner = ScannerFactory.createScanner(config.inputFile());

            sortingTool.harvestData(scanner);
            sortingTool.processData();
            sortingTool.printData();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
