package com.vn.vti.core02.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {
	private static Logger logger = Logger.getLogger(ProcessFiles.class);
	
	public Timestamp convertTimeTransaction(String time) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			Date date = formatter.parse(time);
			java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
			return timeStampDate;
		} catch (ParseException e) {
			logger.error(e.getMessage());
			return null;
		}
	}
}
