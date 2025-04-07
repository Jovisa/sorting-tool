package org.hyperskill.config;

public record ConfigParams(
        String dataType,
        String sortingType,
        String inputFile,
        String outputFile
) {}
