CREATE TABLE product_gallery_tags (
   primg_id BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   prgal_language_id BIGINT(10) UNSIGNED NOT  NULL,
   prgal_image_id BIGINT(10) UNSIGNED NOT  NULL,
   prgal_descr VARCHAR(126) NOT  NULL,
  PRIMARY KEY(primg_id)
);

