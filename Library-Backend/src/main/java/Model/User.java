package Model;

public class User {

    private int idUser;
    private String name;
    private String lastName;
    private String email;
    private String phone;

    public User() {}

    public User(int idUser, String name, String lastName , String email, String phone) {
        this.idUser = idUser;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String lastName) {
        this. lastName = lastName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this. lastName = lastName;
    }


}

