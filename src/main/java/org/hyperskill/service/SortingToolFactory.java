package org.hyperskill.service;



import org.hyperskill.config.ConfigParams;
import org.hyperskill.service.harvester.DataHarvester;
import org.hyperskill.service.harvester.LineHarvester;
import org.hyperskill.service.harvester.NumberHarvester;
import org.hyperskill.service.harvester.WordHarvester;
import org.hyperskill.service.printer.DataPrinter;
import org.hyperskill.service.processor.DataProcessor;
import org.hyperskill.service.processor.DataProcessorByCount;
import org.hyperskill.service.processor.DataProcessorNatural;

import static sorting.util.Constants.*;

public class SortingToolFactory {

    public static DataSortingTool createSortingTool(ConfigParams config) {
        return switch (config.dataType()) {
            case LINE -> createSortingToolForType(new LineHarvester(), config);
            case WORD -> createSortingToolForType(new WordHarvester(), config);
            default -> createSortingToolForType(new NumberHarvester(), config);
        };
    }

    private static <T extends Comparable<T>> DataSortingTool createSortingToolForType(DataHarvester<T> dataHarvester,
                                                                                      ConfigParams configParams) {
        DataProcessor<T> dataProcessor = SORTING_BY_COUNT.equals(configParams.sortingType())
                ? new DataProcessorByCount<>(configParams.dataType())
                : new DataProcessorNatural<>(configParams.dataType());

        return new SortingTool<>(
                dataHarvester,
                dataProcessor,
                new DataPrinter(configParams.outputFile()));
    }

}
