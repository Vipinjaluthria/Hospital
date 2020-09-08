package com.example.hospital.Models;

public class Appointment {

    private String doctorName ,doctorHospital,appointmentId,appointmentTime,appointmentAddress,appointmentDoctorImage;
    private int appointmentFees ;
    private Boolean IsAppointmentComplete,IsAppointmentFeesPaid,IsReminderSet;






    public Appointment(String doctorName, String doctorHospital, String appointmentId, String appointmentTime, String appointmentAddress, int appointmentFees, Boolean isAppointmentComplete, Boolean isAppointmentFeesPaid, Boolean isReminderSet,String appointmentDoctorImage) {
        this.doctorName = doctorName;
        this.doctorHospital = doctorHospital;
        this.appointmentId = appointmentId;
        this.appointmentTime = appointmentTime;
        this.appointmentAddress = appointmentAddress;
        this.appointmentFees = appointmentFees;
        IsAppointmentComplete = isAppointmentComplete;
        IsAppointmentFeesPaid = isAppointmentFeesPaid;
        IsReminderSet = isReminderSet;
        this.appointmentDoctorImage = appointmentDoctorImage;

    }

    public Boolean getAppointmentComplete() {
        return IsAppointmentComplete;
    }

    public Boolean getAppointmentFeesPaid() {
        return IsAppointmentFeesPaid;
    }

    public Boolean getReminderSet() {
        return IsReminderSet;
    }

    public int getAppointmentFees() {
        return appointmentFees;
    }

    public String getAppointmentAddress() {
        return appointmentAddress;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getDoctorHospital() {
        return doctorHospital;
    }

    public String getDoctorName() {
        return doctorName;
    }



    public String getAppointmentDoctorImage() {
        return appointmentDoctorImage;
    }
}
