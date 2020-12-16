package calculator.random.randomizer;

import calculator.random.enums.InputType;
import calculator.random.enums.RandomizerType;
import java.util.List;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class NumberRandomizerTest {

  @Autowired
  private NumberRandomizerFactory numberRandomizerFactory;

  @Test
  public void checkNumbrQuantityPerRandomizer() {
    checkNumberQuantity(RandomizerType.RANDOM_ORG_RANDOMIZER);
    checkNumberQuantity(RandomizerType.JAVA_RANDOMIZER);
  }

  public void checkNumberQuantity(RandomizerType randomizerType) {
    NumberRandomizer numberRandomizer = numberRandomizerFactory.getNumberRandomizer(randomizerType);

    MultiKeyMap<Object, String> inputParameters = new MultiKeyMap<>();
    inputParameters.put(InputType.NUMBERS_QUANTITY, false, "20");
    inputParameters.put(InputType.LOWER_BOUNDARY, false, "1");
    inputParameters.put(InputType.UPPER_BOUNDARY, false, "100");

    List<Integer> randomNumbers = numberRandomizer.getRandomNumbers(inputParameters);
    int numbersQuntity = randomNumbers.size();
    String message = "Expected to get different amount of random numbers";


    assertTrue(numbersQuntity == 20, message);

  }

}
