package org.hyperskill.service;



import org.hyperskill.service.harvester.DataHarvester;
import org.hyperskill.service.printer.DataPrinter;
import org.hyperskill.service.processor.DataProcessor;

import java.util.List;
import java.util.Scanner;

public class SortingTool<T extends Comparable<T>> implements DataSortingTool {

    private final DataHarvester<T> harvester;
    private final DataProcessor<T> processor;
    private final DataPrinter printer;

    private List<T> collectionOfData;
    private String processedData;

    public SortingTool(DataHarvester<T> harvester,
                       DataProcessor<T> processor,
                       DataPrinter printer) {

        this.harvester = harvester;
        this.processor = processor;
        this.printer = printer;
    }

    @Override
    public void harvestData(Scanner scanner) {
        collectionOfData = harvester.harvestData(scanner);
    }

    @Override
    public void processData() {
        processedData = processor.processData(collectionOfData);
    }

    @Override
    public void printData() {
        printer.printData(processedData);
    }

}
