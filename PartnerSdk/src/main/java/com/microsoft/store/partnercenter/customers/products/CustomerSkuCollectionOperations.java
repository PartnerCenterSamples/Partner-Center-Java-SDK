// -----------------------------------------------------------------------
// <copyright file="CustomerSkuCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.customers.products;

import com.fasterxml.jackson.core.type.TypeReference;
import java.text.MessageFormat;

import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.products.Sku;
import com.microsoft.store.partnercenter.models.utils.Tuple;
import com.microsoft.store.partnercenter.products.ISku;
import com.microsoft.store.partnercenter.products.ISkuCollection;
import com.microsoft.store.partnercenter.products.ISkuCollectionByTargetSegment;
import com.microsoft.store.partnercenter.utils.StringHelper;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;

/**
 * Implementation of customer sku collection operations.
 */
public class CustomerSkuCollectionOperations
    extends BasePartnerComponent<Tuple<String, String>>
    implements ISkuCollection
{
    /**
     * Initializes a new instance of the {@link #CustomerSkuCollectionOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param customerId Identifier for the customer.
     * @param productId Identifier for the product.
     */
    public CustomerSkuCollectionOperations( IPartner rootPartnerOperations, String customerId, String productId )
    {
        super( rootPartnerOperations, new Tuple<String, String>(customerId, productId) );

        if ( StringHelper.isNullOrWhiteSpace( customerId ) )
        {
            throw new IllegalArgumentException( "customerId must be set" );
        }

        if ( StringHelper.isNullOrWhiteSpace( productId ) )
        {
            throw new IllegalArgumentException( "productId must be set" );
        }
    }

    /***
     * Retrieves the operations tied with a specific SKU.
     * 
     * @param skuId Identifier for the SKU.
     * @return: The available SKU operations.
     */
    @Override    
    public ISku byId(String skuId)
    {
        return new CustomerSkuOperations(this.getPartner(), this.getContext().getItem1(), this.getContext().getItem2(), skuId);
    }

    /**
     * Retrieves the operations that can be applied on skus that belong to a segment.
     * 
     * @param targetSegement The SKU segment filter.
     * @return The sku collection operations by target segment.
     */
    @Override
    public ISkuCollectionByTargetSegment byTargetSegment(String targetSegment)
    {
        return new CustomerSkuCollectionByTargetSegmentOperations(this.getPartner(), this.getContext().getItem1(), this.getContext().getItem2(), targetSegment);
    }

    /**
     * Retrieves all the SKUs for the provided product.
     * 
     * @return The SKUs for the provided product.
     */
    @Override
    public ResourceCollection<Sku> get()
    {
        IPartnerServiceProxy<Sku, ResourceCollection<Sku>> partnerServiceProxy =
        new PartnerServiceProxy<Sku, ResourceCollection<Sku>>( new TypeReference<ResourceCollection<Sku>>()
        {
        }, 
        this.getPartner(), 
        MessageFormat.format( PartnerService.getInstance().getConfiguration().getApis().get( "GetCustomerSkus" ).getPath(),
            this.getContext().getItem1(), this.getContext().getItem2() ) );

        return partnerServiceProxy.get();
    }
}