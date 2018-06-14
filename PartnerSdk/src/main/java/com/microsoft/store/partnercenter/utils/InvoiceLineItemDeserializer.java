// -----------------------------------------------------------------------
// <copyright file="InvoiceLineItemDeserializer.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.utils;

import java.io.IOException;
import java.text.MessageFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.store.partnercenter.models.invoices.BillingProvider;
import com.microsoft.store.partnercenter.models.invoices.LicenseBasedLineItem;
import com.microsoft.store.partnercenter.models.invoices.OneTimeInvoiceLineItem;
import com.microsoft.store.partnercenter.models.invoices.InvoiceLineItem;
import com.microsoft.store.partnercenter.models.invoices.UsageBasedLineItem;
import com.microsoft.store.partnercenter.models.invoices.DailyUsageLineItem;

public class InvoiceLineItemDeserializer
    extends JsonDeserializer<InvoiceLineItem>
{
    @Override
    public InvoiceLineItem deserialize( JsonParser parser, DeserializationContext context )
        throws IOException, JsonProcessingException
    {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        JsonNode jsonNode = parser.readValueAsTree();
        Object target = null;
        String billingProvider = jsonNode.get( "billingProvider" ).textValue();
        String invoiceLineItemType = jsonNode.get( "invoiceLineItemType" ).textValue();

        if ( invoiceLineItemType.equals( "usage_line_items" ) )
        {
            if ( billingProvider.equalsIgnoreCase( BillingProvider.Azure.toString() ) )
            {
                target = mapper.readValue( parser, DailyUsageLineItem.class );
            }
        }
        else if ( invoiceLineItemType.equals( "billing_line_items" ) )
        {
            if ( billingProvider.equalsIgnoreCase( BillingProvider.Azure.toString()))
            {
                target = mapper.readValue(parser, UsageBasedLineItem.class);
            }
            else if ( billingProvider.equalsIgnoreCase(BillingProvider.Office.toString()))
            {
                target = mapper.readValue(parser, LicenseBasedLineItem.class);
            }
            else if ( billingProvider.equalsIgnoreCase(BillingProvider.OneTime.toString()))
            {
                target = mapper.readValue(parser, OneTimeInvoiceLineItem.class );
            } 
        }
        else
        {
            throw new IOException(MessageFormat.format( "InvoiceLineItemConverter cannot deserialize invoice line items with type {0}", invoiceLineItemType));
        }
        
        if (target == null)
        {
            String data = jsonNode.get("serviceType").asText();
            throw new IOException(MessageFormat.format( "InvoiceLineItemConverter cannot deserialize invoice line items with type {0} and billing provider: {1}", invoiceLineItemType, billingProvider));
        }
        
        return (InvoiceLineItem) target;
    }
}