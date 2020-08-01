CREATE TABLE reviews (
  rew_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  rew_user_id BIGINT(10) UNSIGNED NOT NULL,
  rew_shops_id BIGINT(10) UNSIGNED NOT NULL,
  rew_created_at DATETIME NOT NULL,
  rew_stars DECIMAL(2,1) UNSIGNED DEFAULT 0,
  rew_comments TEXT,
  PRIMARY KEY(rew_id)
);

