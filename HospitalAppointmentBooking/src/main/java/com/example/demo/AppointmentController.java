package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Appointment;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

@RestController
public class AppointmentController {

    private List<Appointment> appointments = new ArrayList<>();

    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody @Valid Appointment appointment) {

        appointments.add(appointment);

        return appointment;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {

        return appointments;
    }

    @GetMapping("/appointments/{appointmentId}")
    public Appointment getAppointmentById(@PathVariable Long appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId().equals(appointmentId)) {

                return appointment;
            }
        }

        return null;
    }

    @DeleteMapping("/appointments/{appointmentId}")
    public String deleteAppointment(@PathVariable Long appointmentId) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId().equals(appointmentId)) {

                appointments.remove(appointment);

                return "Appointment deleted successfully";
            }
        }

        return "Appointment not found";
    }

    @PutMapping("/appointments/{appointmentId}")
    public Appointment updateAppointment(
            @PathVariable Long appointmentId,
            @RequestBody Appointment updatedAppointment) {

        for (Appointment appointment : appointments) {

            if (appointment.getAppointmentId().equals(appointmentId)) {

                appointment.setPatientName(updatedAppointment.getPatientName());
                appointment.setDoctorName(updatedAppointment.getDoctorName());
                appointment.setDisease(updatedAppointment.getDisease());
                appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());

                return appointment;
            }
        }

        return null;
    }
}