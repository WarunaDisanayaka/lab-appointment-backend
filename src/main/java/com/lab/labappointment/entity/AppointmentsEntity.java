package com.lab.labappointment.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "Appointments", schema = "labappointment")
public class AppointmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentId;

    @Column(name = "AppointmentTime")
    private Timestamp appointmentTime;

    @Column(name = "AppointmentNumber")
    private Integer appointmentNumber;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    private PatientsEntity patient;

    // Constructors, getters, setters, equals, hashCode, etc.

    // Constructors
    public AppointmentsEntity() {
    }

    public AppointmentsEntity(Timestamp appointmentTime, Integer appointmentNumber, PatientsEntity patient) {
        this.appointmentTime = appointmentTime;
        this.appointmentNumber = appointmentNumber;
        this.patient = patient;
    }

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getAppointmentNumber() {
        return appointmentNumber;
    }

    public void setAppointmentNumber(Integer appointmentNumber) {
        this.appointmentNumber = appointmentNumber;
    }

    public PatientsEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientsEntity patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointmentsEntity that = (AppointmentsEntity) o;

        if (appointmentId != that.appointmentId) return false;
        if (appointmentTime != null ? !appointmentTime.equals(that.appointmentTime) : that.appointmentTime != null)
            return false;
        if (appointmentNumber != null ? !appointmentNumber.equals(that.appointmentNumber) : that.appointmentNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appointmentId;
        result = 31 * result + (appointmentTime != null ? appointmentTime.hashCode() : 0);
        result = 31 * result + (appointmentNumber != null ? appointmentNumber.hashCode() : 0);
        return result;
    }
}
