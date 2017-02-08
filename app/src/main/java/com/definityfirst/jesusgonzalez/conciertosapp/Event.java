package com.definityfirst.jesusgonzalez.conciertosapp;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("artist_id")
    @Expose
    private Integer artistId;
    @SerializedName("artist_event_id")
    @Expose
    private Integer artistEventId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("formatted_datetime")
    @Expose
    private String formattedDatetime;
    @SerializedName("formatted_location")
    @Expose
    private String formattedLocation;
    @SerializedName("ticket_url")
    @Expose
    private String ticketUrl;
    @SerializedName("ticket_type")
    @Expose
    private String ticketType;
    @SerializedName("ticket_status")
    @Expose
    private String ticketStatus;
    @SerializedName("on_sale_datetime")
    @Expose
    private String onSaleDatetime;
    @SerializedName("facebook_rsvp_url")
    @Expose
    private String facebookRsvpUrl;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("artists")
    @Expose
    private List<Artist> artists = null;
    @SerializedName("venue")
    @Expose
    private Venue venue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public Integer getArtistEventId() {
        return artistEventId;
    }

    public void setArtistEventId(Integer artistEventId) {
        this.artistEventId = artistEventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFormattedDatetime() {
        return formattedDatetime;
    }

    public void setFormattedDatetime(String formattedDatetime) {
        this.formattedDatetime = formattedDatetime;
    }

    public String getFormattedLocation() {
        return formattedLocation;
    }

    public void setFormattedLocation(String formattedLocation) {
        this.formattedLocation = formattedLocation;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getOnSaleDatetime() {
        return onSaleDatetime;
    }

    public void setOnSaleDatetime(String onSaleDatetime) {
        this.onSaleDatetime = onSaleDatetime;
    }

    public String getFacebookRsvpUrl() {
        return facebookRsvpUrl;
    }

    public void setFacebookRsvpUrl(String facebookRsvpUrl) {
        this.facebookRsvpUrl = facebookRsvpUrl;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

}