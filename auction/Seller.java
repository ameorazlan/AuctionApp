package auction;

public class Seller extends User{
  
  private String fullName;
  
  private int totalSales;
  
  private int totalAutomaticSales;
  
  private int totalManualSales;
  
  
  public Seller(String fullName) throws IllegalArgumentException{
    String regEx = "[A-Z]{1}[a-z]+ [A-Z]{1}[a-z]+";
    if (fullName.matches(regEx)) {
      this.fullName = fullName;
    } else {
      throw new IllegalArgumentException();
    }
  }
  
  public int getTotalSales() {
    return totalSales;
  }

  public void addTotalSales() {
    this.totalSales +=1;
  }

  public int getTotalAutomaticSales() {
    return totalAutomaticSales;
  }

  public void addTotalAutomaticSales() {
    this.totalAutomaticSales += 1;
  }

  public int getTotalManualSales() {
    return totalManualSales;
  }

  public void addTotalManualSales() {
    this.totalManualSales +=1;
  }
  
  @Override
  public String getName() {
    int firstSpace = this.fullName.indexOf(" ");
    String firstName = fullName.substring(0, firstSpace);
    return firstName;
  }
  
  
  public String identifyRating() {
    if (this.totalSales == 0) {
      return "Level 0";
    } else if (this.totalSales >=1 && this.totalSales < 6) {
      return "Level 1";
    } else if (this.totalSales >= 6 && this.totalSales < 11) {
      return "Level 2";
    } else {
      return "Level 3";
    }
  }
  
  @Override
  public String toString() {
    int firstSpace = this.fullName.indexOf(" ");
    String lastName = fullName.substring(firstSpace, fullName.length());
    String name ="";
    name += this.getName() + " " + lastName.charAt(1) + ". (" + "Sales: " + this.totalSales + ", Rating: " + this.identifyRating() + ")";
    return name;
  }
}
