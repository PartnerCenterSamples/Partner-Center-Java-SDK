// -----------------------------------------------------------------------
// <copyright file="ICountryValidationRulesCollection.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.countryvalidationrules;

import com.microsoft.store.partnercenter.IPartnerComponentString;
import com.microsoft.store.partnercenter.genericoperations.ICountrySelector;

public interface ICountryValidationRulesCollection 
	extends IPartnerComponentString, ICountrySelector<ICountryValidationRules>
{
    /***
     * Obtains behavior for a specific country's validation rules.
     * @returns: The country validation rules operations.
     * @param name="country": The country's ISO2 code.
     */
    ICountryValidationRules byCountry( String country );

}
