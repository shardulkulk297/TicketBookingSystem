package entity;

public class Venue {

    private String venue_name;
    private String address;

    public Venue(){}

    public Venue(String venue_name, String address){
        this.venue_name = venue_name;
        this.address = address;
    }


    public String getVenue_name() {
        return venue_name;
    }
    public String getAddress(){
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public void displayVenueDetails(){
        System.out.println("Venue Name: " +venue_name);
        System.out.println("Venue Address: "+ address);
    }


}
