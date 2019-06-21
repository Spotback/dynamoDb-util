package io.spotback.util;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.handlers.AsyncHandler;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import io.spotback.pojos.User;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class UserMapper extends Mapper {

    private AmazonDynamoDBAsync client;
    private DynamoDBTableMapper<User, String, ?> mapper;

    public UserMapper(JsonObject config) {
        client = AmazonDynamoDBAsyncClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getString(Constants.DYNAMO), config.getString(Constants.TABLE_REGION)))
                .build();
        mapper = new DynamoDBMapper(client, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)).newTableMapper(User.class);
    }

    public void create(JsonObject user) {
        user.put("verified", false);
        mapper.saveIfNotExists(user.mapTo(User.class));
    }

    public User read(JsonObject user) {
        return mapper.load(user.getString("email"));
    }

    public void update(JsonObject user) {
        User updateUser = user.mapTo(User.class);
        mapper.saveIfExists(updateUser);
    }

    public void delete(JsonObject dltUser) {
        User user = dltUser.mapTo(User.class);
        mapper.deleteIfExists(user);
    }

    public void updateEmail(String oldEmail, String newEmail) {
        User user = read(new JsonObject().put("email", oldEmail));
        delete(JsonObject.mapFrom(user));
        user.setEmail(newEmail);
        create(JsonObject.mapFrom(user));
    }
}


