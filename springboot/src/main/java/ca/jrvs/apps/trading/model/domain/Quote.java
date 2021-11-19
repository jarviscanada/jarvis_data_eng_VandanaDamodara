package ca.jrvs.apps.trading.model.domain;



public class Quote implements Entity<String>{
  private String ticker;
  private Double last_Price;
  private Double bid_Price;
  private Integer bid_Size;
  private Double ask_Price;
  private Integer ask_Size;

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }


  public Double getLast_Price() {
    return last_Price;
  }

  public void setLast_Price(Double last_Price) {
    this.last_Price = last_Price;
  }

  public Double getBid_Price() {
    return bid_Price;
  }

  public void setBid_Price(Double bid_Price) {
    this.bid_Price = bid_Price;
  }

  public Integer getBid_Size() {
    return bid_Size;
  }

  public void setBid_Size(Integer bid_Size) {
    this.bid_Size = bid_Size;
  }

  public Double getAsk_Price() {
    return ask_Price;
  }

  public void setAsk_Price(Double ask_Price) {
    this.ask_Price = ask_Price;
  }

  public Integer getAsk_Size() {
    return ask_Size;
  }

  public void setAsk_Size(Integer ask_Size) {
    this.ask_Size = ask_Size;
  }

  @Override
  public String getId() {
    return ticker;
  }

  @Override
  public void setId(String s) {
    this.ticker = s;
  }
}
