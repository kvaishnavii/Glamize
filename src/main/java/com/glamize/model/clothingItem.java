package com.glamize.model;

public class clothingItem {
    private String name;
    private String imageUrl;
    private String category;      // e.g., "Men", "Women"
    private String occasion;      // e.g., "Casual", "Formal"
    private String weatherTag;    // e.g., "Rainy", "Sunny"

    public clothingItem(String name, String imageUrl, String category, String occasion, String weatherTag) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.occasion = occasion;
        this.weatherTag = weatherTag;
    }

    
    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public String getCategory() { return category; }
    public String getOccasion() { return occasion; }
    public String getWeatherTag() { return weatherTag; }

    
}
