// -----------------------------------------------------------------------
// <copyright file="ICustomerCollection.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.customers;

import com.microsoft.store.partnercenter.IPartnerComponentString;
import com.microsoft.store.partnercenter.genericoperations.IEntireEntityCollectionRetrievalOperations;
import com.microsoft.store.partnercenter.genericoperations.IEntityCreateOperations;
import com.microsoft.store.partnercenter.models.SeekBasedResourceCollection;
import com.microsoft.store.partnercenter.models.customers.Customer;
import com.microsoft.store.partnercenter.models.query.IQuery;
import com.microsoft.store.partnercenter.relationshiprequests.ICustomerRelationshipRequest;
import com.microsoft.store.partnercenter.usage.ICustomerUsageRecordCollection;

/**
 * Represents the behavior of the partner customers.
 */
public interface ICustomerCollection
    extends IPartnerComponentString, IEntityCreateOperations<Customer, Customer>,
    IEntireEntityCollectionRetrievalOperations<Customer, SeekBasedResourceCollection<Customer>>
{
    /**
     * Gets the customer usage record operations.
     */
    ICustomerUsageRecordCollection getUsageRecords();

    /**
     * Gets the customer relationship request operations.
     */
    ICustomerRelationshipRequest getRelationshipRequests();

    /**
     * Gets a single customer operations.
     * 
     * @param customerId The customer id.
     * @return The customer operations.
     */
    ICustomer byId( String customerId );

    /***
     * Retrieves all the partner's customers.
     * 
     * @return: The partner's customers.
     */
    SeekBasedResourceCollection<Customer> get();
    
    /**
     * Queries customers associated to the partner. - Count queries are not supported by this operation. - You can set
     * the page size or filter or do both at the same time. - Sort is not supported. - You can navigate to other pages
     * by specifying a seek query with the seek operation and the continuation token sent by the previous operation.
     * 
     * @param customersQuery A query to apply onto customers. Check {@link #QueryFactory} to see how to build queries.
     * @return The requested customers.
     */
    SeekBasedResourceCollection<Customer> query( IQuery customersQuery );
}