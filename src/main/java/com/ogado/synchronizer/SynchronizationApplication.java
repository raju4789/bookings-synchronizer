package com.ogado.synchronizer;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.ogado.synchronizer.constants.ApplicationConstants;
import com.ogado.synchronizer.services.BookingsSynchronizationService;
import com.ogado.synchronizer.services.IBookingsSynchronizationService;

public class SynchronizationApplication {
	private static Logger log = Logger.getLogger(SynchronizationApplication.class);

	public static void main(String[] args) {
		bookingsSynchronizer();
	}

	public static void bookingsSynchronizer() {
		TimerTask task = new TimerTask() {
			public void run() {
				try {
					IBookingsSynchronizationService synchronizationService = new BookingsSynchronizationService();
					synchronizationService.synchronizeBookings();
					log.info("bookings synchronization running");
				} catch (Exception e) {
					log.error("bookings synchronization stopped tempororily");
				}

			}
		};
		Timer timer = new Timer("Bookings Synchronizer");

		timer.schedule(task, 0, ApplicationConstants.TIMER_INTERVAL);
	}

}
