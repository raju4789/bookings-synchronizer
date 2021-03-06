package com.ogado.synchronizer.models;

import java.util.List;

public class OTABookings {
	private int httpStatus;
	private List<BookingInfo> bookings;
	private String errorMessage;
	
	public OTABookings() {
		super();
	}

	public OTABookings(int httpStatus, List<BookingInfo> bookings, String errorMessage) {
		super();
		this.httpStatus = httpStatus;
		this.bookings = bookings;
		this.errorMessage = errorMessage;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<BookingInfo> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingInfo> bookings) {
		this.bookings = bookings;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "FilteredBookings [httpStatus=" + httpStatus + ", bookings=" + bookings + ", errorMessage="
				+ errorMessage + "]";
	}
		
}
