CREATE TABLE products_attributes (
   prat_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   prat_product_id BIGINT(10) UNSIGNED NOT  NULL,
   prat_language_id BIGINT(10) UNSIGNED NOT  NULL,
   prat_attribute VARCHAR(85) NOT  NULL,
   prat_value VARCHAR(85) NOT  NULL,
  PRIMARY KEY(prat_id)
);

