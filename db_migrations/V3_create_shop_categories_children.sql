CREATE TABLE shop_belongs_categories (
  sht_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  sht_shop_id BIGINT(10) UNSIGNED NOT NULL,
  sht_category_id BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY(sht_id)
);

