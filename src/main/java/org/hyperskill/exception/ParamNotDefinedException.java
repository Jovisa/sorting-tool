package org.hyperskill.exception;

import java.util.Map;

import static sorting.util.Constants.DATA_TYPE_FLAG;
import static sorting.util.Constants.SORTING_STRATEGY_FLAG;

public class ParamNotDefinedException extends RuntimeException {

    private static final Map<String, String> errorMessages = Map.of(
            DATA_TYPE_FLAG, "No data type defined!",
            SORTING_STRATEGY_FLAG, "No sorting type defined!"
    );

    public ParamNotDefinedException(String flag) {
        super(errorMessages.get(flag));
    }
}
