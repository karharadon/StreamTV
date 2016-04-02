package data;

public class WrestlerData {

    public String lastName;
    public String firstName;
    public String dateOfBirth;
    public String middleName;
    public String regionFirst;
    public String regionSecond;
    public String fstFirst;
    public String fstSecond;
    public String trainerFirst;
    public String trainerSecond;
    public String style;
    public String age;
    public String year;
    public String card;


    public WrestlerData(String lastName, String firstName, String dateOfBirth, String middleName,
                        String regionFirst, String regionSecond, String fstFirst, String fstSecond,
                        String trainerFirst, String trainerSecond, String style, String age, String year, String card){
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.middleName = middleName;
        this.regionFirst = regionFirst;
        this.regionSecond = regionSecond;
        this.fstFirst = fstFirst;
        this.fstSecond = fstSecond;
        this.trainerFirst = trainerFirst;
        this.trainerSecond = trainerSecond;
        this.style = style;
        this.age = age;
        this.year = year;
        this.card = card;

    }
    //TODO use one method for two sportsmens, maybe delete
   /* public  void wrestler (WrestlerData wrestlerData){
        this.lastName = wrestlerData.lastName;
        this.firstName = wrestlerData.firstName;
        this.dateOfBirth = wrestlerData.dateOfBirth;
        this.middleName = wrestlerData.middleName;
        this.regionFirst = wrestlerData.regionFirst;
        this.regionSecond = wrestlerData.regionSecond ;
        this.fstFirst = wrestlerData.fstFirst;
        this.fstSecond = wrestlerData.fstSecond;
        this.trainerFirst = wrestlerData.trainerFirst;
        this.trainerSecond = wrestlerData.trainerSecond;
        this.style = wrestlerData.style;
        this.age = wrestlerData.age;
        this.year = wrestlerData.year;
        this.card = wrestlerData.card;
    }*/
}

