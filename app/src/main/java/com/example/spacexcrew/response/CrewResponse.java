package com.example.spacexcrew.response;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.spacexcrew.databases.Converter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "crew")
public class CrewResponse {
    @NonNull
    @PrimaryKey
    @Expose
    @SerializedName("id")
    private String id;
    @Expose
    @SerializedName("status")
    private String status;
    @TypeConverters(Converter.class)
    @Expose
    @SerializedName("launches")
    private List<String> launches;
    @Expose
    @SerializedName("wikipedia")
    private String wikipedia;
    @Expose
    @SerializedName("image")
    private String image;
    @Expose
    @SerializedName("agency")
    private String agency;
    @Expose
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getLaunches() {
        return launches;
    }

    public void setLaunches(List<String> launches) {
        this.launches = launches;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
