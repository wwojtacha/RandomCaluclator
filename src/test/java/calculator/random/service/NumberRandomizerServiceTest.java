package calculator.random.service;

import calculator.random.enums.RandomizerType;
import calculator.random.model.RandomNumbersResult;
import calculator.random.service.NumberRandomizerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



@SpringBootTest
public class NumberRandomizerServiceTest {

  @Autowired
  private NumberRandomizerService numberRandomizerService;

  @Test
  public void getRandomNumberResultUsingJavaRandomizer() {

    RandomizerType javaRandomizer = RandomizerType.JAVA_RANDOMIZER;
    RandomNumbersResult randomNumberResult = numberRandomizerService.getRandomNumberResult(javaRandomizer, "5", "90", "100");

    double actualAverageValue = randomNumberResult.getAverage();
    String message = "Did not expect to get average value equal to 0";
    assertNotEquals(0.0, actualAverageValue, message);
  }

  @Test
  public void getRandomNumberResultUsingRandomOrgRandomizer() {

    RandomizerType javaRandomizer = RandomizerType.RANDOM_ORG_RANDOMIZER;
    RandomNumbersResult randomNumberResult = numberRandomizerService.getRandomNumberResult(javaRandomizer, "5", "19", "20");

    double actualAverageValue = randomNumberResult.getAverage();
    String message = "Did not expect to get average value equal to 0";
    assertNotEquals(0.0, actualAverageValue, message);
  }

  @Test
  public void shouldUseDefaultInputValue() {

    RandomizerType javaRandomizer = RandomizerType.JAVA_RANDOMIZER;
    RandomNumbersResult randomNumberResult = numberRandomizerService.getRandomNumberResult(javaRandomizer, "ten", "1", "50");

    StringBuilder userMessage = randomNumberResult.getUserMessage();
    String message = "Expected to get message concerning uasge of default values";
    assertTrue(!userMessage.toString().isEmpty(), message);
  }

}
