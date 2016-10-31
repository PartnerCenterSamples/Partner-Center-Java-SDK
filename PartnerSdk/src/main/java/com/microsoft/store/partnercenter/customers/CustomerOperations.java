// -----------------------------------------------------------------------
// <copyright file="CustomerOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.customers;

import java.text.MessageFormat;
import java.util.Locale;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.customerdirectoryroles.DirectoryRoleCollectionOperations;
import com.microsoft.store.partnercenter.customerdirectoryroles.IDirectoryRoleCollection;
import com.microsoft.store.partnercenter.customers.profiles.CustomerProfileCollectionOperations;
import com.microsoft.store.partnercenter.customers.profiles.ICustomerProfileCollection;
import com.microsoft.store.partnercenter.customerusers.CustomerUsersCollectionOperations;
import com.microsoft.store.partnercenter.customerusers.ICustomerUserCollection;
import com.microsoft.store.partnercenter.managedservices.IManagedServiceCollection;
import com.microsoft.store.partnercenter.managedservices.ManagedServiceCollectionOperations;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.offers.CustomerOfferCategoryCollectionOperations;
import com.microsoft.store.partnercenter.offers.CustomerOfferCollectionOperations;
import com.microsoft.store.partnercenter.offers.ICustomerOfferCategoryCollection;
import com.microsoft.store.partnercenter.offers.ICustomerOfferCollection;
import com.microsoft.store.partnercenter.orders.IOrderCollection;
import com.microsoft.store.partnercenter.orders.OrderCollectionOperations;
import com.microsoft.store.partnercenter.qualification.CustomerQualificationOperations;
import com.microsoft.store.partnercenter.qualification.ICustomerQualification;
import com.microsoft.store.partnercenter.servicerequests.CustomerServiceRequestCollectionOperations;
import com.microsoft.store.partnercenter.servicerequests.IServiceRequestCollection;
import com.microsoft.store.partnercenter.subscribedskus.CustomerSubscribedSkuCollectionOperations;
import com.microsoft.store.partnercenter.subscribedskus.ICustomerSubscribedSkuCollection;
import com.microsoft.store.partnercenter.subscriptions.ISubscriptionCollection;
import com.microsoft.store.partnercenter.subscriptions.SubscriptionCollectionOperations;
import com.microsoft.store.partnercenter.usage.CustomerUsageSpendingBudgetOperations;
import com.microsoft.store.partnercenter.usage.CustomerUsageSummaryOperations;
import com.microsoft.store.partnercenter.usage.ICustomerUsageSpendingBudget;
import com.microsoft.store.partnercenter.usage.ICustomerUsageSummary;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * Implements a single customer operations.
 */
