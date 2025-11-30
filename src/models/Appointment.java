package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
    private final StringProperty doctorName;
    private final StringProperty doctorId;
    private final StringProperty patientName;
    private final StringProperty patientId;
    private final StringProperty appointmentDate;
    private final StringProperty appointmentTime;

    public Appointment(String doctorName, String doctorId, String patientName, String patientId, String appointmentDate, String appointmentTime) {
        this.doctorName = new SimpleStringProperty(doctorName);
        this.doctorId = new SimpleStringProperty(doctorId);
        this.patientName = new SimpleStringProperty(patientName);
        this.patientId = new SimpleStringProperty(patientId);
        this.appointmentDate = new SimpleStringProperty(appointmentDate);
        this.appointmentTime = new SimpleStringProperty(appointmentTime);
    }

    public String getDoctorName() {
        return doctorName.get();
    }

    public StringProperty doctorNameProperty() {
        return doctorName;
    }

    public String getDoctorId() {
        return doctorId.get();
    }

    public StringProperty doctorIdProperty() {
        return doctorId;
    }

    public String getPatientName() {
        return patientName.get();
    }

    public StringProperty patientNameProperty() {
        return patientName;
    }

    public String getPatientId() {
        return patientId.get();
    }

    public StringProperty patientIdProperty() {
        return patientId;
    }

    public String getAppointmentDate() {
        return appointmentDate.get();
    }

    public StringProperty appointmentDateProperty() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime.get();
    }

    public StringProperty appointmentTimeProperty() {
        return appointmentTime;
    }

    public String toCSV() {
        return getDoctorName() + "," + getDoctorId() + "," + getPatientName() + "," + getPatientId() + "," + getAppointmentDate() + "," + getAppointmentTime();
    }
}