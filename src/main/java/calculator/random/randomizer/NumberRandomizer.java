package calculator.random.randomizer;

import java.util.List;
import org.apache.commons.collections4.map.MultiKeyMap;

public interface NumberRandomizer {

  List<Integer> getRandomNumbers(MultiKeyMap<Object, String> inputParameters);

}
