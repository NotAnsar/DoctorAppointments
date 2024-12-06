package com.example.doctorappointments;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Appointment {
    private Integer IDAppointment;
    private Integer IDDoctor;
    private Integer IDPatient;
    private Timestamp AppointmentDate;
    private Double Price;
    private Integer Paye;
    private String Status;
    private String Service;

    public Appointment(Integer IDAppointment, Integer IDDoctor, Integer IDPatient, Timestamp appointmentDate, Double price, Integer paye, String status, String service) {
        this.IDAppointment = IDAppointment;
        this.IDDoctor = IDDoctor;
        this.IDPatient = IDPatient;
        this.AppointmentDate = appointmentDate;
        this.Price = price;
        this.Paye = paye;
        this.Status = status;
        this.Service = service;
    }

    public Integer getIDAppointment() {
        return IDAppointment;
    }

    public void setIDAppointment(Integer IDAppointment) {
        this.IDAppointment = IDAppointment;
    }

    public Integer getIDDoctor() {
        return IDDoctor;
    }

    public void setIDDoctor(Integer IDDoctor) {
        this.IDDoctor = IDDoctor;
    }

    public Integer getIDPatient() {
        return IDPatient;
    }

    public void setIDPatient(Integer IDPatient) {
        this.IDPatient = IDPatient;
    }

    public Timestamp getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.AppointmentDate = appointmentDate;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        this.Price = price;
    }

    public Integer getPaye() {
        return Paye;
    }

    public void setPaye(Integer paye) {
        this.Paye = paye;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        this.Service = service;
    }

    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(AppointmentDate);
    }

    public String getFormattedTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(AppointmentDate);
    }

}
