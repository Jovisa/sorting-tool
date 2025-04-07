package org.hyperskill.service.processor;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static sorting.util.Constants.LONG;

public class DataProcessorByCount<T extends Comparable<T>> implements DataProcessor<T> {

    private final String dataType;

    public DataProcessorByCount(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String processData(List<T> collectionOfData) {
        Comparator<ElementStats> comparator = getComparator(dataType);

        List<ElementStats> elementStats = collectionOfData.stream()
                .distinct()
                .map(e -> toElementStats(e, collectionOfData))
                .sorted(comparator)
                .toList();

        return formatOutput(dataType, elementStats, collectionOfData.size());
    }

    private Comparator<ElementStats> getComparator(String dataType) {
        Comparator<ElementStats> comparator = Comparator.comparingInt(ElementStats::frequency);
        return LONG.equals(dataType)
                ? comparator.thenComparing(e -> Long.parseLong(e.element))
                : comparator.thenComparing(ElementStats::element, Comparator.naturalOrder());
    }

    private ElementStats toElementStats(T element, List<T> collectionOfData) {
        String value = String.valueOf(element);
        int frequency = Collections.frequency(collectionOfData, element);
        int frequencyPercentage = calculateFrequencyPercentage(collectionOfData.size(), frequency);

        return new ElementStats(value, frequency, frequencyPercentage);
    }

    private int calculateFrequencyPercentage(int totalNumber, int frequency) {
        return Math.floorDiv(frequency * 100, totalNumber);
    }

    private String formatOutput(String dataType, List<ElementStats> elementStats, int totalNumberOfElements) {
        StringBuilder output = new StringBuilder(String.format("""
                Total %ss: %d.
                """, dataType, totalNumberOfElements));

        elementStats.forEach(entry -> {
            output.append(String.format("""
                    %s: %d time(s), %d%%
                    """, entry.element, entry.frequency, entry.percentage));
        });

        return output.toString();
    }

    private record ElementStats(String element, int frequency, int percentage) {}
}
