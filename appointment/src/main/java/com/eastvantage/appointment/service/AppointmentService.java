package com.eastvantage.appointment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eastvantage.appointment.controller.AppointmentController;
import com.eastvantage.appointment.entity.AppointmentEntity;
import com.eastvantage.appointment.exception.NotFoundException;
import com.eastvantage.appointment.repository.AppointmentRepository;


/**
 * 
 * @author ritik
 *
 */

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentService.class);
	
	public List<AppointmentEntity> getAllAppointments() {
		LOGGER.info("[AppointmentService] - getAllAppointments - Starts");
		
		List<AppointmentEntity> appointments = new ArrayList<>();
		appointmentRepository.findAll().forEach(appointments::add);
		
		if(appointments.isEmpty()) {
			throw new NotFoundException(HttpStatus.BAD_REQUEST.toString(), "No appointments available");
		}
		
		LOGGER.info("[AppointmentService] - getAllAppointments - Ends");
		return appointments;
	}
	
	public AppointmentEntity getAppointmentById(Integer id) {
		LOGGER.info("[AppointmentService] - getAppointmentById - Starts");
		
		AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElse(null);
		if(appointmentEntity == null) {
			throw new NotFoundException(HttpStatus.BAD_REQUEST.toString(), "No appointments available with the provided ID");
		}
		
		LOGGER.info("[AppointmentService] - getAppointmentById - Ends");
		return appointmentEntity;
	}
	
	public List<AppointmentEntity> getAppointmentsByDateRange(Date startDate, Date endDate) {
		LOGGER.info("[AppointmentService] - getAppointmentsByDateRange - Starts");
		
		List<AppointmentEntity> appointments = appointmentRepository.findByDateTimeBetween(startDate, endDate);
		if(appointments.isEmpty()) {
			throw new NotFoundException(HttpStatus.BAD_REQUEST.toString(), "No appointments available in the provided date range");
		}
		
		LOGGER.info("[AppointmentService] - getAppointmentsByDateRange - Ends");
		return appointments;
	}
	
	public ResponseEntity<String> addAppointment(AppointmentEntity appointmentEntity) {
		LOGGER.info("[AppointmentService] - addAppointment - Starts");
		appointmentRepository.save(appointmentEntity);
		LOGGER.info("[AppointmentService] - addAppointment - Ends");
		return ResponseEntity.ok("Appointment added.");
	}
	
	public ResponseEntity<String> updateAppointment(String id, AppointmentEntity appointmentEntity) {
		LOGGER.info("[AppointmentService] - updateAppointment - Starts");
		AppointmentEntity oldAppointmentEntity = new AppointmentEntity();
		
		try {
			oldAppointmentEntity.setId(Integer.parseInt(id));
			oldAppointmentEntity.setDateTime(appointmentEntity.getDateTime());
			oldAppointmentEntity.setDuration(appointmentEntity.getDuration());
			oldAppointmentEntity.setName(appointmentEntity.getName());
			oldAppointmentEntity.setPurpose(appointmentEntity.getPurpose());
			
			appointmentRepository.save(oldAppointmentEntity);
		} catch(Exception e) {
			LOGGER.error(e.toString());
		}
		
		LOGGER.info("[AppointmentService] - updateAppointment - Ends");
		return ResponseEntity.ok("Appointment updated.");
		
	}
	
	public ResponseEntity<String> deleteAppointment(Integer id) {
		LOGGER.info("[AppointmentService] - deleteAppointment - Starts");
		appointmentRepository.deleteById(id);
		LOGGER.info("[AppointmentService] - deleteAppointment - Ends");
		return ResponseEntity.ok("Appointment removed.");
	}
}
