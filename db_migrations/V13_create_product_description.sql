CREATE TABLE products_description (
   prdsc_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   prdsc_product_id BIGINT(10) UNSIGNED NOT  NULL,
   prdsc_language_id BIGINT(10) UNSIGNED NOT  NULL,
   prdsc_title VARCHAR(126) NOT  NULL,
   prdsct_description TEXT NOT  NULL,
  PRIMARY KEY(prdsc_id)
);

