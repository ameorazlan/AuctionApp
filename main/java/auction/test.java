package auction;

public class test {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Trader trader = new Trader("Cars Ltd");
    Car car6 = new Car(1234, "Mazda 3", 2000, Condition.USED, SaleType.FORSALE);
    Car car7 = new Car(2345, "Chevrolet", 4500, Condition.USED, SaleType.FORSALE);
    Car car8 = new Car(6789, "Toyota Auris", 2000, Condition.USED, SaleType.FORSALE);
    Car car9 = new Car(0123, "Vauxhal", 1500, Condition.USED, SaleType.FORSALE);

    User seller = new Seller("Sid James");
    User seller2 = new Seller("Alice Wonderland");
    User buyer = new Buyer("Bob Ross", 40);

    Advert ad6 = new Advert(car6);
    Advert ad7 = new Advert(car7);
    Advert ad8 = new Advert(car8);
    Advert ad9 = new Advert(car9);

    trader.registerCar(ad6, seller2, "Blue", CarType.AUTOMATIC, CarBody.HATCHBACK, 5);
    trader.registerCar(ad7, seller, "Red", CarType.MANUAL, CarBody.MICRO, 4);
    trader.registerCar(ad8, seller, "Black", CarType.MANUAL, CarBody.PICKUP, 2);
    trader.registerCar(ad9, seller, "White", CarType.MANUAL, CarBody.SEDAN, 8);

    trader.placeOffer(ad6, buyer, 2000);
    trader.placeOffer(ad7, buyer, 4500);
    trader.placeOffer(ad8, buyer, 2000);
    
    System.out.println(trader.displayStatistics());
  }

}
