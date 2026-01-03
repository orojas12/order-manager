# Order Manager

Order Manager is a simple and easy-to-use order management system designed for small businesses. These are the currently planned features:

## Core Requirements

### Order management

- Create orders manually
- View order status (paid, shipped, completed, cancelled)
- View detailed order information
  - Order id
  - Customer details
  - Products, quantities, prices
  - Shipping cost/estimate
- Edit orders before fulfillment(e.g., address correction, quantity change)
- Cancel or refund orders (full or partial)
- Order history (status changes, edits, timestamps)

### Customer management

- Customer profiles
  - Name
  - Email (optional)
  - Phone (optional)
  - Shipping address
- View customer order history
- Search customers by name, email, or phone

### Inventory management

- Product catalog
  - SKU
  - Name
  - Description
  - Price
  - Stock quantity
- Automatically adjust inventory levels on order placement
- Low-stock alerts
- Support basic product variants (size, color, etc)

### Payment tracking

Note: direct payment handling will not be supported at this time

- Record payment method (e.g., PayPal, CashApp, other third-party payment platforms)
- Record payment status (Pending, Paid, Refunded, Partially Refunded)

### Shipping & fulfillment

- Record shipping method and cost
- Store tracking number and carrier
- Automatically change order status when shipped

## Admin & Usability Requirements

### Admin interface

- Secure login
- Dashboard
  - Recent orders
  - Unpaid orders
  - Unshipped orders
- Search and filter orders by:
  - Date
  - Status
  - Customer
- Pagination for large order lists
