// -----------------------------------------------------------------------
// <copyright file="AvailabilityOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.products;

import java.text.MessageFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.microsoft.store.partnercenter.BasePartnerComponent;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.models.products.Availability;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;
import com.microsoft.store.partnercenter.utils.ParameterValidator;
import com.microsoft.store.partnercenter.utils.StringHelper;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;
import com.microsoft.store.partnercenter.models.utils.QuadrupleTuple;

/**
 * Single availability operations implementation.
 */
public class AvailabilityOperations 
    extends BasePartnerComponent<QuadrupleTuple<String, String, String, String>> implements IAvailability 
{
    /**
     * Initializes a new instance of the {@link #AvailabilityOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     * @param productId             Identifier of the product.. 
     * @param skuId                 Identifier of the SKU.
     * @param availabilityId        Identifier of the availability.
     * @param country               The country on which to base the availability.
     */
    public AvailabilityOperations(IPartner rootPartnerOperations, String productId, String skuId, String availabilityId, String country) 
    {
        super(rootPartnerOperations, new QuadrupleTuple<String, String, String, String>(productId, skuId, availabilityId, country));

        if (StringHelper.isNullOrWhiteSpace(productId)) {
            throw new IllegalArgumentException("productId must be set");
        }

        if (StringHelper.isNullOrWhiteSpace(skuId)) {
            throw new IllegalArgumentException("skuId must be set");
        }
        
        if (StringHelper.isNullOrWhiteSpace(availabilityId)) {
            throw new IllegalArgumentException("availabilityId must be set");
        }

        ParameterValidator.isValidCountryCode(country);
    }

    /***
     * Retrieves the availability information.
     * 
     * @return: The availability information.
     */
    public Availability get()
    {
        IPartnerServiceProxy<Availability, Availability> partnerServiceProxy = new PartnerServiceProxy<Availability, Availability>(
            new TypeReference<Availability>() {
            }, this.getPartner(),
            MessageFormat.format(PartnerService.getInstance().getConfiguration().getApis().get("GetAvailability").getPath(),
                    this.getContext().getItem1(), this.getContext().getItem2(), this.getContext().getItem3() ));

        partnerServiceProxy.getUriParameters().add(
            new KeyValuePair<String, String>(
                PartnerService.getInstance().getConfiguration().getApis().get( "GetAvailability" ).getParameters().get( "Country" ),
                this.getContext().getItem4()));          

        return partnerServiceProxy.get();
    }
}