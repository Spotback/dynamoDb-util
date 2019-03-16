package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Referrals")
public class Referral {
  private String referralCode;
  private String email;

  public Referral() {}

  public Referral(String referralCode, String email) {
    this.referralCode = referralCode;
    this.email = email;
  }

  public Referral(String referralCode) {
    this.referralCode = referralCode;
  }

  @DynamoDBHashKey(attributeName="referralCode")
  public String getReferralCode() {
    return referralCode;
  }
  public void setReferralCode(String referralCode) {
    this.referralCode = referralCode;
  }

  @DynamoDBAttribute(attributeName="email")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}
