package study.nhatha.swd.train;

public class Train {
  private int id;
  private String code;
  private String name;
  private int seatNum;
  private float seatPrice;

  public Train(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Train(int id, String code, String name) {
    this.id = id;
    this.code = code;
    this.name = name;
  }

  public Train(int id, String code, String name, int seatNum, float seatPrice) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.seatNum = seatNum;
    this.seatPrice = seatPrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSeatNum() {
    return seatNum;
  }

  public void setSeatNum(int seatNum) {
    this.seatNum = seatNum;
  }

  public float getSeatPrice() {
    return seatPrice;
  }

  public void setSeatPrice(float seatPrice) {
    this.seatPrice = seatPrice;
  }

  @Override
  public String toString() {
    return String.format("%5d %10s %10s %5d %10f", id, code, name, seatNum, seatPrice);
  }
}
