package auction;

public enum CarType {
  MANUAL(1), AUTOMATIC(2);
  
  private int carType;
  

  private CarType(int carType) {
    this.carType = carType;
  }
  
  private int getCarType() {
    return this.carType;
  }
}
