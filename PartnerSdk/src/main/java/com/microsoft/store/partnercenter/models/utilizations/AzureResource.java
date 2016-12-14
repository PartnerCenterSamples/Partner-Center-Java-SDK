// -----------------------------------------------------------------------
// <copyright file="AzureResource.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.models.utilizations;

/***
 * Represents an Azure resource being metered.
 */
public class AzureResource
{
	/***
	 * Gets or sets the unique identifier of the Azure resource that was consumed. Also known as resourceID or resourceGUID.
	 */
	private String __Id;
	
	public String getId() {
		return __Id;
	}

	public void setId(String id) {
		this.__Id = id;
	}

	/***
	 * Gets or sets the friendly name of the Azure resource being consumed.
	 */
	private String __Name;
	
	public String getName() {
		return __Name;
	}

	public void setName(String name) {
		this.__Name = name;
	}

	/***
	 * Gets or sets the category of the consumed Azure resource.
	 */
	private String __Category;
	
	public String getCategory() {
		return __Category;
	}

	public void setCategory(String category) {
		this.__Category = category;
	}

	/***
	 * Gets or sets the sub-category of the consumed Azure resource.
	 */
	private String __Subcategory;

	public String getSubcategory() {
		return __Subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.__Subcategory = subcategory;
	}
}
