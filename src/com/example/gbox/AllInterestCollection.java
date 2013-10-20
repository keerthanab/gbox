package com.example.gbox;

import java.util.ArrayList;
import java.util.Random;

public class AllInterestCollection {

    public static final int MOVIE = 0;
    public static final int MUSIC = 1;
    public static final int GAME = 2;
    public static final int SPORT = 3;
    public static final int PRODUCT = 4;
    public static final int CLOTHING = 5;
    public static final int PERSON = 6;
    public static final int BOOK = 7;
    public static final int SPECIALTY = 8;
    public static final int NUM_OF_CATEGOTY = 9;
    
    public static final String MOVIE_GROUP = "movie";
    public static final String MUSIC_GROUP = "music";
    public static final String GAME_GROUP = "game";
    public static final String SPORT_GROUP = "sport";
    public static final String PRODUCT_GROUP = "product";
    public static final String CLOTHING_GROUP = "clothing";
    public static final String PERSON_GROUP = "person";
    public static final String BOOK_GROUP = "book";
    public static final String SPECIALTY_GROUP = "specialty";
    
    int[] counts = new int[NUM_OF_CATEGOTY];
    double[] chances =  new double[NUM_OF_CATEGOTY];
    
    
    private ArrayList<InterestList> interestCollections = new ArrayList<InterestList>();
    
    private Random gen = new Random();
    
    public AllInterestCollection(){
        interestCollections.add(new InterestList(MOVIE_GROUP));
        interestCollections.add(new InterestList(MUSIC_GROUP));
        interestCollections.add(new InterestList(GAME_GROUP));
        interestCollections.add(new InterestList(SPORT_GROUP));
        interestCollections.add(new InterestList(PRODUCT_GROUP));
        interestCollections.add(new InterestList(CLOTHING_GROUP));
        interestCollections.add(new InterestList(PERSON_GROUP));
        interestCollections.add(new InterestList(BOOK_GROUP));
        interestCollections.add(new InterestList(SPECIALTY_GROUP));
    }

    public void finalizeSearch(){
        updateSize();
        calculateProperties();
    }
    
    public void addInterest(InterestItem item){
        if(item.getCategory().contains("movie")
                || item.getCategory().contains("tv")
                ||item.getCategory().contains("actor")
                ||item.getCategory().contains("director")
                ||item.getCategory().contains("producer"))
            interestCollections.get(MOVIE).addItem(item);
        else if(item.getCategory().contains("music")||item.getCategory().contains("band"))
            interestCollections.get(MUSIC).addItem(item);
        else if(item.getCategory().contains("game")) 
            interestCollections.get(GAME).addItem(item);
        else if(item.getCategory().contains("sport")) 
            interestCollections.get(SPORT).addItem(item);
        else if(item.getCategory().contains("product")||item.getCategory().contains("brand")) 
            interestCollections.get(PRODUCT).addItem(item);
        else if(item.getCategory().contains("cloth")) 
            interestCollections.get(CLOTHING).addItem(item);
        else if(item.getCategory().contains("athlete")
                ||item.getCategory().contains("figure")
                ||item.getCategory().contains("artist")
                ||item.getCategory().contains("politician")
                ||item.getCategory().contains("writer")
                ||item.getCategory().contains("comedian")
                ||item.getCategory().contains("dancer")
                ||item.getCategory().contains("entertainer")) 
            interestCollections.get(PERSON).addItem(item);
        else if(item.getCategory().contains("book")) 
            interestCollections.get(BOOK).addItem(item);
        else if(item.getCategory().contains("chemic")
                ||item.getCategory().contains("biotech")
                ||item.getCategory().contains("aerospace")
                ||item.getCategory().contains("computer")
                ||item.getCategory().contains("tech")
                ||item.getCategory().contains("automobile")
                ||item.getCategory().contains("engineering")
                ||item.getCategory().contains("energy")
                ||item.getCategory().contains("heath")
                ||item.getCategory().contains("legal")
                ||item.getCategory().contains("media")
                ||item.getCategory().contains("industr")
                ||item.getCategory().contains("politic")
                ||item.getCategory().contains("govern")
                ||item.getCategory().contains("busine")
                ||item.getCategory().contains("scienc")) 
            interestCollections.get(SPECIALTY).addItem(item);
    }
    
    public void addInterestList(InterestList list){
        interestCollections.add(list);
        if(list.getGroupName().equals("movie")) counts[MOVIE]++;
        else if(list.getGroupName().equals("music")) counts[MUSIC]++;
        else if(list.getGroupName().equals("game")) counts[GAME]++;
        else if(list.getGroupName().equals("sport")) counts[SPORT]++;
        else if(list.getGroupName().equals("product")) counts[PRODUCT]++;
        else if(list.getGroupName().equals("clothing")) counts[CLOTHING]++;
    }
    
    public void updateSize(){
        for(int i=0; i<NUM_OF_CATEGOTY; i++) counts[i] = interestCollections.get(i).size();
    }
    
    public void calculateProperties(){
        int sum = 0;
        for(int i:counts) sum+=i;
        for(int i=0; i<NUM_OF_CATEGOTY; i++){
            chances[i] = (i==0) ? ((double) counts[i]/sum) : (((double) counts[i]/sum) + chances[i-1]);
        }
    }
    
    public ArrayList<InterestList> getInterestCollection(){
        return interestCollections;
    }
    
    public String getSuggestion(){
        int index = 0;
        double prob = gen.nextDouble();
        for(int i=0; i<chances.length; i++){
            if(prob<chances[i]){
                index = i; break;
            }
        }
        return interestCollections.get(index).getSuggestionForCategory();
    }
    
}