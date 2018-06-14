// -----------------------------------------------------------------------
// <copyright file="CurrencyInfo.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.products;

/**
 * Class that represents the currency related information.
 */
public class CurrencyInfo
{
    /**
     * Gets or sets the code of the currency.
     */
    private String __Code;

    public String getCode()
    {
        return __Code;
    }

    public void setCode( String value )
    {
        __Code = value;
    }

    /**
     * Gets or sets the symbol of the currency.
     */
    private String __Symbol;

    public String getSymbol()
    {
        return __Symbol;
    }

    public void setSymbol( String value )
    {
        __Symbol = value;
    }
}