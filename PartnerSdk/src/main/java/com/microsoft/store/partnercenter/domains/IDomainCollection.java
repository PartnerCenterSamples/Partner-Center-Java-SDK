// -----------------------------------------------------------------------
// <copyright file="IDomainCollection.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.domains;

import com.microsoft.store.partnercenter.IPartnerComponentString;

public interface IDomainCollection extends IPartnerComponentString
{
    /***
     * Obtains a specific domain behavior.
     * 
     * @param name="country": The domain.
     * @returns: The domain operationsThe domain.
     */
    IDomain byDomain( String domain );

}
