-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 11, 2020 at 05:29 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `commercify_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing_address`
--

CREATE TABLE `billing_address` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `city` varchar(80) NOT NULL,
  `region` varchar(80) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `address` varchar(80) NOT NULL,
  `street_no` varchar(20) NOT NULL,
  `post_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `continents`
--

CREATE TABLE `continents` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(80) NOT NULL,
  `code` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(80) NOT NULL,
  `code` varchar(2) NOT NULL,
  `continent` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `courriers`
--

CREATE TABLE `courriers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `currencies`
--

CREATE TABLE `currencies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `code` varchar(3) NOT NULL,
  `rate` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `currencies`
--

INSERT INTO `currencies` (`id`, `title`, `code`, `rate`) VALUES
(1, 'Euro', 'EUR', '1.00'),
(2, 'Swiss Franch', 'CHF', '1.13');

-- --------------------------------------------------------

--
-- Table structure for table `globe_regions`
--

CREATE TABLE `globe_regions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `title` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `languages`
--

CREATE TABLE `languages` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `code` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `languages`
--

INSERT INTO `languages` (`id`, `title`, `code`) VALUES
(1, 'English', 'eng'),
(2, 'German', 'de'),
(3, 'Sweedish', 'sve');

-- --------------------------------------------------------

--
-- Table structure for table `payment_methods`
--

CREATE TABLE `payment_methods` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `phinxlog`
--

