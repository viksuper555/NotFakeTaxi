package com.notfaketaxi.models.responses;

import java.util.List;
public class GoogleApiResponse {
    // import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
    // import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */

    public List<GeocodedWaypoint> geocoded_waypoints;
    public List<Route> routes;
    public String status;
    public String error_message;

    public static class GeocodedWaypoint{
        public String geocoder_status;
        public String place_id;
        public List<String> types;
    }

    public static class Northeast{
        public double lat;
        public double lng;
    }

    public static class Southwest{
        public double lat;
        public double lng;
    }

    public static class Bounds{
        public Northeast northeast;
        public Southwest southwest;
    }

    public static class Distance{
        public String text;
        public int value;
    }

    public static class Duration{
        public String text;
        public int value;
    }

    public static class EndLocation{
        public double lat;
        public double lng;
    }

    public static class StartLocation{
        public double lat;
        public double lng;
    }

    public static class Polyline{
        public String points;
    }

    public static class Step{
        public Distance distance;
        public Duration duration;
        public EndLocation end_location;
        public String html_instructions;
        public Polyline polyline;
        public StartLocation start_location;
        public String travel_mode;
        public String maneuver;
    }

    public static class Leg{
        public Distance distance;
        public Duration duration;
        public String end_address;
        public EndLocation end_location;
        public String start_address;
        public StartLocation start_location;
        public List<Step> steps;
        public List<Object> traffic_speed_entry;
        public List<Object> via_waypoint;
    }

    public static class OverviewPolyline{
        public String points;
    }

    public static class Route{
        public Bounds bounds;
        public String copyrights;
        public List<Leg> legs;
        public OverviewPolyline overview_polyline;
        public String summary;
        public List<Object> warnings;
        public List<Object> waypoint_order;
    }



}
