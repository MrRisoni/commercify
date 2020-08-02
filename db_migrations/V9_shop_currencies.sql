CREATE TABLE shop_currencies (
  shcr_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  shcr_shop_id BIGINT(10) UNSIGNED NOT NULL,
  shcr_currency_id BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY(shcr_id)
);

