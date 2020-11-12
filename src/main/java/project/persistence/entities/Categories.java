package project.persistence.entities;

public enum Categories{
    INNLENT("Innlent"),
    ERLENT("Erlent"),
    SPORT("Íþróttir"),
    VIDSKIPTI("Viðskipti"),
    TOV("Tækni og vísindi"),
    MATUR("Matur"),
    FERDALOG("Ferðalög"),
    BORN("Börn"),
    SMARTLAND("Smartland"),
    BILAR("Bílar"),
    FOLK("Fólk");

    private String name;

    public String getName(){
        return name;
    }

    private Categories(String name){
        this.name = name;
    }


}