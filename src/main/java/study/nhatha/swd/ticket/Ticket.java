package study.nhatha.swd.ticket;

import java.util.Date;

public class Ticket {
  private int id;
  private String code;
  private String name;
  private String tourCode;
  private String tourName;
  private String trainCode;
  private String seatCode;
  private Date createdAt;
  private float price;
  private Customer customer;

  public Ticket(int id, String code, String name, String tourCode, String tourName, String trainCode, String seatCode, Date createdAt, float price) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.tourCode = tourCode;
    this.tourName = tourName;
    this.trainCode = trainCode;
    this.seatCode = seatCode;
    this.createdAt = createdAt;
    this.price = price;
  }

  public Ticket(int id, String code, String name, String tourCode, String tourName, String trainCode, String seatCode, Date createdAt, float price, Customer customer) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.tourCode = tourCode;
    this.tourName = tourName;
    this.trainCode = trainCode;
    this.seatCode = seatCode;
    this.createdAt = createdAt;
    this.price = price;
    this.customer = customer;
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

  public String getTourCode() {
    return tourCode;
  }

  public void setTourCode(String tourCode) {
    this.tourCode = tourCode;
  }

  public String getTourName() {
    return tourName;
  }

  public void setTourName(String tourName) {
    this.tourName = tourName;
  }

  public String getTrainCode() {
    return trainCode;
  }

  public void setTrainCode(String trainCode) {
    this.trainCode = trainCode;
  }

  public String getSeatCode() {
    return seatCode;
  }

  public void setSeatCode(String seatCode) {
    this.seatCode = seatCode;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return String.format(
        "%5d %10s %10s %10s %10s %10s %10s %10s %10s %70s",
        id, code, name, tourCode, tourName, trainCode, seatCode, price, createdAt, customer
    );
  }

  class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String permanentAddress;

    public Customer(String firstName, String lastName, String phoneNumber, String permanentAddress) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
      this.permanentAddress = permanentAddress;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public String getPermanentAddress() {
      return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
      this.permanentAddress = permanentAddress;
    }

    @Override
    public String toString() {
      return String.format(
          "%10s %10s %10s %10s",
          firstName, lastName, phoneNumber, permanentAddress
      );
    }
  }
}
