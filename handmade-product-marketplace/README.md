# About
This is a test project that will attempt to create a back end for a business use case defined 
in the next section. This is meant to use DDD practices so each submodule will have it's own
bounded context in it's package.

## Use case

```
Business Scenario: Handmade Marketplace
Domain: Handmade Product Marketplace
You're tasked with designing the backend system for a marketplace that connects local artisans with customers. Artisans can list their products for sale, and customers can browse, purchase, and review items. The system needs to handle various key aspects like product management, ordering, payments, and user accounts.

Key Use Cases:
Artisan Registration and Management

Artisans register with the platform, providing information about their craft and shop.
- They can create and manage their product listings (e.g., titles, descriptions, prices, stock).
- Artisans can track their orders and sales history.
- Customer Registration and Shopping

Customers can register on the platform, manage profiles, and browse products by category or search.
- Customers can add products to a shopping cart and complete a purchase.
- After a purchase, customers can leave reviews for the artisan.
- Order and Payment Processing

When a customer places an order, the system needs to handle the payment through a third-party service.
- Orders are routed to the correct artisan, who receives a notification.
- Artisans need to mark orders as shipped and provide tracking information.
- Rating and Review System

Customers can leave ratings and reviews for both products and artisans.
Artisans can respond to reviews.
Reviews impact product rankings on the platform.
Product Recommendations

```