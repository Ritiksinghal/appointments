package com.eastvantage.appointment.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * @author ritik
 *
 */

@Data
public class DateRange {

	@DateTimeFormat(style = "dd-MM-yyyy HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private Date startDate;

	@DateTimeFormat(style = "dd-MM-yyyy HH:mm:ss")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm:ss")
    private Date endDate;
}
