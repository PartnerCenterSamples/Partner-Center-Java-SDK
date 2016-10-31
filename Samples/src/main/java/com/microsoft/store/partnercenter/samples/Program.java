// -----------------------------------------------------------------------
// <copyright file="Program.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.store.partnercenter.models.customers.CustomerSearchField;
import com.microsoft.store.partnercenter.samples.auditrecords.GetAuditRecords;
import com.microsoft.store.partnercenter.samples.auditrecords.SearchAuditRecords;
import com.microsoft.store.partnercenter.samples.context.ScenarioContext;
import com.microsoft.store.partnercenter.samples.customerdirectoryroles.AddUserMemberToDirectoryRole;
import com.microsoft.store.partnercenter.samples.customerdirectoryroles.GetCustomerDirectoryRoleUserMembers;
import com.microsoft.store.partnercenter.samples.customerdirectoryroles.GetCustomerDirectoryRoles;
import com.microsoft.store.partnercenter.samples.customerdirectoryroles.RemoveCustomerUserMemberFromDirectoryRole;
import com.microsoft.store.partnercenter.samples.customers.CheckDomainAvailability;
import com.microsoft.store.partnercenter.samples.customers.CreateCustomer;
import com.microsoft.store.partnercenter.samples.customers.DeleteCustomerFromTipAccount;
import com.microsoft.store.partnercenter.samples.customers.FilterCustomers;
import com.microsoft.store.partnercenter.samples.customers.GetCustomerDetails;
import com.microsoft.store.partnercenter.samples.customers.GetCustomerManagedServices;
import com.microsoft.store.partnercenter.samples.customers.GetCustomerQualification;
import com.microsoft.store.partnercenter.samples.customers.GetCustomerRelationshipRequest;
import com.microsoft.store.partnercenter.samples.customers.GetPagedCustomers;
import com.microsoft.store.partnercenter.samples.customers.UpdateCustomerBillingProfile;
import com.microsoft.store.partnercenter.samples.customers.UpdateCustomerQualification;
import com.microsoft.store.partnercenter.samples.customers.ValidateCustomerAddress;
import com.microsoft.store.partnercenter.samples.customersubscribedskus.CustomerSubscribedSkus;
import com.microsoft.store.partnercenter.samples.customerusers.CreateCustomerUser;
import com.microsoft.store.partnercenter.samples.customerusers.CustomerUserAssignLicenses;
import com.microsoft.store.partnercenter.samples.customerusers.DeleteCustomerUser;
import com.microsoft.store.partnercenter.samples.customerusers.GetCustomerUserAssignedLicenses;
import com.microsoft.store.partnercenter.samples.customerusers.GetCustomerUserDirectoryRoles;
import com.microsoft.store.partnercenter.samples.customerusers.GetCustomerUserInformation;
import com.microsoft.store.partnercenter.samples.customerusers.GetCustomerUsers;
import com.microsoft.store.partnercenter.samples.customerusers.GetInactiveCustomerUsers;
import com.microsoft.store.partnercenter.samples.customerusers.GetPagedCustomerUsers;
import com.microsoft.store.partnercenter.samples.customerusers.GetSortedCustomerUsers;
import com.microsoft.store.partnercenter.samples.customerusers.RestoreCustomerUser;
import com.microsoft.store.partnercenter.samples.customerusers.UpdateCustomerUser;
import com.microsoft.store.partnercenter.samples.indirectpartners.GetSubscriptionsByPartner;
import com.microsoft.store.partnercenter.samples.indirectpartners.VerifyPartnerMpnId;
import com.microsoft.store.partnercenter.samples.invoice.GetInvoice;
import com.microsoft.store.partnercenter.samples.invoice.GetInvoiceLineItems;
import com.microsoft.store.partnercenter.samples.invoice.GetInvoiceSummary;
import com.microsoft.store.partnercenter.samples.invoice.GetPagedInvoices;
import com.microsoft.store.partnercenter.samples.offers.GetCustomerOfferCategories;
import com.microsoft.store.partnercenter.samples.offers.GetCustomerOffers;
import com.microsoft.store.partnercenter.samples.offers.GetOfferCategories;
import com.microsoft.store.partnercenter.samples.offers.GetOfferDetails;
import com.microsoft.store.partnercenter.samples.offers.GetOffers;
import com.microsoft.store.partnercenter.samples.offers.GetPagedOffers;
import com.microsoft.store.partnercenter.samples.orders.CreateOrder;
import com.microsoft.store.partnercenter.samples.orders.GetOrderDetails;
import com.microsoft.store.partnercenter.samples.orders.GetOrders;
import com.microsoft.store.partnercenter.samples.profiles.GetBillingProfile;
import com.microsoft.store.partnercenter.samples.profiles.GetLegalBusinessProfile;
import com.microsoft.store.partnercenter.samples.profiles.GetMpnProfile;
import com.microsoft.store.partnercenter.samples.profiles.GetOrganizationProfile;
import com.microsoft.store.partnercenter.samples.profiles.GetSupportProfile;
import com.microsoft.store.partnercenter.samples.profiles.UpdateBillingProfile;
import com.microsoft.store.partnercenter.samples.profiles.UpdateLegalBusinessProfile;
import com.microsoft.store.partnercenter.samples.profiles.UpdateOrganizationProfile;
import com.microsoft.store.partnercenter.samples.profiles.UpdateSupportProfile;
import com.microsoft.store.partnercenter.samples.ratecards.GetAzureRateCard;
import com.microsoft.store.partnercenter.samples.ratedusage.GetCustomerSubscriptionsUsage;
import com.microsoft.store.partnercenter.samples.ratedusage.GetCustomerUsageSummary;
import com.microsoft.store.partnercenter.samples.ratedusage.GetSubscriptionUsageRecords;
import com.microsoft.store.partnercenter.samples.ratedusage.GetSubscriptionResourceUsage;
import com.microsoft.store.partnercenter.samples.ratedusage.GetSubscriptionUsageSummary;
import com.microsoft.store.partnercenter.samples.serviceincidents.GetServiceIncidents;
import com.microsoft.store.partnercenter.samples.servicerequests.CreatePartnerServiceRequest;
import com.microsoft.store.partnercenter.samples.servicerequests.GetAllCustomerServiceRequests;
import com.microsoft.store.partnercenter.samples.servicerequests.GetPagedPartnerServiceRequests;
import com.microsoft.store.partnercenter.samples.servicerequests.GetPartnerServiceRequestDetails;
import com.microsoft.store.partnercenter.samples.servicerequests.GetServiceRequestSupportTopics;
import com.microsoft.store.partnercenter.samples.servicerequests.UpdatePartnerServiceRequest;
import com.microsoft.store.partnercenter.samples.subscriptions.AddSubscriptionAddOn;
import com.microsoft.store.partnercenter.samples.subscriptions.GetSubscription;
import com.microsoft.store.partnercenter.samples.subscriptions.GetSubscriptions;
import com.microsoft.store.partnercenter.samples.subscriptions.GetSubscriptionsByOrder;
import com.microsoft.store.partnercenter.samples.subscriptions.UpdateSubscription;
import com.microsoft.store.partnercenter.samples.subscriptions.UpgradeSubscription;

