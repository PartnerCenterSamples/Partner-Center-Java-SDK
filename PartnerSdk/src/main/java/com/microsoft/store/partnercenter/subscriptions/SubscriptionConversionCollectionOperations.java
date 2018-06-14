// -----------------------------------------------------------------------
// <copyright file="SubscriptionConversionCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.subscriptions;

import java.text.MessageFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.subscriptions.Conversion;
import com.microsoft.store.partnercenter.models.subscriptions.ConversionResult;
import com.microsoft.store.partnercenter.models.utils.Tuple;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * The customer subscription conversion implementation.
 */
public class SubscriptionConversionCollectionOperations
    extends BasePartnerComponent<Tuple<String, String>>
    implements ISubscriptionConversionCollection
{
    /**
     * Initializes a new instance of the {@link #SubscriptionConversionCollectionOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The customer Id to whom the subscriptions belong.
     * @param subscriptionId The subscription Id where the upgrade is occurring.
     */
    public SubscriptionConversionCollectionOperations( IPartner rootPartnerOperations, String customerId, String subscriptionId )
    {
        super( rootPartnerOperations, new Tuple<String, String>(customerId, subscriptionId));

        if ( StringHelper.isNullOrWhiteSpace( customerId ) )
        {
            throw new IllegalArgumentException( "customerId must be set" );
        }

        if ( StringHelper.isNullOrWhiteSpace( subscriptionId ) )
        {
            throw new IllegalArgumentException( "subscriptionId must be set" );
        }
    }

        /**
     * Submits a trial subscription conversion.
     *
     * @param conversion The new subscription conversion information
     * @return The customer subscription usage record.
     */
    public ConversionResult create(Conversion conversion)
    {
        IPartnerServiceProxy<Conversion, ConversionResult> partnerServiceProxy = new PartnerServiceProxy<Conversion, ConversionResult>(
            new TypeReference<ConversionResult>() 
            {
            }, 
            this.getPartner(),
            MessageFormat.format(PartnerService.getInstance().getConfiguration().getApis().get("GetSubscriptionConversions").getPath(),
                    this.getContext().getItem1(), this.getContext().getItem2() ));

        return partnerServiceProxy.post(conversion);
    }
  
    /***
     * Retrieves all conversions for the trial subscription.
     * @return The subscription conversions.
     */
    public ResourceCollection<Conversion> get()
    {
        IPartnerServiceProxy<Conversion, ResourceCollection<Conversion>> partnerServiceProxy = new PartnerServiceProxy<Conversion, ResourceCollection<Conversion>>(
            new TypeReference<ResourceCollection<Conversion>>()
            {
            }, 
            this.getPartner(),
            MessageFormat.format(PartnerService.getInstance().getConfiguration().getApis().get("GetSubscriptionConversions").getPath(),
                    this.getContext().getItem1(), this.getContext().getItem2() ));

        return partnerServiceProxy.get();
    }
}