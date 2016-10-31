// -----------------------------------------------------------------------
// <copyright file="DeleteCustomerFromTipAccount.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.customers;

import java.text.MessageFormat;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.IPartnerCredentials;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.extensions.PartnerCredentials;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * Deletes a customer from a testing in production account.
 */
public class DeleteCustomerFromTipAccount
    extends BasePartnerScenario
{
    /**
     * Initializes a new instance of the {@link #DeleteCustomerFromTipAccount} class.
     * 
     * @param context The scenario context.
     */
    public DeleteCustomerFromTipAccount( IScenarioContext context )
    {
        super( "Delete customer from TIP account", context );
    }

    /**
     * Executes the scenario.
     */
    @Override
    protected void runScenario()
    {
        // since this operation can only be performed on TIP accounts, we need to login to a TIP account here
        // and ignore the given partner operations which relies on a stadard account
    	IPartnerCredentials tipAccountCredentials = 
    			PartnerCredentials.getInstance().generateByApplicationCredentials
    			(
    				this.getContext().getConfiguration().getTipAccountSettings().get( "ApplicationId" ),
    	            this.getContext().getConfiguration().getTipAccountSettings().get( "ApplicationSecret" ),
    	            this.getContext().getConfiguration().getTipAccountSettings().get( "ApplicationDomain" ),
    	            this.getContext().getConfiguration().getPartnerServiceSettings().get( "AuthenticationAuthorityEndpoint" ),
    	            this.getContext().getConfiguration().getPartnerServiceSettings().get( "GraphEndpoint" )
    			);
    			
    	IPartner tipAccountPartnerOperations = PartnerService.getInstance().createPartnerOperations( tipAccountCredentials );
    	
    	String customerIdToDelete =
            this.getContext().getConfiguration().getScenarioSettings().get( "CustomerIdToDelete" );
        if ( StringHelper.isNullOrWhiteSpace( customerIdToDelete ) )
        {
            // prompt the user the enter the customer ID
            customerIdToDelete =
                this.getContext().getConsoleHelper().readNonEmptyString( "Enter the AAD tenant ID of the customer to be deleted from the tip account:",
                                                                         "The customer ID can't be empty" );
        }
        else
        {
            System.out.println( MessageFormat.format( "Found customer ID: {0} in configuration.",
                                                      customerIdToDelete ) );
        }
        
        this.getContext().getConsoleHelper().startProgress( "Getting Tip customers" );
        Customer c1 = tipAccountPartnerOperations.getCustomers().get().getItems().iterator().next();

        /*this.getContext().getConsoleHelper().startProgress( "Creating Tip customer" );
        Customer c2 = new Customer();
        c2.setBillingProfile( c1.getBillingProfile() );
        c2.setCompanyProfile( c1.getCompanyProfile() );
        Customer createdTipCustomer = tipAccountPartnerOperations.getCustomers().create( c2 );*/
        
        this.getContext().getConsoleHelper().startProgress( "Deleting customer" );
        tipAccountPartnerOperations.getCustomers().byId( c1.getId() ).delete();
        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().success( "Customer successfully deleted" );
    }

}
