package calculator.random.randomizer;

import calculator.random.enums.InputType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;

public class JavaRandomizer implements NumberRandomizer {

  @Override
  public List<Integer> getRandomNumbers(MultiKeyMap<Object, String> inputParameters) {

    List<Integer> result = new ArrayList<>();

    Random random = new Random();

    int quantityOfNumbers = 0;
    int rangeStart = 0;
    int rangeStop = 0;

    for (Map.Entry<MultiKey<?>, String> entry : inputParameters.entrySet()) {
      if (entry.getKey().getKey(0) == InputType.NUMBERS_QUANTITY) {
        quantityOfNumbers = Integer.valueOf(entry.getValue());
      } else if (entry.getKey().getKey(0) == InputType.LOWER_BOUNDARY) {
        rangeStart = Integer.valueOf(entry.getValue());
      } else {
        rangeStop = Integer.valueOf(entry.getValue());
      }
    }

    for (int i = 0; i < quantityOfNumbers; i++) {
      int randomNumber = 1 + random.nextInt(rangeStop - rangeStart + 1);
      result.add(randomNumber);
    }

    return result;
  }
}
