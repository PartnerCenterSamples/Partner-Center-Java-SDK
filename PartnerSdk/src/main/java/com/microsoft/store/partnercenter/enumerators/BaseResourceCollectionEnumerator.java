// -----------------------------------------------------------------------
// <copyright file="BaseResourceCollectionEnumerator.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.enumerators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceBaseWithLinks;
import com.microsoft.store.partnercenter.models.StandardResourceCollectionLinks;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.requestcontext.IRequestContext;

/**
 * Base implementation for resource collection enumerators. The type of the resource collection.
 */
public abstract class BaseResourceCollectionEnumerator<T extends ResourceBaseWithLinks<StandardResourceCollectionLinks>>
    extends BasePartnerComponentString
    implements IResourceCollectionEnumerator<T>
{
    private TypeReference<T> responseType;

    /**
     * The current resource collection.
     */
    private T resourceCollection = null;

    /**
     * Initializes a new instance of the {@link #BaseResourceCollectionEnumerator{T}} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param resourceCollection The initial resource collection.
     * @param resourceCollectionConverter An optional converter.
     */
    protected BaseResourceCollectionEnumerator( IPartner rootPartnerOperations, T resourceCollection,
                                                ObjectMapper resourceCollectionConverter,
                                                TypeReference<T> responseType )
    {
        super( rootPartnerOperations );
        if ( resourceCollection == null )
        {
            throw new IllegalArgumentException( "resourceCollection null" );
        }

        this.resourceCollection = resourceCollection;
        this.responseType = responseType;
    }

    /**
     * Gets whether the current customer collection is the first page of results or not.
     */
    @Override
    public boolean isFirstPage()
    {
        if ( !this.hasValue() )
        {
            throw new UnsupportedOperationException( "The enumerator does not have a current value" );
        }

        return this.getCurrent().getLinks() == null || this.getCurrent().getLinks().getPrevious() == null;
    }

    /**
     * Gets whether the current customer collection is the last page of results or not.
     */
    @Override
    public boolean isLastPage()
    {
        if ( !this.hasValue() )
        {
            throw new UnsupportedOperationException( "The enumerator does not have a current value" );
        }

        return this.getCurrent().getLinks() == null || this.getCurrent().getLinks().getNext() == null;
    }

    /**
     * Gets whether the current result collection has a value or not. This indicates if the collection has been fully
     * enumerated or not.
     */
    @Override
    public boolean hasValue()
    {
        return this.resourceCollection != null;
    }

    /**
     * The current resource collection.
     */
    @Override
    public T getCurrent()
    {
        return this.resourceCollection;
    }

    /**
     * Retrieves the next customer result set.
     * 
     * @param context An optional request context. If not provided, the context associated with the partner operations
     *            will be used.
     */
    @Override
    public void next()
    {
        this.next( null );
    }

    @Override
    public void next( IRequestContext context )
    {
        if (!this.hasValue())
        {
            throw new UnsupportedOperationException( "The enumerator does not have a current value" );
        }

        if (this.isLastPage())
        {
            // we are done
            this.resourceCollection = null;
        }
        else
        {
            // get the next page
            PartnerServiceProxy<T, T> partnerServiceProxy =
                new PartnerServiceProxy<T, T>( 
                    responseType, 
                    this.getPartner(),
                    this.resourceCollection.getLinks().getNext().getUri().toString() );
          
            // the links already contains the version number, let's not replicate it
            partnerServiceProxy.setIsUrlPathAlreadyBuilt( true );
          
            for (KeyValuePair<String, String> header : this.resourceCollection.getLinks().getNext().getHeaders())
            {
                partnerServiceProxy.getAdditionalRequestHeaders().add( 
                    new KeyValuePair<String, String>( 
                        header.getKey(),
                        header.getValue() ) );
            }

            this.resourceCollection = partnerServiceProxy.get();
        }
    }

    /**
     * Retrieves the previous customer result set.
     * 
     * @param context An optional request context. If not provided, the context associated with the partner operations
     *            will be used.
     */
    @Override
    public void previous()
    {
        this.previous(null);
    }

    @Override
    public void previous( IRequestContext context )
    {
        if ( !this.hasValue() )
        {
            throw new UnsupportedOperationException( "The enumerator does not have a current value" );
        }

        if ( this.isFirstPage() )
        {
            // we are done
            this.resourceCollection = null;

        }
        else
        {
            // get the previous page
            PartnerServiceProxy<T, T> partnerServiceProxy =
                new PartnerServiceProxy<T, T>( responseType, this.getPartner(),
                                               this.resourceCollection.getLinks().getPrevious().getUri().toString() );
            // the links already contains the query parameters, let's not replicate them
            partnerServiceProxy.setIsUrlPathAlreadyBuilt( true );
            for ( KeyValuePair<String, String> header : this.resourceCollection.getLinks().getPrevious().getHeaders() )
            {
                partnerServiceProxy.getAdditionalRequestHeaders().add( new KeyValuePair<String, String>( header.getKey(),
                                                                                                         header.getValue() ) );
            }
            this.resourceCollection = partnerServiceProxy.get();
        }
    }
}