package comp3350.acmis.objects;

public class Location {
    private String city;
    private String country;
    private String airport;

    public Location(String city, String country, String airport) {
        this.city = city;
        this.country = country;
        this.airport = airport;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getAirport() {
        return airport;
    }

    @Override
    public String toString() {
        return city +", "+country;
    }


}
