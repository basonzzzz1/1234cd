package model;

public class Staff {
    private int id;
    private String name;
    private float  salary;
    private String email;
    private String address;
    private int phoneNumber;
    private Department department;

    public Staff(int id, String name, float salary, String email, String address, int phoneNumber, Department department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    public Staff(String name, float salary, String email, String address, int phoneNumber, Department department) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}