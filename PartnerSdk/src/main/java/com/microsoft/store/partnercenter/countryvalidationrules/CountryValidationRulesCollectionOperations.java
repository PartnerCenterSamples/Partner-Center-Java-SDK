// -----------------------------------------------------------------------
// <copyright file="CountryValidationRulesCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.countryvalidationrules;

import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;

public class CountryValidationRulesCollectionOperations 
	extends BasePartnerComponentString
	implements ICountryValidationRulesCollection 
{

	/***
	 * Initializes a new instance of the CountryValidationRulesCollectionOperations class.
	 * @param rootPartnerOperations:  root partner operations instance.
	 */
	public CountryValidationRulesCollectionOperations( IPartner rootPartnerOperations ) 
	{
		super(rootPartnerOperations);
	}

	/***
	 * Obtains a specific behavior for country validation rules.
	 * 
	 * @return: The country validation rules operations
	 * @param country: The country
	 */
	@Override
	public ICountryValidationRules byCountry( String country ) {
		return new CountryValidationRulesOperations( this.getPartner(), country );
	}

}
