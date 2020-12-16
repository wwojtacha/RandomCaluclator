package calculator.random.service;

import calculator.random.model.RandomNumbersResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.stereotype.Service;

@Service
public class RandomNumbersResultCalculator {

  public RandomNumbersResult calculateResults(List<Integer> numbers, MultiKeyMap<Object, String> correctInputs) {


    int sum = getSum(numbers);
    double average = getAverage(numbers);

    List<Integer> sortedNumbers = getSortedList(numbers);
    int min = getNumberAtPosition(sortedNumbers, 0);
    int max = getNumberAtPosition(sortedNumbers, sortedNumbers.size() - 1);

    StringBuilder message = getMessage(correctInputs);

    RandomNumbersResult randomNumbersResult = new RandomNumbersResult(sum, average, min, max, message);

    return randomNumbersResult;
  }


  private int getSum(List<Integer> numbers) {
    return numbers.stream().mapToInt(Integer::intValue).sum();
  }

  private double getAverage(List<Integer> numbers) {
    return numbers.stream().mapToDouble(Integer::intValue).average().orElse(0.0);
  }

  private List<Integer> getSortedList(List<Integer> numbers) {
    return numbers.stream().sorted().collect(Collectors.toList());
  }

  private int getNumberAtPosition(List<Integer> sortedNumbers, int index) {

    int result = 0;

    if (!sortedNumbers.isEmpty()) {
      result = sortedNumbers.get(index);
    }

    return result;
  }

  private StringBuilder getMessage(MultiKeyMap<Object, String> correctInputs) {

    StringBuilder message = new StringBuilder();

    for (Map.Entry<MultiKey<?>, String> entry : correctInputs.entrySet()) {
      boolean isDefault = (Boolean) entry.getKey().getKey(1);
      if (isDefault) {
        message.append(entry.getKey().getKey(0)).append(", ");
      }
    }

    if (message.length() != 0) {
      removeLastComa(message);
      message.append("have been set as default due to wrong input format.");
    }

    return message;
  }

  private void removeLastComa(StringBuilder message) {
    message.deleteCharAt(message.length() - 2);
  }

}
