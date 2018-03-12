package com.esic.bmc.model;

import com.google.gson.Gson;

public class ESICIncident {

	private String Incident_Number;
	private String Impact;
	private String Urgency;
	private String Priority;
	private String Status;
	private String Site_type;
	private String State_Province;
	private String location;
	private String Summary;
	private String Assigned_Support_Organization;
	private String Assigned_group;
	private String assignee;
	private String Categorization_Tier_1;
	private String Categorization_Tier_2;
	private String Categorization_Tier_3;
	private String first_name;
	private String last_name;
	private String Submit_Date;
	private String Submitter;
	private String last_resolved_date;
	private String closed_date;
	private String SLM_Status;
	private String Total_transfers;
	private String Customer_site;
	private String direct_contact_site;
	private String Incident_Type;
	private String Reported_Source;
	private String Reported_date;
	private String Detailed_Decription;
	private String City;
	private String Street;
	private String Status_Reason;
	private String vendor_ticket_Number;
	private String vendor_group;
	private String phone_number;
	private String voip_number;
	private String email_timestamp;
	private String customer_email;
	private String last_modified_by;
	private String last_modified_date;
	private String resolution;
	private String kb_id;
	private String NO_OF_DAYS;
	private String CLASSIFICATION_OF_DAYS;

	public String getIncident_Number() {
		return Incident_Number;
	}

	public void setIncident_Number(String incident_Number) {
		Incident_Number = incident_Number;
	}

	public String getImpact() {
		return Impact;
	}

	public void setImpact(String impact) {
		Impact = impact;
	}

	public String getUrgency() {
		return Urgency;
	}

	public void setUrgency(String urgency) {
		Urgency = urgency;
	}

	public String getPriority() {
		return Priority;
	}

	public void setPriority(String priority) {
		Priority = priority;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getSite_type() {
		return Site_type;
	}

	public void setSite_type(String site_type) {
		Site_type = site_type;
	}

	public String getState_Province() {
		return State_Province;
	}

	public void setState_Province(String state_Province) {
		State_Province = state_Province;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSummary() {
		return Summary;
	}

	public void setSummary(String summary) {
		Summary = summary;
	}

	public String getAssigned_Support_Organization() {
		return Assigned_Support_Organization;
	}

	public void setAssigned_Support_Organization(String assigned_Support_Organization) {
		Assigned_Support_Organization = assigned_Support_Organization;
	}

	public String getAssigned_group() {
		return Assigned_group;
	}

	public void setAssigned_group(String assigned_group) {
		Assigned_group = assigned_group;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getCategorization_Tier_1() {
		return Categorization_Tier_1;
	}

	public void setCategorization_Tier_1(String categorization_Tier_1) {
		Categorization_Tier_1 = categorization_Tier_1;
	}

	public String getCategorization_Tier_2() {
		return Categorization_Tier_2;
	}

	public void setCategorization_Tier_2(String categorization_Tier_2) {
		Categorization_Tier_2 = categorization_Tier_2;
	}

	public String getCategorization_Tier_3() {
		return Categorization_Tier_3;
	}

	public void setCategorization_Tier_3(String categorization_Tier_3) {
		Categorization_Tier_3 = categorization_Tier_3;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getSubmit_Date() {
		return Submit_Date;
	}

	public void setSubmit_Date(String submit_Date) {
		Submit_Date = submit_Date;
	}

	public String getSubmitter() {
		return Submitter;
	}

	public void setSubmitter(String submitter) {
		Submitter = submitter;
	}

	public String getLast_resolved_date() {
		return last_resolved_date;
	}

	public void setLast_resolved_date(String last_resolved_date) {
		this.last_resolved_date = last_resolved_date;
	}

	public String getClosed_date() {
		return closed_date;
	}

	public void setClosed_date(String closed_date) {
		this.closed_date = closed_date;
	}

	public String getSLM_Status() {
		return SLM_Status;
	}

	public void setSLM_Status(String sLM_Status) {
		SLM_Status = sLM_Status;
	}

	public String getTotal_transfers() {
		return Total_transfers;
	}

	public void setTotal_transfers(String total_transfers) {
		Total_transfers = total_transfers;
	}

	public String getCustomer_site() {
		return Customer_site;
	}

	public void setCustomer_site(String customer_site) {
		Customer_site = customer_site;
	}

	public String getDirect_contact_site() {
		return direct_contact_site;
	}

	public void setDirect_contact_site(String direct_contact_site) {
		this.direct_contact_site = direct_contact_site;
	}

	public String getIncident_Type() {
		return Incident_Type;
	}

	public void setIncident_Type(String incident_Type) {
		Incident_Type = incident_Type;
	}

	public String getReported_Source() {
		return Reported_Source;
	}

	public void setReported_Source(String reported_Source) {
		Reported_Source = reported_Source;
	}

	public String getReported_date() {
		return Reported_date;
	}

	public void setReported_date(String reported_date) {
		Reported_date = reported_date;
	}

	public String getDetailed_Decription() {
		return Detailed_Decription;
	}

	public void setDetailed_Decription(String detailed_Decription) {
		Detailed_Decription = detailed_Decription;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getStatus_Reason() {
		return Status_Reason;
	}

	public void setStatus_Reason(String status_Reason) {
		Status_Reason = status_Reason;
	}

	public String getVendor_ticket_Number() {
		return vendor_ticket_Number;
	}

	public void setVendor_ticket_Number(String vendor_ticket_Number) {
		this.vendor_ticket_Number = vendor_ticket_Number;
	}

	public String getVendor_group() {
		return vendor_group;
	}

	public void setVendor_group(String vendor_group) {
		this.vendor_group = vendor_group;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getVoip_number() {
		return voip_number;
	}

	public void setVoip_number(String voip_number) {
		this.voip_number = voip_number;
	}

	public String getEmail_timestamp() {
		return email_timestamp;
	}

	public void setEmail_timestamp(String email_timestamp) {
		this.email_timestamp = email_timestamp;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getLast_modified_by() {
		return last_modified_by;
	}

	public void setLast_modified_by(String last_modified_by) {
		this.last_modified_by = last_modified_by;
	}

	public String getLast_modified_date() {
		return last_modified_date;
	}

	public void setLast_modified_date(String last_modified_date) {
		this.last_modified_date = last_modified_date;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getKb_id() {
		return kb_id;
	}

	public void setKb_id(String kb_id) {
		this.kb_id = kb_id;
	}

	public String getNO_OF_DAYS() {
		return NO_OF_DAYS;
	}

	public void setNO_OF_DAYS(String nO_OF_DAYS) {
		NO_OF_DAYS = nO_OF_DAYS;
	}

	public String getCLASSIFICATION_OF_DAYS() {
		return CLASSIFICATION_OF_DAYS;
	}

	public void setCLASSIFICATION_OF_DAYS(String cLASSIFICATION_OF_DAYS) {
		CLASSIFICATION_OF_DAYS = cLASSIFICATION_OF_DAYS;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
