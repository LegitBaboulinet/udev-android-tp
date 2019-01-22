package com.example.udevAndroidTp.classes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Client implements Serializable {
    @SerializedName("civilite")
    private String civility;
    @SerializedName("nom")
    private String name;
    @SerializedName("prenom")
    private String firstName;
    @SerializedName("adresse")
    private String address;
    @SerializedName("societe")
    private String company;
    @SerializedName("tel")
    private String phoneNumber;
    @SerializedName("mail")
    private String mail;
    @SerializedName("age")
    private int age;
    @SerializedName("siteweb")
    private String webSite;
    @SerializedName("premium")
    private boolean premium;

    public Client(String civility, String name, String firstName, String address, String company, String phoneNumber, String mail, int age, String webSite, boolean premium) {
        this.civility = civility;
        this.name = name;
        this.firstName = firstName;
        this.address = address;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.age = age;
        this.webSite = webSite;
        this.premium = premium;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
