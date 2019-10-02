package io.spotback.util;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import io.spotback.pojos.Fee;
import io.spotback.pojos.PushMessage;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class FeeMapper extends Mapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeeMapper.class);

    private AmazonDynamoDBAsync client;
    private DynamoDBTableMapper<Fee, String, ?> mapper;

    public FeeMapper(JsonObject config) {
        client = AmazonDynamoDBAsyncClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain())
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(config.getString(Constants.DYNAMO), config.getString(Constants.TABLE_REGION)))
                .build();
        mapper = new DynamoDBMapper(client, new DynamoDBMapperConfig(DynamoDBMapperConfig.SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES)).newTableMapper(Fee.class);
    }



    @Override
    public void create(JsonObject object) {
        try {
            Fee fee = object.mapTo(Fee.class);
            mapper.saveIfNotExists(fee);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Fee read(JsonObject object) {
        return mapper.load(object.getString("feeName"));
    }

    @Override
    public void update(JsonObject object) {
        Fee fee = object.mapTo(Fee.class);
        mapper.saveIfExists(fee);
    }

    @Override
    public void delete(JsonObject object) {
        Fee fee = object.mapTo(Fee.class);
        mapper.deleteIfExists(fee);
    }
}