CREATE TABLE `phinxlog` (
  `version` bigint(20) NOT NULL,
  `migration_name` varchar(100) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `breakpoint` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `phinxlog`
--

INSERT INTO `phinxlog` (`version`, `migration_name`, `start_time`, `end_time`, `breakpoint`) VALUES
(20200802155909, 'NewTableUsers', '2020-08-02 13:12:39', '2020-08-02 13:12:39', 0),
(20200802161342, 'NewTableUserRoles', '2020-08-02 13:14:53', '2020-08-02 13:14:53', 0),
(20200802161613, 'NewTableCurrencies', '2020-08-02 13:18:07', '2020-08-02 13:18:07', 0),
(20200802161825, 'NewTableLanguages', '2020-08-02 13:19:12', '2020-08-02 13:19:12', 0),
(20200802162029, 'NewTableShopCategories', '2020-08-02 13:21:07', '2020-08-02 13:21:07', 0),
(20200802162114, 'NewTableProductCategories', '2020-08-02 13:22:10', '2020-08-02 13:22:10', 0),
(20200802162241, 'NewTableShops', '2020-08-02 13:26:37', '2020-08-02 13:26:37', 0),
(20200802162756, 'NewTableShopCurrencies', '2020-08-02 13:29:41', '2020-08-02 13:29:41', 0),
(20200802163002, 'NewTableShopLanguages', '2020-08-02 13:31:50', '2020-08-02 13:31:50', 0),
(20200802163006, 'NewTableShopManagers', '2020-08-02 13:31:50', '2020-08-02 13:31:50', 0),
(20200802163214, 'NewTableShopCategoriesBelongs', '2020-08-02 13:33:12', '2020-08-02 13:33:12', 0),
(20200802163409, 'NewTableShopReviews', '2020-08-02 13:36:09', '2020-08-02 13:36:09', 0),
(20200802163632, 'NewTableProducts', '2020-08-02 13:42:47', '2020-08-02 13:42:47', 0),
(20200802164440, 'NewTableProductAttributes', '2020-08-02 13:49:05', '2020-08-02 13:49:05', 0),
(20200802164943, 'NewTableProductTags', '2020-08-02 13:50:30', '2020-08-02 13:50:30', 0),
(20200802165044, 'NewTableProductGallery', '2020-08-02 13:51:40', '2020-08-02 13:51:41', 0),
(20200802165204, 'NewTableProductDescription', '2020-08-02 13:53:16', '2020-08-02 13:53:16', 0),
(20200802165341, 'NewTableProductGalleryTags', '2020-08-02 13:55:03', '2020-08-02 13:55:03', 0),
(20200802180930, 'NewTableProductReviews', '2020-08-02 15:10:47', '2020-08-02 15:10:48', 0),
(20200808095219, 'NewTableCourriers', '2020-08-08 06:53:31', '2020-08-08 06:53:31', 0),
(20200808095422, 'NewTableCountries', '2020-08-08 06:57:15', '2020-08-08 06:57:15', 0),
(20200808095634, 'NewTableContinents', '2020-08-08 06:57:15', '2020-08-08 06:57:15', 0),
(20200808095817, 'NewTableShopCouriers', '2020-08-08 06:59:33', '2020-08-08 06:59:33', 0),
(20200808100552, 'NewTableShopCountries', '2020-08-08 07:06:49', '2020-08-08 07:06:49', 0),
(20200808100711, 'NewTableShopDisableZipCodes', '2020-08-08 07:07:57', '2020-08-08 07:07:57', 0),
(20200808101015, 'AddTaxableImagesToProducts', '2020-08-08 07:12:13', '2020-08-08 07:12:13', 0),
(20200808101416, 'CreatePaymentMethods', '2020-08-08 07:15:22', '2020-08-08 07:15:22', 0),
(20200808101540, 'CreateDisableCashOnDeliveryContinents', '2020-08-08 11:55:52', '2020-08-08 11:55:52', 0),
(20200808145622, 'NewTableDisableCodCountries', '2020-08-08 11:57:56', '2020-08-08 11:57:56', 0),
(20200808145648, 'NewTableDisableCodZipCodes', '2020-08-08 11:57:56', '2020-08-08 11:57:56', 0),
(20200808150156, 'AlterCoDProducts', '2020-08-08 12:02:48', '2020-08-08 12:02:48', 0),
(20200808150317, 'AlterCoDShopCategories', '2020-08-08 12:06:36', '2020-08-08 12:06:36', 0),
(20200808150812, 'AlterDisableCoDGreaterThanX', '2020-08-08 12:09:29', '2020-08-08 12:09:29', 0),
(20200810045015, 'TableShippingAddress', '2020-08-10 01:53:17', '2020-08-10 01:53:17', 0),
(20200810045327, 'TableBillingAddress', '2020-08-10 01:53:53', '2020-08-10 01:53:53', 0),
(20200810045522, 'AddThumbnailAndOrderToCategories', '2020-08-10 01:57:28', '2020-08-10 01:57:28', 0),
(20200810050332, 'NewTableBanks', '2020-08-10 02:06:10', '2020-08-10 02:06:10', 0),
(20200811051511, 'TableShippingZones', '2020-08-11 02:18:52', '2020-08-11 02:18:52', 0),
(20200811051914, 'TableShippingZonesZipCodes', '2020-08-11 02:20:36', '2020-08-11 02:20:36', 0),
(20200811052705, 'TableGlobeRegions', '2020-08-11 02:27:58', '2020-08-11 02:27:58', 0),
(20200811052807, 'TableShippingZonesRegions', '2020-08-11 02:29:31', '2020-08-11 02:29:31', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `currency_id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(120) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `thumbnail_url` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `discount_percent` decimal(5,2) NOT NULL,
  `kilos` decimal(5,2) NOT NULL,
  `dim_l` decimal(6,2) NOT NULL,
  `dim_w` decimal(6,2) NOT NULL,
  `dim_h` decimal(6,2) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `stock` bigint(20) UNSIGNED NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `taxable` tinyint(1) NOT NULL,
  `disable_cod` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `shop_id`, `category_id`, `currency_id`, `code`, `img_url`, `thumbnail_url`, `price`, `discount_percent`, `kilos`, `dim_l`, `dim_w`, `dim_h`, `active`, `stock`, `created`, `updated`, `taxable`, `disable_cod`) VALUES
(1, 1, 1, 1, 'SelmaLagerlefNielsHolgresson', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0),
(2, 1, 1, 1, 'StringbergDromspel', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product_attributes`
--

CREATE TABLE `product_attributes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `value` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_attributes`
--

INSERT INTO `product_attributes` (`id`, `product_id`, `language_id`, `title`, `value`) VALUES
(1, 1, 1, 'Pages', '658'),
(2, 1, 2, 'Seiten', '658'),
(3, 2, 1, 'Pages', '256'),
(4, 2, 3, 'Seite', '234'),
(5, 1, 1, 'Cover', 'Paperback');

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_categories`
--

INSERT INTO `product_categories` (`id`, `title`) VALUES
(1, 'Books');

-- --------------------------------------------------------

--
-- Table structure for table `product_description`
--

CREATE TABLE `product_description` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) NOT NULL,
  `descr` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_gallery`
--

CREATE TABLE `product_gallery` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `file_path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_gallery_tag`
--

CREATE TABLE `product_gallery_tag` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `image_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL,
  `tag` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_reviews`
--

CREATE TABLE `product_reviews` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_ud` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `stars` decimal(2,1) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_tags`
--

CREATE TABLE `product_tags` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL,
  `tag` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shipping_address`
--

CREATE TABLE `shipping_address` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `city` varchar(80) NOT NULL,
  `region` varchar(80) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `address` varchar(80) NOT NULL,
  `street_no` varchar(20) NOT NULL,
  `post_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shipping_zones_regions`
--

CREATE TABLE `shipping_zones_regions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `zone_id` bigint(20) UNSIGNED NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shipping_zones_zip_codes`
--

CREATE TABLE `shipping_zones_zip_codes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `zone_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `zip_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shops`
--

CREATE TABLE `shops` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `owner_id` bigint(20) UNSIGNED NOT NULL,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shops`
--

INSERT INTO `shops` (`id`, `title`, `owner_id`, `created`) VALUES
(1, 'vivliofagos', 1, '2020-08-03 08:22:05');

-- --------------------------------------------------------

--
-- Table structure for table `shop_banks`
--

CREATE TABLE `shop_banks` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `bank` varchar(52) NOT NULL,
  `account_no` varchar(52) NOT NULL,
  `iban` varchar(52) NOT NULL,
  `swift_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_belongs_categories`
--

CREATE TABLE `shop_belongs_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `disable_cod` tinyint(1) NOT NULL,
  `thumbnail_url` varchar(255) NOT NULL,
  `show_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_belongs_categories`
--

INSERT INTO `shop_belongs_categories` (`id`, `shop_id`, `category_id`, `disable_cod`, `thumbnail_url`, `show_order`) VALUES
(1, 1, 1, 0, '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `shop_categories`
--

CREATE TABLE `shop_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_categories`
--

INSERT INTO `shop_categories` (`id`, `title`) VALUES
(1, 'Books'),
(2, 'Clothing');

-- --------------------------------------------------------

--
-- Table structure for table `shop_countries`
--

CREATE TABLE `shop_countries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_couriers`
--

CREATE TABLE `shop_couriers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `courier_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_currencies`
--

CREATE TABLE `shop_currencies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `currency_id` bigint(20) UNSIGNED NOT NULL,
  `disable_cod` tinyint(1) NOT NULL,
  `disable_cod_greater_than` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_currencies`
--

INSERT INTO `shop_currencies` (`id`, `shop_id`, `currency_id`, `disable_cod`, `disable_cod_greater_than`) VALUES
(1, 1, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `shop_disable_cod_continents`
--

CREATE TABLE `shop_disable_cod_continents` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `continent_code` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_disable_cod_countries`
--

CREATE TABLE `shop_disable_cod_countries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_disable_cod_zipcode`
--

CREATE TABLE `shop_disable_cod_zipcode` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `zip_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_disable_zip_codes`
--

CREATE TABLE `shop_disable_zip_codes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `zip` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_languages`
--

CREATE TABLE `shop_languages` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_languages`
--

INSERT INTO `shop_languages` (`id`, `shop_id`, `language_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `shop_managers`
--

CREATE TABLE `shop_managers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `manager_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_managers`
--

INSERT INTO `shop_managers` (`id`, `shop_id`, `manager_id`) VALUES
(1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `shop_reviews`
--

CREATE TABLE `shop_reviews` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `stars` decimal(2,1) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_ship_zones`
--

CREATE TABLE `shop_ship_zones` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `ship_cost` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `password_salt` varchar(40) NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `password_salt`, `email`, `first_name`, `last_name`, `created`, `updated`) VALUES
(1, 'test', '', '', '', '', '', '2020-08-03 08:16:17', '2020-08-03 08:16:17'),
(2, 'foo', '', '', 'foo@bar', '', '', '2020-08-03 08:22:37', '2020-08-03 08:22:37');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing_address`
--
ALTER TABLE `billing_address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `continents`
--
ALTER TABLE `continents`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `courriers`
--
ALTER TABLE `courriers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `currencies`
--
ALTER TABLE `currencies`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `globe_regions`
--
ALTER TABLE `globe_regions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `languages`
--
ALTER TABLE `languages`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `payment_methods`
--
ALTER TABLE `payment_methods`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `phinxlog`
--
ALTER TABLE `phinxlog`
  ADD PRIMARY KEY (`version`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `shop_id` (`shop_id`,`code`),
  ADD KEY `category_id` (`category_id`),
  ADD KEY `currency_id` (`currency_id`);

--
-- Indexes for table `product_attributes`
--
ALTER TABLE `product_attributes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `product_description`
--
ALTER TABLE `product_description`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `product_gallery`
--
ALTER TABLE `product_gallery`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_gallery_tag`
--
ALTER TABLE `product_gallery_tag`
  ADD PRIMARY KEY (`id`),
  ADD KEY `image_id` (`image_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `product_reviews`
--
ALTER TABLE `product_reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_ud` (`product_ud`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `product_tags`
--
ALTER TABLE `product_tags`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `shipping_address`
--
ALTER TABLE `shipping_address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `shipping_zones_regions`
--
ALTER TABLE `shipping_zones_regions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `region_id` (`region_id`),
  ADD KEY `zone_id` (`zone_id`);

--
-- Indexes for table `shipping_zones_zip_codes`
--
ALTER TABLE `shipping_zones_zip_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zone_id` (`zone_id`);

--
-- Indexes for table `shops`
--
ALTER TABLE `shops`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner_id` (`owner_id`);

--
-- Indexes for table `shop_banks`
--
ALTER TABLE `shop_banks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_belongs_categories`
--
ALTER TABLE `shop_belongs_categories`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `shop_categories`
--
ALTER TABLE `shop_categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `shop_countries`
--
ALTER TABLE `shop_countries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_couriers`
--
ALTER TABLE `shop_couriers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `courier_id` (`courier_id`);

--
-- Indexes for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `currency_id` (`currency_id`);

--
-- Indexes for table `shop_disable_cod_continents`
--
ALTER TABLE `shop_disable_cod_continents`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_disable_cod_countries`
--
ALTER TABLE `shop_disable_cod_countries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_disable_cod_zipcode`
--
ALTER TABLE `shop_disable_cod_zipcode`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_disable_zip_codes`
--
ALTER TABLE `shop_disable_zip_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_languages`
--
ALTER TABLE `shop_languages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `shop_managers`
--
ALTER TABLE `shop_managers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `manager_id` (`manager_id`);

--
-- Indexes for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `shop_ship_zones`
--
ALTER TABLE `shop_ship_zones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing_address`
--
ALTER TABLE `billing_address`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `continents`
--
ALTER TABLE `continents`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `courriers`
--
ALTER TABLE `courriers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `currencies`
--
ALTER TABLE `currencies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `globe_regions`
--
ALTER TABLE `globe_regions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `languages`
--
ALTER TABLE `languages`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `payment_methods`
--
ALTER TABLE `payment_methods`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product_attributes`
--
ALTER TABLE `product_attributes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product_categories`
--
ALTER TABLE `product_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product_description`
--
ALTER TABLE `product_description`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_gallery`
--
ALTER TABLE `product_gallery`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_gallery_tag`
--
ALTER TABLE `product_gallery_tag`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_reviews`
--
ALTER TABLE `product_reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_tags`
--
ALTER TABLE `product_tags`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shipping_address`
--
ALTER TABLE `shipping_address`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shipping_zones_regions`
--
ALTER TABLE `shipping_zones_regions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shipping_zones_zip_codes`
--
ALTER TABLE `shipping_zones_zip_codes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shops`
--
ALTER TABLE `shops`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_banks`
--
ALTER TABLE `shop_banks`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_belongs_categories`
--
ALTER TABLE `shop_belongs_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_categories`
--
ALTER TABLE `shop_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shop_countries`
--
ALTER TABLE `shop_countries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_couriers`
--
ALTER TABLE `shop_couriers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_disable_cod_continents`
--
ALTER TABLE `shop_disable_cod_continents`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_disable_cod_countries`
--
ALTER TABLE `shop_disable_cod_countries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_disable_cod_zipcode`
--
ALTER TABLE `shop_disable_cod_zipcode`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_disable_zip_codes`
--
ALTER TABLE `shop_disable_zip_codes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_languages`
--
ALTER TABLE `shop_languages`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_managers`
--
ALTER TABLE `shop_managers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_ship_zones`
--
ALTER TABLE `shop_ship_zones`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billing_address`
--
ALTER TABLE `billing_address`
  ADD CONSTRAINT `billing_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`),
  ADD CONSTRAINT `products_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`);

--
-- Constraints for table `product_attributes`
--
ALTER TABLE `product_attributes`
  ADD CONSTRAINT `product_attributes_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_attributes_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `product_description`
--
ALTER TABLE `product_description`
  ADD CONSTRAINT `product_description_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_description_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `product_gallery`
--
ALTER TABLE `product_gallery`
  ADD CONSTRAINT `product_gallery_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `product_gallery_tag`
--
ALTER TABLE `product_gallery_tag`
  ADD CONSTRAINT `product_gallery_tag_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `product_gallery` (`id`),
  ADD CONSTRAINT `product_gallery_tag_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `product_reviews`
--
ALTER TABLE `product_reviews`
  ADD CONSTRAINT `product_reviews_ibfk_1` FOREIGN KEY (`product_ud`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `product_tags`
--
ALTER TABLE `product_tags`
  ADD CONSTRAINT `product_tags_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_tags_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `shipping_address`
--
ALTER TABLE `shipping_address`
  ADD CONSTRAINT `shipping_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `shipping_zones_regions`
--
ALTER TABLE `shipping_zones_regions`
  ADD CONSTRAINT `shipping_zones_regions_ibfk_1` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`),
  ADD CONSTRAINT `shipping_zones_regions_ibfk_2` FOREIGN KEY (`zone_id`) REFERENCES `shop_ship_zones` (`id`);

--
-- Constraints for table `shipping_zones_zip_codes`
--
ALTER TABLE `shipping_zones_zip_codes`
  ADD CONSTRAINT `shipping_zones_zip_codes_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `shop_ship_zones` (`id`);

--
-- Constraints for table `shops`
--
ALTER TABLE `shops`
  ADD CONSTRAINT `shops_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `shop_banks`
--
ALTER TABLE `shop_banks`
  ADD CONSTRAINT `shop_banks_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_belongs_categories`
--
ALTER TABLE `shop_belongs_categories`
  ADD CONSTRAINT `shop_belongs_categories_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_belongs_categories_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `shop_categories` (`id`);

--
-- Constraints for table `shop_countries`
--
ALTER TABLE `shop_countries`
  ADD CONSTRAINT `shop_countries_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_couriers`
--
ALTER TABLE `shop_couriers`
  ADD CONSTRAINT `shop_couriers_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_couriers_ibfk_2` FOREIGN KEY (`courier_id`) REFERENCES `courriers` (`id`);

--
-- Constraints for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  ADD CONSTRAINT `shop_currencies_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_currencies_ibfk_2` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`);

--
-- Constraints for table `shop_disable_cod_continents`
--
ALTER TABLE `shop_disable_cod_continents`
  ADD CONSTRAINT `shop_disable_cod_continents_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_disable_cod_countries`
--
ALTER TABLE `shop_disable_cod_countries`
  ADD CONSTRAINT `shop_disable_cod_countries_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_disable_cod_zipcode`
--
ALTER TABLE `shop_disable_cod_zipcode`
  ADD CONSTRAINT `shop_disable_cod_zipcode_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_disable_zip_codes`
--
ALTER TABLE `shop_disable_zip_codes`
  ADD CONSTRAINT `shop_disable_zip_codes_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_languages`
--
ALTER TABLE `shop_languages`
  ADD CONSTRAINT `shop_languages_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_languages_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `shop_managers`
--
ALTER TABLE `shop_managers`
  ADD CONSTRAINT `shop_managers_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_managers_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  ADD CONSTRAINT `shop_reviews_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `shop_ship_zones`
--
ALTER TABLE `shop_ship_zones`
  ADD CONSTRAINT `shop_ship_zones_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