/**
 * The main program class for the partner center .NET SDK samples.
 */
public class Program
{
    /**
     * The main entry function.
     * 
     * @param args Program arguments.
     */
    public static void main( String[] args )
    {
        ScenarioContext context = new ScenarioContext();
        List<IPartnerScenario> mainScenarios = new ArrayList<IPartnerScenario>();
        mainScenarios.add( Program.getCustomerScenarios( context ) );
        mainScenarios.add( Program.getOfferScenarios( context ) );
        mainScenarios.add( Program.getOrderScenarios( context ) );
        mainScenarios.add( Program.getSubscriptionScenarios( context ) );
        mainScenarios.add( Program.getRatedUsageScenarios( context ) );
        mainScenarios.add( Program.getServiceRequestScenarios( context ) );
        mainScenarios.add( Program.getInvoiceScenarios( context ) );
        mainScenarios.add( Program.getProfileScenarios( context ) );
        mainScenarios.add( Program.getCustomerUserScenarios( context ) );
        mainScenarios.add( Program.getCustomerSubscribedSkusScenarios( context ) );
        mainScenarios.add( Program.getCustomerDirectoryRoleScenarios( context ) );
        mainScenarios.add( Program.getAuditRecordScenarios( context ) );
        mainScenarios.add( Program.getRateCardScenarios( context ) );
        mainScenarios.add( Program.getIndirectPartnerScenarios( context ) );
        mainScenarios.add( Program.getServiceIncidentScenarios( context ) );
        // run the main scenario
        new AggregatePartnerScenario( "Partner SDK samples", mainScenarios, context ).run();
    }

