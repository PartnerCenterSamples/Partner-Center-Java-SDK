// -----------------------------------------------------------------------
// <copyright file="CustomerLicensesDeploymentInsightsCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.analytics;

import java.text.MessageFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.analytics.CustomerLicensesDeploymentInsights;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * Implements the operations on customer licenses deployment insights collection.
 */
public class CustomerLicensesDeploymentInsightsCollectionOperations
    extends BasePartnerComponentString
    implements ICustomerLicensesDeploymentInsightsCollection
{
    /**
     * Initializes a new instance of the {@link #CustomerLicensesDeploymentInsightsCollectionOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The identifier for the customer.
     */
    public CustomerLicensesDeploymentInsightsCollectionOperations(IPartner rootPartnerOperations, String customerId)
    {
        super( rootPartnerOperations, customerId );

        if ( StringHelper.isNullOrWhiteSpace( customerId ) )
        {
            throw new IllegalArgumentException( "customerId must be set." );
        }
    }

    /**
     * Retrieves the collection of customer's licenses' deployment insights.
     * 
     * @return Collection of customer licenses deployment insights.
     */
    @Override
    public ResourceCollection<CustomerLicensesDeploymentInsights> get()
    {
        PartnerServiceProxy<CustomerLicensesDeploymentInsights, ResourceCollection<CustomerLicensesDeploymentInsights>> partnerServiceProxy =
            new PartnerServiceProxy<CustomerLicensesDeploymentInsights, ResourceCollection<CustomerLicensesDeploymentInsights>>( 
                new TypeReference<ResourceCollection<CustomerLicensesDeploymentInsights>>()
                {
                }, 
                this.getPartner(), 
                MessageFormat.format( 
                    PartnerService.getInstance().getConfiguration().getApis().get( "CustomerLicensesDeploymentInsights" ).getPath(),
                    this.getContext() ) );
        
        return partnerServiceProxy.get();
    }
}