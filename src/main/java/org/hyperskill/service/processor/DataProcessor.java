package org.hyperskill.service.processor;

import java.util.List;

public interface DataProcessor<T extends Comparable<T>> {

    String processData(List<T> collectionOfData);
}
