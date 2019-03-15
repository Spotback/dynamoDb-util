package io.spotback.util;

import io.spotback.pojos.Users;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
        JsonObject jo = new JsonObject("{\n" +
                "    \"email\": \"dylancorbus@outlook.com\",\n" +
                "    \"firstName\": \"Dylan\",\n" +
                "    \"freeSpots\": 1,\n" +
                "    \"lastName\": \"Corbus\",\n" +
                "    \"phone\": null,\n" +
                "    \"profilePic\": \"www.google.com\",\n" +
                "    \"referrals\": [],\n" +
                "    \"verified\": false,\n" +
                "    \"car\": {\n" +
                "        \"color\": \"default\",\n" +
                "        \"make\": \"default\",\n" +
                "        \"model\": \"default\",\n" +
                "        \"year\": \"default\"\n" +
                "    }\n" +
                "}");
        Users user = jo.mapTo(Users.class);
        JsonObject update = new JsonObject("{\n" +
                "\t\"firstName\": \"Dylan2\",\n" +
                "\t\"freeSpots\": 1,\n" +
                "\t\"car\": {\n" +
                "\t\t\"color\": \"red\",\n" +
                "\t\t\"make\": \"bmw\",\n" +
                "\t\t\"model\": \"3.30\",\n" +
                "\t\t\"year\": \"1997\"\n" +
                "\t}\n" +
                "}");
        update.stream().forEach(field -> user.updateFields(field.getKey(), field.getValue()));
        System.out.println(user.toString());
    }
}
