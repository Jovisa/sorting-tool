package org.hyperskill.service.processor;

import java.util.List;
import java.util.stream.Collectors;

import static sorting.util.Constants.LINE;

public class DataProcessorNatural<T extends Comparable<T>> implements DataProcessor<T>{

    private static final String DEFAULT_SEPARATOR = " ";
    private static final String LINE_SEPARATOR = "\n";

    private final String dataType;

    public DataProcessorNatural(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String processData(List<T> collectionOfData) {
        final String separator = LINE.equals(dataType)
                ? LINE_SEPARATOR
                : DEFAULT_SEPARATOR;

        final String sortedData = collectionOfData.stream()
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.joining(separator));

        return String.format("""
                Total %ss: %d.
                Sorted data:%s%s
                """, dataType, collectionOfData.size(), separator, sortedData);
    }

}
