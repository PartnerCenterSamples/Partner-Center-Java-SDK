// -----------------------------------------------------------------------
// <copyright file="OrderLineItemLinks.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.orders;

import com.microsoft.store.partnercenter.models.Link;

/**
 * Bundles the links for an order line item.
 */
public class OrderLineItemLinks
{
    /**
     * Gets or sets the subscription link for the order line item.
     */
    private Link __Subscription;

    public Link getSubscription()
    {
        return __Subscription;
    }

    public void setSubscription( Link value )
    {
        __Subscription = value;
    }

}
