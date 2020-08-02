CREATE TABLE product_tags (
   prtg_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   prtg_product_id BIGINT(10) UNSIGNED NOT  NULL,
   prtg_tag VARCHAR(55) NOT  NULL,
  PRIMARY KEY(prtg_id)
);

