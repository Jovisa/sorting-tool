package org.hyperskill.service.harvester;

import java.util.List;
import java.util.Scanner;

public interface DataHarvester<T> {

    List<T> harvestData(Scanner scanner);
}
