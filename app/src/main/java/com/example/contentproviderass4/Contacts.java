package com.example.contentproviderass4;

public class Contacts {
    String id,name , number , email,photo,details;

    public Contacts( String id , String name , String number , String email , String photo , String details ) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.photo = photo;
        this.details = details;
    }

    public Contacts( String name , String photo ) {
        this.name = name;
        this.photo = photo;
    }

    public String getId( ) {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName( ) {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getNumber( ) {
        return number;
    }

    public void setNumber( String number ) {
        this.number = number;
    }

    public String getEmail( ) {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPhoto( ) {
        return photo;
    }

    public void setPhoto( String photo ) {
        this.photo = photo;
    }

    public String getDetails( ) {
        return details;
    }

    public void setDetails( String details ) {
        this.details = details;
    }
}
