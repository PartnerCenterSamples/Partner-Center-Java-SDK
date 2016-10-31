// ----------------------------------------------------------------
// <copyright file="CustomerSearchField.java" company="Microsoft Corporation">
// Copyright (c) Microsoft Corporation. All rights reserved.
// </copyright>
// ----------------------------------------------------------------

package com.microsoft.store.partnercenter.models.customers;

/**
 * List of search fields for customer search
 */
public enum CustomerSearchField
{
    /**
     * Customer Company name
     */
    COMPANY_NAME( "CompanyName" ), 
    /**
     * Customer domain name
     */
    DOMAIN_NAME( "Domain" );

    private String name;

    private CustomerSearchField( String name )
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
