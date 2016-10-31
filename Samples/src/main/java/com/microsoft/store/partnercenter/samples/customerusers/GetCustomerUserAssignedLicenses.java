// -----------------------------------------------------------------------
// <copyright file="GetCustomerUserAssignedLicenses.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.customerusers;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.licenses.License;
import com.microsoft.store.partnercenter.models.licenses.ServicePlan;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

public class GetCustomerUserAssignedLicenses extends BasePartnerScenario {

	public GetCustomerUserAssignedLicenses(IScenarioContext context) {
		super("Get licenses assigned to a Customer User", context);
	}

    /***
     * Executes the unit of work.
     * @param name="partnerOperations" A reference to the partner operations.
     * @param name="state" The state hash table. This is used as the communication mechanism between units of work.
     */
	@Override
	protected void runScenario() {
		// Get the customer id of customer to run this scenario
        String selectedCustomerId = this.obtainCustomerId();
        ResourceCollection<License> customerUserAssignedLicenses = null;
        IPartner partnerOperations = this.getContext().getUserPartnerOperations();


        String selectedCustomerUserId = this.obtainCustomerUserId();
        

        // get customer user assigned licenses information.
        customerUserAssignedLicenses = partnerOperations.getCustomers().byId(selectedCustomerId).getUsers().byId(selectedCustomerUserId).getLicenses().get();
        if (customerUserAssignedLicenses != null && customerUserAssignedLicenses.getTotalCount() > 0)
        {
            System.out.println( "Customer User Assigned Licenses Count: " + customerUserAssignedLicenses.getTotalCount() );

            for ( License userLicense : customerUserAssignedLicenses.getItems() )
            {
            	Iterable<ServicePlan> servicePlans = userLicense.getServicePlans();//getServicePlans().ToList();
            	System.out.println("Customer User License ServicePlans" );// Count: " + servicePlans. .Count);
                for (ServicePlan servicePlan : servicePlans)
                {
                	System.out.println( "Customer User License service plan display name: " + servicePlan.getDisplayName() );
                	System.out.println( "Customer User License service plan service name: " + servicePlan.getServiceName() );
                	System.out.println( "Customer User License service plan service id: " + servicePlan.getId() );
                	System.out.println( "Customer User License service plan capability status: " + servicePlan.getCapabilityStatus() );
                	System.out.println( "Customer User License service plan target type: " + servicePlan.getTargetType() );
                	System.out.println();
                }
            }
        }
}

}
