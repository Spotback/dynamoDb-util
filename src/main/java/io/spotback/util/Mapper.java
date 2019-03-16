package io.spotback.util;

import io.vertx.core.json.JsonObject;

public abstract class Mapper {

  abstract void create(JsonObject object);

  abstract Object read(JsonObject object) ;

  abstract void update(JsonObject object);

  abstract void delete(JsonObject object);
}


