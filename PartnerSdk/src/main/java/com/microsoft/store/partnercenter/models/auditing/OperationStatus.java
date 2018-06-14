// -----------------------------------------------------------------------
// <copyright file="OperationStatus.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.auditing;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Represents status of an operation
 */
public enum OperationStatus {
    /***
     * Indicates success of the operation
     */
    @JsonProperty("succeeded")
    Succeeded,

    /***
     * Indicates failure of the operation
     */
    @JsonProperty("failed")
    Failed,

    /***
     * Indicates that the operation is still in progress
     */
    @JsonProperty("progress")
    Progress,

    /***
     * Indicates that the operation is declined
     */
    @JsonProperty("decline")
    Decline
}