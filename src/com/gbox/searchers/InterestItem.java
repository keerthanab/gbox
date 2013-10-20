package com.gbox.searchers;

public class InterestItem {

    private String name;
    private String category;
    private String genre;
    private String description;
    
    
    public InterestItem(){
        name = "";
        category = "";
        genre = "";
        description = "";
    }
    
    public InterestItem(String someName, String someCate, String someGenre){
        name = someName.toLowerCase();
        category = someCate.toLowerCase();
        genre = someGenre.toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String newGenre) {
        String tmp= newGenre.replaceAll("(\\p{Punct}|[^'-])+", "+");
        this.genre = tmp.replaceAll("  ", " ");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
