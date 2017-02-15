/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.avmfritz.internal.hardware.callbacks;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.openhab.binding.avmfritz.internal.ahamodel.DeviceModel;
import org.openhab.binding.avmfritz.internal.ahamodel.DevicelistModel;
import org.openhab.binding.avmfritz.internal.discovery.AvmDiscoveryService;
import org.openhab.binding.avmfritz.internal.hardware.FritzahaWebInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Callback for discovering SmartHome devices connected to a FRITZ!Box
 * 
 * @author Robert Bausdorf
 * @author Christoph Weitkamp
 * 
 */
public class FritzAhaDiscoveryCallback extends FritzAhaXMLCallback {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Handler to update
	 */
	private AvmDiscoveryService service;

	/**
	 * Constructor
	 * 
	 * @param webIface
	 *            Webinterface to FRITZ!Box
	 * @param service
	 *            Discovery service to call with result.
	 */
	public FritzAhaDiscoveryCallback(FritzahaWebInterface webIface, AvmDiscoveryService service) {
		super(WEBSERVICE_PATH, "switchcmd=getdevicelistinfos", webIface, Method.GET, 1);
		this.service = service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute(int status, String response) {
		super.execute(status, response);
		logger.trace("Received discovery callback response: " + response);
		if (this.isValidRequest()) {
			try {
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				DevicelistModel model = (DevicelistModel) jaxbUnmarshaller.unmarshal(new StringReader(response));
				if (model != null) {
					for (DeviceModel device : model.getDevicelist()) {
						service.onDeviceAddedInternal(device);
					}
				} else {
					logger.warn("no model in response");
				}
			} catch (JAXBException e) {
				logger.error(e.getLocalizedMessage(), e);
			}
		} else {
			logger.info("request is invalid: " + status);
		}
	}
}
