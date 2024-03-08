package domain;

import javax.swing.text.html.parser.TagElement;

public class Customer {
    private String name;
    private int age;

    public Customer(String name, int age){
        this.name = name;
        this.age = age;
    }

    // Getters
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }


    // Setters
    public void setName(String newName){
        this.name = newName;
    }

    public void setAge(int newAge){
        this.age = newAge;
    }
}
