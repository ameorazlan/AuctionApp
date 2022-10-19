package auction;

public class Offer {
  private User buyer;
  
  private double value;
  
  public Offer(User buyer, double value) throws IllegalArgumentException{
    if (buyer != null) {
      this.buyer = buyer;
    } else {
      throw new IllegalArgumentException();
    }
    if (value > 0) {
      this.value = value;
    } else {
      throw new IllegalArgumentException();
    }
  }
  
  public Buyer getBuyer() {
    return (Buyer) this.buyer;
  }
  
  public double getValue() {
    return value;
  }

  public String toString() {
    return this.buyer.toString() + " offered £" + this.value;
  }
}
