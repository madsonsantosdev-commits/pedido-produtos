CREATE TABLE products (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  name NVARCHAR(120) NOT NULL,
  price DECIMAL(18,2) NOT NULL,
  active BIT NOT NULL DEFAULT 1,
  created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

CREATE TABLE orders (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  customer_name NVARCHAR(120) NOT NULL,
  status NVARCHAR(30) NOT NULL,
  total DECIMAL(18,2) NOT NULL,
  created_at DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME()
);

CREATE TABLE order_items (
  id BIGINT IDENTITY(1,1) PRIMARY KEY,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL,
  unit_price DECIMAL(18,2) NOT NULL,
  line_total DECIMAL(18,2) NOT NULL,

  CONSTRAINT fk_order_items_orders
    FOREIGN KEY (order_id) REFERENCES orders(id),

  CONSTRAINT fk_order_items_products
    FOREIGN KEY (product_id) REFERENCES products(id)
);
