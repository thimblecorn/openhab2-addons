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
@XmlType(propOrder = { "tist", "tsoll", "absenk", "komfort", "lock", "devicelock", "errorcode", "batterylow",
		"nextchange" })
public class HeatingModel {
	public static final BigDecimal TEMP_FACTOR = new BigDecimal("0.5");
	public static final BigDecimal TEMP_MIN = new BigDecimal("8.0");
	public static final BigDecimal TEMP_MAX = new BigDecimal("28.0");
	public static final BigDecimal TEMP_OFF = new BigDecimal("253.0");
	public static final BigDecimal TEMP_ON = new BigDecimal("254.0");
	public static final BigDecimal BATTERY_OFF = BigDecimal.ZERO;
	public static final BigDecimal BATTERY_ON = BigDecimal.ONE;

	protected BigDecimal tist;
	protected BigDecimal tsoll;
	protected BigDecimal absenk;
	protected BigDecimal komfort;
	protected BigDecimal lock;
	protected BigDecimal devicelock;
	protected String errorcode;
	protected BigDecimal batterylow;
	protected Nextchange nextchange;

	public BigDecimal getTist() {
		if (tist == null) {
			return BigDecimal.ZERO;
		} else if (tist.compareTo(TEMP_ON) == 0) {
			return TEMP_MAX;
		} else if (tist.compareTo(TEMP_OFF) == 0) {
			return TEMP_MIN;
		} else {
			return tist.multiply(TEMP_FACTOR);
		}
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

	public BigDecimal getDevicelock() {
		return devicelock;
	}

	public void setDevicelock(BigDecimal devicelock) {
		this.devicelock = devicelock;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public BigDecimal getBatterylow() {
		return batterylow;
	}

	public void setBatterylow(BigDecimal batterylow) {
		this.batterylow = batterylow;
	}

	public Nextchange getNextchange() {
		return nextchange;
	}

	public void setNextchange(Nextchange value) {
		this.nextchange = value;
	}

	public String toString() {
		return new ToStringBuilder(this).append("tist", this.getTist()).append("tsoll", this.getTsoll())
				.append("absenk", this.getAbsenk()).append("komfort", this.getKomfort()).append("lock", this.getLock())
				.append("errorcode", this.getErrorcode()).append("batterylow", this.getBatterylow())
				.append("nextchange", this.getNextchange()).toString();
	}

	@XmlType(name = "", propOrder = { "endperiod", "tchange" })
	public static class Nextchange {

		protected int endperiod;
		protected byte tchange;

		/**
		 * Ruft den Wert der endperiod-Eigenschaft ab.
		 * 
		 */
		public int getEndperiod() {
			return endperiod;
		}

		/**
		 * Legt den Wert der endperiod-Eigenschaft fest.
		 * 
		 */
		public void setEndperiod(int value) {
			this.endperiod = value;
		}

		/**
		 * Ruft den Wert der tchange-Eigenschaft ab.
		 * 
		 */
		public byte getTchange() {
			return tchange;
		}

		/**
		 * Legt den Wert der tchange-Eigenschaft fest.
		 * 
		 */
		public void setTchange(byte value) {
			this.tchange = value;
		}

		public String toString() {
			return new ToStringBuilder(this).append("endperiod", this.getEndperiod())
					.append("tchange", this.getTchange()).toString();
		}

	}
}
