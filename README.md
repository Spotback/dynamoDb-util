# dynamoDb-util

This utility can be used to read and write to our dynamoDB tables. This tool is comprised of an abstract Mapper class, UserMapper class and ReferralMapper class (will be adding more mapper classes as we get more tables). To use this tool first construct a new Mapper object and pass in a config JsonObject `Mapper mapper = new ReferralMapper(JsonObject config);`.
The config should look something like this:

```json
{
    "aws_access_key_id" : "someAccessKey",
    "aws_secret_access_key" : "someSecretKey",
    "dynamo.endpoint" : "xyz",
    "region" : "xyz",
    "matching.service" : "www.xyz.com",
    "matching.uri.get" : "xyz",
    "matching.uri.post" : "xyz",
    "matching.port" : xxx
  }
```
To read or write from the database you can call one of these methods:
```java
public abstract class Mapper {

  public abstract void create(JsonObject object);
  
//this method will return the object of the table you are mapping from(user if UserMapper, referral if ReferralMapper)
  public abstract Object read(JsonObject object) ;

  public abstract void update(JsonObject object);

  public abstract void delete(JsonObject object);
}
```
# User Object
```json
{
    "email": "example@gmail.com",
    "firstName": "John",
    "freeSpots": 3,
    "lastName": "Smith",
    "phone": "15555555555",
    "profilePic": "www.google.com",
    "token": null,
    "referrals": [
        "dylancorbus@outlook.com",
        "dylancorbus@outlook.com",
        "dylancorbus@outlook.com"
    ],
    "verified": true,
    "car": {
        "color": "default",
        "make": "default",
        "model": "default",
        "year": "default"
    }
}
```
# Referral Object
```json
{
    "email": "example@gmail.com",
    "referralCode" : "someCode"
}
```
To get a this project into your build:

# Step 1. Add the JitPack repository to your build file
**maven**

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
# Step 2. Add the dependency
	<dependency>
	    <groupId>com.github.Spotback</groupId>
	    <artifactId>dynamoDb-util</artifactId>
	    <version>Tag</version>
	</dependency>
