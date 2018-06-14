// -----------------------------------------------------------------------
// <copyright file="GetCustomerProduct.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.customerproducts;

import java.text.MessageFormat;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.products.Product;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
 * A scenario that retrieves product details for a customer.
 */
public class GetCustomerProduct extends BasePartnerScenario 
{
    public GetCustomerProduct (IScenarioContext context) 
    {
		super("Get product for customer", context);
	}

    /**
     * Executes the scenario.
     */
	@Override
    protected void runScenario() 
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        String customerId = this.obtainCustomerId("Enter the ID of the corresponding customer");
        String productId = this.obtainProductId("Enter the ID of the corresponding product");

        this.getContext().getConsoleHelper().startProgress(
            MessageFormat.format("Getting product {0} details for customer {1}", productId, customerId));
        
        Product product  = partnerOperations.getCustomers().byId(customerId).getProducts().byId(productId).get();

        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().writeObject(product, "Product details for customer");
	}
}