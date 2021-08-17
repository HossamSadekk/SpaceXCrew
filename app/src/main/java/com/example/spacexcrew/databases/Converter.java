package com.example.spacexcrew.databases;

import androidx.room.TypeConverter;

import com.example.spacexcrew.response.CrewResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {



  /*  @TypeConverter
    public String fromValuesToList(List<String> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>(){}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public List<String> toOptionValuesList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(value, type);
    }*/

    @TypeConverter
    public String fromList(List<String> list)
    {
        Gson gson =  new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public List<String> toList(String string)
    {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(string,listType);
    }

}
