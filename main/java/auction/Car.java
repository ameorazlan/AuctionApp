package auction;

import java.text.DecimalFormat;

public class Car {
  private CarBody body;
  
  private String colour;
  
  private Condition condition;
  
  private CarType gearbox;
  
  private int id;
  
  private String name;
  
  
  private int numberOfSeats;
  
  private double reservedPrice;
  
  private SaleType saleType;
  
  public Car(int id, String name, double reservedPrice, Condition condition, SaleType saleType) throws IllegalArgumentException {
    if (id < 0 || name == null || reservedPrice < 0) {
      throw new IllegalArgumentException();
    } else {
      this.id = id;
      this.name = name;
      this.reservedPrice = reservedPrice;
      this.condition = condition;
      this.saleType = saleType;
    }
  }
  

  public String displayCarSpecification() {
    DecimalFormat formatter = new DecimalFormat("0.00");
    String statement = this.id + " - " + this.name + " (£" + formatter.format(this.reservedPrice) +")\n";
    statement += "\t Type: " + this.gearbox + "\n";
    statement += "\t Style: " + this.body + "\n";
    statement += "\t Colour: " + this.colour + "\n";
    statement += "\t No. of Seats: " + this.numberOfSeats + "\n";
    statement += "\t Condition: " + this.condition;
    return statement;
  }
  
  public CarBody getBodyStyle() {
    return body;
  }
  
  public String getColour() {
    return colour;
  }
  
  public CarType getGearbox() {
    return gearbox;
  }
  
  public int getID() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public int getNumberOfSeats() {
    return numberOfSeats;
  }

  public double getPrice() {
    return reservedPrice;
  }
  
  public SaleType getType() {
    return saleType;
  }

  public void setBody(CarBody body) {
    this.body = body;
  }
  
  public void setColour(String colour) {
    this.colour = colour;
  }
  public void setNumberOfSeats(int numberOfSeats) {
    this.numberOfSeats = numberOfSeats;
  }
  
  public void setGearbox(CarType gearbox) {
    this.gearbox = gearbox;
  }
  public String toString() {
    return this.id + " - " + this.name;
  }
  
}
