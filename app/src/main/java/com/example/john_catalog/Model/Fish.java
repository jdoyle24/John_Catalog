package com.example.john_catalog.Model;

public class Fish
{
    private String name;
    private String wiki;
    private String img_src_set;
    private String conservation_status;
    private String binomial_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public String getImg_src_set() {
        return img_src_set;
    }

    public void setImg_src_set(String img_src_set) {
        this.img_src_set = img_src_set;
    }

    public String getConservation_status() {
        return conservation_status;
    }

    public void setConservation_status(String conservation_status) {
        this.conservation_status = conservation_status;
    }

    public String getBinomial_name() {
        return binomial_name;
    }

    public void setBinomial_name(String binomial_name) {
        this.binomial_name = binomial_name;
    }
}
