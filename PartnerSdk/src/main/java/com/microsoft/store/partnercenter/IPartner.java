// -----------------------------------------------------------------------
// <copyright file="IPartner.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter;

import com.microsoft.store.partnercenter.analytics.IPartnerAnalyticsCollection;
import com.microsoft.store.partnercenter.auditrecords.IAuditRecordsCollection;
import com.microsoft.store.partnercenter.countryvalidationrules.ICountryValidationRulesCollection;
import com.microsoft.store.partnercenter.customers.ICustomerCollection;
import com.microsoft.store.partnercenter.domains.IDomainCollection;
import com.microsoft.store.partnercenter.enumerators.IResourceCollectionEnumeratorContainer;
import com.microsoft.store.partnercenter.extensions.IExtensions;
import com.microsoft.store.partnercenter.genericoperations.ICountrySelector;
import com.microsoft.store.partnercenter.invoices.IInvoiceCollection;
import com.microsoft.store.partnercenter.offers.IOfferCategoryCollection;
import com.microsoft.store.partnercenter.offers.IOfferCollection;
import com.microsoft.store.partnercenter.products.IProductCollection;
import com.microsoft.store.partnercenter.profiles.IPartnerProfileCollection;
import com.microsoft.store.partnercenter.ratecards.IRateCardCollection;
import com.microsoft.store.partnercenter.relationships.IRelationshipCollection;
import com.microsoft.store.partnercenter.requestcontext.IRequestContext;
import com.microsoft.store.partnercenter.serviceincidents.IServiceIncidentCollection;
import com.microsoft.store.partnercenter.servicerequests.IPartnerServiceRequestCollection;
import com.microsoft.store.partnercenter.usage.IPartnerUsageSummary;
import com.microsoft.store.partnercenter.validations.IValidationOperations;

/**
 * The main entry point into using the partner SDK functionality. Represents a partner and encapsulates all the behavior
 * attached to partners. Use this interface to get to the partner's customers, profiles, and customer orders, profiles
 * and subscriptions and more.
 */
public interface IPartner
{
    /**
     * Gets the partner credentials.
     */
    IPartnerCredentials getCredentials();

    /**
     * Gets the partner context.
     */
    IRequestContext getRequestContext();

    /**
     * Gets the collection enumerators available for traversing through results.
     */
    IResourceCollectionEnumeratorContainer getEnumerators();

    /**
     * Gets the offer categories operations available to the partner.
     */
    ICountrySelector<IOfferCategoryCollection> getOfferCategories();

    /**
     * Gets the offer operations available to the partner
     */
    ICountrySelector<IOfferCollection> getOffers();

    /**
     * Gets the profiles operations available to the partner.
     */
    IPartnerProfileCollection getProfiles();

    /**
     * Gets the partner customers operations.
     */
    ICustomerCollection getCustomers();

    /**
     * Gets the partner's invoices.
     */
    IInvoiceCollection getInvoices();

    /**
     * Gets the operations that can be performed on a partners' service requests
     */
    IPartnerServiceRequestCollection getServiceRequests();
    
    /***
     * Gets the service health operations that can be performed on a partner's subscribed services.
     */
    IServiceIncidentCollection getServiceIncidents();

    /***
     *  Gets the country validation rules collection operations available to the partner.
     */
    ICountryValidationRulesCollection getCountryValidationRules();
    
    /**
     * Gets the usage summary operations available to the partner.
     */
    IPartnerUsageSummary getUsageSummary();

    /***
     * Gets the domains operations available to the partner.
     */
    IDomainCollection getDomains();

    /**
     * Gets the audit records operations available to the partner.
     */
    IAuditRecordsCollection getAuditRecords();

    /***
     * Gets the rate card operations available to the partner.
     */
    IRateCardCollection getRateCards();

    /***
     * Gets the analytics collection operations.
     */
    IPartnerAnalyticsCollection getAnalytics();

    /***
     * Gets the extensions operations available to the partner.
     */
    IExtensions getExtensions();

    /***
     * Gets the product operations available to the partner.
     */
    IProductCollection getProducts();

    /***
     * Gets the relationship collection operations available to the partner.
     */    
    IRelationshipCollection getRelationships();

    /***
     * Gets the validation operations available to the partner.
     */    
    IValidationOperations getValidations();
}