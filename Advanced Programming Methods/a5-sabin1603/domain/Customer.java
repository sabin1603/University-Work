package a5;

import javax.swing.text.html.parser.TagElement;
import java.util.UUID;

public class Customer implements Identifiable<String> {
    private String id;
    private String name;
    private int age;

    public Customer(String id, String name, int age){
        this.id = id;
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

    @Override
    public String getId() {
        return id;
    }

    public String toString(){
        return "Customer: ID: " + id + " - " + "Name: " + name + " - " + "Age: " + age;
    }
}
