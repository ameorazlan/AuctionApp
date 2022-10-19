package auction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Trader extends Dealership{
  
  private List<Seller> sellers;
  
  public Trader(String name){
    super(name);
    this.sellers = new ArrayList<Seller>();
  }
  
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
  
  public String displayStatistics() {
    int totalSales = 0;
    for (Seller seller : this.sellers) {
      totalSales += seller.getTotalSales();
    }
    try {
      this.saveInFile(totalSales);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
     String statement ="";
     try {
       BufferedReader BReader = new BufferedReader(new FileReader("trade_statistics.txt"));
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
 
  public String displayUnsoldCars() {
    String statement = "UNSOLD CARS:\n";
    for (Advert advert : unsoldCars.keySet()) {
      statement += "Ad: " +advert.getCar().displayCarSpecification() + "\n";
    }
    return statement;
  }
  
  public void endSale(Advert advert) throws IllegalArgumentException{
    if (advert == null) {
      throw new IllegalArgumentException();
    } else {
      //Checks if the highest offer is actually greater or equal to the price of the car on the advert
      if (advert.getHighestOffer() != null && advert.getHighestOffer().getValue() >= advert.getCar().getPrice()) {
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
      //At the end of the sale, all the cars for sale will be moved to the unsold cars map
        for (Entry<Advert, Seller> entry : this.carsForSale.entrySet()) {
          this.unsoldCars.put(entry.getKey(), entry.getValue());
        }
      }

      }
  }

  public boolean placeOffer(Advert carAdvert, User user, double value) throws IllegalArgumentException{
    if (carAdvert == null || user == null || value < 0) {
      throw new IllegalArgumentException();
    } else {
      if (this.checkExistence(carAdvert.getCar()) == false || value < carAdvert.getCar().getPrice()) {
        this.endSale(carAdvert);
        return false;
      } else {
        for (Advert advert : carsForSale.keySet()) {
          if (advert == carAdvert) {
            advert.placeOffer(user, value);
      }
    }
        this.endSale(carAdvert);
        
        return true;
  }
 }
}
  
  public void registerCar(Advert carAdvert, User user, String colour, CarType type, CarBody body, int noOfSeats)
      throws IllegalArgumentException {
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
    }
  
  public void saveInFile(int noOfSales) throws IOException {
    try {
      try (BufferedWriter auctionStatistics = new BufferedWriter(new FileWriter("trade_statistics.txt"))) {
        auctionStatistics.write("** Trader - " + this.name + "**");
        auctionStatistics.newLine();
        auctionStatistics.write("Total Sales: " + noOfSales);
        auctionStatistics.newLine();
        auctionStatistics.write("All Sellers:");
        for (Seller seller : this.sellers) {
          auctionStatistics.newLine();
          auctionStatistics.write("\t" + seller.toString());
        }
        auctionStatistics.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void updateStatistics(Seller seller) {
    // TODO Auto-generated method stub
    if (!this.sellers.contains(seller)) {
      this.sellers.add(seller);
    }
  }
}
  
