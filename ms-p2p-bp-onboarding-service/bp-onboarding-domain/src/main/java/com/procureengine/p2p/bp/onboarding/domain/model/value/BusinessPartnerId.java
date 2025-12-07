package com.procureengine.p2p.bp.onboarding.domain.model.value;

import java.util.Objects;

public final class BusinessPartnerId {

	private final String value;

	public BusinessPartnerId(String value) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException("BusinessPartnerId cannot be null or empty");
		}
		this.value = value;
	}

	public static BusinessPartnerId of(String value) {
		return new BusinessPartnerId(value);
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof BusinessPartnerId other))
			return false;
		return Objects.equals(this.value, other.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
