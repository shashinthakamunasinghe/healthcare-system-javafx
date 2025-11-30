package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Person {
    protected final StringProperty name;
    protected final StringProperty id;
    protected final StringProperty gender;
    protected final StringProperty contactNumber;

    public Person(String name, String id, String gender, String contactNumber) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.gender = new SimpleStringProperty(gender);
        this.contactNumber = new SimpleStringProperty(contactNumber);
    }

    // Getters and Property methods
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    // Abstract method to be implemented by subclasses
    public abstract String toCSV();
}