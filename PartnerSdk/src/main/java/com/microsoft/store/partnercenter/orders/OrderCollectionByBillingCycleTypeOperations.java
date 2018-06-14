// -----------------------------------------------------------------------
// <copyright file="OrderCollectionByBillingCycleTypeOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.orders;

import java.text.MessageFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.offers.BillingCycleType;
import com.microsoft.store.partnercenter.models.orders.Order;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.utils.StringHelper;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;
import com.microsoft.store.partnercenter.models.utils.Tuple;

/**
 * Order collection by billing cycle type operations implementation class.
 */
public class OrderCollectionByBillingCycleTypeOperations
    extends BasePartnerComponent<Tuple<String, BillingCycleType>>
    implements IOrderCollectionByBillingCycleType
{
    /**
     * Initializes a new instance of the {@link #OrderCollectionByBillingCycleTypeOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The customer tenant Id.
     */
    public OrderCollectionByBillingCycleTypeOperations( IPartner rootPartnerOperations, String customerId, BillingCycleType billingCyleType )
    {
        super( rootPartnerOperations, new Tuple<String, BillingCycleType>(customerId, billingCyleType) );

        if ( StringHelper.isNullOrWhiteSpace( customerId ) )
        {
            throw new IllegalArgumentException( "customerId must be set." );
        }
    }

    /**
     * Retrieves all the orders the customer made.
     * 
     * @return All the customer orders.
     */
    public ResourceCollection<Order> get()
    {
        IPartnerServiceProxy<Order, ResourceCollection<Order>> partnerServiceProxy =
            new PartnerServiceProxy<Order, ResourceCollection<Order>>( 
                new TypeReference<ResourceCollection<Order>>()
                {
                }, 
                this.getPartner(), 
                MessageFormat.format( 
                    PartnerService.getInstance().getConfiguration().getApis().get( "GetOrdersByBillingCyleType" ).getPath(),
                    this.getContext().getItem1() ) );
                
        partnerServiceProxy.getUriParameters().add(
            new KeyValuePair<String, String>(
                PartnerService.getInstance().getConfiguration().getApis().get( "GetOrdersByBillingCyleType" ).getParameters().get( "BillingType" ),
                this.getContext().getItem2().toString()));   

        return partnerServiceProxy.get();
    }
}