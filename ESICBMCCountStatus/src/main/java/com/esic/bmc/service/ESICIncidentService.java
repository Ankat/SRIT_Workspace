package com.esic.bmc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esic.bmc.model.ESICIncident;
import com.esic.bmc.repository.ESICIncidentRepository;

@Service(value = "esicIncidentService")
public class ESICIncidentService implements IESICIncident {

	@Autowired
	private ESICIncidentRepository esicIncidentRepository;

	@Override
	public List<ESICIncident> getESICIncidentList() {
		return esicIncidentRepository.getESICIncidentList();
	}

}
