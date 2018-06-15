# Partner Center Java SDK Changelog

To get started or get more information about Partner Center SDK and API, go to <http://partnercenter.microsoft.com/partner/developer>.

## 1.8.0 (2018-07-01)

SDK v1.8 supports Partner Center one time purchase transaction, including Azure Reserved Virtual Machine Instances (RI) along with 2 additional updates for "New Entitlement property for Subscription class", and "Expose new legacy Offer properties".

The following enhancements were made with this release

- "AdditionalInfo" property was added to AzureInstanceData model in the Azure utilization API
- [Check Inventory](https://docs.microsoft.com/partner-center/develop/check-inventory)
- [Delete a customer account from the integration sandbox](https://docs.microsoft.com/partner-center/develop/delete-a-customer-account-from-the-integration-sandbox)
- [Checkout a cart](https://docs.microsoft.com/partner-center/develop/checkout-a-cart)
- [Create a cart](https://docs.microsoft.com/partner-center/develop/create-a-cart)
- [Get a collection of entitlements](https://docs.microsoft.com/partner-center/develop/get-a-collection-of-entitlements)
- [Get a list of availabilities for a SKU](https://docs.microsoft.com/partner-center/develop/get-a-list-of-availabilities-for-a-sku)
- [Get a list of products](https://docs.microsoft.com/partner-center/develop/get-a-list-of-products)
- [Get a list of SKUs for a product](https://docs.microsoft.com/partner-center/develop/get-a-list-of-skus-for-a-product)
- [Get a product by ID](https://docs.microsoft.com/partner-center/develop/get-a-product-by-id)
- [Get a SKU by ID](https://docs.microsoft.com/partner-center/develop/get-a-sku-by-id)
- [Get a subscription registration status](https://docs.microsoft.com/partner-center/develop/get-subscription-registration-status)
- [Get an availability by ID](https://docs.microsoft.com/partner-center/develop/get-an-availability-by-id)
- [Get Invoice statement](https://docs.microsoft.com/partner-center/develop/get-invoice-statement)
- [Get invoice summaries](https://docs.microsoft.com/partner-center/develop/get-invoice-summaries)
- [Get prices for Microsoft Azure Partner Shared Services](https://docs.microsoft.com/partner-center/develop/get-prices-for-microsoft-azure-partner-shared-services)
- [Get the reseller’s current account balance (Get Invoice summary)](https://docs.microsoft.com/partner-center/develop/get-the-reseller-s-current-account-balance)
- New SDK was added to [remove reseller relationship with customer](https://docs.microsoft.com/partner-center/develop/remove-a-reseller-relationship-with-a-customer).
- [Purchase Partner Center Azure reserved VM instances](https://docs.microsoft.com/partner-center/develop/purchase-azure-reserved-vm-instances)
- [Register a subscription](https://docs.microsoft.com/partner-center/develop/register-a-subscription)
- [Update a cart](https://docs.microsoft.com/partner-center/develop/update-a-cart)
- Windows AutoPilot Deployment SDK - Windows AutoPilot deployment enables partners to offer "managed desktop as a service" to their customers. The Partner Center portal experience was release prior to Inspire conference in July 2017, followed by REST API in August 2017.

The following changes, and bug fixes, where made with this release

- Activity log - Added missing the audit operation type "remove customer relationship operation"
- Activity log - Audit records contract changes "record Id, Partner Id and correlation Id"
- Fixed the bug of the missing currency symbol from "invoices/summary"
- Fixed the bug preventing service incidents from being requested.
- Fixed the bug prevent service request from being opened.
- Fixed the bug where offers and offer categories could not be retrieved.
- Fixed the bug where the enumerators were returning 404 when requesting another page of records.
- Fixed the bug with processing the invoice line items.
- The FileLinks property was removed from the Service Request API since uploading file through the API is not supported at the moment.
- Updated Rate card API to support Azure Partner Share Services