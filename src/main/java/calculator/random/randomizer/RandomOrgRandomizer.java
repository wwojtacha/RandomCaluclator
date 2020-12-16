package calculator.random.randomizer;

import calculator.random.enums.InputType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public class RandomOrgRandomizer implements NumberRandomizer {

  private RestOperations restTemplate = new RestTemplate();

  @Override
  public List<Integer> getRandomNumbers(MultiKeyMap<Object, String> inputParameters) {

    String quantityOfNumbers = "";
    String rangeStart = "";
    String rangeStop = "";

    for (Map.Entry<MultiKey<?>, String> entry : inputParameters.entrySet()) {
      if (entry.getKey().getKey(0) == InputType.NUMBERS_QUANTITY) {
        quantityOfNumbers = entry.getValue();
      } else if (entry.getKey().getKey(0) == InputType.LOWER_BOUNDARY) {
        rangeStart = entry.getValue();
      } else {
        rangeStop = entry.getValue();
      }
    }

    String numbersString = getNumbersString(quantityOfNumbers, rangeStart, rangeStop);

    String[] numbersArray = numbersString.split("\\n");

    return Arrays.stream(numbersArray).map(Integer::valueOf).collect(Collectors.toList());
  }

  public String getNumbersString(String numbersQuantity, String lowerBoundary, String upperBoundary) {

    String url = String.format("https://www.random.org/integers/?num=%s&min=%s&max=%s&col=1&base=10&format=plain", numbersQuantity, lowerBoundary, upperBoundary);

    return restTemplate.getForEntity(url, String.class).getBody();
  }


}
