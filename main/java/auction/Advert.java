package auction;

import java.util.ArrayList;
import java.util.List;

public class Advert {
  private Car car;
  
  private List <Offer> offers;
  
  public Advert(Car car) throws IllegalArgumentException{
    if (car == null) {
      throw new IllegalArgumentException();
    } else {
      this.car = car;
      this.offers = new ArrayList<Offer>();
    }
  }
  
  public Car getCar() {
    return this.car;
  }
  
  public Offer getHighestOffer() {
    Offer highestOffer = null;
    for (Offer offer: offers) {
      if (highestOffer == null) {
        highestOffer = offer;
      } else {
        if (offer.getValue() > highestOffer.getValue()) {
          highestOffer = offer;
        }
      }
    }
    return highestOffer;
  }

  public boolean placeOffer(User buyer, double value) throws IllegalArgumentException{
    if (value < 0 || buyer == null) {
      throw new IllegalArgumentException();
    } else {
      offers.add(new Offer(buyer, value));
      return true;
    }
  }
  
  public String toString() {
    return "Ad: " + this.car.toString();
  }
}
