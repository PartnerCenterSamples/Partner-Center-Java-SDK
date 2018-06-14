// -----------------------------------------------------------------------
// <copyright file="InvoiceDetail.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.invoices;

import com.microsoft.store.partnercenter.models.Link;
import com.microsoft.store.partnercenter.models.ResourceBaseWithLinks;
import com.microsoft.store.partnercenter.models.StandardResourceLinks;

/**
 * Represents the detailed information of an invoice
 */
public class InvoiceDetail
    extends ResourceBaseWithLinks<StandardResourceLinks>
{
    /**
     * Gets or sets the type of invoice detail
     */
    private InvoiceLineItemType __InvoiceLineItemType = InvoiceLineItemType.NONE;

    public InvoiceLineItemType getInvoiceLineItemType()
    {
        return __InvoiceLineItemType;
    }

    public void setInvoiceLineItemType( InvoiceLineItemType value )
    {
        __InvoiceLineItemType = value;
    }

    /**
     * Gets or sets the billing provider
     */
    private BillingProvider __BillingProvider = BillingProvider.None;

    public BillingProvider getBillingProvider()
    {
        return __BillingProvider;
    }

    public void setBillingProvider( BillingProvider value )
    {
        __BillingProvider = value;
    }

    /**
     * Gets or sets the link to the invoice detail
     */
    private Link __DetailLink;

    public Link getDetailLink()
    {
        return __DetailLink;
    }

    public void setDetailLink( Link value )
    {
        __DetailLink = value;
    }
}