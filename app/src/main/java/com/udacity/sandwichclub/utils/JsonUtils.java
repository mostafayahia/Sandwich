package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject rootObject = new JSONObject(json);
            String mainName = rootObject.getJSONObject("name").getString("mainName");
            JSONArray alsoKnownAs = rootObject.getJSONObject("name").getJSONArray("alsoKnownAs");
            String placeOfOrigin = rootObject.getString("placeOfOrigin");
            String description = rootObject.getString("description");
            String image = rootObject.getString("image");
            JSONArray ingredients = rootObject.getJSONArray("ingredients");

            return new Sandwich(mainName, jsonArrayToList(alsoKnownAs), placeOfOrigin, description, image, jsonArrayToList(ingredients));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "error in parsing json");
            return null;
        }

    }

    private static List<String> jsonArrayToList(JSONArray jsonArray) throws JSONException {

        ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonArray.get(i).toString());
        }

        return list;
    }
}
