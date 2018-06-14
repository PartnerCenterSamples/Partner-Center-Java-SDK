// -----------------------------------------------------------------------
// <copyright file="RelationshipCollectionOperations.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.relationships;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.store.partnercenter.BasePartnerComponentString;
import com.microsoft.store.partnercenter.IPartner;
import com.microsoft.store.partnercenter.PartnerService;
import com.microsoft.store.partnercenter.exception.PartnerErrorCategory;
import com.microsoft.store.partnercenter.exception.PartnerException;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.query.IQuery;
import com.microsoft.store.partnercenter.models.query.QueryType;
import com.microsoft.store.partnercenter.models.relationships.PartnerRelationship;
import com.microsoft.store.partnercenter.models.relationships.PartnerRelationshipType;
import com.microsoft.store.partnercenter.models.utils.KeyValuePair;
import com.microsoft.store.partnercenter.network.IPartnerServiceProxy;
import com.microsoft.store.partnercenter.network.PartnerServiceProxy;

/**
 * The relationship collection implementation.
 */
public class RelationshipCollectionOperations
    extends BasePartnerComponentString
    implements IRelationshipCollection
{
    /**
     * Initializes a new instance of the {@link #RelationshipCollectionOperations} class.
     * 
     * @param rootPartnerOperations The root partner operations instance.
     */
    public RelationshipCollectionOperations( IPartner rootPartnerOperations )
    {
        super( rootPartnerOperations );
    }

    /***
     * Retrieves all the partner relationships.
     * 
     * @param partnerRelationshipType The type of partner relationship.
     * @return: The available partner relationships.
     */
    public ResourceCollection<PartnerRelationship> get(PartnerRelationshipType partnerRelationshipType)
    {
        IPartnerServiceProxy<PartnerRelationship, ResourceCollection<PartnerRelationship>> partnerServiceProxy =
            new PartnerServiceProxy<PartnerRelationship, ResourceCollection<PartnerRelationship>>(
                 new TypeReference<ResourceCollection<PartnerRelationship>>()
                {
                }, 
                this.getPartner(), 
                PartnerService.getInstance().getConfiguration().getApis().get( "GetPartnerRelationships" ).getPath());

        partnerServiceProxy.getUriParameters().add(
            new KeyValuePair<String, String>(
                PartnerService.getInstance().getConfiguration().getApis().get( "GetPartnerRelationships" ).getParameters().get( "RelationshipType" ),
                partnerRelationshipType.toString())); 

        return partnerServiceProxy.get();
    }

    /***
     * Queries partner relationships associated to the partner.
     * 
     * @param partnerRelationshipType The type of partner relationship.
     * @param query A query to apply onto partner relationships. 
     * @return: TThe partner relationships that match the given query.
     */
    public ResourceCollection<PartnerRelationship> Query(PartnerRelationshipType partnerRelationshipType, IQuery query)
    {
        if ( query != null && query.getType() !=  QueryType.SIMPLE )
        {
            throw new IllegalArgumentException( "This type of query is not supported." );
        }

        IPartnerServiceProxy<PartnerRelationship, ResourceCollection<PartnerRelationship>> partnerServiceProxy =
            new PartnerServiceProxy<PartnerRelationship, ResourceCollection<PartnerRelationship>>(
                new TypeReference<ResourceCollection<PartnerRelationship>>()
                {
                }, 
                this.getPartner(), 
                PartnerService.getInstance().getConfiguration().getApis().get( "GetPartnerRelationships" ).getPath());

        if ( query.getFilter() != null )
        {
            // add the filter to the request if specified
        	ObjectMapper mapper = new ObjectMapper();
            try
            {
	            partnerServiceProxy.getUriParameters().add
	            (
	            	new KeyValuePair<String, String>
	            	(
	            		PartnerService.getInstance().getConfiguration().getApis().get( "GetPartnerRelationships" ).getParameters().get("Filter"), 
	            		URLEncoder.encode
	            		( 
	            			mapper.writeValueAsString( query.getFilter() ),
	            			"UTF-8" 
	            		)
	            	)
	            );
            }
            catch( JsonProcessingException e )
            {
                throw new PartnerException( "", null, PartnerErrorCategory.REQUEST_PARSING, e );
            }
            catch ( UnsupportedEncodingException e )
            {
                throw new PartnerException( "", null, PartnerErrorCategory.REQUEST_PARSING, e );
            }
        }

        return partnerServiceProxy.get();
    }
}