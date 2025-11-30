package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Patient extends Person {
    private final StringProperty birthday;

    public Patient(String name, String id, String birthday, String gender, String contactNumber) {
        super(name, id, gender, contactNumber);
        this.birthday = new SimpleStringProperty(birthday);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    @Override
    public String toCSV() {
        return getName() + "," + getId() + "," + getBirthday() + "," + getGender() + "," + getContactNumber();
    }
}