// -----------------------------------------------------------------------
// <copyright file="GetDevices.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.devicesdeployment;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.devicesdeployment.Device;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
 * Gets devices of a device batch.
 */
public class GetDevices extends BasePartnerScenario 
{
    public GetDevices (IScenarioContext context) 
    {
		super("Get devces", context);
	}

    /**
     * Executes the scenario.
     */
	@Override
    protected void runScenario() 
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        String customerId = this.obtainCustomerId("Enter the Customer Id to get the devices for");
        String deviceBatchId = this.obtainDeviceBatchId("Enter the ID of the Device batch to retrieve the devices of");

        this.getContext().getConsoleHelper().startProgress("Querying for the devices");

        ResourceCollection<Device> devices = partnerOperations.getCustomers().byId(customerId).getDeviceBatches().byId(deviceBatchId).getDevices().get();

        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().writeObject(devices, "Devices");
	}
}