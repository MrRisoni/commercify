CREATE TABLE products_gallery (
   prgal_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   prgal_product_id BIGINT(10) UNSIGNED NOT  NULL,
   prgal_url VARCHAR(126) NOT  NULL,
   prgal_show_order TINYINT(5) UNSIGNED NOT  NULL,
  PRIMARY KEY(prgal_id)
);

