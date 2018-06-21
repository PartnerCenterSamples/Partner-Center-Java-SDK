// -----------------------------------------------------------------------
// <copyright file="IAggregatePartner.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter;

import com.microsoft.store.partnercenter.requestcontext.IRequestContext;

/**
 * A partner operations interface which can be used to generate scoped partner operations that use a specific partner
 * context.
 */
public interface IAggregatePartner
    extends IPartner
{
    /**
     * Returns a partner operations object which uses the provided context when executing operations.
     * 
     * @param context An operation context object.
     * @return A partner operations object which uses the provided operation context.
     */
    IPartner with( IRequestContext context );
}