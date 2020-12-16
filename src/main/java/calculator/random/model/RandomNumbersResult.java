package calculator.random.model;

public class RandomNumbersResult {

  private int sum;
  private double average;
  private int min;
  private int max;
  private StringBuilder userMessage;

  public int getSum() {
    return sum;
  }

  public double getAverage() {
    return average;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public StringBuilder getUserMessage() {
    return userMessage;
  }

  public RandomNumbersResult(final int sum, final double average, final int min, final int max, final StringBuilder userMessage) {
    this.sum = sum;
    this.average = average;
    this.min = min;
    this.max = max;
    this.userMessage = userMessage;
  }
}
