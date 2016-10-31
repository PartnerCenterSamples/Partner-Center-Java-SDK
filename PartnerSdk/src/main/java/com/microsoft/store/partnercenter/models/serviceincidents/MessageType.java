// -----------------------------------------------------------------------
// <copyright file="MessageType.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.serviceincidents;

import com.fasterxml.jackson.annotation.JsonProperty;

/***
 * Represents the status of partner center services.
 */
public enum MessageType
{
    /***
     * Default type none.
     */
	@JsonProperty( "None" ) NONE,
	
    /***
     * Active incident.
     */
	@JsonProperty( "Incident" ) INCIDENT,
	
    /***
     * Message center message.
     */
	@JsonProperty( "MessageCenter" ) MESSAGECENTER,
	
    /***
     * All types.
     */
	@JsonProperty( "All" ) ALL

}
