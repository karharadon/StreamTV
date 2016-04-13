package lastochkin.streamTV.data;

public class Wrestler {

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


    public Wrestler(String lastName, String firstName, String dateOfBirth, String middleName,
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
}

