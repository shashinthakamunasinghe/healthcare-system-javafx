package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Doctor extends Person {
    private final StringProperty specialization;

    public Doctor(String name, String id, String specialization, String gender, String contactNumber) {
        super(name, id, gender, contactNumber);
        this.specialization = new SimpleStringProperty(specialization);
    }

    public String getSpecialization() {
        return specialization.get();
    }

    public StringProperty specializationProperty() {
        return specialization;
    }

    @Override
    public String toCSV() {
        return getName() + "," + getId() + "," + getSpecialization() + "," + getGender() + "," + getContactNumber();
    }
}