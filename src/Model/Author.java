package Model;

public class Author {

    private int idAuthor;
    private String name;
    private String lastName;
    private String nationality;

    public Author() {}

    public Author(int idAuthor, String name, String lastName , String nationality) {
        this.idAuthor = idAuthor;
        this.name = name;
        this. lastName = lastName;
        this.nationality = nationality;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this. lastName = lastName;
    }


    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}

