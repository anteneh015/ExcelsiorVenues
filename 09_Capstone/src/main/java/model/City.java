package model;

public class City {
    private Long id;
    private String name;
    private String stateAbbreviation;

    public Long getCityId() {
        return id;
    }

    public void setCityId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return name;
    }

    public void setCityName(String name) {
        this.name = name;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }
}
