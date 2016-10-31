// -----------------------------------------------------------------------
// <copyright file="AuditRecord.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.auditing;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.store.partnercenter.models.ResourceBase;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;

/***
 * Represents a record of operation performed by a 
 * Partner user or application
 */
@JsonIgnoreProperties( { "partnerId" } )
public class AuditRecord extends ResourceBase {
    /***
     * Gets or sets the customerId of customer in whose context
     * operation was performed
     */
	@JsonProperty( "customerid" )
    private String __CustomerId;

    public String getCustomerId()
    {
        return __CustomerId;
    }

    public void setCustomerId( String value )
    {
    	__CustomerId = value;
    }

    /***
     *  Gets or sets the name of customer in whose context
     *  operation was performed
     */
    @JsonProperty( "customername" )
    private String __CustomerName;

    public String getCustomerName()
    {
        return __CustomerName;
    }

    public void setCustomerName( String value )
    {
    	__CustomerName = value;
    }

    /***
     *  Gets or sets the  UserId of the operation. This could be in the context of
     *  a 3rd party or 1st party application 
     */
    @JsonProperty( "userprincipalname" )
    private String __UserPrincipalName;

    public String getUserPrincipalName()
    {
        return __UserPrincipalName;
    }

    public void setUserPrincipalName( String value )
    {
    	__UserPrincipalName = value;
    }

    /***
     *  Gets or sets the id of the app invoking the operation
     */
    @JsonProperty( "applicationid" )
    private String __ApplicationId;

    public String getApplicationId()
    {
        return __ApplicationId;
    }

    public void setApplicationId( String value )
    {
    	__ApplicationId = value;
    }

    /***
     *  Gets or sets the  type of the resource acted upon by the operation
     */
    @JsonProperty( "resourcetype" )
    private ResourceType __ResourceType;

    public ResourceType getResourceType()
    {
        return __ResourceType;
    }

    public void setResourceType( ResourceType value )
    {
    	__ResourceType = value;
    }

    /***
     *  Gets or sets the old value of the resource 
     */
    @JsonProperty( "resourceoldvalue" )
    private String __ResourceOldValue;

    public String getResourceOldValue()
    {
        return __ResourceOldValue;
    }

    public void setResourceOldValue( String value )
    {
    	__ResourceOldValue = value;
    }


    /***
     * Gets or sets the new value of the resource
     */
    @JsonProperty( "resourcenewvalue" )
    private String __ResourceNewValue;

    public String getResourceNewValue()
    {
        return __ResourceNewValue;
    }

    public void setResourceNewValue( String value )
    {
    	__ResourceNewValue = value;
    }

    /// <summary>
    /// Gets or sets the type of the operation being performed
    @JsonProperty( "operationtype" )
    private OperationType __OperationType;

    public OperationType getOperationType()
    {
        return __OperationType;
    }

    public void setOperationType( OperationType value )
    {
    	__OperationType = value;
    }

    /***
     * Gets or sets the dateTime when the operation was performed
     */
    @JsonProperty( "operationdate" )
    private DateTime __OperationDate;

    public DateTime getOperationDate()
    {
        return __OperationDate;
    }

    public void setOperationDate( DateTime value )
    {
    	__OperationDate = value;
    }

    /***
     * Gets or sets the status of the operation that is audited
     */
    @JsonProperty( "operationstatus" )
    private OperationStatus __OperationStatus;

    public OperationStatus getOperationStatus()
    {
        return __OperationStatus;
    }

    public void setOperationStatus( OperationStatus value )
    {
    	__OperationStatus = value;
    }

    /***
     * Gets or sets the dictionary which holds additional data
     * that is customized to the operation performed
     */
    @JsonProperty( "customizeddata" )
    private Iterable<KeyValuePair<String, String>> __CustomizedData;

    public Iterable<KeyValuePair<String, String>> getCustomizedData()
    {
        return __CustomizedData;
    }

    public void setCustomizedData( Iterable<KeyValuePair<String, String>> value )
    {
    	__CustomizedData = value;
    }

}
