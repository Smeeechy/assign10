package assign10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GraphUtilityTest {

    @Test
    void shortestWeightedPathWithPriorityQueue() {
        List<String> destinations = List.of("a","b","c","d","w","x","y","z","w","x","y","z","w","x","y","z","w","x","y","z","t","t","t","t");
        List<String> sources = List.of("r","r","r","r","a","a","a","a","b","b","b","b","c","c","c","c","d","d","d","d","w","x","y","z");
        Random rng = new Random();
        ArrayList<Double> weights = new ArrayList<>();

        for (int i = 0; i < destinations.size(); i++) {
            weights.add(rng.nextDouble());
        }
        List<String> pathS = GraphUtility.shortestWeightedPathWithSorting(sources,destinations,weights,"r","t");
        List<String> pathPQ = GraphUtility.shortestWeightedPathWithPriorityQueue(sources,destinations,weights,"r","t");

        assertEquals(pathPQ,pathS);

    }
}