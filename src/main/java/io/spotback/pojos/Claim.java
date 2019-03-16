package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Referral")
public class Claim {
    private String userEmail;
    private String message;
}
