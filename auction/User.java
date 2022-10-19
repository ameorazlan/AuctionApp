package auction;
public abstract class User {
  private String fullName;
  
  public User() {
    super();
  }
  public User(String fullName) throws IllegalArgumentException{
    String regEx = "[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+";
    if (fullName.matches(regEx)) {
      this.fullName = fullName;
    } else {
      throw new IllegalArgumentException();
    }
  }
  
  public abstract String getName();
  
  public abstract String toString();
}
