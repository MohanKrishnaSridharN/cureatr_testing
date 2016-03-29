package com.cureatr.testpackage;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CurrentTime {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String formattedDate = dateFormat.format(new Date(0)).toString();
		System.out.println(formattedDate);
		//System.out.println(dateFormat);
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm a");
		Calendar cal1 = Calendar.getInstance();
		String PresentTime= dateFormat1.format(cal1.getTime());
		System.out.println(PresentTime);
		    }
	}