    /**
     * Gets the customer scenarios.
     * 
     * @param context A scenario context.
     * @return The customer scenarios.
     */
    private static IPartnerScenario getCustomerScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> customerFilteringScenarios = new ArrayList<IPartnerScenario>();
        customerFilteringScenarios.add( new FilterCustomers( "Filter by company name", CustomerSearchField.COMPANY_NAME,
                                                             context ) );
        customerFilteringScenarios.add( new FilterCustomers( "Filter by domain name", CustomerSearchField.DOMAIN_NAME,
                                                             context ) );
        List<IPartnerScenario> customerScenarios = new ArrayList<IPartnerScenario>();

        customerScenarios.add( new CreateCustomer( context ) );
        customerScenarios.add( new CheckDomainAvailability( context ) );
        customerScenarios.add( new GetPagedCustomers( context,
                                                      Integer.parseInt( context.getConfiguration().getScenarioSettings().get( "CustomerPageSize" ) ) ) );
        customerScenarios.add( new AggregatePartnerScenario( "Customer filtering", customerFilteringScenarios,
                                                             context ) );
        customerScenarios.add( new GetCustomerDetails( context ) );
        customerScenarios.add( new GetCustomerQualification( context ) );
        customerScenarios.add( new UpdateCustomerQualification( context ) );
        customerScenarios.add( new DeleteCustomerFromTipAccount( context ) );
        customerScenarios.add( new GetCustomerManagedServices( context ) );
        customerScenarios.add( new GetCustomerRelationshipRequest( context ) );
        customerScenarios.add( new UpdateCustomerBillingProfile( context ) );
        customerScenarios.add( new ValidateCustomerAddress( context ) );
        return new AggregatePartnerScenario( "Customer samples", customerScenarios, context );
    }

    /**
     * Gets the offer scenarios.
     *
     * @param context A scenario context.
     * @return The offer scenarios.
     */
    private static IPartnerScenario getOfferScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> offerScenarios = new ArrayList<IPartnerScenario>();
        offerScenarios.add( new GetOfferDetails( context ) );
        offerScenarios.add( new GetOfferCategories( context ) );
        offerScenarios.add( new GetOffers( context ) );
        offerScenarios.add( new GetPagedOffers( context ) );
        offerScenarios.add( new GetCustomerOffers( context ) );
        offerScenarios.add( new GetCustomerOfferCategories( context ) );
        return new AggregatePartnerScenario( "Offer samples", offerScenarios, context );
    }

    /**
     * Gets the order scenarios.
     *
     * @param context A scenario context.
     * @return The order scenarios.
     */
    private static IPartnerScenario getOrderScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> orderScenarios = new ArrayList<IPartnerScenario>();
        orderScenarios.add( new CreateOrder( context ) );
        orderScenarios.add( new GetOrderDetails( context ) );
        orderScenarios.add( new GetOrders( context ) );
        return new AggregatePartnerScenario( "Order samples", orderScenarios, context );
    }

    /**
     * Gets the subscription scenarios.
     *
     * @param context A scenario context.
     * @return The subscription scenarios.
     */
    private static IPartnerScenario getSubscriptionScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> subscriptionScenarios = new ArrayList<IPartnerScenario>();
        subscriptionScenarios.add( new GetSubscription( context ) );
        subscriptionScenarios.add( new GetSubscriptions( context ) );
        subscriptionScenarios.add( new GetSubscriptionsByOrder( context ) );
        subscriptionScenarios.add( new UpdateSubscription( context ) );
        subscriptionScenarios.add( new UpgradeSubscription( context ) );
        subscriptionScenarios.add( new AddSubscriptionAddOn( context ) );
        return new AggregatePartnerScenario( "Subscription samples", subscriptionScenarios, context );
    }

    /**
     * Gets the subscription scenarios.
     *
     * @param context A scenario context.
     * @return The subscription scenarios.
     */
    private static IPartnerScenario getRatedUsageScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> ratedUsageScenarios = new ArrayList<IPartnerScenario>();
        ratedUsageScenarios.add( new GetCustomerUsageSummary( context ) );
        ratedUsageScenarios.add( new GetCustomerSubscriptionsUsage( context ) );
        ratedUsageScenarios.add( new GetSubscriptionResourceUsage( context ) );
        ratedUsageScenarios.add( new GetSubscriptionUsageRecords( context ) );
        ratedUsageScenarios.add( new GetSubscriptionUsageSummary( context ) );
        return new AggregatePartnerScenario( "Rated Usage samples", ratedUsageScenarios, context );
    }

    /**
     * Gets the service request scenarios.
     *
     * @param context A scenario context.
     * @return The service request scenarios.
     */
    private static IPartnerScenario getServiceRequestScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> serviceRequestScenarios = new ArrayList<IPartnerScenario>();
        serviceRequestScenarios.add( new CreatePartnerServiceRequest( context ) );
        serviceRequestScenarios.add( new GetAllCustomerServiceRequests( context ) );
        serviceRequestScenarios.add( new GetPagedPartnerServiceRequests( context,
                Integer.parseInt( context.getConfiguration().getScenarioSettings().get( "ServiceRequestPageSize" ) ) ) );
        serviceRequestScenarios.add( new GetPartnerServiceRequestDetails( context ) );
        serviceRequestScenarios.add( new GetServiceRequestSupportTopics( context ) );
        serviceRequestScenarios.add( new UpdatePartnerServiceRequest( context ) );
        return new AggregatePartnerScenario( "Service request samples", serviceRequestScenarios, context );
    }

    /**
     * Gets the invoice scenarios.
     *
     * @param context A scenario context.
     * @return The invoice scenarios.
     */
    private static IPartnerScenario getInvoiceScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> invoiceScenarios = new ArrayList<IPartnerScenario>();
        invoiceScenarios.add( new GetInvoiceSummary( context ) );
        invoiceScenarios.add( new GetInvoice( context ) );
        invoiceScenarios.add( new GetInvoiceLineItems( context,
                                                       Integer.parseInt( context.getConfiguration().getScenarioSettings().get( "InvoicePageSize" ) ) ) );
        invoiceScenarios.add( new GetPagedInvoices( context,
                                                    Integer.parseInt( context.getConfiguration().getScenarioSettings().get( "InvoicePageSize" ) ) ) );
        return new AggregatePartnerScenario( "Invoice samples", invoiceScenarios, context );
    }

    /**
     * Gets the Partner Profile scenarios.
     *
     * @param context A scenario context.
     * @return The invoice scenarios.
     */
    private static IPartnerScenario getProfileScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> profileScenarios = new ArrayList<IPartnerScenario>();
        profileScenarios.add( new GetBillingProfile( context ) );
        profileScenarios.add( new GetLegalBusinessProfile( context ) );
        profileScenarios.add( new GetOrganizationProfile( context ) );
        profileScenarios.add( new GetMpnProfile( context ) );
        profileScenarios.add( new GetSupportProfile( context ) );
        profileScenarios.add( new UpdateBillingProfile( context ) );
        profileScenarios.add( new UpdateLegalBusinessProfile( context ) );
        profileScenarios.add( new UpdateOrganizationProfile( context ) );
        profileScenarios.add( new UpdateSupportProfile( context ) );
        return new AggregatePartnerScenario( "Partner Profile samples", profileScenarios, context );
        
    }

    /**
     * Gets the Customer User scenarios.
     *
     * @param context A scenario context.
     * @return The invoice scenarios.
     */
    private static IPartnerScenario getCustomerUserScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> customerUserScenarios = new ArrayList<IPartnerScenario>();
        customerUserScenarios.add( new GetCustomerUsers( context ) );
        customerUserScenarios.add( new GetSortedCustomerUsers ( context ) );
        customerUserScenarios.add( new CreateCustomerUser( context ) );
        customerUserScenarios.add( new DeleteCustomerUser( context ) );
        customerUserScenarios.add( new GetCustomerUserInformation ( context ) );
        customerUserScenarios.add( new UpdateCustomerUser( context ) );
        customerUserScenarios.add( new GetPagedCustomerUsers ( context ) );
        customerUserScenarios.add( new GetCustomerUserDirectoryRoles( context ) );
        customerUserScenarios.add( new GetCustomerUserAssignedLicenses ( context ) );
        customerUserScenarios.add( new CustomerUserAssignLicenses ( context ) );
        customerUserScenarios.add( new GetInactiveCustomerUsers ( context ) );
        customerUserScenarios.add( new RestoreCustomerUser ( context ) );
        return new AggregatePartnerScenario( "Customer User samples", customerUserScenarios, context );
        
    }
    
    /**
     * Gets the customer subscribed skus scenarios.
     *
     * @param context A scenario context.
     * @return The Customer subscribed skus scenarios.
     */
    private static IPartnerScenario getCustomerSubscribedSkusScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> customerSubscribedSkusScenarios = new ArrayList<IPartnerScenario>();
        customerSubscribedSkusScenarios.add( new CustomerSubscribedSkus( context ) );
        return new AggregatePartnerScenario( "Customer Subscribed Skus samples", customerSubscribedSkusScenarios, context );
    }

    /**
     * Gets the customer directory role scenarios.
     *
     * @param context A scenario context.
     * @return The directory role scenarios.
     */
    private static IPartnerScenario getCustomerDirectoryRoleScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> customerDirectoryRoleScenarios = new ArrayList<IPartnerScenario>();
        customerDirectoryRoleScenarios.add( new GetCustomerDirectoryRoles( context ) );
        customerDirectoryRoleScenarios.add( new AddUserMemberToDirectoryRole( context ) );
        customerDirectoryRoleScenarios.add( new GetCustomerDirectoryRoleUserMembers( context ) );
        customerDirectoryRoleScenarios.add( new RemoveCustomerUserMemberFromDirectoryRole( context ) );

        return new AggregatePartnerScenario( "Customer Directory Role samples", customerDirectoryRoleScenarios, context );
    }

    /**
     * Gets the Audit record scenarios.
     *
     * @param context A scenario context.
     * @return The invoice scenarios.
     */
    private static IPartnerScenario getAuditRecordScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> auditRecordScenarios = new ArrayList<IPartnerScenario>();
        auditRecordScenarios.add( new GetAuditRecords( context ) );
        auditRecordScenarios.add( new SearchAuditRecords( context ) );
        return new AggregatePartnerScenario( "Auditing samples", auditRecordScenarios, context );
        
    }


    /**
     * Gets the rate card scenarios.
     *
     * @param context A scenario context.
     * @return The rate card scenarios.
     */
    private static IPartnerScenario getRateCardScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> rateCardScenarios = new ArrayList<IPartnerScenario>();
        rateCardScenarios.add( new GetAzureRateCard( context ) );
        
        return new AggregatePartnerScenario( "Rate Card samples", rateCardScenarios, context );
    }

    /**
     * Gets the indirect partner scenarios.
     *
     * @param context A scenario context.
     * @return The indirect partner scenarios.
     */
    private static IPartnerScenario getIndirectPartnerScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> partnerScenarios = new ArrayList<IPartnerScenario>();
        partnerScenarios.add( new VerifyPartnerMpnId( context ) );
        partnerScenarios.add( new GetSubscriptionsByPartner( context ) );

        return new AggregatePartnerScenario( "Indirect model samples", partnerScenarios, context );
    }

    /**
     * Gets the Customer User scenarios.
     *
     * @param context A scenario context.
     * @return The invoice scenarios.
     */
    private static IPartnerScenario getServiceIncidentScenarios( IScenarioContext context )
    {
        List<IPartnerScenario> serviceIncidentScenarios = new ArrayList<IPartnerScenario>();
        serviceIncidentScenarios.add( new GetServiceIncidents( context ) );
        return new AggregatePartnerScenario( "Service Incident samples", serviceIncidentScenarios, context );
        
    }

}
