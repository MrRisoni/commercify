CREATE TABLE shop_languages (
   shl_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   shl_shop_id BIGINT(10) UNSIGNED NOT  NULL,
   shl_language_id BIGINT(10) UNSIGNED NOT  NULL,
  PRIMARY KEY(shl_id)
);

