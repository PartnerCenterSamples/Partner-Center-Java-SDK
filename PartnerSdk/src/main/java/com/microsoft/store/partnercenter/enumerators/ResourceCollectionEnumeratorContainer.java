// -----------------------------------------------------------------------
// <copyright file="ResourceCollectionEnumeratorContainer.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.enumerators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.factory.IResourceCollectionEnumeratorFactory;
import com.microsoft.store.partnercenter.factory.IndexBasedCollectionEnumeratorFactory;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.SeekBasedResourceCollection;
import com.microsoft.store.partnercenter.models.auditing.AuditRecord;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.models.invoices.Invoice;
import com.microsoft.store.partnercenter.models.invoices.InvoiceLineItem;
import com.microsoft.store.partnercenter.models.offers.Offer;
import com.microsoft.store.partnercenter.models.servicerequests.ServiceRequest;
import com.microsoft.store.partnercenter.models.users.CustomerUser;

/**
 * Contains supported resource collection enumerators.
 */
public class ResourceCollectionEnumeratorContainer
    extends BasePartnerComponentString
    implements IResourceCollectionEnumeratorContainer
{
    /**
     * A lazy reference to an offer enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<Offer, ResourceCollection<Offer>> offerEnumeratorFactory;

    /**
     * A lazy reference to a customer enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<Customer, SeekBasedResourceCollection<Customer>> customerEnumeratorFactory;

    /**
     * A lazy reference to a customer enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<CustomerUser, SeekBasedResourceCollection<CustomerUser>> customerUserEnumeratorFactory;

    /**
     * A lazy reference to an invoice enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<Invoice, ResourceCollection<Invoice>> invoiceEnumeratorFactory;

    /**
     * A lazy reference to a service request enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<ServiceRequest, ResourceCollection<ServiceRequest>> serviceRequestEnumeratorFactory;

    /**
     * A lazy reference to an invoice line enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<InvoiceLineItem, ResourceCollection<InvoiceLineItem>> invoiceLineItemEnumeratorFactory;

    /**
     * A lazy reference to an audit record enumerator factory.
     */
    private IndexBasedCollectionEnumeratorFactory<AuditRecord, SeekBasedResourceCollection<AuditRecord>> auditRecordEnumeratorFactory;

    /**
     * Initializes a new instance of the {@link #ResourceCollectionEnumeratorContainer} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     */
    public ResourceCollectionEnumeratorContainer( IPartner rootPartnerOperations )
    {
        super( rootPartnerOperations );
    }

    /**
     * Gets a factory that creates offer collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<ResourceCollection<Offer>> getOffers()
    {
        if ( this.offerEnumeratorFactory == null )
        {
            this.offerEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<Offer, ResourceCollection<Offer>>( this.getPartner(),
                                                                                             new TypeReference<ResourceCollection<Offer>>()
                                                                                             {
                                                                                             } );
        }
        return this.offerEnumeratorFactory;
    }

    /**
     * Gets a factory that creates customer collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<SeekBasedResourceCollection<Customer>> getCustomers()
    {
        if ( this.customerEnumeratorFactory == null )
        {
            this.customerEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<Customer, SeekBasedResourceCollection<Customer>>( this.getPartner(),
                                                                                                        new TypeReference<SeekBasedResourceCollection<Customer>>()
                                                                                                        {
                                                                                                        } );
        }
        return this.customerEnumeratorFactory;
    }

    /**
     * Gets a factory that creates customer collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<SeekBasedResourceCollection<CustomerUser>> getCustomerUsers()
    {
        if ( this.customerUserEnumeratorFactory == null )
        {
            this.customerUserEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<CustomerUser, SeekBasedResourceCollection<CustomerUser>>( this.getPartner(),
                                                                                                        new TypeReference<SeekBasedResourceCollection<CustomerUser>>()
                                                                                                        {
                                                                                                        } );
        }
        return this.customerUserEnumeratorFactory;
    }
    
    /**
     * Gets a factory that creates invoice collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<ResourceCollection<Invoice>> getInvoices()
    {
        if ( this.invoiceEnumeratorFactory == null )
        {
            this.invoiceEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<Invoice, ResourceCollection<Invoice>>( this.getPartner(),
                                                                                                      new TypeReference<ResourceCollection<Invoice>>()
                                                                                                      {
                                                                                                      } );
        }
        return this.invoiceEnumeratorFactory;
    }

    /**
     * Gets a factory that creates service request collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<ResourceCollection<ServiceRequest>> getServiceRequests()
    {
        if ( this.serviceRequestEnumeratorFactory == null )
        {
            this.serviceRequestEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<ServiceRequest, ResourceCollection<ServiceRequest>>( this.getPartner(),
                                                                                                                    new TypeReference<ResourceCollection<ServiceRequest>>()
                                                                                                                    {
                                                                                                                    } );
        }
        return this.serviceRequestEnumeratorFactory;
    }

    /**
     * Gets a factory that creates invoice line item collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<ResourceCollection<InvoiceLineItem>> getInvoiceLineItems()
    {
        if ( this.invoiceLineItemEnumeratorFactory == null )
        {
            this.invoiceLineItemEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<InvoiceLineItem, ResourceCollection<InvoiceLineItem>>( this.getPartner(),
                                                                                                                      new TypeReference<ResourceCollection<InvoiceLineItem>>()
                                                                                                                      {
                                                                                                                      } );
        }
        return this.invoiceLineItemEnumeratorFactory;
    }

    /**
     * Gets a factory that creates audit records collection enumerators.
     */
    @Override
    public IResourceCollectionEnumeratorFactory<SeekBasedResourceCollection<AuditRecord>> getAuditRecords()
    {
        if ( this.auditRecordEnumeratorFactory == null )
        {
            this.auditRecordEnumeratorFactory =
                new IndexBasedCollectionEnumeratorFactory<AuditRecord, SeekBasedResourceCollection<AuditRecord>>
            ( 
            	this.getPartner(),
            	new TypeReference<SeekBasedResourceCollection<AuditRecord>>(){} 
            );
        }
        return this.auditRecordEnumeratorFactory;
    }

}
