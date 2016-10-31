//-----------------------------------------------------------------------
//<copyright file="GetOfferDetails.java" company="Microsoft">
//   Copyright (c) Microsoft Corporation.  All rights reserved.
//</copyright>
//-----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.offers;

import java.text.MessageFormat;

import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.models.offers.Offer;
import com.microsoft.store.partnercenter.samples.BasePartnerScenario;
import com.microsoft.store.partnercenter.samples.IScenarioContext;

/**
* A scenario that retrieves all the offers supported in a country.
*/
public class GetOfferDetails
 extends BasePartnerScenario
{
 /**
  * Initializes a new instance of the {@link #GetOffers} class.
  * 
  * @param context The scenario context.
  */
 public GetOfferDetails( IScenarioContext context )
 {
     super( "Get offer details", context );
 }

 /**
  * Executes the scenario.
  */
 @Override
 protected void runScenario()
 {
     IPartner partnerOperations = this.getContext().getUserPartnerOperations();
     String countryCode =
         this.getContext().getConsoleHelper().readNonEmptyString( "Enter the 2 digit country code to get its supported offers",
                                                                  "The country code can't be empty" );
     this.getContext().getConsoleHelper().startProgress( MessageFormat.format( "Getting offers for {0}",
                                                                               countryCode ) );
     Offer offer = partnerOperations.getOffers().byCountry( countryCode ).byId( "8AA7E78B-B265-4AC6-ADA0-14900A8A3F94" ).get();
     this.getContext().getConsoleHelper().stopProgress();
     this.getContext().getConsoleHelper().writeObject( offer, "Offers details" );
 }

}
