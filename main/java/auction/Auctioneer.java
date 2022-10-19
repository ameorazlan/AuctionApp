/**
 * Auctioneer.java
 */
package auction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Defines the auctioneer system in which it manages all the car adverts that are available for auction
 * and keeps track of the cars for sale, sold cars, and unsold cars.
 * @author Adam Meor Azlan
 *
 */
public class Auctioneer extends Dealership{
  
  private Map<Seller, Integer> sales;
  
  private Seller topSeller;
  /**
   * Constructor of the Auctioneer. Sets the name field and initializes all the maps into hashmaps.
   * @param name
   *        Name of the auctioneer
   */
  public Auctioneer(String name) {
    super(name);
    this.sales = new HashMap<Seller, Integer>();
    this.topSeller = new Seller("Place Holder");
  }
  
  /**
   * 
   * @param car
   *        The car that the user wants to check its existence
   * @return if the given car parameter is part of the cars for sale map.
   */
  public boolean checkExistence(Car car) {
    boolean result = false;
    for (Advert advert : carsForSale.keySet()) {
      if (advert.getCar() == car) {
        result = true;
        break;
      }
    }
    return result;
  }
  
  /**
   * 
   * @return A list of all sold cars so far
   */
  public String displaySoldCars() {
    String statement = "SOLD CARS:\n";
    for (Advert advert : soldCars.keySet()) {
      statement += advert.getCar().getID() + " - Purchased by " + advert.getHighestOffer().getBuyer().toString();
      statement += " with a successful £";
      DecimalFormat formatter = new DecimalFormat("0.00");
      statement += formatter.format(advert.getHighestOffer().getValue()) + " bid. \n";
    }
    return statement;
  }
  
  /**
   * 
   * @return Statistics string
   */
  @Override
  public String displayStatistics() {
    int noOfSales = 0;
    double auto = 0;
    double manual = 0;
    double percentageOfAuto;
    double percentageOfManual;
    
    for (Advert advert : this.soldCars.keySet()) {
      noOfSales +=1;
      if (advert.getCar().getGearbox() == CarType.AUTOMATIC) {
        auto += 1;
      } else {
        manual +=1;
      }
    }
    percentageOfAuto = auto / noOfSales * 100;
    percentageOfManual = manual / noOfSales * 100;
   try {
    this.saveInFile(noOfSales, percentageOfAuto, percentageOfManual);
  } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
   String statement ="";
   try {
     BufferedReader BReader = new BufferedReader(new FileReader("auction_statistics.txt"));
     String s = BReader.readLine();
     while (s != null) {
       statement += s + "\n";
        s = BReader.readLine();
     }
     BReader.close();
   } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
   }
   return statement;
   
  }
  
  /**
   * 
   * @return list of all unsold cars so far
   */
  public String displayUnsoldCars() {
    String statement = "UNSOLD CARS:\n";
    for (Advert advert : unsoldCars.keySet()) {
      statement += "Ad: " +advert.getCar().displayCarSpecification() + "\n";
    }
    return statement;
  }
  
  /**
   * 
   * @param advert
   *        The advert that the user wants to end sale for.
   * @throws IllegalArgumentException
   *        Throws an illegal argument exception if the given advert parameter is null
   */
  public void endSale(Advert advert) throws IllegalArgumentException{
    if (advert == null) {
      throw new IllegalArgumentException();
    } else {
      //Checks if the highest offer is actually greater or equal to the price of the car on the advert
      if (advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
        Seller seller = this.carsForSale.get(advert);
        seller.addTotalSales();
        this.updateStatistics(seller);
        if (advert.getCar().getGearbox() == CarType.AUTOMATIC) {
          seller.addTotalAutomaticSales();
        } else if (advert.getCar().getGearbox() == CarType.MANUAL) {
          seller.addTotalManualSales();
        }
        this.carsForSale.remove(advert);
        this.soldCars.put(advert, advert.getHighestOffer().getBuyer());
      }
      //At the end of the sale, all the cars for sale will be moved to the unsold cars map
      for (Entry<Advert, Seller> entry : this.carsForSale.entrySet()) {
        this.unsoldCars.put(entry.getKey(), entry.getValue());
      }
      }
  }
  
  /**
   * 
   * @param carAdvert
   *        The advert the user wants to place an offer on
   * @param user
   *        The user that wants to place an offer
   * @param value
   *        The value of the offer that the user placed
   * @return true if the offer was successfully placed and false if the 
   * advert that the user wants to place an offer on doesn't exist
   * @throws IllegalArgumentException
   *        Throws an illegal argument exception if either the advert or user parameters are null or if the offer value is less than 0.
   */
  public boolean placeOffer(Advert carAdvert, User user, double value) throws IllegalArgumentException{
    if (carAdvert == null || user == null || value < 0) {
      throw new IllegalArgumentException();
    } else {
      if (this.checkExistence(carAdvert.getCar()) == false) {
        return false;
      } else {
        for (Advert advert : carsForSale.keySet()) {
          if (advert == carAdvert) {
            advert.placeOffer(user, value);
      }
    }
        return true;
  }
 }
}
  
  /**
   * 
   * @param carAdvert
   *        The advert that is going to be registered
   * @param user
   *        The user that wants to register the car
   * @param colour
   *        Colour of car
   * @param type
   *        Type of car
   * @param body
   *        Body of car
   * @param noOfSeats
   *        Number of seats in car
   * @throws IllegalArgumentException
   *        Throws an illegal argument exception if either the advert or user or the colour is null,
   *         or if the number of seats is less or equal to 0
   */
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body, int noOfSeats) throws IllegalArgumentException{
    if (carAdvert == null || user == null || colour == null || noOfSeats <= 0) {
      throw new IllegalArgumentException();
    } else {
      Car car = carAdvert.getCar();
      car.setColour(colour);
      car.setGearbox(type);
      car.setNumberOfSeats(noOfSeats);
      car.setBody(body);
      if (this.checkExistence(car) != true) {
        this.carsForSale.put(carAdvert, (Seller) user);
      }
    }
    if (!this.sales.containsKey((Seller) user)) {
      this.sales.put((Seller) user, 0);
    }
  }
  
  public void saveInFile(int noOfSales, double percentageOfAuto, double percentageOfManual) throws IOException {
    try {
      try (BufferedWriter auctionStatistics = new BufferedWriter(new FileWriter("auction_statistics.txt"))) {
        auctionStatistics.write("** Auctioneer - " + this.name + "**");
        auctionStatistics.newLine();
        auctionStatistics.write("Total Auction Sales: " + noOfSales + "\t Automatic Cars: " 
        + percentageOfAuto + "%\t" + " Manual Cars: " + percentageOfManual + "%");
        auctionStatistics.newLine();
        auctionStatistics.write("Top Seller: " + this.topSeller.toString());
        auctionStatistics.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public void updateStatistics(Seller seller) {
        this.sales.remove(seller);
        this.sales.put(seller, seller.getTotalSales());
      if (seller.getTotalSales() > topSeller.getTotalSales()) {
        this.topSeller = seller;
      }
    }
  }
