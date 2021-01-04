package com.ogado.synchronizer.services;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ogado.synchronizer.SynchronizationApplication;
import com.ogado.synchronizer.client.APIURL;
import com.ogado.synchronizer.client.ClientConnectionManager;
import com.ogado.synchronizer.constants.ApplicationConstants;
import com.ogado.synchronizer.constants.HTTPStatus;
import com.ogado.synchronizer.models.BookingInfo;
import com.ogado.synchronizer.models.OTABookings;
import com.ogado.synchronizer.models.SupplierResponse;
import com.ogado.synchronizer.utils.ConfigLoader;
import com.ogado.synchronizer.utils.JsonMapper;

public class BookingsSynchronizationService implements IBookingsSynchronizationService {
	private static Logger log = Logger.getLogger(SynchronizationApplication.class);

	@Override
	public void synchronizeBookings() {

		try {
			APIURL apiURls = ConfigLoader.loadConfiguration(ApplicationConstants.ENV_API_URLS.get(System.getProperty("env")), APIURL.class);
			String OTAFetchURL = apiURls.getOtaFetchURL() + LocalDate.now().toString();
			HttpResponse<String> res = ClientConnectionManager.get(OTAFetchURL);
			OTABookings otaResponse = JsonMapper.parse(res.body(), OTABookings.class);

			if (HTTPStatus.OK == otaResponse.getHttpStatus()) {
				List<BookingInfo> bookings = otaResponse.getBookings();
				List<BookingInfo> updatedBookings = new ArrayList<BookingInfo>();
				for (BookingInfo bookingInfo : bookings) {
					String supplierURL = apiURls.getSupplierURL() + bookingInfo.getBookingReference();
					res = ClientConnectionManager.get(supplierURL);
					SupplierResponse supplierResponse = JsonMapper.parse(res.body(), SupplierResponse.class);

					if (HTTPStatus.OK == supplierResponse.getHttpStatus()) {
						BookingInfo supplierBooking = supplierResponse.getBookingInfo();
						if (supplierBooking != null && !supplierBooking.equals(bookingInfo)) {
							supplierBooking.setBookingId(bookingInfo.getBookingId());
							updatedBookings.add(supplierBooking);
						}
					}

				}

				if (updatedBookings.size() > 0) {
					String requestBody = JsonMapper.stringifyPretty(updatedBookings);
					String OTAAmendURL = apiURls.getOtaAmendURL();

					ClientConnectionManager.put(OTAAmendURL, requestBody);
					
					log.info("bookings synchronized successfully");

				}else {
					log.info("no new bookings to update");
				}
			}else {
				log.info("no new bookings to update");
			}

		} catch (Exception e) {
			log.error("failed to synchronize bookings.operation will re-tried again in some time.");
			e.printStackTrace();
		} 

	}

}
