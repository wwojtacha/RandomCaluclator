package calculator.random.randomizer;

import calculator.random.enums.RandomizerType;
import calculator.random.randomizer.JavaRandomizer;
import calculator.random.randomizer.NumberRandomizer;
import calculator.random.randomizer.RandomOrgRandomizer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class NumberRandomizerFactory {

public NumberRandomizer getNumberRandomizer(RandomizerType randomizerType) {

  Map<RandomizerType, NumberRandomizer> randomizers = new HashMap<>();
  randomizers.put(RandomizerType.JAVA_RANDOMIZER, new JavaRandomizer());
  randomizers.put(RandomizerType.RANDOM_ORG_RANDOMIZER, new RandomOrgRandomizer());

  return randomizers.get(randomizerType);
}

}
