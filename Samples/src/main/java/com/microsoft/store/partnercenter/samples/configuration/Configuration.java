// -----------------------------------------------------------------------
// <copyright file="Configuration.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples.configuration;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration
{
    @JsonProperty( "PartnerServiceSettings" )
    private Map<String, String> partnerServiceSettings;

    public Map<String, String> getPartnerServiceSettings()
    {
        return partnerServiceSettings;
    }

    public void setPartnerServiceSettings( Map<String, String> partnerServiceSettings )
    {
        this.partnerServiceSettings = partnerServiceSettings;
    }

    @JsonProperty( "UserAuthentication" )
    private Map<String, String> userAuthentication;

    public Map<String, String> getUserAuthentication()
    {
        return userAuthentication;
    }

    public void setUserAuthentication( Map<String, String> userAuthentication )
    {
        this.userAuthentication = userAuthentication;
    }

    @JsonProperty( "AppAuthentication" )
    private Map<String, String> appAuthentication;

    public Map<String, String> getAppAuthentication()
    {
        return appAuthentication;
    }

    public void setAppAuthentication( Map<String, String> appAuthentication )
    {
        this.appAuthentication = appAuthentication;
    }

    @JsonProperty( "TipAccountSettings" )
    private Map<String, String> tipAccountSettings;

    public Map<String, String> getTipAccountSettings()
    {
        return tipAccountSettings;
    }

    public void setTipAccountSettings( Map<String, String> tipAccountSettings )
    {
        this.tipAccountSettings = tipAccountSettings;
    }

    @JsonProperty( "ScenarioSettings" )
    private Map<String, String> scenarioSettings;

    public Map<String, String> getScenarioSettings()
    {
        return scenarioSettings;
    }

    public void setScenarioSettings( Map<String, String> scenarioSettings )
    {
        this.scenarioSettings = scenarioSettings;
    }
}