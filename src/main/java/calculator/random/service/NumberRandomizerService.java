package calculator.random.service;

import calculator.random.randomizer.NumberRandomizer;
import calculator.random.randomizer.NumberRandomizerFactory;
import calculator.random.enums.InputType;
import calculator.random.enums.RandomizerType;
import calculator.random.model.RandomNumbersResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberRandomizerService {

  private Map<InputType, String> defaultValues = getDefaultValues();

  @Autowired
  private NumberRandomizerFactory numberRandomizerFactory;

  @Autowired
  private RandomNumbersResultCalculator randomNumbersResultCalculator;

  private Map<InputType, String> getDefaultValues() {
    defaultValues = new HashMap<>();
    defaultValues.put(InputType.NUMBERS_QUANTITY, "10");
    defaultValues.put(InputType.LOWER_BOUNDARY, "1");
    defaultValues.put(InputType.UPPER_BOUNDARY, "100");

    return defaultValues;
  }

  public RandomNumbersResult getRandomNumberResult(RandomizerType randomizerType, String numbersQuantity, String lowerBoundary, String upperBoundary) {

    Map<InputType, String> inputNumbers = new HashMap<>();
    inputNumbers.put(InputType.NUMBERS_QUANTITY, numbersQuantity);
    inputNumbers.put(InputType.LOWER_BOUNDARY, lowerBoundary);
    inputNumbers.put(InputType.UPPER_BOUNDARY, upperBoundary);

    MultiKeyMap<Object, String> correctInputs = getCorrectInputFormat(inputNumbers);

    NumberRandomizer numberRandomizer = numberRandomizerFactory.getNumberRandomizer(randomizerType);
    List<Integer> randomNumbers = numberRandomizer.getRandomNumbers(correctInputs);

    return randomNumbersResultCalculator.calculateResults(randomNumbers, correctInputs);
  }

  private MultiKeyMap<Object, String> getCorrectInputFormat(Map<InputType, String> inputNumbers) {

    MultiKeyMap <Object, String> correctInputs = new MultiKeyMap<>();

    for (Map.Entry<InputType, String> entry : inputNumbers.entrySet()) {
      InputType key = entry.getKey();
      String value = entry.getValue();
      boolean isDefault = false;
      if (!isCorrectFormat(value)) {
        isDefault = true;
        correctInputs.put(key, isDefault, defaultValues.get(key));
      } else {
        correctInputs.put(key, isDefault, value);
      }
    }

    return correctInputs;
  }

  private boolean isCorrectFormat(String input) {

    boolean result = true;

    try {
      Integer.valueOf(input);
    } catch(NumberFormatException nfe) {
      result = false;
    }

    return result;
  }

}
