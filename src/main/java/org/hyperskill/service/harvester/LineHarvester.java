package org.hyperskill.service.harvester;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LineHarvester implements DataHarvester<String> {

    @Override
    public List<String> harvestData(Scanner scanner) {
        final List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        scanner.close();
        return lines;
    }

}
