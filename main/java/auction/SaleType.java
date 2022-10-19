package auction;

public enum SaleType {
  FORSALE(1), AUCTION(2);
  
private int saleType;
  
  private SaleType(int saleType) {
    this.saleType = saleType;
  }
  
  private int getSaleType() {
    return this.saleType;
  }
}
