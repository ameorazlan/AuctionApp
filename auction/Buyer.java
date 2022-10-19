package auction;

public class Buyer extends User {
  private int age;
  
  private String fullName;
  
  public Buyer(String fullName, int age) throws IllegalArgumentException{
    String regEx = "[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+";
    if (age <18 || !fullName.matches(regEx)) {
      throw new IllegalArgumentException();
    } else {
      this.fullName = fullName;
      this.age = age;
    }
  }
  
  public int getAge() {
    return age;
  }
  
  @Override
  public String getName() {
    int firstSpace = this.fullName.indexOf(" ");
    String firstName = fullName.substring(0, firstSpace);
    return firstName;
  }

  @Override
  public String toString() {
    String encryptedName = "";
    encryptedName += fullName.charAt(0) + "***" + this.getName().charAt(this.getName().length() - 1);
    return encryptedName;
  }

  
}
