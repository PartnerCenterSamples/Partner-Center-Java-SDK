// -----------------------------------------------------------------------
// <copyright file="BasePartnerScenario.java" company="Microsoft">
//      Copyright (c) Microsoft Corporation.  All rights reserved.
// </copyright>
// -----------------------------------------------------------------------

package com.microsoft.store.partnercenter.samples;

import java.text.MessageFormat;
import java.util.List;

import com.microsoft.store.partnercenter.IAggregatePartner;
import com.microsoft.store.partnercenter.models.ResourceCollection;
import com.microsoft.store.partnercenter.models.subscriptions.Subscription;
import com.microsoft.store.partnercenter.samples.scenarioexecution.IScenarioExecutionStrategy;
import com.microsoft.store.partnercenter.samples.scenarioexecution.PromptExecutionStrategy;
import com.microsoft.store.partnercenter.utils.StringHelper;

/**
 * The base class for partner scenarios. Provides common behavior for all partner scenarios.
 */
public abstract class BasePartnerScenario
    implements IPartnerScenario
{
    /**
     * Initializes a new instance of the {@link #BasePartnerScenario} class.
     * 
     * @param title The scenario title.
     * @param context The scenario context.
     * @param executionStrategy The scenario execution strategy.
     * @param childScenarios The child scenarios attached to the current scenario.
     */
    public BasePartnerScenario( String title, IScenarioContext context )
    {
        this( title, context, null, null );
    }

    public BasePartnerScenario( String title, IScenarioContext context, IScenarioExecutionStrategy executionStrategy )
    {
        this( title, context, executionStrategy, null );
    }

    public BasePartnerScenario( String title, IScenarioContext context, IScenarioExecutionStrategy executionStrategy,
                                List<IPartnerScenario> childScenarios )
    {
        if ( StringHelper.isNullOrWhiteSpace( title ) )
        {
            throw new IllegalArgumentException( "title has to be set" );
        }

        if ( context == null )
        {
            throw new IllegalArgumentException( "context can't be null" );
        }

        this.setTitle( title );
        this.setContext( context );
        this.setExecutionStrategy( executionStrategy != null ? executionStrategy : new PromptExecutionStrategy() );
        this.setChildren( childScenarios );
    }

    /**
     * Gets the scenario title.
     */
    private String __Title;

    public String getTitle()
    {
        return __Title;
    }

    public void setTitle( String value )
    {
        __Title = value;
    }

    /**
     * Gets the children scenarios of the current scenario.
     */
    private List<IPartnerScenario> __Children;

    public List<IPartnerScenario> getChildren()
    {
        return __Children;
    }

    public void setChildren( List<IPartnerScenario> value )
    {
        __Children = value;
    }

    /**
     * Gets the scenario context.
     */
    private IScenarioContext __Context;

    public IScenarioContext getContext()
    {
        return __Context;
    }

    public void setContext( IScenarioContext value )
    {
        __Context = value;
    }

    /**
     * Gets or sets the scenario execution behavior.
     */
    private IScenarioExecutionStrategy __ExecutionStrategy;

    private IScenarioExecutionStrategy getExecutionStrategy()
    {
        return __ExecutionStrategy;
    }

    private void setExecutionStrategy( IScenarioExecutionStrategy value )
    {
        __ExecutionStrategy = value;
    }

    /**
     * Runs the scenario.
     */
    public void run()
    {
        do
        {
            System.out.println();
            System.out.println( new String( new char[80] ).replace( '\0', '-' ) );
            System.out.println( this.getTitle() );
            System.out.println( new String( new char[80] ).replace( '\0', '-' ) );
            System.out.println();
            try
            {
                this.runScenario();
            }
            catch ( Exception exception )
            {
                this.getContext().getConsoleHelper().error( exception.toString() );
            }

            System.out.println();
        }
        while ( !this.getExecutionStrategy().isScenarioComplete( this ) );
    }

    /**
     * Obtains a customer ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return A customer ID.
     */
    protected String obtainCustomerId()
    {
        return this.obtainCustomerId( null );
    }

    protected String obtainCustomerId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultCustomerId" ),
                                 "Customer Id", StringHelper.isNullOrWhiteSpace( promptMessage )
                                                 ? "Enter the customer ID" : promptMessage,
                                 "The customer ID can't be empty" );
    }

    /**
     * Obtains a customer ID to work with for Usage scenarios from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return A customer ID.
     */
    protected String obtainCustomerIdForUsage( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultCustomerIdForUsage" ),
                "Customer Id", StringHelper.isNullOrWhiteSpace( promptMessage )
                                ? "Enter the customer ID" : promptMessage,
                "The customer ID can't be empty" );
    }

    /**
     * Obtains a customer user ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return A customer ID.
     */
    protected String obtainCustomerUserId()
    {
        return this.obtainCustomerUserId( null );
    }

    protected String obtainCustomerUserId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultCustomerUserId" ),
                                 "Customer User Id", StringHelper.isNullOrWhiteSpace( promptMessage )
                                                 ? "Enter the customer user ID" : promptMessage,
                                 "The customer user ID can't be empty" );
    }

    /**
     * Obtains a directory role ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return A customer ID.
     */
    protected String obtainDirectoryRoleId()
    {
        return this.obtainDirectoryRoleId( null );
    }

    protected String obtainDirectoryRoleId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultDirectoryRoleId" ),
                                 "Directory Role Id", StringHelper.isNullOrWhiteSpace( promptMessage )
                                                 ? "Enter the Directory Role ID" : promptMessage,
                                 "The Directory Role ID can't be empty" );
    }

    /***
     * Obtains an indirect reseller ID to work with from the configuration if set there or prompts the user to enter it.
     * @param promptMessage An optional custom prompt message.
     * @return An indirect reseller ID.
     */
    protected String obtainIndirectResellerId()
    {
        return this.obtainIndirectResellerId( null );
    }

    protected String obtainIndirectResellerId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultIndirectResellerId" ),
                "Indirect Reseller Id", StringHelper.isNullOrWhiteSpace( promptMessage )
                                ? "Enter the indirect reseller ID" : promptMessage,
                "The indirect reseller ID can't be empty" );
    }

    /**
     * Obtains an MPN ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return The MPN ID.
     */
    protected String obtainMpnId()
    {
        return this.obtainMpnId( null );
    }

    protected String obtainMpnId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "PartnerMpnId" ),
                                 "MPN Id",
                                 StringHelper.isNullOrWhiteSpace( promptMessage ) ? "Enter the MPN ID" : promptMessage,
                                 "The MPN ID can't be empty" );
    }

    /**
     * Obtains an offer ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return The offer ID.
     */
    protected String obtainOfferId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultOfferId" ),
                                 "Offer Id", StringHelper.isNullOrWhiteSpace( promptMessage ) ? "Enter the offer ID"
                                                 : promptMessage,
                                 "The Offer ID can't be empty" );
    }

    /**
     * Obtains an order ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param promptMessage An optional custom prompt message.
     * @return The offer ID.
     */
    protected String obtainOrderId( String promptMessage )
    {
        return this.obtainValue( this.getContext().getConfiguration().getScenarioSettings().get( "DefaultOrderId" ),
                                 "Order Id", StringHelper.isNullOrWhiteSpace( promptMessage ) ? "Enter the order ID"
                                                 : promptMessage,
                                 "The Order ID can't be empty" );
    }
    
    /**
     * Obtains an subscription ID to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param customerId The customer ID who owns the subscription.
     * @param promptMessage An optional custom prompt message.
     * @return The subscription ID.
     */
    protected String obtainSubscriptionId( String customerId, String promptMessage )
    {
        IAggregatePartner partnerOperations = this.getContext().getUserPartnerOperations();
        String subscriptionId =
            this.getContext().getConfiguration().getScenarioSettings().get( "DefaultSubscriptionId" );
        if ( StringHelper.isNullOrWhiteSpace( subscriptionId ) )
        {
            // get the customer subscriptions and let the user enter the subscription Id afterwards
            System.out.println( "Retrieving customer subscriptions... " );
            ResourceCollection<Subscription> subscriptions =
                partnerOperations.getCustomers().byId( customerId ).getSubscriptions().get();
            System.out.println( "Done!" );
            this.getContext().getConsoleHelper().writeObject( subscriptions, "Customer subscriptions" );
            System.out.println();
            subscriptionId =
                this.getContext().getConsoleHelper().readNonEmptyString( StringHelper.isNullOrWhiteSpace( promptMessage )
                                ? "Enter the subscription ID" : promptMessage, "Subscription ID can't be empty" );
        }
        else
        {
            System.out.println( MessageFormat.format( "Found subscription ID: {0} in configuration.",
                                                      subscriptionId ) );
        }
        return subscriptionId;
    }

    /**
     * Obtains an subscription ID to work with from the configuration for usage demo if set there or prompts the user to enter it.
     * 
     * @param customerId The customer ID who owns the subscription.
     * @param promptMessage An optional custom prompt message.
     * @return The subscription ID.
     */
    protected String obtainSubscriptionIdForUsage( String customerId, String promptMessage )
    {
        IAggregatePartner partnerOperations = this.getContext().getUserPartnerOperations();
        String subscriptionId =
            this.getContext().getConfiguration().getScenarioSettings().get( "DefaultSubscriptionIdForUsage" );
        if ( StringHelper.isNullOrWhiteSpace( subscriptionId ) )
        {
            // get the customer subscriptions and let the user enter the subscription Id afterwards
            System.out.println( "Retrieving customer subscriptions... " );
            ResourceCollection<Subscription> subscriptions =
                partnerOperations.getCustomers().byId( customerId ).getSubscriptions().get();
            System.out.println( "Done!" );
            this.getContext().getConsoleHelper().writeObject( subscriptions, "Customer subscriptions" );
            System.out.println();
            subscriptionId =
                this.getContext().getConsoleHelper().readNonEmptyString( StringHelper.isNullOrWhiteSpace( promptMessage )
                                ? "Enter the subscription ID" : promptMessage, "Subscription ID can't be empty" );
        }
        else
        {
            System.out.println( MessageFormat.format( "Found subscription ID: {0} in configuration.",
                                                      subscriptionId ) );
        }
        return subscriptionId;
    }

    /**
     * Runs the scenario logic. This is delegated to the implementing sub class.
     */
    protected abstract void runScenario();

    /**
     * Obtains a value to work with from the configuration if set there or prompts the user to enter it.
     * 
     * @param configuredValue The value read from the configuration.
     * @param title The title of the value.
     * @param promptMessage The prompt message to use if the value was not set in the configuration.
     * @param errorMessage The error message to use if the user did not enter a value.
     * @return A string value.
     */
    private String obtainValue( String configuredValue, String title, String promptMessage, String errorMessage )
    {
        String value = configuredValue;
        if ( StringHelper.isNullOrWhiteSpace( value ) )
        {
            // The value was not set in the configuration, prompt the user the enter value
            value = this.getContext().getConsoleHelper().readNonEmptyString( promptMessage, errorMessage );
        }
        else
        {
            System.out.println( MessageFormat.format( "Found {0}: {1} in configuration.", title, value ) );
        }
        return value;
    }

}
