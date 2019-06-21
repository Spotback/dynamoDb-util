package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Push-Message-Configuration")
public class PushMessage {
    private String messageId;
    private String title;
    private String body;

    public PushMessage(String messageId, String title, String body) {
        this.messageId = messageId;
        this.title = title;
        this.body = body;
    }

    public PushMessage() {
    }

    @DynamoDBHashKey(attributeName="messageId")
    public void setMessageId(String messageId){
        this.messageId = messageId;
    }
    public String getMessageId(){
        return messageId;
    }

    @DynamoDBAttribute(attributeName="title")
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }

    @DynamoDBAttribute(attributeName="body")
    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return body;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "messageId = '" + messageId + '\'' +
                        ",title = '" + title + '\'' +
                        ",body = '" + body + '\'' +
                        "}";
    }
}
