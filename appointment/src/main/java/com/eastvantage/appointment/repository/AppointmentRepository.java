package com.eastvantage.appointment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eastvantage.appointment.entity.AppointmentEntity;

/**
 * 
 * @author ritik
 *
 */

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer>{

	 List<AppointmentEntity> findByDateTimeBetween(Date startDate, Date endDate);
}
