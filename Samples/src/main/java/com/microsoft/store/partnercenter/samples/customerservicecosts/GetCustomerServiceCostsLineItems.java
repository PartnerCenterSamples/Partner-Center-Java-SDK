// -----------------------------------------------------------------------
// <copyright file="GetCustomerServiceCostsLineItems.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.customerservicecosts;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.servicecosts.ServiceCostLineItem;
import com.microsoft.store.partnercenter.models.servicecosts.ServiceCostsBillingPeriod;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
 * Gets Customer Service Costs Line Items.
 */
public class GetCustomerServiceCostsLineItems extends BasePartnerScenario 
{
    public GetCustomerServiceCostsLineItems (IScenarioContext context) 
    {
		super("Get customer service costs line items", context);
	}

    /**
     * Executes the scenario.
     */
	@Override
    protected void runScenario() 
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        String customerId = this.obtainCustomerId("Enter the ID of the customer to get service costs line items");

        this.getContext().getConsoleHelper().startProgress("Getting customer service costs line items");
        
        ResourceCollection<ServiceCostLineItem> customerServiceCostsLineItems = partnerOperations.getCustomers().byId(customerId).getServiceCosts().byBillingPeriod(ServiceCostsBillingPeriod.MostRecent).getLineItems().get();

        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().writeObject(customerServiceCostsLineItems, "Customer Service Costs Line Items");
	}
}