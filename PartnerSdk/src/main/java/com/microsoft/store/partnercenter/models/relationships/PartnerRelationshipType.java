// -----------------------------------------------------------------------
// <copyright file="PartnerRelationshipType.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.relationships;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * The types of relationships between partners for two tier partner scenario.
 */
public enum PartnerRelationshipType 
{
    @JsonProperty("is_indirect_reseller_pf")
    IsIndirectResellerOf,

    @JsonProperty("is_indirect_cloud_colution_provider_of")
    IsIndirectCloudSolutionProviderOf
}