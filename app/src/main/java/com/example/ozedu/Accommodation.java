package com.example.ozedu;

public class Accommodation {

    String Id, name, address, phone,contactPerson;

    Accommodation(String Id,String name,String phone, String address, String contactPerson){
        this.Id = Id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.contactPerson = contactPerson;
    }

    //all the getters
    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    //all the setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
}



