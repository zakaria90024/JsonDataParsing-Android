package com.androwep.androidjsondataparsing.jsondatalistview;

public class DemoStudent  {
    private String name;
    private String department;
    private String country;

    public DemoStudent(String country) {
        this.country = country;

    }

    public DemoStudent() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
