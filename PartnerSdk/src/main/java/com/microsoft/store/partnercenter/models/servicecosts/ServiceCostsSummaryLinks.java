// -----------------------------------------------------------------------
// <copyright file="ServiceCostsSummaryLinks.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.servicecosts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.store.partnercenter.models.Link;
import com.microsoft.store.partnercenter.models.StandardResourceLinks;

/**
 * Holds the operations related to a customer's service costs.
 */
public class ServiceCostsSummaryLinks 
    extends StandardResourceLinks
{
    /***
     * Gets or sets the link to the service cost line items.
     */
	@JsonProperty( "serviceCostLineItems" )
    private Link __ServiceCostLineItems;

    public Link getServiceCostLineItems()
    {
        return __ServiceCostLineItems;
    }

    public void setServiceCostLineItems( Link value )
    {
    	__ServiceCostLineItems = value;
    }
}
