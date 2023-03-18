package com.kusithms.dongastd.domain.content.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ContentType {

	@Enumerated(EnumType.STRING)
	private Internal internal;
	@Enumerated(EnumType.STRING)
	private Surgery surgery;
	@Enumerated(EnumType.STRING)
	private Clinical clinical;
	@Enumerated(EnumType.STRING)
	private Etc etc;

	public ContentType(MedicalType medicalType) {
		if (medicalType instanceof Internal) {
			this.internal = (Internal) medicalType;
		} else if (medicalType instanceof Surgery) {
			this.surgery = (Surgery) medicalType;
		} else if (medicalType instanceof Clinical) {
			this.clinical = (Clinical) medicalType;
		} else if (medicalType instanceof Etc) {
			this.etc = (Etc) medicalType;
		}
	}
}

