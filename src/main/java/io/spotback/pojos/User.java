package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName="Users")
public class User {
  Car CarObject;
  private String email;
  private String firstName;
  private int freeSpots;
  private String lastName;
  private String password;
  private String phone;
  private String profilePic;
  private String stripeToken;
  private String pushToken;
  List<String> referrals;
  private boolean verified;

  private double balance;
  private long created_time;


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
  public String getProfilePic() {
    return profilePic;
  }
  public void setProfilePic(String profilePic) {
    this.profilePic = profilePic;
  }

  @DynamoDBAttribute(attributeName="stripeToken")
  public String getStripeToken() {
    return stripeToken;
  }
  public void setStripeToken(String token) {
    this.stripeToken = token;
  }

  @DynamoDBAttribute(attributeName="pushToken")
  public String getPushToken() {
    return pushToken;
  }
  public void setPushToken(String token) {
    this.pushToken = token;
  }

  @DynamoDBAttribute(attributeName="balance")
  public double getBalance() { return balance; }
  public void setBalance(double balance) {
    this.balance = balance;
  }
  @DynamoDBAttribute(attributeName="created_time")
  public long getCreated_time() { return created_time; }
  public void setCreated_time(long created_time) {
    this.created_time = created_time;
  }

  @DynamoDBAttribute(attributeName="verified")
  public boolean getVerified() {
    return verified;
  }
  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  public void updateFields(String key, Object value) {
    switch (key) {
      case "email" : {
        setEmail(StringUtils.lowerCase(value.toString()));
        return;
      }
      case "password" : {
        setPassword(value.toString());
        return;
      }
      case "firstName" : {
        setFirstName(value.toString());
        return;
      }
      case "lastName" : {
        setLastName(value.toString());
        return;
      }
      case "phone" : {
        setPhone(value.toString());
        return;
      }
      case "profilePic" : {
        setProfilePic(value.toString());
        return;
      }
      case "pushToken" : {
        setPushToken(value.toString());
        return;
      }
      case "stripeToken" : {
        setStripeToken(value.toString());
        return;
      }
      case "car" : {
        setCar(new JsonObject(value.toString()).mapTo(Car.class));
        return;
      }
    }
  }

  @Override
  public String toString() {
    return JsonObject.mapFrom(this).encodePrettily();
  }
}

