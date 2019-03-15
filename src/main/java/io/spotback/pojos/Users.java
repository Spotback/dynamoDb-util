package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName="Users")
public class Users {
  Car CarObject;
  private String email;
  private String firstName;
  private int freeSpots;
  private String lastName;
  private String password;
  private String phone;
  private String profilepic;
  List<String> referrals;
  private boolean verified;

  @DynamoDBAttribute(attributeName="car")
  public Car getCar() {
    return CarObject;
  }
  public void setCar(Car carObject) {
    this.CarObject = carObject;
  }

  @DynamoDBHashKey(attributeName="email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  @DynamoDBAttribute(attributeName="firstName")
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  @DynamoDBAttribute(attributeName="referrals")
  public List<String> getReferrals() {
    if(referrals == null) {
      return new ArrayList<String>();
    } else {
      return referrals;
    }
  }
  public void setReferrals(List<String> referrals) {
    this.referrals = referrals;
  }

  @DynamoDBAttribute(attributeName="freeSpots")
  public int getFreeSpots() {
    return freeSpots;
  }
  public void setFreeSpots(int freeSpots) {
    this.freeSpots = freeSpots;
  }

  @DynamoDBAttribute(attributeName="lastName")
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @DynamoDBAttribute(attributeName="password")
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  @DynamoDBAttribute(attributeName="phone")
  public String getPhone() {
    return phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }

  @DynamoDBAttribute(attributeName="profilePic")
  public String getProfilepic() {
    return profilepic;
  }
  public void setProfilepic(String profilepic) {
    this.profilepic = profilepic;
  }

  @DynamoDBAttribute(attributeName="verified")
  public boolean getVerified() {
    return verified;
  }
  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  @DynamoDBDocument
  public static class Car {
    private String color;
    private String make;
    private String model;
    private String year;

    @DynamoDBAttribute(attributeName = "color")
    public String getColor() {
      return color;
    }
    public void setColor(String color) {
      this.color = color;
    }
    @DynamoDBAttribute(attributeName = "make")
    public String getMake() {
      return make;
    }
    public void setMake(String make) {
      this.make = make;
    }
    @DynamoDBAttribute(attributeName = "model")
    public String getModel() {
      return model;
    }
    public void setModel(String model) {
      this.model = model;
    }
    @DynamoDBAttribute(attributeName = "year")
    public String getYear() {
      return year;
    }
    public void setYear(String year) {
      this.year = year;
    }
  }
}

