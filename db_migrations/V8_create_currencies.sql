CREATE TABLE currencies (
  cur_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  cur_title VARCHAR(55) NOT  NULL,
  cur_symbol VARCHAR(5) NOT  NULL,
  cur_rate DECIMAL(6,2) UNSIGNED DEFAULT 0,
  PRIMARY KEY(cur_id)
);
