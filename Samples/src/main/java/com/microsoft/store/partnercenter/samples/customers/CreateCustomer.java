// -----------------------------------------------------------------------
// <copyright file="CreateCustomer.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.customers;

import java.util.Random;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.Address;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.models.customers.CustomerBillingProfile;
import com.microsoft.store.partnercenter.models.customers.CustomerCompanyProfile;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
 * Creates a new customer for a partner.
 */
public class CreateCustomer
    extends BasePartnerScenario
{
    /**
     * Initializes a new instance of the {@link #CreateCustomer} class.
     * 
     * @param context The scenario context.
     */
    public CreateCustomer( IScenarioContext context )
    {
        super( "Create a new customer", context );
    }

    /**
     * executes the create customer scenario.
     */
    @Override
    protected void runScenario()
    {
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();

        Address address = new Address();

        address.setFirstName( "Gena" );
        address.setLastName( "Soto" );
        address.setAddressLine1( "One Microsoft Way" );
        address.setCity( "Redmond" );
        address.setState( "WA" );
        address.setCountry( "US" );
        address.setPostalCode( "98052" );
        address.setPhoneNumber( "4255550101" );

        CustomerBillingProfile billingProfile = new CustomerBillingProfile();
        
        billingProfile.setCulture( "en-US" );
        billingProfile.setEmail( "gena@wingtiptoys.com" );
        billingProfile.setLanguage( "en" );
        billingProfile.setCompanyName( "Wingtip Toys" + new Random().nextInt() );
        billingProfile.setDefaultAddress( address );

        CustomerCompanyProfile companyProfile = new CustomerCompanyProfile();
        
        companyProfile.setDomain( "WingtipToys" + Math.abs( new Random().nextInt() ) + ".onmicrosoft.com" );

        Customer customerToCreate = new Customer();
        
        customerToCreate.setBillingProfile( billingProfile );
        customerToCreate.setCompanyProfile( companyProfile );

        this.getContext().getConsoleHelper().writeObject( customerToCreate, "New customer information" );
        this.getContext().getConsoleHelper().startProgress( "Creating customer" );
        
        Customer newCustomer = partnerOperations.getCustomers().create( customerToCreate );
        
        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().success( "Success!" );
        this.getContext().getConsoleHelper().writeObject( newCustomer, "Created customer information" );
    }
}