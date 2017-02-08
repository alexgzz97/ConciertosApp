package com.definityfirst.jesusgonzalez.conciertosapp;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("facebook_tour_dates_url")
    @Expose
    private String facebookTourDatesUrl;
    @SerializedName("facebook_page_url")
    @Expose
    private String facebookPageUrl;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("tracker_count")
    @Expose
    private Integer trackerCount;
    @SerializedName("upcoming_event_count")
    @Expose
    private Integer upcomingEventCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getFacebookTourDatesUrl() {
        return facebookTourDatesUrl;
    }

    public void setFacebookTourDatesUrl(String facebookTourDatesUrl) {
        this.facebookTourDatesUrl = facebookTourDatesUrl;
    }

    public String getFacebookPageUrl() {
        return facebookPageUrl;
    }

    public void setFacebookPageUrl(String facebookPageUrl) {
        this.facebookPageUrl = facebookPageUrl;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public Integer getTrackerCount() {
        return trackerCount;
    }

    public void setTrackerCount(Integer trackerCount) {
        this.trackerCount = trackerCount;
    }

    public Integer getUpcomingEventCount() {
        return upcomingEventCount;
    }

    public void setUpcomingEventCount(Integer upcomingEventCount) {
        this.upcomingEventCount = upcomingEventCount;
    }

}