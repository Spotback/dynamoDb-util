package io.spotback.util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTableMapper;
import io.spotback.pojos.Referral;
import io.vertx.core.json.JsonObject;


public class ReferralMapper extends Mapper {

    private AmazonDynamoDBAsync client;
    private DynamoDBTableMapper<Referral, String, ?> mapper;

    public ReferralMapper() {
        client = AmazonDynamoDBAsyncClientBuilder.defaultClient();
        mapper = new DynamoDBMapper(client).newTableMapper(Referral.class);
    }

    public void create(JsonObject referral) {
        Referral ref = referral.mapTo(Referral.class);
        mapper.saveIfNotExists(ref);
    }

    public Referral read(JsonObject referral) {
        return mapper.load(referral.getString("referralCode"));
    }

    public void update(JsonObject referral) {
        Referral updateReferral = referral.mapTo(Referral.class);
        mapper.saveIfExists(updateReferral);
    }

    public void delete(JsonObject referral) {
        Referral dltRef = referral.mapTo(Referral.class);
        mapper.deleteIfExists(dltRef);
    }
}


