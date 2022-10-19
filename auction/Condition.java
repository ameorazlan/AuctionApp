package auction;

public enum Condition {
  NEW(1), USED(2);
  
  private int condition;
  
  private Condition(int condition) {
    this.condition = condition;
  }
  
  private int getCondition() {
    return this.condition;
  }
}
