package com.melardev.models;

import org.springframework.format.annotation.NumberFormat;

public class MockModel {
    private final double money;
    //@NumberFormat(pattern = "# -, ###")
    private double salary;

    public MockModel(double salary, double money) {
        this.salary = salary;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
