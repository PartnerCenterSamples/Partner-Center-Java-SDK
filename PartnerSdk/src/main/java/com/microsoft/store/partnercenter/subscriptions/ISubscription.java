// -----------------------------------------------------------------------
// <copyright file="ISubscription.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.subscriptions;

import com.microsoft.store.partnercenter.IPartnerComponent;
import com.microsoft.store.partnercenter.genericoperations.IEntityGetOperations;
import com.microsoft.store.partnercenter.genericoperations.IEntityPatchOperations;
import com.microsoft.store.partnercenter.models.subscriptions.Subscription;
import com.microsoft.store.partnercenter.models.utils.Tuple;
import com.microsoft.store.partnercenter.usage.ISubscriptionUsageRecordCollection;
import com.microsoft.store.partnercenter.usage.ISubscriptionUsageSummary;
import com.microsoft.store.partnercenter.utilization.IUtilizationCollection;

/**
 * This interface defines the operations available on a customer's subscription.
 */
public interface ISubscription
    extends IPartnerComponent<Tuple<String, String>>, IEntityGetOperations<Subscription>,
    IEntityPatchOperations<Subscription>
{
    /**
     * Gets the current subscription's add-ons operations.
     */
    ISubscriptionAddOnCollection getAddOns();

    /**
     * Gets the current subscription's upgrade operations.
     */
    ISubscriptionUpgradeCollection getUpgrades();

    /**
     * Gets the current subscription's resource usage records operations.
     */
    ISubscriptionUsageRecordCollection getUsageRecords();

    /**
     * Gets the current subscription's usage summary operations.
     */
    ISubscriptionUsageSummary getUsageSummary();
    
    /***
     * Gets the current subscription's utilization operations.
     */
    IUtilizationCollection getUtilization();
    
    /***
     * Retrieves the subscription.
     * @return The subscription.
     */
    Subscription get();

    /***
     * Patches the subscription.
     * @param subscription A subscription that has the properties to be patched set.
     * @return The updated subscription.
     */
    Subscription patch( Subscription subscription );

}
