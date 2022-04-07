package com.example.homebrewerproject;

public class RecipeModel {
    private int id;
    private String title;
    private String additional_ingredient;
    private double lbsHoney;
    private double startingGravity;
    private int gallons;
    private String notes;

    public RecipeModel() {
    }

    public RecipeModel(int id, String title, String additional_ingredient, double lbsHoney, double startingGravity, int gallons, String notes) {
        this.id = id;
        this.title = title;
        this.additional_ingredient = additional_ingredient;
        this.lbsHoney = lbsHoney;
        this.startingGravity = startingGravity;
        this.gallons = gallons;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return  "Title: " + title + "\n"+
                "Additional Ingredient: " + additional_ingredient + "\n" +
                "Honey (lbs): " + lbsHoney + "\n" +
                "Starting Gravity: " + startingGravity + "\n"+
                "Gallons: " + gallons + "\n" +
                "Notes: " + notes +"\n";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAdditional_ingredient() {
        return additional_ingredient;
    }

    public void setAdditional_ingredient(String additional_ingredient) {
        this.additional_ingredient = additional_ingredient;
    }

    public double getLbsHoney() {
        return lbsHoney;
    }

    public void setLbsHoney(double lbsHoney) {
        this.lbsHoney = lbsHoney;
    }

    public double getStartingGravity() { return startingGravity;}

    public void setStartingGravity(double startingGravity) {this.startingGravity = startingGravity;}

    public int getGallons() {
        return gallons;
    }

    public void setGallons(int gallons) {
        this.gallons = gallons;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
