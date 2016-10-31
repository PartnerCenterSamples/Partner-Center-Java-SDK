// -----------------------------------------------------------------------
// <copyright file="UriDeserializer.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UriDeserializer 
	extends JsonDeserializer<URI>
{

	@Override
	public URI deserialize( JsonParser parser, DeserializationContext context )
			throws IOException, JsonProcessingException
	{
		JsonToken currentToken = parser.getCurrentToken();
		if ( currentToken.equals( JsonToken.VALUE_STRING ) )
		{
			ObjectMapper mapper = (ObjectMapper) parser.getCodec();
			String linkUri = parser.getText().trim();
	        linkUri = URLEncoder.encode( mapper.writeValueAsString( linkUri ), "UTF-8" );
	        try
	        {
				return new URI ( linkUri );
			}
	        catch (URISyntaxException e)
	        {
				e.printStackTrace();
			}
		}
		else if( currentToken.equals( JsonToken.VALUE_NULL ) )
		{
			return null;
		}
		
		throw context.mappingException( URI.class );
	}

}
