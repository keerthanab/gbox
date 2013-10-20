package com.gbox.searchers;

import java.util.*;

public class InterestList {
    
    private ArrayList<InterestItem> items = new ArrayList<InterestItem>();
    HashMap<String,Integer> genreProperties = new HashMap<String, Integer>();
    HashMap<String,Integer> nameProperties = new HashMap<String, Integer>();
    
    private String groupName;
    Random gen = new Random();
    
    public InterestList(String groupName){
        this.setGroupName(groupName);
        if(groupName.equals(AllInterestCollection.SPORT_GROUP)){
            genreProperties.put("team", 0);
            genreProperties.put("league", 0);
            genreProperties.put("sport", 0);
        }
    }

    public void addItem(InterestItem item){
        if(groupName.equals(AllInterestCollection.SPORT_GROUP)) addSport(item);
        else if(groupName.equals(AllInterestCollection.PERSON_GROUP)
                ||groupName.equals(AllInterestCollection.SPECIALTY_GROUP)){
            item.setGenre(item.getCategory());
            addRegular(item);
        }
        else if(groupName.equals(AllInterestCollection.MOVIE_GROUP)
                ||groupName.equals(AllInterestCollection.MUSIC_GROUP))  
            addMovieMusic(item);
        else addRegular(item);
    }
    
    public void addRegular(InterestItem item){
        if(!item.getName().equals("")){
            String s = item.getName();
            if(!nameProperties.containsKey(s)) nameProperties.put(s, 1);
            else nameProperties.put(s, nameProperties.get(s)+1);
        
        }
    }
    
    public void addMovieMusic(InterestItem item){
        items.add(item);
        if(!item.getGenre().equals("")){
            String[] genres = item.getGenre().split(" ");
            for(String s:genres){
                if(!genreProperties.containsKey(s)) genreProperties.put(s, 1);
                else genreProperties.put(s, genreProperties.get(s)+1);
            }
        }
        
        if(!item.getName().equals("")){
                String s = item.getName();
                if(!nameProperties.containsKey(s)) nameProperties.put(s, 1);
                else nameProperties.put(s, nameProperties.get(s)+1);
            
        }
    }
    
    public void addSport(InterestItem item){
        items.add(item);
        
        if(item.getCategory().contains("team"))
            genreProperties.put("team", genreProperties.get("team")+1);
        else if(item.getCategory().contains("league")) 
            genreProperties.put("league", genreProperties.get("league")+1);
        else if(item.getCategory().equals("sport"))
            genreProperties.put("sport", genreProperties.get("sport")+1);
        
        
        if(!item.getName().equals("")){
            String s = item.getName();
            if(!nameProperties.containsKey(s)) nameProperties.put(s, 1);
            else nameProperties.put(s, nameProperties.get(s)+1);
        
        }
    }
    
    
    public String getBest(HashMap<String,Integer> properties){
        String best = "";
        int max = -1;
        for(String k:properties.keySet()){
            if(properties.get(k) > max && (gen.nextInt(5)>0)){
                best = k;
                max = properties.get(k);
            }else if (properties.get(k) == max && (gen.nextInt(2)==0)){
                best = k;
                max = properties.get(k);
            }
        }
        return best;
    }
    
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public ArrayList<InterestItem> getAllItemInCategory(){
        return items;
    }

