package dbpkg;

public class Employee {
    private int id;
    private String fName;
    private String mName;
    private String lName;
    private String email;
    private String phone;


    public Employee() {
    }

    ;

    public Employee(int id, String fName, String mName, String lName, String email, String phone) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public String getFName() {
        return fName;
    }

    public String getMName() {
        return mName;
    }

    public String getLName() {
        return lName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
