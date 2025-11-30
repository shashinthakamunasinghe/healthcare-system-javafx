package utils;

import models.Doctor;
import models.Patient;
import models.Appointment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    private static final String DOCTOR_FILE = "src/data/doctors.csv";
    private static final String PATIENT_FILE = "src/data/patients.csv";
    private static final String APPOINTMENT_FILE = "src/data/appointments.csv";

    public static boolean writeDoctorToCSV(Doctor doctor) {
        try (FileWriter writer = new FileWriter(DOCTOR_FILE, true)) {
            writer.write(doctor.toCSV() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writePatientToCSV(Patient patient) {
        try (FileWriter writer = new FileWriter(PATIENT_FILE, true)) {
            writer.write(patient.toCSV() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean writeAppointmentToCSV(Appointment appointment) {
        try (FileWriter writer = new FileWriter(APPOINTMENT_FILE, true)) {
            writer.write(appointment.toCSV() + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Doctor doctor = new Doctor(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    doctors.add(doctor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Patient patient = new Patient(parts[0], parts[1], parts[2], parts[3], parts[4]);
                    patients.add(patient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public static List<Appointment> loadAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(APPOINTMENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Appointment appointment = new Appointment(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    appointments.add(appointment);
                } else if (parts.length == 4) {
                    // For backward compatibility with old format
                    Appointment appointment = new Appointment(parts[0], parts[1], parts[2], parts[3], "", "");
                    appointments.add(appointment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    
    public static boolean deleteAppointment(Appointment appointmentToRemove) {
        List<Appointment> appointments = loadAppointments();
        boolean removed = appointments.removeIf(app -> 
            app.getDoctorId().equals(appointmentToRemove.getDoctorId()) &&
            app.getPatientId().equals(appointmentToRemove.getPatientId()) &&
            app.getAppointmentDate().equals(appointmentToRemove.getAppointmentDate()) &&
            app.getAppointmentTime().equals(appointmentToRemove.getAppointmentTime())
        );

        if (removed) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(APPOINTMENT_FILE))) {
                for (Appointment app : appointments) {
                    writer.println(app.toCSV());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return removed;
    }
}