public class CustomerOperations
    extends BasePartnerComponentString
    implements ICustomer
{
    /**
     * The customer subscriptions operations.
     */
    private ISubscriptionCollection subscriptions;

    /**
     * The customer orders operations.
     */
    private IOrderCollection orders;

    /**
     * The customer profiles operations.
     */
    private ICustomerProfileCollection profiles;

    /**
     * The customer service requests operations.
     */
    private IServiceRequestCollection serviceRequests;

    /**
     * The customer managed services.
     */
    private IManagedServiceCollection managedServices;

    /***
     * The offer operations.
     */
    private ICustomerOfferCollection offers;

    /***
     * The offer category operations.
     */
    private ICustomerOfferCategoryCollection offerCategories;

    /**
     * The customer summary for usage-based subscriptions operations.
     */
    private ICustomerUsageSummary usageSummary;

    /**
     * The operations for the spending budget allocated to the customer by the partner.
     */
    private ICustomerUsageSpendingBudget usageBudget;

    /***
     * The customer qualification operations.
     */
    private ICustomerQualification customerQualification;

    /**
     * The operations for the customer users associated with the customer.
     */
    private ICustomerUserCollection customerUserCollectionOperations;

    /***
     * The directory role collection operations.
     */
    private IDirectoryRoleCollection directoryRoleCollectionOperations;

    /**
     * The operations for the subscribed skus associated with the customer.
     */
    private ICustomerSubscribedSkuCollection customerSubscribedSkuCollectionOperations;

    private String customerId;

    /**
     * Initializes a new instance of the {@link #CustomerOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId The customer Id.
     */
    public CustomerOperations( IPartner rootPartnerOperations, String customerId )
    {
        super( rootPartnerOperations, customerId );
        if ( StringHelper.isNullOrWhiteSpace( customerId ) )
        {
            throw new IllegalArgumentException( "customerId must be set" );
        }
        this.customerId = customerId;
    }

    /**
     * Gets the orders behavior for the customer.
     *
     * @return The customer orders.
     */
    @Override
    public IOrderCollection getOrders()
    {
        if ( this.orders == null )
        {
            this.orders = new OrderCollectionOperations( this.getPartner(), this.getContext() );
        }
        return this.orders;
    }

    /**
     * Obtains the profiles behavior for the customer.
     * 
     * @return The customer profiles.
     */
    @Override
    public ICustomerProfileCollection getProfiles()
    {
        if ( this.profiles == null )
        {
            this.profiles = new CustomerProfileCollectionOperations( this.getPartner(), this.getContext() );
        }
        return this.profiles;
    }

    @Override
    public ISubscriptionCollection getSubscriptions()
    {
        if ( subscriptions == null )
        {
            subscriptions = new SubscriptionCollectionOperations( this.getPartner(), this.getContext() );
        }
        return subscriptions;
    }

    /**
     * Obtains the service requests behavior for the customer.
     *
     * @return The customer service request operations.
     */
    @Override
    public IServiceRequestCollection getServiceRequests()
    {
        if ( this.serviceRequests == null )
        {
            this.serviceRequests = new CustomerServiceRequestCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.serviceRequests;
    }

    /**
     * Obtains the managed services behavior for the customer.
     *
     * @return The customer managed services operations.
     */
    @Override
    public IManagedServiceCollection getManagedServices()
    {
        if ( this.managedServices == null )
        {
            this.managedServices = new ManagedServiceCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.managedServices;
    }

	/***
	 * Obtains the Offer Categories behavior for the customer.
	 */
    @Override
	public ICustomerOfferCategoryCollection getOfferCategories() {
        if ( this.offerCategories == null )
        {
            this.offerCategories = new CustomerOfferCategoryCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.offerCategories;
	}

	/***
	 * Obtains the Offers behavior for the customer.
	 */
	@Override
	public ICustomerOfferCollection getOffers() {
        if ( this.offers == null )
        {
            this.offers = new CustomerOfferCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.offers;
	}

    /**
     * Obtains the customer usage summary behavior for the customer.
     *
     * @return The customer usage summary operations.
     */
    @Override
    public ICustomerUsageSummary getUsageSummary()
    {
        if ( this.usageSummary == null )
        {
            this.usageSummary = new CustomerUsageSummaryOperations( this.getPartner(), customerId );
        }
        return this.usageSummary;
    }

    /**
     * Obtains the usage spending budget behavior for the customer.
     *
     * @return The customer usage spending budget operations.
     */
    @Override
    public ICustomerUsageSpendingBudget getUsageBudget()
    {
        if ( this.usageBudget == null )
        {
            this.usageBudget = new CustomerUsageSpendingBudgetOperations( this.getPartner(), customerId );
        }
        return this.usageBudget;
    }

	/***
	 * Obtains the Customer qualification.
	 */
	@Override
	public ICustomerQualification getQualification()
	{
        if ( this.customerQualification == null )
        {
            this.customerQualification = new CustomerQualificationOperations( this.getPartner(), this.customerId );
        }
        return this.customerQualification;
	}

    /**
     * Obtains the users for the customer.
     *
     * @return The customer usage spending budget operations.
     */
	@Override
	public ICustomerUserCollection getUsers()
	{
        if ( this.customerUserCollectionOperations == null )
        {
            this.customerUserCollectionOperations = new CustomerUsersCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.customerUserCollectionOperations;
	}

	/***
	 * Obtains the Customer qualification.
	 */
	@Override
	public IDirectoryRoleCollection getDirectoryRoles()
	{
        if ( this.directoryRoleCollectionOperations == null )
        {
            this.directoryRoleCollectionOperations = new DirectoryRoleCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.directoryRoleCollectionOperations;
	}

    /**
     * Obtains the subscribed skus for the customer.
     *
     * @return The customer usage spending budget operations.
     */
	@Override
	public ICustomerSubscribedSkuCollection getSubscribedSkus()
	{
        if ( this.customerSubscribedSkuCollectionOperations == null )
        {
            this.customerSubscribedSkuCollectionOperations = new CustomerSubscribedSkuCollectionOperations( this.getPartner(), this.customerId );
        }
        return this.customerSubscribedSkuCollectionOperations;
	}

	/**
     * Retrieves information of a specific customer.
     * 
     * @return The customer object.
     */
    @Override
    public Customer get()
    {
        IPartnerServiceProxy<Customer, Customer> partnerServiceProxy =
            new PartnerServiceProxy<Customer, Customer>( new TypeReference<Customer>()
            {
            }, this.getPartner(), MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "GetCustomer" ).getPath(),
                                                        this.getContext(), Locale.US ) );
        return partnerServiceProxy.get();
    }

    /**
     * Deletes the customer from a testing in production account. This won't work for real accounts.
     */
    @Override
    public void delete()
    {
        IPartnerServiceProxy<Customer, Customer> partnerServiceProxy =
            new PartnerServiceProxy<Customer, Customer>( new TypeReference<Customer>()
            {
            }, this.getPartner(), MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "DeleteCustomer" ).getPath(),
                                                        this.getContext(), Locale.US ) );
        partnerServiceProxy.delete();
    }

}