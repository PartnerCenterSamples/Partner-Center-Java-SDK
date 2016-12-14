// -----------------------------------------------------------------------
// <copyright file="AzureInstanceData.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.utilizations;

import java.net.URI;
import java.util.Map;

public class AzureInstanceData
{
    /***
     * Gets or sets the fully qualified Azure resource ID, which includes the resource groups and the instance name.
     */
    private URI __ResourceUri;

	public URI getResourceUri() {
		return __ResourceUri;
	}

	public void setResourceUri(URI resourceUri) {
		this.__ResourceUri = resourceUri;
	}

    /***
     * Gets or sets the region in which the this service was run.
     */
    private String __Location;

	public String getLocation() {
		return __Location;
	}

	public void setLocation(String location) {
		this.__Location = location;
	}

    /***
     * Gets or sets the unique namespace used to identify the resource for Azure Marketplace 3rd party usage.
     */
    private String __PartNumber;

	public String getPartNumber() {
		return __PartNumber;
	}

	public void setPartNumber(String partNumber) {
		this.__PartNumber = partNumber;
	}

    /***
     * Gets or sets the unique namespace used to identify the 3rd party order for Azure Marketplace.
     */
    private String __OrderNumber;


	public String getOrderNumber() {
		return __OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.__OrderNumber = orderNumber;
	}

	/***
     * Gets or sets the the resource tags specified by the user.
     */
    private Map<String, String> __Tags;

	public Map<String, String> getTags() {
		return __Tags;
	}

	public void setTags(Map<String, String> tags) {
		this.__Tags = tags;
	}

}
