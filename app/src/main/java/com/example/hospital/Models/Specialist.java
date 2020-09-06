package com.example.hospital.Models;

public class Specialist  {
    String Img;

    public String getImg() {
        return Img;
    }

    public String getText() {
        return text;
    }

    String text;

    public Specialist(String img, String text) {
        Img = img;
        this.text = text;
    }

}


