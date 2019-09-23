package io.spotback.util;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import io.spotback.pojos.PushMessage;
import io.spotback.pojos.User;
import io.vertx.core.json.JsonObject;

public class PushMapper extends Mapper {

    private AmazonDynamoDBAsync client;
    private DynamoDBTableMapper<PushMessage, String, ?> mapper;

    public PushMapper(JsonObject config) {
        client = AmazonDynamoDBAsyncClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getString(Constants.DYNAMO), config.getString(Constants.TABLE_REGION)))
                .build();
        mapper = new DynamoDBMapper(client, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)).newTableMapper(PushMessage.class);
    }


    @Override
    public void create(JsonObject object) {
        mapper.saveIfNotExists(object.mapTo(PushMessage.class));
    }


    public PushMessage read(JsonObject object) {
        return mapper.load(object.getString("messageId"));
    }

    @Override
    public void update(JsonObject object) {
        mapper.saveIfExists(object.mapTo(PushMessage.class));
    }

    @Override
    public void delete(JsonObject object) {
        mapper.deleteIfExists(object.mapTo(PushMessage.class));
    }
}
