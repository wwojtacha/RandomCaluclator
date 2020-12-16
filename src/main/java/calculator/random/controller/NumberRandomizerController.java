package calculator.random.controller;

import calculator.random.enums.RandomizerType;
import calculator.random.model.RandomNumbersResult;
import calculator.random.service.NumberRandomizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/randomNumbers")
public class NumberRandomizerController {

  @Autowired
  private NumberRandomizerService numberRandomizerService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public RandomNumbersResult getResult(
      @RequestParam(name = "randomizerType", defaultValue = "JAVA_RANDOMIZER") RandomizerType randomizerType,
      @RequestParam(name = "numbersQuantity", required = false, defaultValue = "") String numbersQuantity,
      @RequestParam(name = "lowerBoundary", required = false, defaultValue = "") String lowerBoundary,
      @RequestParam(name = "upperBoundary", required = false, defaultValue = "") String upperBoundary) {

    return numberRandomizerService.getRandomNumberResult(randomizerType, numbersQuantity, lowerBoundary, upperBoundary);
  }

}
