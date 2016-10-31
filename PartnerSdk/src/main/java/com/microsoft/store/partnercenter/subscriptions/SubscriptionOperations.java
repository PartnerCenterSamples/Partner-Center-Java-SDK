// -----------------------------------------------------------------------
// <copyright file="SubscriptionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.subscriptions;

import java.text.MessageFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.subscriptions.Subscription;
import com.microsoft.store.partnercenter.models.utils.Tuple;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.usage.ISubscriptionUsageRecordCollection;
import com.microsoft.store.partnercenter.usage.ISubscriptionUsageSummary;
import com.microsoft.store.partnercenter.usage.SubscriptionUsageRecordCollectionOperations;
import com.microsoft.store.partnercenter.usage.SubscriptionUsageSummaryOperations;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * This class implements the operations for a customer's subscription.
 */
public class SubscriptionOperations
    extends BasePartnerComponent<Tuple<String, String>>
    implements ISubscription
{
    private String customerId;

    private String subscriptionId;

    /**
     * A lazy reference to the current subscription's add-ons operations.
     */
    private ISubscriptionAddOnCollection subscriptionAddOnsOperations;

    /**
     * A lazy reference to the current subscription's upgrade operations.
     */
    private ISubscriptionUpgradeCollection subscriptionUpgradeOperations;
    
    /***
     * A lazy reference to the current subscription's resource usage records operations.
     */
    private ISubscriptionUsageRecordCollection usageRecordsOperations;

    /**
     * A lazy reference to the current subscription's usage summary operations.
     */
    private ISubscriptionUsageSummary subscriptionUsageSummaryOperations;

    /**
     * Initializes a new instance of the {@link #SubscriptionOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The customer Id.
     * @param subscriptionId The subscription id.
     */
    public SubscriptionOperations( IPartner rootPartnerOperations, String customerId, String subscriptionId )
    {
        super( rootPartnerOperations, new Tuple<String, String>( customerId, subscriptionId ) );
        if ( StringHelper.isNullOrWhiteSpace( customerId ) )
        {
            throw new IllegalArgumentException( "customerId must be set." );
        }

        if ( StringHelper.isNullOrWhiteSpace( subscriptionId ) )
        {
            throw new IllegalArgumentException( "subscriptionId must be set." );
        }
        this.customerId = customerId;
        this.subscriptionId = subscriptionId;
    }

    /**
     * Gets the current subscription's add-ons operations.
     */
    @Override
    public ISubscriptionAddOnCollection getAddOns()
    {
        if ( this.subscriptionAddOnsOperations == null )
        {
            this.subscriptionAddOnsOperations =
                new SubscriptionAddOnCollectionOperations( this.getPartner(), this.customerId, this.subscriptionId );
        }
        return this.subscriptionAddOnsOperations;
    }

    /**
     * Gets the current subscription's upgrade operations.
     */
    @Override
    public ISubscriptionUpgradeCollection getUpgrades()
    {
        if ( this.subscriptionUpgradeOperations == null )
        {
            this.subscriptionUpgradeOperations =
                new SubscriptionUpgradeCollectionOperations( this.getPartner(), this.customerId, this.subscriptionId );
        }
        return this.subscriptionUpgradeOperations;
    }

    /**
     * Gets the current subscription's resource usage records operations.
     */
    @Override
    public ISubscriptionUsageRecordCollection getUsageRecords()
    {
        if ( this.usageRecordsOperations == null )
        {
            this.usageRecordsOperations =
                new SubscriptionUsageRecordCollectionOperations( this.getPartner(), customerId, subscriptionId );
        }
        return this.usageRecordsOperations;
    }

    /**
     * Gets the current subscription's usage summary operations.
     */
    @Override
    public ISubscriptionUsageSummary getUsageSummary()
    {
        if ( this.subscriptionUsageSummaryOperations == null )
        {
            this.subscriptionUsageSummaryOperations =
                new SubscriptionUsageSummaryOperations( this.getPartner(), customerId, subscriptionId );
        }
        return this.subscriptionUsageSummaryOperations;
    }

    /**
     * Gets the subscription.
     * 
     * @return The subscription.
     */
    @Override
    public Subscription get()
    {
        {
            IPartnerServiceProxy<Subscription, Subscription> partnerServiceProxy =
                new PartnerServiceProxy<Subscription, Subscription>( new TypeReference<Subscription>()
                {
                }, this.getPartner(), MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "GetSubscription" ).getPath(),
                                                            this.getContext().getItem1(), this.getContext().getItem2(),
                                                            Locale.US ) );
            return partnerServiceProxy.get();
        }
    }

    // await
    /**
     * Patches a subscription.
     * 
     * @param subscription The subscription information.
     * @return The updated subscription information.
     */
    @Override
    public Subscription patch( Subscription subscription )
    {
        if ( subscription == null )
        {
            throw new IllegalArgumentException( "subscription is required." );
        }
        PartnerServiceProxy<Subscription, Subscription> partnerApiServiceProxy =
            new PartnerServiceProxy<Subscription, Subscription>( new TypeReference<Subscription>()
            {
            }, this.getPartner(), MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "UpdateSubscription" ).getPath(),
                                                        this.getContext().getItem1(), this.getContext().getItem2(),
                                                        Locale.US ) );
        return partnerApiServiceProxy.patch( subscription );
    }

}
