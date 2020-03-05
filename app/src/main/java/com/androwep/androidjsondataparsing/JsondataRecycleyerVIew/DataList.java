package com.androwep.androidjsondataparsing.JsondataRecycleyerVIew;

public class DataList {

    String name;
    String department;

    public DataList() {
    }

    public DataList(String name, String department) {
        this.name = name;
        this.department = department;
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
}
