package project.persistence.entities;

public enum Categories{
    INNLENT("Innlent"),
    ERLENT("Erlent"),
    SPORT("Íþróttir"),
    VIDSKIPTI("Viðskipti");

    private String name;

    Categories(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }
}