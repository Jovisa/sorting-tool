package org.hyperskill.service.harvester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NumberHarvester implements DataHarvester<Long> {

    @Override
    public List<Long> harvestData(Scanner scanner) {
        final List<Long> numbers = new ArrayList<>();
        while (scanner.hasNextLong()) {
            numbers.add(scanner.nextLong());
        }
        scanner.close();
        return numbers;
    }

}
