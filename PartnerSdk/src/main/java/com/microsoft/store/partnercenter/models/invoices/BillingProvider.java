// ----------------------------------------------------------------
// <copyright file="BillingProvider.java" company="Microsoft Corporation">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// ----------------------------------------------------------------

package com.microsoft.store.partnercenter.models.invoices;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BillingProvider
{
    /**
     * Different providers of billing information Enum initializer
     */
    @JsonProperty( "none" ) NONE("None" ), /**
                                            * Bill is provided by Office. Example: O365, and In-tune.
                                            */
    @JsonProperty( "office" ) OFFICE("Office" ), /**
                                                  * Bill is provided by Azure Example: Azure Services
                                                  */
    @JsonProperty( "azure" ) AZURE("Azure" ), /**
     											* Bill is provided by Azure Data Market
     											*/
    @JsonProperty( "azureDataMarket" ) AZUREDATAMARKET("AzureDataMarket" );;

    private String urlName;

    private BillingProvider( String urlName )
    {
        this.urlName = urlName;
    }

    public String getUrlName()
    {
        return this.urlName;
    }
}
