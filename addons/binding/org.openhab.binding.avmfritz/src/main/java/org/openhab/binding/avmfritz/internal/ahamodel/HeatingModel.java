/**
 * Copyright (c) 2014-2016 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.avmfritz.internal.ahamodel;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * See {@link DevicelistModel}.
 * 
 * @author Christoph Weitkamp
 * 
 * 
 */
@XmlRootElement(name = "hkr")
@XmlType(propOrder = { "tist", "tsoll", "komfort", "absenk", "lock", "batterylow" })
public class HeatingModel {
	public static final BigDecimal TEMP_FACTOR = new BigDecimal("0.5");
	public static final BigDecimal TEMP_MIN = new BigDecimal("8.0");
	public static final BigDecimal TEMP_MAX = new BigDecimal("28.0");
	public static final BigDecimal TEMP_OFF = new BigDecimal("253.0");
	public static final BigDecimal TEMP_ON = new BigDecimal("254.0");
	public static final BigDecimal BATTERY_ON = BigDecimal.ONE;
	public static final BigDecimal BATTERY_OFF = BigDecimal.ZERO;

	private BigDecimal tist;
	private BigDecimal tsoll;
	private BigDecimal komfort;
	private BigDecimal absenk;
	private BigDecimal lock;
	private BigDecimal batterylow;

	public BigDecimal getTist() {
		return tist != null ? tist.multiply(TEMP_FACTOR) : BigDecimal.ZERO;
	}

	public void setTist(BigDecimal tist) {
		this.tist = tist;
	}

	public BigDecimal getTsoll() {
		return tsoll != null ? tsoll.multiply(TEMP_FACTOR) : BigDecimal.ZERO;
	}

	public void setTsoll(BigDecimal tsoll) {
		this.tsoll = tsoll;
	}

	public BigDecimal getKomfort() {
		return komfort != null ? komfort.multiply(TEMP_FACTOR) : BigDecimal.ZERO;
	}

	public void setKomfort(BigDecimal komfort) {
		this.komfort = komfort;
	}

	public BigDecimal getAbsenk() {
		return absenk != null ? absenk.multiply(TEMP_FACTOR) : BigDecimal.ZERO;
	}

	public void setAbsenk(BigDecimal absenk) {
		this.absenk = absenk;
	}

	public BigDecimal getLock() {
		return lock;
	}

	public void setLock(BigDecimal lock) {
		this.lock = lock;
	}

	public BigDecimal getBatterylow() {
		return batterylow;
	}

	public void setBatterylow(BigDecimal batterylow) {
		this.batterylow = batterylow;
	}

	public String toString() {
		return new ToStringBuilder(this).append("tist", this.getTist()).append("tsoll", this.getTsoll())
				.append("komfort", this.getKomfort()).append("absenk", this.getAbsenk()).append("lock", this.getLock())
				.append("batterylow", this.getBatterylow()).toString();
	}
}
