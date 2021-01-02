package com.ogado.synchronizer.client;

public class APIURL {
	private String otaFetchURL;
	private String supplierURL;
	private String otaAmendURL;

	public APIURL() {
	}

	public APIURL(String otaFetchURL, String supplierURL, String otaAmendURL) {
		super();
		this.otaFetchURL = otaFetchURL;
		this.supplierURL = supplierURL;
		this.otaAmendURL = otaAmendURL;
	}

	public String getOtaFetchURL() {
		return otaFetchURL;
	}

	public void setOtaFetchURL(String otaFetchURL) {
		this.otaFetchURL = otaFetchURL;
	}

	public String getSupplierURL() {
		return supplierURL;
	}

	public void setSupplierURL(String supplierURL) {
		this.supplierURL = supplierURL;
	}

	public String getOtaAmendURL() {
		return otaAmendURL;
	}

	public void setOtaAmendURL(String otaAmendURL) {
		this.otaAmendURL = otaAmendURL;
	}

}
