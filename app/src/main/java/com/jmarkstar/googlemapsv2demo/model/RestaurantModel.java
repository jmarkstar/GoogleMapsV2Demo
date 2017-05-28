package com.jmarkstar.googlemapsv2demo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jmarkstar on 27/05/2017.
 */
public class RestaurantModel implements Parcelable {

    public static final String TABLE_NAME = "restaurant";
    public static final String ID_FIELD = "_id";
    public static final String NAME_FIELD = "name";
    public static final String DESCRIPTION_FIELD = "description";
    public static final String LATITUDE_FIELD = "latitude";
    public static final String LONGITUDE_FIELD = "longitude";

    private Integer id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;

    public RestaurantModel() {}

    public RestaurantModel(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



    @Override public String toString() {
        return "RestaurantModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    protected RestaurantModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<RestaurantModel> CREATOR = new Parcelable.Creator<RestaurantModel>() {
        @Override public RestaurantModel createFromParcel(Parcel source) {
            return new RestaurantModel(source);
        }

        @Override public RestaurantModel[] newArray(int size) {
            return new RestaurantModel[size];
        }
    };
}
