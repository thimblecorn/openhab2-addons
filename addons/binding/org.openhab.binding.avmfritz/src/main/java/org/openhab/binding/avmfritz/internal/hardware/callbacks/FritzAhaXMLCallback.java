/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.avmfritz.internal.hardware.callbacks;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.openhab.binding.avmfritz.internal.ahamodel.DevicelistModel;
import org.openhab.binding.avmfritz.internal.hardware.FritzahaWebInterface;

/**
 * 
 * @author Christoph Weitkamp
 * 
 */
public class FritzAhaXMLCallback extends FritzAhaReauthCallback {
	/**
	 * JAXBContext
	 */
	public static final JAXBContext jaxbContext = initContext();

	/**
	 * 
	 * @return
	 */
	private static JAXBContext initContext() {
		JAXBContext jaxbc = null;
		try {
			jaxbc = JAXBContext.newInstance(DevicelistModel.class);
		} catch (JAXBException e) {
			// TODO
			jaxbc = null;
		}
		return jaxbc;
	}

	/**
	 * Constructor
	 * 
	 * @param path
	 * @param args
	 * @param webIface
	 * @param httpMethod
	 * @param retries
	 */
	public FritzAhaXMLCallback(String path, String args, FritzahaWebInterface webIface, Method httpMethod,
			int retries) {
		super(path, args, webIface, httpMethod, retries);
	}
}
