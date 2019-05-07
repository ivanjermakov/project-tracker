package com.gmail.ivanjermakov1.projecttracker.core.entity.nontable;

import java.time.LocalDate;

public interface ProjectActivity {
	
	LocalDate getDay();
	
	Integer getActivityAmount();
	
}
