package com.exoplanet.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Exoplanet {

    @JsonProperty
    private String PlanetIdentifier;
    @JsonProperty
    private int TypeFlag;
    @JsonProperty
    private int HostStarTempK;

    @JsonProperty
    private String DiscoveryYear;

    @JsonProperty
    private double RadiusJpt;

    public String getPlanetIdentifier() {
        return PlanetIdentifier;
    }

    public void setPlanetIdentifier(String planetIdentifier) {
        PlanetIdentifier = planetIdentifier;
    }

    public int getTypeFlag() {
        return TypeFlag;
    }

    public void setTypeFlag(int typeFlag) {
        TypeFlag = typeFlag;
    }

    public int getHostStarTempK() {
        return HostStarTempK;
    }

    public void setHostStarTempK(int hostStarTempK) {
        HostStarTempK = hostStarTempK;
    }

    public String getDiscoveryYear() {
        return DiscoveryYear;
    }

    public void setDiscoveryYear(String discoveryYear) {
        DiscoveryYear = discoveryYear;
    }

    public double getRadiusJpt() {
        return RadiusJpt;
    }

    public void setRadiusJpt(double radiusJpt) {
        RadiusJpt = radiusJpt;
    }


}
