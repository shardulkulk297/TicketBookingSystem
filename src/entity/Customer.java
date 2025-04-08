package entity;

public class Customer {
    private String customer_name;
    private String email;
    private String phone_number;

    public Customer(){

    }

    public Customer(String customer_name, String email, String phone_number){
        this.customer_name = customer_name;
        this.email = email;
        this.phone_number = phone_number;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }


}
