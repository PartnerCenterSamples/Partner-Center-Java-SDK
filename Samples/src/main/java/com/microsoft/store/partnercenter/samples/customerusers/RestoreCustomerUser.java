// -----------------------------------------------------------------------
// <copyright file="RestoreCustomerUser.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.customerusers;

import java.util.UUID;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.models.users.CustomerUser;
import com.microsoft.store.partnercenter.models.users.PasswordProfile;
import com.microsoft.store.partnercenter.models.users.UserState;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

public class RestoreCustomerUser extends BasePartnerScenario {

	public RestoreCustomerUser(IScenarioContext context) {
		super("Restore a Customer User after deletion", context);
	}

	/***
	 * Executes the scenario
	 */
	@Override
	protected void runScenario() {
        String customerId = this.obtainCustomerId();
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();
        this.getContext().getConsoleHelper().startProgress( "Getting the customer to run the delete user scenario for" );
        Customer selectedCustomer = partnerOperations.getCustomers().byId( customerId ).get();
        
        String selectedCustomerId = selectedCustomer.getId();
        PasswordProfile passwordProfile = new PasswordProfile();
        passwordProfile.setForceChangePassword(true);
        passwordProfile.setPassword("Password!1");

        CustomerUser userToCreate = new CustomerUser();
        userToCreate.setPasswordProfile(passwordProfile);
        userToCreate.setDisplayName( "TestDisplayName" );
        userToCreate.setFirstName( "TestFirstName" );
        userToCreate.setLastName( "TestLastName" );
        userToCreate.setUsageLocation( "US" );
        userToCreate.setUserPrincipalName(UUID.randomUUID().toString().toUpperCase() + "@" + selectedCustomer.getCompanyProfile().getDomain());

        this.getContext().getConsoleHelper().writeObject( userToCreate, "New user Information" );
        this.getContext().getConsoleHelper().startProgress( "Creating user" );
        CustomerUser createdUser = partnerOperations.getCustomers().byId( selectedCustomerId ).getUsers().create( userToCreate );
        this.getContext().getConsoleHelper().writeObject( createdUser, "Created user Information" );

        // Now delete the newly created customer user
        this.getContext().getConsoleHelper().startProgress( "Created user. Deleting the user" );
        partnerOperations.getCustomers().byId( selectedCustomerId ).getUsers().byId( createdUser.getId() ).delete();
        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().success( "Success!" );
        this.getContext().getConsoleHelper().writeObject( createdUser, "Deleted user Information" );

        // Try to restore the deleted user by specifying update attribute state as Active. And try to update the other attributes.
        CustomerUser customerUserToUpdate = new CustomerUser();
        passwordProfile.setPassword( "testPw@!122B" );
        customerUserToUpdate.setPasswordProfile(passwordProfile);
        customerUserToUpdate.setDisplayName( "Michael Phelps" );
        customerUserToUpdate.setFirstName( "Michael" );
        customerUserToUpdate.setLastName( "Phelps" );
        customerUserToUpdate.setUsageLocation ( "US" );
        customerUserToUpdate.setUserPrincipalName( createdUser.getUserPrincipalName() );
        customerUserToUpdate.setUserState( UserState.Active );
        
        // Now update the newly created customer user
        this.getContext().getConsoleHelper().startProgress( "Deleted user. Updating the user" );
        CustomerUser updatedCustomerUserInfo = partnerOperations.getCustomers().byId( selectedCustomerId ).getUsers().byId( createdUser.getId() ).patch( customerUserToUpdate );
        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().success( "Success!" );
        this.getContext().getConsoleHelper().writeObject( updatedCustomerUserInfo, "Updated user Information" );

        // Get user details.
        CustomerUser customerUserInfo = partnerOperations.getCustomers().byId( selectedCustomerId ).getUsers().byId( createdUser.getId() ).get();
        if ( customerUserInfo.getUserPrincipalName().equals( updatedCustomerUserInfo.getUserPrincipalName() ) 
        		&& customerUserInfo.getFirstName().equals( updatedCustomerUserInfo.getFirstName() ) 
        		&& customerUserInfo.getDisplayName().equals( updatedCustomerUserInfo.getDisplayName() ) 
        		&& customerUserInfo.getLastName().equals( updatedCustomerUserInfo.getLastName() ) )
        {
            System.out.println( "The restore and update operation together was successful.");
            System.out.println( "First Name: " + customerUserInfo.getFirstName() );
            System.out.println( "Last Name: " + customerUserInfo.getLastName() );
            System.out.println( "Display Name: " + customerUserInfo.getDisplayName() );
            System.out.println( "Id: " + customerUserInfo.getId() );
        }
        
        // Now delete the newly created customer user for cleanup after the test
        this.getContext().getConsoleHelper().startProgress( "Restored user. Now deleting the user" );
        partnerOperations.getCustomers().byId( selectedCustomerId ).getUsers().byId( createdUser.getId() ).delete();
        this.getContext().getConsoleHelper().stopProgress();
        this.getContext().getConsoleHelper().success( "Success!" );
        this.getContext().getConsoleHelper().writeObject( createdUser, "Deleted user Information" );
	}

}
