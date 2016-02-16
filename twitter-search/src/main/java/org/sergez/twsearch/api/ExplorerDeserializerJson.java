package org.sergez.twsearch.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by simpumind on 2/16/16.
 */
public class ExplorerDeserializerJson implements JsonDeserializer<TrendList> {

    @Override
    public TrendList deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
            throws JsonParseException {
        TrendList explorer = new TrendList();
        JsonArray jsonArray = je.getAsJsonArray();
        Gson gson = new Gson();
        for(JsonElement element : jsonArray){
            JsonObject jsonObject = element.getAsJsonObject();
            Trend nataCenter = gson.fromJson(jsonObject.get("trends"), Trend.class);
            //explorer.add(nataCenter);
        }
        return explorer;

    }
}

