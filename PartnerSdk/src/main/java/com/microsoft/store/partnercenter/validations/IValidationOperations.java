// -----------------------------------------------------------------------
// <copyright file="IValidationOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.validations;

import com.microsoft.store.partnercenter.IPartnerComponentString;
import com.microsoft.store.partnercenter.models.validations.CountryInformation;

/**
 * Represents the behavior of the validations.
 */
public interface IValidationOperations
    extends IPartnerComponentString
{
    /**
     * Checks the availability of a domain prefix.
     * 
     * @param domainPrefix The domain prefix to be verified.
     * @return A boolean value to indicate if the domain is available.
     */
    Boolean checkDomainAvailability( String domainPrefix );

    /**
     * Gets the market specific validation data by country.
     * 
     * @param country The country.
     * @return The market specific validation data operations.
     */
    CountryInformation getMarketSpecificValidationDataByCountry( String country );

}
