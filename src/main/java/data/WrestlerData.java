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
    public String license;
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
        this.license = year;
        this.card = card;

    }
    //TODO use one method for two sportsmens, maybe delete
   /* public  void wrestler (WrestlerData wrestler1){
        this.lastName = wrestler1.lastName;
        this.firstName = wrestler1.firstName;
        this.dateOfBirth = wrestler1.dateOfBirth;
        this.middleName = wrestler1.middleName;
        this.regionFirst = wrestler1.regionFirst;
        this.regionSecond = wrestler1.regionSecond ;
        this.fstFirst = wrestler1.fstFirst;
        this.fstSecond = wrestler1.fstSecond;
        this.trainerFirst = wrestler1.trainerFirst;
        this.trainerSecond = wrestler1.trainerSecond;
        this.style = wrestler1.style;
        this.age = wrestler1.age;
        this.license = wrestler1.license;
        this.card = wrestler1.card;
    }*/
}

