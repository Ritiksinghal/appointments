package com.eastvantage.appointment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eastvantage.appointment.entity.AppointmentEntity;
import com.eastvantage.appointment.entity.DateRange;
import com.eastvantage.appointment.service.AppointmentService;

/**
 * 
 * @author ritik
 *
 */

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);
	
	
	@GetMapping("/appointments")
	public List<AppointmentEntity> getAllAppointments() {
		LOGGER.info("[AppointmentController] - getAllAppointments - Starts");
		return appointmentService.getAllAppointments();
	}
	
	@GetMapping("/appointment/{id}")
    public AppointmentEntity getAppointmentById(@PathVariable Integer id) {
		LOGGER.info("[AppointmentController] - getAppointmentById - Starts");
        return appointmentService.getAppointmentById(id);
    }
	
	@GetMapping("/appointments/dateRange")
    public List<AppointmentEntity> getAppointmentsByDateRange(@RequestBody DateRange dateRange) {
		LOGGER.info("[AppointmentController] - getAppointmentsByDateRange - Starts");
        return appointmentService.getAppointmentsByDateRange(dateRange.getStartDate(), dateRange.getEndDate());
    }
	
    @PostMapping("/{appointments}")
    public ResponseEntity<String> addAppointment(@RequestBody AppointmentEntity appointmentEntity) {
    	LOGGER.info("[AppointmentController] - addAppointment - Starts");
        return appointmentService.addAppointment(appointmentEntity);
    }

    @PutMapping("/appointment/{id}")
    public ResponseEntity<String> updateAppointment(@PathVariable String id, @RequestBody AppointmentEntity appointmentEntity) {
    	LOGGER.info("[AppointmentController] -  - Starts");
        return appointmentService.updateAppointment(id, appointmentEntity);
    }

    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Integer id) {
    	LOGGER.info("[AppointmentController] - deleteAppointment - Starts");
        return appointmentService.deleteAppointment(id);
    }
}
