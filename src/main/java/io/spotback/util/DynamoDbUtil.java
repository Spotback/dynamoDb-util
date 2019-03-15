package io.spotback.util;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import io.spotback.pojos.Referrals;
import io.spotback.pojos.Users;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class DynamoDbUtil {
  private final Logger LOGGER = LoggerFactory.getLogger(DynamoDbUtil.class);
  private AmazonDynamoDB client;
  private DynamoDB dynamoDB;
  private Table referralTable;
  public Table userTable;
  private DynamoDBMapper mapper;

  public DynamoDbUtil(JsonObject config) {
    init(config);
  }

  private void init(JsonObject config) {
//    AWSCredentials credentials = new DefaultAWSCredentialsProviderChain().getCredentials();
//    client = new AmazonDynamoDBClient(credentials);
    client = AmazonDynamoDBClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
      .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getJsonObject(Constants.AWS_CONFIGURATION_KEY).getString(Constants.DYNAMO), config.getJsonObject(Constants.AWS_CONFIGURATION_KEY).getString(Constants.TABLE_REGION)))
      .build();
    mapper = new DynamoDBMapper(client);
  }

  public AmazonDynamoDB getClient(JsonObject config) {
    LOGGER.info("Attempting to make connection to DynamoDB.");
    if(client == null) {
      client = AmazonDynamoDBClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getJsonObject(Constants.AWS_CONFIGURATION_KEY).getString(Constants.DYNAMO), config.getJsonObject(Constants.AWS_CONFIGURATION_KEY).getString(Constants.TABLE_REGION)))
        .build();
      return client;
    } else {
      return client;
    }
  }

  public void createUser(JsonObject user) {
    Users item = user.mapTo(Users.class);
    item.setVerified(false);
    mapper.save(item);
  }

  public void updateUser(JsonObject user) {
    Users updateUser = user.mapTo(Users.class);
    mapper.save(updateUser);
  }

  public void deleteUser(JsonObject dltUser) {
    Users user = dltUser.mapTo(Users.class);
    mapper.delete(user);
  }

  public Users readUser(JsonObject user) {
    DynamoDBMapperConfig config = new DynamoDBMapperConfig(
      DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
    Users updatedItem = mapper.load(Users.class, user.getString("email"), config);
    return updatedItem;
  }

  public Referrals readReferral(JsonObject referral) {
    DynamoDBMapperConfig config = new DynamoDBMapperConfig(
      DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
    Referrals updatedItem = mapper.load(Referrals.class, referral.getString("referralCode"), config);
    return updatedItem;
  }

  public DynamoDB getDynamoDB() {
    if(dynamoDB == null) {
      return dynamoDB = new DynamoDB(client);
    } else {
      return dynamoDB;
    }
  }

  public Table getUserTable() {
    if(userTable == null) {
      return userTable = dynamoDB.getTable(Constants.USER_TABLE);
    } else {
      return userTable;
    }
  }

  public Table getReferralTable() {
    if(referralTable == null) {
      return referralTable = dynamoDB.getTable(Constants.REFERRAL_TABLE);
    } else {
      return referralTable;
    }
  }

  public Table setConnectionsReferrals(JsonObject config) {
    if(client == null && dynamoDB == null && referralTable == null) {
      getClient(config);
      getDynamoDB();
      getReferralTable();
      return referralTable;
    } else {
      return referralTable;
    }
  }

  public Table setConnectionsUsers(JsonObject config) {
    if(client == null && dynamoDB == null && userTable == null) {
      getClient(config);
      getDynamoDB();
      return getUserTable();
    } else {
      return getUserTable();
    }
  }

  public void closeConnection() {
    if(client != null && dynamoDB != null && referralTable != null && userTable != null) {
      client.shutdown();
      dynamoDB.shutdown();
      userTable = null;
      referralTable = null;
      client = null;
      dynamoDB = null;
    }
  }
}


