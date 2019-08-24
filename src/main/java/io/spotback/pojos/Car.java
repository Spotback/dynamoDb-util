package io.spotback.pojos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import io.vertx.core.json.JsonObject;

@DynamoDBDocument
public class Car {
    private String color;
    private String make;
    private String model;
    private String year;
    private String carType;

    @DynamoDBAttribute(attributeName = "color")
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    @DynamoDBAttribute(attributeName = "make")
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    @DynamoDBAttribute(attributeName = "model")
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    @DynamoDBAttribute(attributeName = "year")
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    @DynamoDBAttribute(attributeName = "carType")
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return JsonObject.mapFrom(this).encodePrettily();
    }
}
