package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

@DynamoDBTable(tableName="Push-Message-Configuration")
public class PushMessage {
  private String messageId;
  private String title;
  private String body;




  @DynamoDBHashKey(attributeName="messageId")
  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  @DynamoDBAttribute(attributeName="title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @DynamoDBAttribute(attributeName="body")
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return JsonObject.mapFrom(this).encodePrettily();
  }
}



