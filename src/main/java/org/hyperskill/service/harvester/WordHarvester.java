package org.hyperskill.service.harvester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordHarvester implements DataHarvester<String> {

    @Override
    public List<String> harvestData(Scanner scanner) {
        final List<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.next());
        }
        scanner.close();
        return words;
    }

}
