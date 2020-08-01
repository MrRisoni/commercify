CREATE TABLE shop_categories (
  cat_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  cat_title VARCHAR(65) NOT  NULL,
  PRIMARY KEY(cat_id)
);

CREATE TABLE product_categories (
  prc_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  prc_title VARCHAR(65) NOT  NULL,
  PRIMARY KEY(prc_id)
);