    public String getSuggestionForCategory(){
        String suggest = "";
        if(groupName.equals(AllInterestCollection.MOVIE_GROUP)){
            switch(gen.nextInt(3)){
            case 0: suggest = getBest(nameProperties) + " film"; break;
            case 1: suggest = getBest(genreProperties) + " dvd movie"; break;
            default: suggest = "movie tickets";break;
            }
        } 
        else if(groupName.equals(AllInterestCollection.MUSIC_GROUP)){
            if (gen.nextInt(2)==0) suggest = getBest(nameProperties) + " music collection";
            else suggest = getBest(genreProperties) + " music cd";
        } 
        else if(groupName.equals(AllInterestCollection.GAME_GROUP)){
            switch(gen.nextInt(3)){
            case 0: suggest = getBest(nameProperties) + " console game"; break;
            case 1: suggest = getBest(nameProperties) + " pc game"; break;
            default: suggest = "game magazine";break;
            }
        } 
        else if(groupName.equals(AllInterestCollection.SPORT_GROUP)){
            if(getBest(genreProperties).equals("team")){
                String theItem = "";
                switch(gen.nextInt(7)){
                case 0: theItem = " jersey"; break;
                case 1: theItem = " souvenir"; break;
                case 2: theItem = " jacket"; break;
                case 3: theItem = " flag"; break;
                case 4: theItem = " accessories"; break;
                case 5: theItem = " gift"; break;
                default: theItem = " poster"; break;
                }
                suggest = internalSearchSport("team", getBest(nameProperties)) + theItem;
            }else if(getBest(genreProperties).equals("sport")){
                String theItem = "";
                switch(gen.nextInt(3)){
                case 0: theItem = " gear"; break;
                case 1: theItem = " accessories"; break;
                default: theItem = " equipment"; break;
                }
                suggest = internalSearchSport("sport", getBest(nameProperties)) + theItem;
            }
            else{
                String theItem = "";
                switch(gen.nextInt(5)){
                case 0: theItem = " game"; break;
                case 1: theItem = " sport gear"; break;
                case 2: theItem = " magazine"; break;
                case 3: theItem = " souvenir"; break;
                default: theItem = " poster"; break;
                }
                suggest = internalSearchSport("league", getBest(nameProperties)) + theItem;
            }
                
        } else if(groupName.equals(AllInterestCollection.PRODUCT_GROUP)){
            String theItem = "";
            switch(gen.nextInt(5)){
            case 0: theItem = " product"; break;
            case 1: theItem = " service"; break;
            case 2: theItem = " deal"; break;
            case 3: theItem = " special"; break;
            default: theItem = " sale"; break;
            }
            suggest = getBest(nameProperties) + theItem;
        }else if(groupName.equals(AllInterestCollection.CLOTHING_GROUP)){
            String theItem = "";
            switch(gen.nextInt(5)){
            case 0: theItem = " wear"; break;
            case 1: theItem = " casual"; break;
            case 2: theItem = " formal wear"; break;
            case 3: theItem = " deal"; break;
            default: theItem = " sale"; break;
            }
            suggest = getBest(nameProperties) + theItem;
        } else if(groupName.equals(AllInterestCollection.PERSON_GROUP)){
            String theItem = "";
            switch(gen.nextInt(4)){
            case 0: theItem = " book"; break;
            case 1: theItem = " autograph"; break;
            case 2: theItem = " poster"; break;
            default: theItem = " biography"; break;
            }
            suggest = getBest(nameProperties) + theItem;
        } else if(groupName.equals(AllInterestCollection.BOOK_GROUP)){
            String theItem = "";
            switch(gen.nextInt(5)){
            case 0: theItem = " used"; break;
            case 1: theItem = " classic"; break;
            case 2: theItem = " film"; break;
            case 3: theItem = " book"; break;
            default: theItem = " collection"; break;
            }
            suggest = getBest(nameProperties) + theItem;
        } else if(groupName.equals(AllInterestCollection.SPECIALTY_GROUP)){
            String theItem = "";
            switch(gen.nextInt(3)){
            case 0: theItem = " book"; break;
            case 1: theItem = " career"; break;
            default: theItem = " referrence"; break;
            }
            suggest = getBest(nameProperties) + theItem;
        }
        return suggest;
    }
    
    public int size(){
        return items.size();
    }
    
    public String internalSearchSport(String id, String name){
        for(InterestItem i:items)
            if(i.getName().equals(name) && i.getCategory().contains(id)) return name;
        for(InterestItem i:items)
            if(i.getCategory().contains(id)) return i.getName();
        return "";
    }
    
    public InterestItem get(int index){
        return items.get(index);
    }
    
}
