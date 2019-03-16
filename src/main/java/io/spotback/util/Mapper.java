package io.spotback.util;

import io.vertx.core.json.JsonObject;

public abstract class Mapper {

  public abstract void create(JsonObject object);

  public abstract Object read(JsonObject object) ;

  public abstract void update(JsonObject object);

  public abstract void delete(JsonObject object);
}


