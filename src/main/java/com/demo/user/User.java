package com.demo.user;

public class User {
    private Integer id;
    private String name;
    private String age;
    private String address;


    public User() {

    }

    public User(Integer id, String age, String name, String address) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.address = address;
    }
    @Override
    public String toString(){
        return "User{" +
                "id='" + id +", name=" + name + "\'" +
                ", age='" + age + "\'" +
                ", address='" + address + "\'" +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
