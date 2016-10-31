// -----------------------------------------------------------------------
// <copyright file="OrderLineItem.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.orders;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Order line item associates order information to a specific offer of a product
 */
@JsonIgnoreProperties( "subscriptionId" )
// @JsonInclude(Include.ALWAYS)
public class OrderLineItem
{
    /**
     * Initializes a new instance of the {@link #OrderLineItem} class.
     */
    public OrderLineItem()
    {
    }

    /**
     * Gets or sets the line item number.
     */
    private int __LineItemNumber;

    public int getLineItemNumber()
    {
        return __LineItemNumber;
    }

    public void setLineItemNumber( int value )
    {
        __LineItemNumber = value;
    }

    /**
     * Gets or sets the offer identifier.
     */
    private String __OfferId;

    public String getOfferId()
    {
        return __OfferId;
    }

    public void setOfferId( String value )
    {
        __OfferId = value;
    }

    /**
     * Gets or sets the resulting subscription identifier.
     */
    private String __SubscriptionId;

    public String getSubscriptionId()
    {
        return __SubscriptionId;
    }

    public void setSubscriptionId( String value )
    {
        __SubscriptionId = value;
    }

    /**
     * Gets or sets the parent subscription identifier. This parameter should only be set for add-on offer purchase
     * Applies to Order updates only.
     */
    private String __ParentSubscriptionId;

    public String getParentSubscriptionId()
    {
        return __ParentSubscriptionId;
    }

    public void setParentSubscriptionId( String value )
    {
        __ParentSubscriptionId = value;
    }

    /**
     * Gets or sets the friendly name for the result contract (subscription)
     */
    private String __FriendlyName;

    public String getFriendlyName()
    {
        return __FriendlyName;
    }

    public void setFriendlyName( String value )
    {
        __FriendlyName = value;
    }

    /**
     * Gets or sets the product quantity.
     */
    private int __Quantity;

    public int getQuantity()
    {
        return __Quantity;
    }

    public void setQuantity( int value )
    {
        __Quantity = value;
    }

    /**
     * Gets or sets the partner identifier on record.
     */
    private String __PartnerIdOnRecord;

    public String getPartnerIdOnRecord()
    {
        return __PartnerIdOnRecord;
    }

    public void setPartnerIdOnRecord( String value )
    {
        __PartnerIdOnRecord = value;
    }

    /**
     * Gets or sets the order resulting contract information. This is out parameter only any input values to it will be
     * ignored.
     */
    private OrderLineItemLinks __Links;

    public OrderLineItemLinks getLinks()
    {
        return __Links;
    }

    public void setLinks( OrderLineItemLinks value )
    {
        __Links = value;
    }

}
