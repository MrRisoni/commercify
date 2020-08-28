-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2020 at 10:24 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `bank_transactions`
--

CREATE TABLE `bank_transactions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `order_id` bigint(20) UNSIGNED NOT NULL,
  `card_type` text NOT NULL,
  `card_bin` varchar(8) NOT NULL,
  `card_last_four` varchar(4) NOT NULL,
  `card_bank` text NOT NULL,
  `card_holder` varchar(50) NOT NULL,
  `transaction_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `billing_address`
--

CREATE TABLE `billing_address` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL DEFAULT 2,
  `city` varchar(80) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `address` varchar(80) NOT NULL,
  `street_no` varchar(20) NOT NULL,
  `post_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `billing_address`
--

INSERT INTO `billing_address` (`id`, `user_id`, `country_code`, `region_id`, `city`, `full_name`, `address`, `street_no`, `post_code`) VALUES
(1, 3, 'GR', 2, 'Athens', '', 'Syntagma', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `continents`
--

CREATE TABLE `continents` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(80) NOT NULL,
  `code` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `continents`
--

INSERT INTO `continents` (`id`, `title`, `code`) VALUES
(1, 'Europe', 'EU');

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

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `title`, `code`, `continent`) VALUES
(1, 'Greece', 'GR', 'EU'),
(2, 'Germany', 'DE', 'EU');

-- --------------------------------------------------------

--
-- Table structure for table `courriers`
--

CREATE TABLE `courriers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `courriers`
--

INSERT INTO `courriers` (`id`, `title`) VALUES
(1, 'ACS'),
(2, 'Speedex'),
(3, 'UPS'),
(4, 'DHL'),
(5, 'ΕΛΤΑ Courrier');

-- --------------------------------------------------------

--
-- Table structure for table `currencies`
--

CREATE TABLE `currencies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `code` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `currencies`
--

INSERT INTO `currencies` (`id`, `title`, `code`) VALUES
(1, 'Euro', 'EUR'),
(2, 'Swiss Franch', 'CHF');

-- --------------------------------------------------------

--
-- Table structure for table `currency_rates`
--

CREATE TABLE `currency_rates` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `from_cur` varchar(3) NOT NULL,
  `to_cur` varchar(3) NOT NULL,
  `rate` decimal(10,2) UNSIGNED NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `globe_regions`
--

CREATE TABLE `globe_regions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `title` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `globe_regions`
--

INSERT INTO `globe_regions` (`id`, `country_code`, `title`) VALUES
(1, '*', '*'),
(3, 'GR', 'Δωδεκάνησα'),
(4, 'GR', 'Νησία Ιονίου'),
(2, 'GR', 'Στερέα Ελλάδα');

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
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `shipping_address_id` bigint(20) UNSIGNED NOT NULL,
  `billing_address_id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `status_id` bigint(20) UNSIGNED NOT NULL,
  `pay_method_id` bigint(20) UNSIGNED NOT NULL,
  `ship_class_id` bigint(20) UNSIGNED NOT NULL,
  `currency` varchar(3) NOT NULL,
  `currency_rate` decimal(10,2) UNSIGNED NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `net` decimal(10,2) NOT NULL,
  `commission` decimal(10,2) UNSIGNED NOT NULL,
  `tax` decimal(10,2) NOT NULL,
  `courrier_fees` decimal(10,2) UNSIGNED NOT NULL DEFAULT 0.00,
  `shipping` decimal(10,2) NOT NULL,
  `success` tinyint(1) NOT NULL,
  `void` tinyint(1) NOT NULL,
  `refund` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `shipping_address_id`, `billing_address_id`, `shop_id`, `status_id`, `pay_method_id`, `ship_class_id`, `currency`, `currency_rate`, `total`, `net`, `commission`, `tax`, `courrier_fees`, `shipping`, `success`, `void`, `refund`, `created_at`, `updated_at`) VALUES
(1, 3, 1, 1, 2, 3, 4, 1, 'EUR', '1.00', '3560.00', '3520.00', '0.00', '50.00', '0.00', '30.00', 1, 0, 0, '2020-07-12 08:55:50', '2020-07-12 08:55:50'),
(2, 3, 1, 1, 2, 3, 3, 1, 'CHF', '1.89', '2560.00', '2520.00', '0.00', '10.00', '0.00', '25.00', 1, 0, 0, '2020-08-12 18:35:10', '2020-08-12 18:15:10');

-- --------------------------------------------------------

--
-- Table structure for table `orders_status_history`
--

CREATE TABLE `orders_status_history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `order_id` bigint(20) UNSIGNED NOT NULL,
  `status_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders_status_history`
--

INSERT INTO `orders_status_history` (`id`, `order_id`, `status_id`, `created_at`) VALUES
(1, 2, 1, '2020-08-12 08:55:50');

-- --------------------------------------------------------

--
-- Table structure for table `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `order_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `status_id` bigint(20) UNSIGNED NOT NULL,
  `quantity` int(11) UNSIGNED NOT NULL,
  `tracking_no` varchar(52) NOT NULL,
  `dispatched_on` datetime DEFAULT NULL,
  `expected_on` date DEFAULT NULL,
  `arrived_on` date DEFAULT NULL,
  `net_price` decimal(10,2) NOT NULL,
  `taxes` decimal(10,2) NOT NULL,
  `gift_cost` decimal(10,2) NOT NULL,
  `success` tinyint(1) NOT NULL,
  `void` tinyint(1) NOT NULL,
  `refund` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_items`
--

INSERT INTO `order_items` (`id`, `order_id`, `product_id`, `status_id`, `quantity`, `tracking_no`, `dispatched_on`, `expected_on`, `arrived_on`, `net_price`, `taxes`, `gift_cost`, `success`, `void`, `refund`) VALUES
(1, 1, 9, 3, 2, '', NULL, NULL, NULL, '3560.00', '12.00', '0.00', 1, 0, 0),
(2, 2, 8, 3, 2, '', NULL, NULL, NULL, '1456.00', '234.00', '1.00', 1, 0, 0),
(3, 2, 7, 3, 1, '', NULL, NULL, NULL, '567.00', '23.00', '0.00', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_items_status_history`
--

CREATE TABLE `order_items_status_history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `item_id` bigint(20) UNSIGNED NOT NULL,
  `status_id` bigint(20) UNSIGNED NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_items_status_history`
--

INSERT INTO `order_items_status_history` (`id`, `item_id`, `status_id`, `created_at`) VALUES
(1, 2, 1, '2020-08-21 08:20:26'),
(2, 2, 3, '2020-08-21 08:20:26');

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_status`
--

INSERT INTO `order_status` (`id`, `title`) VALUES
(1, 'Pending'),
(2, 'Processing'),
(3, 'Dispatched'),
(4, 'Refunded'),
(5, 'Voided');

-- --------------------------------------------------------

--
-- Table structure for table `payment_methods`
--

CREATE TABLE `payment_methods` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment_methods`
--

INSERT INTO `payment_methods` (`id`, `title`, `code`) VALUES
(1, 'Cash on delivery', 'cod'),
(2, 'Credit Card', 'cc'),
(3, 'Bank transfer', 'bank'),
(4, 'Paypal', 'paypal'),
(5, 'Western Union', 'wu');

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
(20200811052807, 'TableShippingZonesRegions', '2020-08-11 02:29:31', '2020-08-11 02:29:31', 0),
(20200811054511, 'TableShippingClasses', '2020-08-11 02:47:10', '2020-08-11 02:47:10', 0),
(20200811054839, 'TableEnableShipClassRegions', '2020-08-11 02:50:59', '2020-08-11 02:50:59', 0),
(20200811055306, 'TableWeightShipRules', '2020-08-11 03:05:45', '2020-08-11 03:05:45', 0),
(20200811063853, 'TableOrderStatus', '2020-08-11 03:40:10', '2020-08-11 03:40:10', 0),
(20200811064213, 'TableOrders', '2020-08-11 03:56:52', '2020-08-11 03:56:52', 0),
(20200811101628, 'TableGiftWraps', '2020-08-11 07:18:53', '2020-08-11 07:18:53', 0),
(20200811103059, 'AddCostCostToCourrierClasses', '2020-08-11 07:31:35', '2020-08-11 07:31:35', 0),
(20200811103524, 'CodWeightRules', '2020-08-11 07:36:20', '2020-08-11 07:36:21', 0),
(20200811104321, 'CreateTaxRules', '2020-08-11 07:46:34', '2020-08-11 07:46:34', 0),
(20200811105032, 'AddBillShipAddress', '2020-08-11 07:53:42', '2020-08-11 07:53:42', 0),
(20200811105356, 'OrderItems', '2020-08-11 08:00:40', '2020-08-11 08:00:40', 0),
(20200811110620, 'TaxRegionsRules', '2020-08-11 08:07:52', '2020-08-11 08:07:52', 0),
(20200811110922, 'TaxShopCustomCodeNames', '2020-08-11 08:12:51', '2020-08-11 08:12:51', 0),
(20200811111314, 'AddTaxCodeToTaxRules', '2020-08-11 09:12:14', '2020-08-11 09:12:14', 0),
(20200811121350, 'BindTaxCodesToCategories', '2020-08-11 09:17:58', '2020-08-11 09:17:58', 0),
(20200812043725, 'AddSKUTOProducts', '2020-08-12 01:39:43', '2020-08-12 01:39:43', 0),
(20200812053402, 'AddDefaultLangToShop', '2020-08-12 02:36:25', '2020-08-12 02:36:25', 0),
(20200812054731, 'AddShopIdToProductReviews', '2020-08-12 02:49:09', '2020-08-12 02:49:09', 0),
(20200812140958, 'AddVisibilityToProducts', '2020-08-12 11:13:15', '2020-08-12 11:13:15', 0),
(20200812143604, 'AddNormalPriceToProducts', '2020-08-12 11:37:34', '2020-08-12 11:37:34', 0),
(20200812160546, 'ShopStyling', '2020-08-12 13:08:06', '2020-08-12 13:08:06', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pricing_plans`
--

CREATE TABLE `pricing_plans` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `commission_per_order` decimal(5,2) UNSIGNED NOT NULL,
  `total_shops` tinyint(3) UNSIGNED NOT NULL,
  `total_products_per_shop` mediumint(8) UNSIGNED NOT NULL,
  `total_products` mediumint(8) UNSIGNED NOT NULL,
  `total_photos_per_product` tinyint(3) UNSIGNED NOT NULL,
  `total_photo_space_gb` smallint(5) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED DEFAULT 1,
  `category_id` bigint(20) UNSIGNED DEFAULT 1,
  `currency_id` bigint(20) UNSIGNED DEFAULT 1,
  `manufacturer_id` bigint(20) UNSIGNED DEFAULT 1,
  `code` varchar(120) DEFAULT NULL,
  `title` varchar(55) NOT NULL,
  `descr` varchar(55) DEFAULT NULL,
  `SKU` varchar(50) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `discount_percent` decimal(5,2) DEFAULT NULL,
  `kilos` decimal(5,2) DEFAULT NULL,
  `dim_l` decimal(6,2) DEFAULT NULL,
  `dim_w` decimal(6,2) DEFAULT NULL,
  `dim_h` decimal(6,2) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `stock` bigint(20) UNSIGNED DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `taxable` tinyint(1) DEFAULT NULL,
  `gift_wrap_cost` decimal(10,2) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  `total_orders` bigint(20) UNSIGNED DEFAULT 0,
  `avg_rating` decimal(2,1) DEFAULT 0.0,
  `total_clicks` bigint(20) UNSIGNED DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `shop_id`, `category_id`, `currency_id`, `manufacturer_id`, `code`, `title`, `descr`, `SKU`, `img_url`, `thumbnail_url`, `price`, `discount_percent`, `kilos`, `dim_l`, `dim_w`, `dim_h`, `active`, `stock`, `created`, `updated`, `taxable`, `gift_wrap_cost`, `visible`, `total_orders`, `avg_rating`, `total_clicks`) VALUES
(1, 1, 1, 1, 5, 'SelmaLagerlefNielsHolgresson', '', '', '', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 0, 0, '0.0', 0),
(2, 1, 1, 1, 5, 'StringbergDromspel', '', '', '', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 0, 0, '0.0', 0),
(3, 1, 1, 1, 5, 'StringbergDamaskusI\r\n', '', '', '', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 0, 0, '0.0', 0),
(4, 1, 1, 1, 5, 'StringbergDamaskusII\r\n', '', '', '', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 0, 0, '0.0', 0),
(5, 1, 1, 1, 1, 'StringbergDamaskusIII\r\n', '', '', '', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 0, 0, '0.0', 0),
(6, 1, 1, 1, 1, 'StringbergSpoksSonaten\r\n', '', '', '', '', '', '15.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 0, 0, '0.0', 0),
(7, 2, 5, 1, 4, 'MacBookPro13.3AirGray', 'Apple MacBook Air Retina', '', '', '', '', '1300.00', '0.00', '1.29', '30.41', '21.24', '1.61', 1, 50, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 1, 0, '0.0', 0),
(8, 2, 5, 1, 4, 'MacBookPro12.3AirSilber', 'Apple MacBook Air Silver', '', '', '', '', '1100.00', '0.00', '1.29', '30.41', '21.24', '1.61', 1, 50, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 1, 0, '0.0', 0),
(9, 2, 5, 1, 6, 'DELL G5 15-5590 I7-9750H/16/1TB+256GB SSD/RTX2060', 'Laptop DELL G5 15-5590 I7-9750H/16/1TB+256GB SSD/RTX206', '', '', '', '', '1700.00', '0.00', '2.61', '0.00', '0.00', '0.00', 1, 25, '2020-08-28 10:51:34', '2020-08-28 10:51:34', 1, '0.00', 1, 0, '0.0', 0),
(10, 1, 1, 1, 1, NULL, 'MobyDick', NULL, NULL, NULL, NULL, '19.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(11, 1, 1, 1, 1, NULL, 'Gunslinger', NULL, NULL, NULL, NULL, '31.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(12, 1, 1, 1, 1, NULL, 'Drawing of three', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(13, 1, 1, 1, 1, NULL, 'Wastelands', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(14, 1, 1, 1, 1, NULL, 'Wizard and Glass', NULL, NULL, NULL, NULL, '28.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(15, 1, 1, 1, 1, NULL, 'Caves of Caila', NULL, NULL, NULL, NULL, '44.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(16, 1, 1, 1, 1, NULL, 'Song of Susanna', NULL, NULL, NULL, NULL, '29.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(17, 1, 1, 1, 1, NULL, 'Dark Tower', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(18, 1, 1, 1, 1, NULL, 'It', NULL, NULL, NULL, NULL, '17.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(19, 1, 1, 1, 1, NULL, 'Salem\'s lot', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(20, 1, 1, 1, 1, NULL, 'Doctor Sleep', NULL, NULL, NULL, NULL, '43.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(21, 1, 1, 1, 1, NULL, 'The Shining', NULL, NULL, NULL, NULL, '24.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(22, 1, 1, 1, 1, NULL, '1968', NULL, NULL, NULL, NULL, '36.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(23, 1, 1, 1, 1, NULL, 'The Dome', NULL, NULL, NULL, NULL, '32.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(24, 1, 1, 1, 1, NULL, 'Bad Dreams', NULL, NULL, NULL, NULL, '37.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(25, 1, 1, 1, 1, NULL, 'Bazaar of Bad Dreams', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(26, 1, 1, 1, 1, NULL, 'The Stand', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(27, 1, 1, 1, 1, NULL, 'Night Shift', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(28, 1, 1, 1, 1, NULL, 'After Sunset', NULL, NULL, NULL, NULL, '21.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(29, 1, 1, 1, 1, NULL, 'Pet Semetary', NULL, NULL, NULL, NULL, '32.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(30, 1, 1, 1, 1, NULL, 'Green Mile', NULL, NULL, NULL, NULL, '22.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(31, 1, 1, 1, 1, NULL, 'Flight 29', NULL, NULL, NULL, NULL, '28.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(32, 1, 1, 1, 1, NULL, 'End of Watche', NULL, NULL, NULL, NULL, '29.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(33, 1, 1, 1, 1, NULL, 'Different Seasons', NULL, NULL, NULL, NULL, '17.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(34, 1, 1, 1, 1, NULL, 'Skeleton Crew', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(35, 1, 1, 1, 1, NULL, 'Four Past Midnight', NULL, NULL, NULL, NULL, '27.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(36, 1, 1, 1, 1, NULL, 'Full Dark no stars', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(37, 1, 1, 1, 1, NULL, 'Oliver Twist', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(38, 1, 1, 1, 1, NULL, 'Scrooge', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(39, 1, 1, 1, 1, NULL, 'Great Expectations', NULL, NULL, NULL, NULL, '17.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(40, 1, 1, 1, 1, NULL, 'Anna Karenina', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(41, 1, 1, 1, 1, NULL, 'War and Peace', NULL, NULL, NULL, NULL, '34.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(42, 1, 1, 1, 1, NULL, 'Ressurection', NULL, NULL, NULL, NULL, '41.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(43, 1, 1, 1, 1, NULL, 'Master and Man', NULL, NULL, NULL, NULL, '29.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(44, 1, 1, 1, 1, NULL, 'Don Quixote', NULL, NULL, NULL, NULL, '39.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(45, 1, 1, 1, 1, NULL, 'Les Miserables', NULL, NULL, NULL, NULL, '32.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(46, 1, 1, 1, 1, NULL, '20000 Miles under the Sea', NULL, NULL, NULL, NULL, '28.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(47, 1, 1, 1, 1, NULL, 'Le tour du monde in 80 jours', NULL, NULL, NULL, NULL, '29.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(48, 1, 1, 1, 1, NULL, 'De la terre a la lune', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(49, 1, 1, 1, 1, NULL, 'Autour de la lune', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(50, 1, 1, 1, 1, NULL, 'L ile mysteriese', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(51, 1, 1, 1, 1, NULL, 'Voyage au centre de la teree', NULL, NULL, NULL, NULL, '35.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(52, 1, 1, 1, 1, NULL, 'Robur le conquerant', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(53, 1, 1, 1, 1, NULL, 'Le Compte de monte cristo', NULL, NULL, NULL, NULL, '37.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(54, 1, 1, 1, 1, NULL, 'The three musketeers', NULL, NULL, NULL, NULL, '32.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(55, 1, 1, 1, 1, NULL, 'Master and Margarita', NULL, NULL, NULL, NULL, '36.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(56, 1, 1, 1, 1, NULL, 'Prestuplinie i Nakazanie', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(57, 1, 1, 1, 1, NULL, 'Idiot', NULL, NULL, NULL, NULL, '36.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(58, 1, 1, 1, 1, NULL, 'Der Idiot', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(59, 1, 1, 1, 1, NULL, 'Ilithios', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(60, 1, 1, 1, 1, NULL, 'Eglima k Timoria', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(61, 1, 1, 1, 1, NULL, 'Daimonismenoi', NULL, NULL, NULL, NULL, '17.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(62, 1, 1, 1, 1, NULL, 'Afoi karamazon', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(63, 1, 1, 1, 1, NULL, 'Demons', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(64, 1, 1, 1, 1, NULL, 'The brothers Karamazov', NULL, NULL, NULL, NULL, '28.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(65, 1, 1, 1, 1, NULL, 'White Nights', NULL, NULL, NULL, NULL, '43.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(66, 1, 1, 1, 1, NULL, 'Galbmer', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(67, 1, 1, 1, 1, NULL, 'Deadhouse memories', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(68, 1, 1, 1, 1, NULL, 'Herr und Knecht', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(69, 1, 1, 1, 1, NULL, 'Oresteia', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(70, 1, 1, 1, 1, NULL, 'Oedipus', NULL, NULL, NULL, NULL, '17.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(71, 1, 1, 1, 1, NULL, 'Persians', NULL, NULL, NULL, NULL, '24.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(72, 1, 1, 1, 1, NULL, 'Thebes', NULL, NULL, NULL, NULL, '24.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(73, 1, 1, 1, 1, NULL, 'Fellowship of the ring', NULL, NULL, NULL, NULL, '35.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(74, 1, 1, 1, 1, NULL, 'Two Towers', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(75, 1, 1, 1, 1, NULL, 'To tarme', NULL, NULL, NULL, NULL, '41.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(76, 1, 1, 1, 1, NULL, 'Return of the King', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(77, 1, 1, 1, 1, NULL, 'Hobbit', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(78, 1, 1, 1, 1, NULL, 'Hobbitus Ille', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(79, 1, 1, 1, 1, NULL, 'King Lear', NULL, NULL, NULL, NULL, '22.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(80, 1, 1, 1, 1, NULL, 'Vasilias Lir', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(81, 1, 1, 1, 1, NULL, 'Hamlet', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(82, 1, 1, 1, 1, NULL, 'Othello', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(83, 1, 1, 1, 1, NULL, 'Makbeth', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(84, 1, 1, 1, 1, NULL, 'Ghost Sonata', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(85, 1, 1, 1, 1, NULL, 'Pelikan', NULL, NULL, NULL, NULL, '28.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(86, 1, 1, 1, 1, NULL, 'The Pretenders', NULL, NULL, NULL, NULL, '36.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(87, 1, 1, 1, 1, NULL, 'Kongsemnerne', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(88, 1, 1, 1, 1, NULL, 'BrandGR', NULL, NULL, NULL, NULL, '31.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(89, 1, 1, 1, 1, NULL, 'BrandDE', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(90, 1, 1, 1, 1, NULL, 'BrandEN', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(91, 1, 1, 1, 1, NULL, 'BrandNO', NULL, NULL, NULL, NULL, '32.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(92, 1, 1, 1, 1, NULL, 'PeerGyntDE', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(93, 1, 1, 1, 1, NULL, 'PeerGyntGR', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(94, 1, 1, 1, 1, NULL, 'PeerGyntNO', NULL, NULL, NULL, NULL, '43.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(95, 1, 1, 1, 1, NULL, 'Emperor and Galilean GR', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(96, 1, 1, 1, 1, NULL, 'Emperor and Galilean DE', NULL, NULL, NULL, NULL, '20.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(97, 1, 1, 1, 1, NULL, 'Et Dukkehjem ', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(98, 1, 1, 1, 1, NULL, 'Et Dukkehjem GR', NULL, NULL, NULL, NULL, '37.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(99, 1, 1, 1, 1, NULL, 'Gengangere NO', NULL, NULL, NULL, NULL, '21.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(100, 1, 1, 1, 1, NULL, 'Gengangere GR', NULL, NULL, NULL, NULL, '41.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(101, 1, 1, 1, 1, NULL, 'Gengangere DE', NULL, NULL, NULL, NULL, '34.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(102, 1, 1, 1, 1, NULL, 'En Folkefiende', NULL, NULL, NULL, NULL, '35.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(103, 1, 1, 1, 1, NULL, 'Vildanden DE', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(104, 1, 1, 1, 1, NULL, 'Vildanden GR', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(105, 1, 1, 1, 1, NULL, 'Rosmersholm DE', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(106, 1, 1, 1, 1, NULL, 'Rosmersholm EN', NULL, NULL, NULL, NULL, '20.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(107, 1, 1, 1, 1, NULL, 'Fruen fra Havet', NULL, NULL, NULL, NULL, '39.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(108, 1, 1, 1, 1, NULL, 'Hedda Gabler GR', NULL, NULL, NULL, NULL, '31.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(109, 1, 1, 1, 1, NULL, 'Bygmester Solness', NULL, NULL, NULL, NULL, '21.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(110, 1, 1, 1, 1, NULL, 'Lille Eyolf', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(111, 1, 1, 1, 1, NULL, 'John Gabriel Borkman', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(112, 1, 1, 1, 1, NULL, 'Når vi døde vaagner', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(113, 1, 1, 1, 1, NULL, 'All My Sons', NULL, NULL, NULL, NULL, '23.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(114, 1, 1, 1, 1, NULL, 'Death of a Salesman ', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(115, 1, 1, 1, 1, NULL, 'The Crucible ', NULL, NULL, NULL, NULL, '37.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(116, 1, 1, 1, 1, NULL, 'View from bridge', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(117, 1, 1, 1, 1, NULL, 'Iliad', NULL, NULL, NULL, NULL, '34.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(118, 1, 1, 1, 1, NULL, 'Odyssey', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(119, 1, 1, 1, 1, NULL, 'Niels HOlgreson DE', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(120, 1, 1, 1, 1, NULL, 'Niels HOlgreson SWE', NULL, NULL, NULL, NULL, '37.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(121, 1, 1, 1, 1, NULL, 'Niels HOlgreson GR', NULL, NULL, NULL, NULL, '30.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(122, 1, 1, 1, 1, NULL, 'The Price ', NULL, NULL, NULL, NULL, '27.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(123, 1, 1, 1, 1, NULL, 'The Eye of the World', NULL, NULL, NULL, NULL, '29.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(124, 1, 1, 1, 1, NULL, 'The Great Hunt', NULL, NULL, NULL, NULL, '21.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(125, 1, 1, 1, 1, NULL, 'The Dragon Reborn', NULL, NULL, NULL, NULL, '32.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(126, 1, 1, 1, 1, NULL, 'The Shadow Rising', NULL, NULL, NULL, NULL, '22.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(127, 1, 1, 1, 1, NULL, 'What men live by', NULL, NULL, NULL, NULL, '31.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(128, 1, 1, 1, 1, NULL, 'Russian I', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(129, 1, 1, 1, 1, NULL, 'Russian II', NULL, NULL, NULL, NULL, '15.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(130, 1, 1, 1, 1, NULL, 'Valkomna', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(131, 1, 1, 1, 1, NULL, 'Fjord pa mordet', NULL, NULL, NULL, NULL, '34.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(132, 1, 1, 1, 1, NULL, 'Iskalt mord', NULL, NULL, NULL, NULL, '20.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(133, 1, 1, 1, 1, NULL, 'Mord pa Gjoteburg', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(134, 1, 1, 1, 1, NULL, 'Notre Dame de paris', NULL, NULL, NULL, NULL, '33.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(135, 1, 1, 1, 1, NULL, 'Rama I', NULL, NULL, NULL, NULL, '43.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(136, 1, 1, 1, 1, NULL, 'Rama II', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(137, 1, 1, 1, 1, NULL, 'Rama III', NULL, NULL, NULL, NULL, '36.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(138, 1, 1, 1, 1, NULL, 'The Martian', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(139, 1, 1, 1, 1, NULL, 'Moondust', NULL, NULL, NULL, NULL, '41.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(140, 1, 1, 1, 1, NULL, '2001: A Space Odyssey', NULL, NULL, NULL, NULL, '43.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(141, 1, 1, 1, 1, NULL, '2010: Odyssey Two', NULL, NULL, NULL, NULL, '19.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(142, 1, 1, 1, 1, NULL, '2061: Odyssey Three', NULL, NULL, NULL, NULL, '41.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(143, 1, 1, 1, 1, NULL, '3001: The Final Odyssey ', NULL, NULL, NULL, NULL, '44.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(144, 1, 1, 1, 1, NULL, 'Time\'s Eye ', NULL, NULL, NULL, NULL, '24.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(145, 1, 1, 1, 1, NULL, 'Sunstorm', NULL, NULL, NULL, NULL, '34.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(146, 1, 1, 1, 1, NULL, 'Firstborn', NULL, NULL, NULL, NULL, '21.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(147, 1, 1, 1, 1, NULL, 'Childhood\'s End ', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(148, 1, 1, 1, 1, NULL, 'The Fountains of Paradise ', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(149, 1, 1, 1, 1, NULL, 'The Hammer of God ', NULL, NULL, NULL, NULL, '24.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(150, 1, 1, 1, 1, NULL, 'Last theorem', NULL, NULL, NULL, NULL, '40.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(151, 1, 1, 1, 1, NULL, 'Digital Fortress', NULL, NULL, NULL, NULL, '24.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(152, 1, 1, 1, 1, NULL, 'Deception point', NULL, NULL, NULL, NULL, '43.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(153, 1, 1, 1, 1, NULL, 'Angels And demons', NULL, NULL, NULL, NULL, '39.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(154, 1, 1, 1, 1, NULL, 'DaVincie', NULL, NULL, NULL, NULL, '23.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(155, 1, 1, 1, 1, NULL, 'Lost Symbol', NULL, NULL, NULL, NULL, '42.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(156, 1, 1, 1, 1, NULL, 'Inferno', NULL, NULL, NULL, NULL, '38.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(157, 1, 1, 1, 1, NULL, 'Origin', NULL, NULL, NULL, NULL, '17.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(158, 1, 1, 1, 1, NULL, 'Isprinsessan', NULL, NULL, NULL, NULL, '18.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(159, 1, 1, 1, 1, NULL, 'Predikanten', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(160, 1, 1, 1, 1, NULL, 'Stenhuggaren', NULL, NULL, NULL, NULL, '27.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(161, 1, 1, 1, 1, NULL, 'Olycksfågeln', NULL, NULL, NULL, NULL, '44.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(162, 1, 1, 1, 1, NULL, 'Tyskungen', NULL, NULL, NULL, NULL, '35.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(163, 1, 1, 1, 1, NULL, 'Snöstorm och mandeldoft', NULL, NULL, NULL, NULL, '29.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(164, 1, 1, 1, 1, NULL, 'Sjöjungfrun', NULL, NULL, NULL, NULL, '25.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(165, 1, 1, 1, 1, NULL, 'Smaker från Fjällbacka', NULL, NULL, NULL, NULL, '26.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(166, 1, 1, 1, 1, NULL, 'Sjöjungfrun', NULL, NULL, NULL, NULL, '37.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(167, 1, 1, 1, 1, NULL, 'Fyrvaktaren', NULL, NULL, NULL, NULL, '34.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0),
(168, 1, 1, 1, 1, NULL, 'Änglamakerskan', NULL, NULL, NULL, NULL, '16.00', NULL, NULL, NULL, NULL, NULL, 1, NULL, '2020-08-28 10:51:34', '2020-08-28 10:51:34', NULL, NULL, NULL, 0, '0.0', 0);

-- --------------------------------------------------------

--
-- Table structure for table `products_discounts`
--

CREATE TABLE `products_discounts` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `discount_per_cent` bigint(20) UNSIGNED NOT NULL,
  `active_from` datetime NOT NULL,
  `active_until` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_attributes_values`
--

CREATE TABLE `product_attributes_values` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `attribute_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `value` varchar(55) DEFAULT NULL,
  `value_numeric` decimal(10,2) UNSIGNED NOT NULL DEFAULT 0.00,
  `value_boolean` tinyint(3) UNSIGNED NOT NULL DEFAULT 2 COMMENT 'can either be zero or one ,2 means nothing'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_attributes_values`
--

INSERT INTO `product_attributes_values` (`id`, `product_id`, `attribute_id`, `value`, `value_numeric`, `value_boolean`) VALUES
(1, 1, 1, '658', '0.00', 2),
(3, 2, 1, '256', '0.00', 2),
(6, 7, 1, 'Silver Gray', '0.00', 2),
(7, 7, 3, 'Catalina', '0.00', 2),
(8, 7, 4, '', '11.00', 2),
(9, 7, 5, 'Lithium', '0.00', 2),
(10, 7, 6, '', '0.00', 1),
(11, 7, 7, '', '14.50', 2),
(12, 7, 8, '', '450.00', 2),
(13, 7, 9, '', '0.00', 1),
(14, 7, 10, '', '8.00', 2),
(15, 8, 1, 'Silver ', '0.00', 2),
(16, 8, 3, 'Catalina', '0.00', 2),
(17, 8, 4, '', '9.00', 2),
(18, 8, 5, 'Lithium', '0.00', 2),
(19, 8, 6, '', '0.00', 1),
(20, 8, 7, '', '12.00', 2),
(21, 8, 8, '', '350.00', 2),
(22, 8, 9, '', '0.00', 1),
(23, 8, 10, '', '6.00', 2),
(26, 7, 20, '', '0.00', 0),
(27, 8, 20, '', '0.00', 0),
(28, 9, 20, '', '0.00', 1),
(29, 7, 19, '', '0.00', 2),
(30, 8, 19, '', '0.00', 2),
(31, 9, 1, 'Black', '0.00', 2),
(32, 9, 8, '', '256.00', 2),
(33, 9, 10, '', '16.00', 2),
(34, 9, 19, '', '1000.00', 2),
(35, 9, 12, '', '0.00', 1),
(36, 9, 7, '', '15.60', 2),
(37, 9, 21, '', '8.00', 2),
(38, 10, 13, NULL, '1124.00', 2),
(39, 11, 13, NULL, '642.00', 2),
(40, 12, 13, NULL, '668.00', 2),
(41, 13, 13, NULL, '287.00', 2),
(42, 14, 13, NULL, '262.00', 2),
(43, 15, 13, NULL, '297.00', 2),
(44, 16, 13, NULL, '550.00', 2),
(45, 17, 13, NULL, '730.00', 2),
(46, 18, 13, NULL, '871.00', 2),
(47, 19, 13, NULL, '1035.00', 2),
(48, 20, 13, NULL, '454.00', 2),
(49, 21, 13, NULL, '976.00', 2),
(50, 22, 13, NULL, '428.00', 2),
(51, 23, 13, NULL, '1022.00', 2),
(52, 24, 13, NULL, '737.00', 2),
(53, 25, 13, NULL, '469.00', 2),
(54, 26, 13, NULL, '964.00', 2),
(55, 27, 13, NULL, '322.00', 2),
(56, 28, 13, NULL, '529.00', 2),
(57, 29, 13, NULL, '552.00', 2),
(58, 30, 13, NULL, '1023.00', 2),
(59, 31, 13, NULL, '368.00', 2),
(60, 32, 13, NULL, '580.00', 2),
(61, 33, 13, NULL, '669.00', 2),
(62, 34, 13, NULL, '475.00', 2),
(63, 35, 13, NULL, '217.00', 2),
(64, 36, 13, NULL, '491.00', 2),
(65, 37, 13, NULL, '673.00', 2),
(66, 38, 13, NULL, '765.00', 2),
(67, 39, 13, NULL, '675.00', 2),
(68, 40, 13, NULL, '929.00', 2),
(69, 41, 13, NULL, '511.00', 2),
(70, 42, 13, NULL, '599.00', 2),
(71, 43, 13, NULL, '333.00', 2),
(72, 44, 13, NULL, '700.00', 2),
(73, 45, 13, NULL, '390.00', 2),
(74, 46, 13, NULL, '681.00', 2),
(75, 47, 13, NULL, '1105.00', 2),
(76, 48, 13, NULL, '393.00', 2),
(77, 49, 13, NULL, '459.00', 2),
(78, 50, 13, NULL, '967.00', 2),
(79, 51, 13, NULL, '368.00', 2),
(80, 52, 13, NULL, '749.00', 2),
(81, 53, 13, NULL, '532.00', 2),
(82, 54, 13, NULL, '263.00', 2),
(83, 55, 13, NULL, '551.00', 2),
(84, 56, 13, NULL, '837.00', 2),
(85, 57, 13, NULL, '422.00', 2),
(86, 58, 13, NULL, '430.00', 2),
(87, 59, 13, NULL, '732.00', 2),
(88, 60, 13, NULL, '262.00', 2),
(89, 61, 13, NULL, '923.00', 2),
(90, 62, 13, NULL, '738.00', 2),
(91, 63, 13, NULL, '775.00', 2),
(92, 64, 13, NULL, '530.00', 2),
(93, 65, 13, NULL, '174.00', 2),
(94, 66, 13, NULL, '1092.00', 2),
(95, 67, 13, NULL, '866.00', 2),
(96, 68, 13, NULL, '907.00', 2),
(97, 69, 13, NULL, '806.00', 2),
(98, 70, 13, NULL, '178.00', 2),
(99, 71, 13, NULL, '285.00', 2),
(100, 72, 13, NULL, '742.00', 2),
(101, 73, 13, NULL, '744.00', 2),
(102, 74, 13, NULL, '363.00', 2),
(103, 75, 13, NULL, '415.00', 2),
(104, 76, 13, NULL, '836.00', 2),
(105, 77, 13, NULL, '824.00', 2),
(106, 78, 13, NULL, '485.00', 2),
(107, 79, 13, NULL, '782.00', 2),
(108, 80, 13, NULL, '345.00', 2),
(109, 81, 13, NULL, '212.00', 2),
(110, 82, 13, NULL, '855.00', 2),
(111, 83, 13, NULL, '550.00', 2),
(112, 84, 13, NULL, '1014.00', 2),
(113, 85, 13, NULL, '330.00', 2),
(114, 86, 13, NULL, '421.00', 2),
(115, 87, 13, NULL, '963.00', 2),
(116, 88, 13, NULL, '462.00', 2),
(117, 89, 13, NULL, '253.00', 2),
(118, 90, 13, NULL, '708.00', 2),
(119, 91, 13, NULL, '673.00', 2),
(120, 92, 13, NULL, '1092.00', 2),
(121, 93, 13, NULL, '351.00', 2),
(122, 94, 13, NULL, '288.00', 2),
(123, 95, 13, NULL, '240.00', 2),
(124, 96, 13, NULL, '187.00', 2),
(125, 97, 13, NULL, '1047.00', 2),
(126, 98, 13, NULL, '601.00', 2),
(127, 99, 13, NULL, '694.00', 2),
(128, 100, 13, NULL, '540.00', 2),
(129, 101, 13, NULL, '469.00', 2),
(130, 102, 13, NULL, '575.00', 2),
(131, 103, 13, NULL, '340.00', 2),
(132, 104, 13, NULL, '804.00', 2),
(133, 105, 13, NULL, '890.00', 2),
(134, 106, 13, NULL, '911.00', 2),
(135, 107, 13, NULL, '754.00', 2),
(136, 108, 13, NULL, '888.00', 2),
(137, 109, 13, NULL, '1047.00', 2),
(138, 110, 13, NULL, '463.00', 2),
(139, 111, 13, NULL, '986.00', 2),
(140, 112, 13, NULL, '450.00', 2),
(141, 113, 13, NULL, '1102.00', 2),
(142, 114, 13, NULL, '1072.00', 2),
(143, 115, 13, NULL, '923.00', 2),
(144, 116, 13, NULL, '271.00', 2),
(145, 117, 13, NULL, '396.00', 2),
(146, 118, 13, NULL, '1017.00', 2),
(147, 119, 13, NULL, '806.00', 2),
(148, 120, 13, NULL, '832.00', 2),
(149, 121, 13, NULL, '612.00', 2),
(150, 122, 13, NULL, '412.00', 2),
(151, 123, 13, NULL, '1057.00', 2),
(152, 124, 13, NULL, '959.00', 2),
(153, 125, 13, NULL, '494.00', 2),
(154, 126, 13, NULL, '422.00', 2),
(155, 127, 13, NULL, '481.00', 2),
(156, 128, 13, NULL, '988.00', 2),
(157, 129, 13, NULL, '406.00', 2),
(158, 130, 13, NULL, '878.00', 2),
(159, 131, 13, NULL, '1063.00', 2),
(160, 132, 13, NULL, '572.00', 2),
(161, 133, 13, NULL, '500.00', 2),
(162, 134, 13, NULL, '637.00', 2),
(163, 135, 13, NULL, '555.00', 2),
(164, 136, 13, NULL, '715.00', 2),
(165, 137, 13, NULL, '778.00', 2),
(166, 138, 13, NULL, '615.00', 2),
(167, 139, 13, NULL, '593.00', 2),
(168, 140, 13, NULL, '971.00', 2),
(169, 141, 13, NULL, '965.00', 2),
(170, 142, 13, NULL, '782.00', 2),
(171, 143, 13, NULL, '865.00', 2),
(172, 144, 13, NULL, '851.00', 2),
(173, 145, 13, NULL, '529.00', 2),
(174, 146, 13, NULL, '924.00', 2),
(175, 147, 13, NULL, '923.00', 2),
(176, 148, 13, NULL, '716.00', 2),
(177, 149, 13, NULL, '658.00', 2),
(178, 150, 13, NULL, '995.00', 2),
(179, 151, 13, NULL, '889.00', 2),
(180, 152, 13, NULL, '330.00', 2),
(181, 153, 13, NULL, '795.00', 2),
(182, 154, 13, NULL, '876.00', 2),
(183, 155, 13, NULL, '864.00', 2),
(184, 156, 13, NULL, '562.00', 2),
(185, 157, 13, NULL, '1050.00', 2),
(186, 158, 13, NULL, '476.00', 2),
(187, 159, 13, NULL, '1037.00', 2),
(188, 160, 13, NULL, '671.00', 2),
(189, 161, 13, NULL, '1072.00', 2),
(190, 162, 13, NULL, '257.00', 2),
(191, 163, 13, NULL, '860.00', 2),
(192, 164, 13, NULL, '439.00', 2),
(193, 165, 13, NULL, '445.00', 2),
(194, 166, 13, NULL, '760.00', 2),
(195, 167, 13, NULL, '355.00', 2),
(196, 168, 13, NULL, '323.00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `product_attribute_units`
--

CREATE TABLE `product_attribute_units` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `attribute_id` bigint(20) UNSIGNED NOT NULL,
  `units` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_attribute_units`
--

INSERT INTO `product_attribute_units` (`id`, `attribute_id`, `units`) VALUES
(1, 8, 'GB');

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `parent_category_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_categories`
--

INSERT INTO `product_categories` (`id`, `shop_id`, `parent_category_id`, `title`) VALUES
(1, 1, 0, 'Books'),
(2, 1, 0, 'Jewel'),
(3, 1, 0, 'Clothing'),
(4, 1, 0, 'Shoes'),
(5, 2, 0, 'Laptops');

-- --------------------------------------------------------

--
-- Table structure for table `product_category_attributes`
--

CREATE TABLE `product_category_attributes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `product_category_id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(55) NOT NULL,
  `filterable` tinyint(4) NOT NULL DEFAULT 1,
  `rangeable` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'e.g ram size slider on filters',
  `searchable` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `isBoolean` tinyint(4) NOT NULL DEFAULT 0 COMMENT 'aka boolean'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_category_attributes`
--

INSERT INTO `product_category_attributes` (`id`, `shop_id`, `product_category_id`, `code`, `filterable`, `rangeable`, `searchable`, `isBoolean`) VALUES
(1, 2, 5, 'Color', 0, 0, 0, 0),
(3, 2, 5, 'OS', 0, 0, 0, 0),
(4, 2, 5, 'BatteryLife', 1, 1, 0, 0),
(5, 2, 5, 'Battery', 0, 0, 0, 0),
(6, 2, 5, 'HDMI', 1, 0, 0, 1),
(7, 2, 5, 'Screen_inches', 1, 1, 0, 0),
(8, 2, 5, 'Disk_size', 1, 1, 0, 0),
(9, 2, 5, 'SSD', 1, 0, 0, 1),
(10, 2, 5, 'Ram_Size', 1, 1, 0, 0),
(11, 2, 5, 'CPU_Brand', 1, 0, 0, 0),
(12, 2, 5, 'Ethernet_port', 0, 0, 0, 1),
(13, 1, 1, 'Pages', 0, 0, 0, 0),
(14, 1, 1, 'Author', 0, 0, 1, 0),
(15, 1, 1, 'Cover', 0, 0, 0, 0),
(16, 1, 1, 'ISBN', 0, 0, 1, 0),
(17, 1, 1, 'Genre', 1, 0, 1, 0),
(18, 1, 1, 'Language', 1, 0, 0, 0),
(19, 2, 5, '2nd_Disk_size', 1, 1, 0, 0),
(20, 2, 5, 'Has_2nd_disk', 0, 0, 0, 1),
(21, 2, 5, 'CPU_Cores', 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product_gallery`
--

CREATE TABLE `product_gallery` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `file_path` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_gallery`
--

INSERT INTO `product_gallery` (`id`, `product_id`, `file_path`) VALUES
(1, 7, 'foo');

-- --------------------------------------------------------

--
-- Table structure for table `product_gallery_tag`
--

CREATE TABLE `product_gallery_tag` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `image_id` bigint(20) UNSIGNED NOT NULL,
  `tag` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_reviews`
--

CREATE TABLE `product_reviews` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `shop_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `stars` decimal(2,1) NOT NULL,
  `comment` varchar(255) NOT NULL,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_reviews`
--

INSERT INTO `product_reviews` (`id`, `product_id`, `shop_id`, `user_id`, `stars`, `comment`, `created`) VALUES
(1, 1, 1, 2, '3.0', '', '2020-08-04 08:54:22'),
(2, 7, 2, 2, '4.0', 'excellent', '2020-08-18 07:47:11');

-- --------------------------------------------------------

--
-- Table structure for table `product_tags`
--

CREATE TABLE `product_tags` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL,
  `tag` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_tags`
--

INSERT INTO `product_tags` (`id`, `product_id`, `tag`) VALUES
(1, 7, 'apple');

-- --------------------------------------------------------

--
-- Table structure for table `restrict_payment_parameters`
--

CREATE TABLE `restrict_payment_parameters` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(12) NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `restrict_payment_parameters`
--

INSERT INTO `restrict_payment_parameters` (`id`, `code`, `title`) VALUES
(1, '', 'Shipping zone'),
(2, 'shipClass', 'Shipping class'),
(3, 'countryCo', 'Country Code'),
(4, 'regionId', 'Region'),
(5, 'zip', 'Zip Code'),
(6, 'currency', 'Currency'),
(7, 'lang', 'Language'),
(8, 'total', 'CartAmount'),
(9, 'email', 'Email'),
(10, 'phone', 'Phone'),
(11, 'coupon', 'Coupon'),
(12, 'discount', 'Has Discount'),
(13, 'productId', 'Product'),
(14, 'productCatId', 'Product Category'),
(15, 'totalWeight', 'Total Weight');

-- --------------------------------------------------------

--
-- Table structure for table `restrict_payment_rules`
--

CREATE TABLE `restrict_payment_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(120) NOT NULL,
  `active` tinyint(3) UNSIGNED NOT NULL,
  `priority` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='rules for disabling criteria ';

--
-- Dumping data for table `restrict_payment_rules`
--

INSERT INTO `restrict_payment_rules` (`id`, `shop_id`, `title`, `active`, `priority`, `created_at`, `updated_at`) VALUES
(1, 2, 'No COD over 10 Kg', 1, 1, '2020-08-25 18:12:03', '2020-08-25 18:12:03');

-- --------------------------------------------------------

--
-- Table structure for table `restrict_payment_rules_criteria`
--

CREATE TABLE `restrict_payment_rules_criteria` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `rule_id` bigint(20) UNSIGNED NOT NULL,
  `parameter_id` bigint(20) UNSIGNED NOT NULL,
  `operator_id` bigint(20) UNSIGNED NOT NULL,
  `value` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `restrict_payment_rules_criteria`
--

INSERT INTO `restrict_payment_rules_criteria` (`id`, `rule_id`, `parameter_id`, `operator_id`, `value`) VALUES
(1, 1, 15, 5, '10');

-- --------------------------------------------------------

--
-- Table structure for table `restrict_payment_rules_operators`
--

CREATE TABLE `restrict_payment_rules_operators` (
  `id` bigint(10) UNSIGNED NOT NULL,
  `code` varchar(12) NOT NULL,
  `title` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `restrict_payment_rules_operators`
--

INSERT INTO `restrict_payment_rules_operators` (`id`, `code`, `title`) VALUES
(1, 'eq', 'Equals'),
(2, 'lt', 'Less than'),
(3, 'lte', 'Less equal'),
(4, 'gt', 'Greater than'),
(5, 'gte', 'Greater equal'),
(6, 'in_list', 'In list'),
(7, 'contains', 'Contains');

-- --------------------------------------------------------

--
-- Table structure for table `rules_disables_methods`
--

CREATE TABLE `rules_disables_methods` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `rule_id` bigint(20) UNSIGNED NOT NULL,
  `payment_method_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rules_disables_methods`
--

INSERT INTO `rules_disables_methods` (`id`, `rule_id`, `payment_method_id`) VALUES
(1, 1, 1),
(2, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `shipping_address`
--

CREATE TABLE `shipping_address` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `user_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `city` varchar(80) NOT NULL,
  `full_name` varchar(80) NOT NULL,
  `address` varchar(80) NOT NULL,
  `street_no` varchar(20) NOT NULL,
  `post_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shipping_address`
--

INSERT INTO `shipping_address` (`id`, `user_id`, `country_code`, `region_id`, `city`, `full_name`, `address`, `street_no`, `post_code`) VALUES
(1, 3, 'GR', 1, 'Athens', '', 'Syntagma', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `shipping_region_zips`
--

CREATE TABLE `shipping_region_zips` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `zone_region_id` bigint(20) UNSIGNED NOT NULL,
  `code` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shipping_region_zips`
--

INSERT INTO `shipping_region_zips` (`id`, `zone_region_id`, `code`) VALUES
(1, 4, '28100');

-- --------------------------------------------------------

--
-- Table structure for table `shipping_zip_codes_forbid`
--

CREATE TABLE `shipping_zip_codes_forbid` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL,
  `zip_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='DO NOT SHIP HERE';

-- --------------------------------------------------------

--
-- Table structure for table `shipping_zones`
--

CREATE TABLE `shipping_zones` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ship to these zones';

--
-- Dumping data for table `shipping_zones`
--

INSERT INTO `shipping_zones` (`id`, `shop_id`, `title`) VALUES
(1, 2, 'Σχεδόν ολη η Ελλάδα'),
(2, 2, 'Ακριτικά'),
(3, 2, 'Επτάνησα'),
(4, 2, 'Κεφαλονιά');

-- --------------------------------------------------------

--
-- Table structure for table `shipping_zones_regions`
--

CREATE TABLE `shipping_zones_regions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `zone_id` bigint(20) UNSIGNED NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ship to these regions';

--
-- Dumping data for table `shipping_zones_regions`
--

INSERT INTO `shipping_zones_regions` (`id`, `zone_id`, `region_id`) VALUES
(1, 1, 2),
(2, 1, 3),
(4, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `shops`
--

CREATE TABLE `shops` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL,
  `basic_currency` varchar(3) NOT NULL DEFAULT 'EUR',
  `owner_id` bigint(20) UNSIGNED NOT NULL,
  `default_lang_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shops`
--

INSERT INTO `shops` (`id`, `title`, `basic_currency`, `owner_id`, `default_lang_id`, `created`) VALUES
(1, 'vivliofagos', 'EUR', 1, 1, '2020-08-03 08:22:05'),
(2, 'elektroktenhini', 'EUR', 1, 1, '2020-08-17 07:25:53');

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

--
-- Dumping data for table `shop_banks`
--

INSERT INTO `shop_banks` (`id`, `shop_id`, `bank`, `account_no`, `iban`, `swift_code`) VALUES
(1, 1, 'Pireaus', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `shop_belongs_categories`
--

CREATE TABLE `shop_belongs_categories` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `thumbnail_url` varchar(255) NOT NULL,
  `show_order` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_belongs_categories`
--

INSERT INTO `shop_belongs_categories` (`id`, `shop_id`, `category_id`, `thumbnail_url`, `show_order`) VALUES
(1, 1, 1, '', 0),
(2, 2, 3, '', 0);

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
(2, 'Clothing'),
(3, 'Computers');

-- --------------------------------------------------------

--
-- Table structure for table `shop_countries`
--

CREATE TABLE `shop_countries` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `country_code` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='shops to  these countires';

--
-- Dumping data for table `shop_countries`
--

INSERT INTO `shop_countries` (`id`, `shop_id`, `country_code`) VALUES
(1, 1, 'GR');

-- --------------------------------------------------------

--
-- Table structure for table `shop_couriers`
--

CREATE TABLE `shop_couriers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `courier_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_couriers`
--

INSERT INTO `shop_couriers` (`id`, `shop_id`, `courier_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `shop_courier_classes`
--

CREATE TABLE `shop_courier_classes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_courier_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(52) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_courier_classes`
--

INSERT INTO `shop_courier_classes` (`id`, `shop_courier_id`, `title`, `active`) VALUES
(1, 1, 'Standard', 1),
(2, 1, 'Express', 1);

-- --------------------------------------------------------

--
-- Table structure for table `shop_currencies`
--

CREATE TABLE `shop_currencies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `currency_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_currencies`
--

INSERT INTO `shop_currencies` (`id`, `shop_id`, `currency_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 2);

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
-- Table structure for table `shop_eulas`
--

CREATE TABLE `shop_eulas` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL,
  `terms` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_giftwrap`
--

CREATE TABLE `shop_giftwrap` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `image_path` varchar(52) NOT NULL,
  `active` tinyint(1) NOT NULL
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
(1, 1, 1),
(2, 2, 1),
(3, 2, 2);

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
-- Table structure for table `shop_manufacturers`
--

CREATE TABLE `shop_manufacturers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_manufacturers`
--

INSERT INTO `shop_manufacturers` (`id`, `shop_id`, `title`) VALUES
(1, 1, 'Asus'),
(2, 2, 'Toshiba'),
(3, 2, 'HP'),
(4, 2, 'Apple'),
(5, 1, 'Reclam Verlag'),
(6, 2, 'DELL');

-- --------------------------------------------------------

--
-- Table structure for table `shop_over_weight_ship_rules`
--

CREATE TABLE `shop_over_weight_ship_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `shipping_class_id` bigint(20) UNSIGNED NOT NULL,
  `zone_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `taxable` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `charge` decimal(10,2) NOT NULL,
  `over_total_weight` decimal(10,2) NOT NULL,
  `for_each_kg` decimal(10,2) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_over_weight_ship_rules`
--

INSERT INTO `shop_over_weight_ship_rules` (`id`, `shop_id`, `shipping_class_id`, `zone_id`, `taxable`, `created_at`, `updated_at`, `charge`, `over_total_weight`, `for_each_kg`, `active`) VALUES
(1, 2, 1, 1, 0, '2020-08-12 08:55:50', '2020-08-12 08:55:50', '0.00', '0.00', '0.00', 1),
(2, 2, 2, 4, 0, '2020-08-23 08:42:46', '2020-08-23 08:42:46', '0.00', '0.00', '0.00', 1);

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
-- Table structure for table `shop_styling`
--

CREATE TABLE `shop_styling` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `style` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_suppliers`
--

CREATE TABLE `shop_suppliers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_tax_region_rules`
--

CREATE TABLE `shop_tax_region_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `product_category_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `country_code` varchar(3) NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL,
  `flat_cost` decimal(10,2) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `tax_address` varchar(4) NOT NULL DEFAULT 'ship' COMMENT 'ship or bill',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `active_from` date DEFAULT NULL,
  `active_until` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='exceptions';

-- --------------------------------------------------------

--
-- Table structure for table `shop_tax_rules`
--

CREATE TABLE `shop_tax_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `product_category_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `country_code` varchar(3) NOT NULL,
  `flat_cost` decimal(10,2) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `tax_address` varchar(4) NOT NULL DEFAULT 'ship' COMMENT 'bill or ship',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `active_from` date DEFAULT NULL,
  `active_until` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_tax_rules`
--

INSERT INTO `shop_tax_rules` (`id`, `shop_id`, `product_category_id`, `country_code`, `flat_cost`, `rate`, `tax_address`, `created`, `updated`, `active`, `active_from`, `active_until`) VALUES
(1, 1, 1, 'GR', '1.50', '13.00', 'ship', '2020-08-22 07:46:58', '2020-08-22 07:46:58', 1, '2020-05-31', '2030-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `shop_tax_zipcode_rules`
--

CREATE TABLE `shop_tax_zipcode_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `product_category_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `country_code` varchar(3) NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL,
  `zip_codes` text NOT NULL,
  `flat_cost` decimal(10,2) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `tax_address` varchar(4) NOT NULL DEFAULT 'ship' COMMENT 'ship or bill',
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `active` tinyint(1) NOT NULL,
  `active_from` date DEFAULT NULL,
  `active_until` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='exceptions';

-- --------------------------------------------------------

--
-- Table structure for table `shop_translations`
--

CREATE TABLE `shop_translations` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `language_id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(80) NOT NULL,
  `transltr` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_weight_cod_rules`
--

CREATE TABLE `shop_weight_cod_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `shipping_class_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(52) NOT NULL,
  `taxable` tinyint(1) NOT NULL,
  `less_than_kg` decimal(10,2) NOT NULL,
  `less_equal` tinyint(1) NOT NULL,
  `over_than_kg` decimal(10,2) NOT NULL,
  `over_equal` tinyint(1) NOT NULL,
  `base_cost` decimal(10,2) NOT NULL,
  `charge` decimal(10,2) NOT NULL,
  `over_total_weight` decimal(10,2) NOT NULL,
  `for_each_kg` decimal(10,2) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_weight_ship_rules`
--

CREATE TABLE `shop_weight_ship_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `shipping_class_id` bigint(20) UNSIGNED NOT NULL,
  `zone_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `taxable` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `over_than_kg` decimal(10,2) NOT NULL,
  `over_equal` tinyint(1) NOT NULL,
  `less_than_kg` decimal(10,2) NOT NULL,
  `less_equal` tinyint(1) NOT NULL,
  `base_cost` decimal(10,2) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_weight_ship_rules`
--

INSERT INTO `shop_weight_ship_rules` (`id`, `shop_id`, `shipping_class_id`, `zone_id`, `taxable`, `created_at`, `updated_at`, `over_than_kg`, `over_equal`, `less_than_kg`, `less_equal`, `base_cost`, `active`) VALUES
(1, 2, 1, 1, 0, '2020-08-12 08:55:50', '2020-08-12 08:55:50', '0.00', 1, '0.00', 0, '3.00', 1),
(2, 2, 2, 4, 0, '2020-08-23 08:42:46', '2020-08-23 08:42:46', '0.00', 1, '5.00', 1, '5.00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliers_supplies`
--

CREATE TABLE `suppliers_supplies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `supplier_id` bigint(20) UNSIGNED NOT NULL,
  `product_id` bigint(20) UNSIGNED NOT NULL
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
(1, 'test', '', '', '', 'ANNA', 'KARENINA', '2020-08-03 08:16:17', '2020-08-03 08:16:17'),
(2, 'foo', '', '', 'foo@bar', 'LEV', 'GOGOL', '2020-08-03 08:22:37', '2020-08-03 08:22:37'),
(3, 'richguy', '', '', '', 'ANTON', 'TSEKOF', '2020-08-03 08:22:37', '2020-08-02 08:05:32');

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
-- Indexes for table `bank_transactions`
--
ALTER TABLE `bank_transactions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `billing_address`
--
ALTER TABLE `billing_address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `region_id` (`region_id`);

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
-- Indexes for table `currency_rates`
--
ALTER TABLE `currency_rates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `from_cur` (`from_cur`,`to_cur`);

--
-- Indexes for table `globe_regions`
--
ALTER TABLE `globe_regions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `country_code` (`country_code`,`title`);

--
-- Indexes for table `languages`
--
ALTER TABLE `languages`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `status_id` (`status_id`),
  ADD KEY `pay_method_id` (`pay_method_id`),
  ADD KEY `ship_class_id` (`ship_class_id`),
  ADD KEY `shipping_address_id` (`shipping_address_id`),
  ADD KEY `billing_address_id` (`billing_address_id`);

--
-- Indexes for table `orders_status_history`
--
ALTER TABLE `orders_status_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `order_items_status_history`
--
ALTER TABLE `order_items_status_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `item_id` (`item_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `order_status`
--
ALTER TABLE `order_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment_methods`
--
ALTER TABLE `payment_methods`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

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
  ADD KEY `category_id` (`category_id`),
  ADD KEY `currency_id` (`currency_id`),
  ADD KEY `manufacturer_id` (`manufacturer_id`);

--
-- Indexes for table `products_discounts`
--
ALTER TABLE `products_discounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_attributes_values`
--
ALTER TABLE `product_attributes_values`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `product_id_2` (`product_id`,`attribute_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `attribute_id` (`attribute_id`);

--
-- Indexes for table `product_attribute_units`
--
ALTER TABLE `product_attribute_units`
  ADD PRIMARY KEY (`id`),
  ADD KEY `attribute_id` (`attribute_id`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `product_category_attributes`
--
ALTER TABLE `product_category_attributes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `product_category_id` (`product_category_id`),
  ADD KEY `code` (`code`);

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
  ADD KEY `image_id` (`image_id`);

--
-- Indexes for table `product_reviews`
--
ALTER TABLE `product_reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product_tags`
--
ALTER TABLE `product_tags`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `restrict_payment_parameters`
--
ALTER TABLE `restrict_payment_parameters`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `parameter` (`title`);

--
-- Indexes for table `restrict_payment_rules`
--
ALTER TABLE `restrict_payment_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `restrict_payment_rules_criteria`
--
ALTER TABLE `restrict_payment_rules_criteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rule_id` (`rule_id`),
  ADD KEY `parameter_id` (`parameter_id`),
  ADD KEY `operator_id` (`operator_id`);

--
-- Indexes for table `restrict_payment_rules_operators`
--
ALTER TABLE `restrict_payment_rules_operators`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `rules_disables_methods`
--
ALTER TABLE `rules_disables_methods`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rule_id` (`rule_id`),
  ADD KEY `payment_method_id` (`payment_method_id`);

--
-- Indexes for table `shipping_address`
--
ALTER TABLE `shipping_address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `region_id` (`region_id`);

--
-- Indexes for table `shipping_region_zips`
--
ALTER TABLE `shipping_region_zips`
  ADD PRIMARY KEY (`id`),
  ADD KEY `zone_region_id` (`zone_region_id`);

--
-- Indexes for table `shipping_zip_codes_forbid`
--
ALTER TABLE `shipping_zip_codes_forbid`
  ADD PRIMARY KEY (`id`),
  ADD KEY `region_id` (`region_id`);

--
-- Indexes for table `shipping_zones`
--
ALTER TABLE `shipping_zones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shipping_zones_regions`
--
ALTER TABLE `shipping_zones_regions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `region_id` (`region_id`),
  ADD KEY `zone_id` (`zone_id`);

--
-- Indexes for table `shops`
--
ALTER TABLE `shops`
  ADD PRIMARY KEY (`id`),
  ADD KEY `owner_id` (`owner_id`),
  ADD KEY `default_lang_id` (`default_lang_id`);

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
  ADD PRIMARY KEY (`id`) USING BTREE,
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
-- Indexes for table `shop_courier_classes`
--
ALTER TABLE `shop_courier_classes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_courier_id` (`shop_courier_id`);

--
-- Indexes for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `currency_id` (`currency_id`);

--
-- Indexes for table `shop_disable_zip_codes`
--
ALTER TABLE `shop_disable_zip_codes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_eulas`
--
ALTER TABLE `shop_eulas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `shop_giftwrap`
--
ALTER TABLE `shop_giftwrap`
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
-- Indexes for table `shop_manufacturers`
--
ALTER TABLE `shop_manufacturers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_over_weight_ship_rules`
--
ALTER TABLE `shop_over_weight_ship_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `shipping_class_id` (`shipping_class_id`),
  ADD KEY `zone_id` (`zone_id`);

--
-- Indexes for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `shop_styling`
--
ALTER TABLE `shop_styling`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_suppliers`
--
ALTER TABLE `shop_suppliers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

--
-- Indexes for table `shop_tax_region_rules`
--
ALTER TABLE `shop_tax_region_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `region_id` (`region_id`),
  ADD KEY `product_category_id` (`product_category_id`);

--
-- Indexes for table `shop_tax_rules`
--
ALTER TABLE `shop_tax_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `product_category_id` (`product_category_id`);

--
-- Indexes for table `shop_tax_zipcode_rules`
--
ALTER TABLE `shop_tax_zipcode_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `region_id` (`region_id`),
  ADD KEY `product_category_id` (`product_category_id`);

--
-- Indexes for table `shop_translations`
--
ALTER TABLE `shop_translations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `shop_id_2` (`shop_id`,`code`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `language_id` (`language_id`);

--
-- Indexes for table `shop_weight_cod_rules`
--
ALTER TABLE `shop_weight_cod_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `shipping_class_id` (`shipping_class_id`);

--
-- Indexes for table `shop_weight_ship_rules`
--
ALTER TABLE `shop_weight_ship_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `shipping_class_id` (`shipping_class_id`),
  ADD KEY `zone_id` (`zone_id`);

--
-- Indexes for table `suppliers_supplies`
--
ALTER TABLE `suppliers_supplies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `product_id` (`product_id`);

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
-- AUTO_INCREMENT for table `bank_transactions`
--
ALTER TABLE `bank_transactions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `billing_address`
--
ALTER TABLE `billing_address`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `continents`
--
ALTER TABLE `continents`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `courriers`
--
ALTER TABLE `courriers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `currencies`
--
ALTER TABLE `currencies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `currency_rates`
--
ALTER TABLE `currency_rates`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `globe_regions`
--
ALTER TABLE `globe_regions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `languages`
--
ALTER TABLE `languages`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orders_status_history`
--
ALTER TABLE `orders_status_history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `order_items_status_history`
--
ALTER TABLE `order_items_status_history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `order_status`
--
ALTER TABLE `order_status`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment_methods`
--
ALTER TABLE `payment_methods`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=169;

--
-- AUTO_INCREMENT for table `products_discounts`
--
ALTER TABLE `products_discounts`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_attributes_values`
--
ALTER TABLE `product_attributes_values`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=293;

--
-- AUTO_INCREMENT for table `product_attribute_units`
--
ALTER TABLE `product_attribute_units`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product_categories`
--
ALTER TABLE `product_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product_category_attributes`
--
ALTER TABLE `product_category_attributes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `product_gallery`
--
ALTER TABLE `product_gallery`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product_gallery_tag`
--
ALTER TABLE `product_gallery_tag`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_reviews`
--
ALTER TABLE `product_reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product_tags`
--
ALTER TABLE `product_tags`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `restrict_payment_parameters`
--
ALTER TABLE `restrict_payment_parameters`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `restrict_payment_rules`
--
ALTER TABLE `restrict_payment_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `restrict_payment_rules_criteria`
--
ALTER TABLE `restrict_payment_rules_criteria`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `restrict_payment_rules_operators`
--
ALTER TABLE `restrict_payment_rules_operators`
  MODIFY `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `rules_disables_methods`
--
ALTER TABLE `rules_disables_methods`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shipping_address`
--
ALTER TABLE `shipping_address`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shipping_region_zips`
--
ALTER TABLE `shipping_region_zips`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shipping_zip_codes_forbid`
--
ALTER TABLE `shipping_zip_codes_forbid`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shipping_zones`
--
ALTER TABLE `shipping_zones`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shipping_zones_regions`
--
ALTER TABLE `shipping_zones_regions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shops`
--
ALTER TABLE `shops`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shop_banks`
--
ALTER TABLE `shop_banks`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_belongs_categories`
--
ALTER TABLE `shop_belongs_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shop_categories`
--
ALTER TABLE `shop_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shop_countries`
--
ALTER TABLE `shop_countries`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shop_couriers`
--
ALTER TABLE `shop_couriers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_courier_classes`
--
ALTER TABLE `shop_courier_classes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shop_disable_zip_codes`
--
ALTER TABLE `shop_disable_zip_codes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_eulas`
--
ALTER TABLE `shop_eulas`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_giftwrap`
--
ALTER TABLE `shop_giftwrap`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_languages`
--
ALTER TABLE `shop_languages`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `shop_managers`
--
ALTER TABLE `shop_managers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_manufacturers`
--
ALTER TABLE `shop_manufacturers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `shop_over_weight_ship_rules`
--
ALTER TABLE `shop_over_weight_ship_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_styling`
--
ALTER TABLE `shop_styling`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_suppliers`
--
ALTER TABLE `shop_suppliers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_tax_region_rules`
--
ALTER TABLE `shop_tax_region_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_tax_rules`
--
ALTER TABLE `shop_tax_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_tax_zipcode_rules`
--
ALTER TABLE `shop_tax_zipcode_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_translations`
--
ALTER TABLE `shop_translations`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_weight_cod_rules`
--
ALTER TABLE `shop_weight_cod_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_weight_ship_rules`
--
ALTER TABLE `shop_weight_ship_rules`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `suppliers_supplies`
--
ALTER TABLE `suppliers_supplies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bank_transactions`
--
ALTER TABLE `bank_transactions`
  ADD CONSTRAINT `bank_transactions_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `billing_address`
--
ALTER TABLE `billing_address`
  ADD CONSTRAINT `billing_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `billing_address_ibfk_2` FOREIGN KEY (`id`) REFERENCES `globe_regions` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`),
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`pay_method_id`) REFERENCES `payment_methods` (`id`),
  ADD CONSTRAINT `orders_ibfk_5` FOREIGN KEY (`ship_class_id`) REFERENCES `shop_courier_classes` (`id`),
  ADD CONSTRAINT `orders_ibfk_6` FOREIGN KEY (`shipping_address_id`) REFERENCES `shipping_address` (`id`),
  ADD CONSTRAINT `orders_ibfk_7` FOREIGN KEY (`billing_address_id`) REFERENCES `billing_address` (`id`);

--
-- Constraints for table `orders_status_history`
--
ALTER TABLE `orders_status_history`
  ADD CONSTRAINT `orders_status_history_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `orders_status_history_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`);

--
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `order_items_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`);

--
-- Constraints for table `order_items_status_history`
--
ALTER TABLE `order_items_status_history`
  ADD CONSTRAINT `order_items_status_history_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `order_items` (`id`),
  ADD CONSTRAINT `order_items_status_history_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`);

--
-- Constraints for table `products_discounts`
--
ALTER TABLE `products_discounts`
  ADD CONSTRAINT `products_discounts_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `product_attributes_values`
--
ALTER TABLE `product_attributes_values`
  ADD CONSTRAINT `product_attributes_values_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_attributes_values_ibfk_2` FOREIGN KEY (`attribute_id`) REFERENCES `product_category_attributes` (`id`);

--
-- Constraints for table `product_attribute_units`
--
ALTER TABLE `product_attribute_units`
  ADD CONSTRAINT `product_attribute_units_ibfk_1` FOREIGN KEY (`attribute_id`) REFERENCES `product_category_attributes` (`id`);

--
-- Constraints for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD CONSTRAINT `product_categories_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `product_category_attributes`
--
ALTER TABLE `product_category_attributes`
  ADD CONSTRAINT `product_category_attributes_ibfk_1` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`),
  ADD CONSTRAINT `product_category_attributes_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `product_gallery`
--
ALTER TABLE `product_gallery`
  ADD CONSTRAINT `product_gallery_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `product_gallery_tag`
--
ALTER TABLE `product_gallery_tag`
  ADD CONSTRAINT `product_gallery_tag_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `product_gallery` (`id`);

--
-- Constraints for table `product_reviews`
--
ALTER TABLE `product_reviews`
  ADD CONSTRAINT `product_reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `product_reviews_ibfk_3` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `product_reviews_ibfk_4` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `product_tags`
--
ALTER TABLE `product_tags`
  ADD CONSTRAINT `product_tags_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `restrict_payment_rules`
--
ALTER TABLE `restrict_payment_rules`
  ADD CONSTRAINT `restrict_payment_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `restrict_payment_rules_criteria`
--
ALTER TABLE `restrict_payment_rules_criteria`
  ADD CONSTRAINT `restrict_payment_rules_criteria_ibfk_1` FOREIGN KEY (`rule_id`) REFERENCES `restrict_payment_rules` (`id`),
  ADD CONSTRAINT `restrict_payment_rules_criteria_ibfk_2` FOREIGN KEY (`parameter_id`) REFERENCES `restrict_payment_parameters` (`id`),
  ADD CONSTRAINT `restrict_payment_rules_criteria_ibfk_3` FOREIGN KEY (`operator_id`) REFERENCES `restrict_payment_rules_operators` (`id`);

--
-- Constraints for table `rules_disables_methods`
--
ALTER TABLE `rules_disables_methods`
  ADD CONSTRAINT `rules_disables_methods_ibfk_1` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_methods` (`id`),
  ADD CONSTRAINT `rules_disables_methods_ibfk_2` FOREIGN KEY (`rule_id`) REFERENCES `restrict_payment_rules` (`id`);

--
-- Constraints for table `shipping_address`
--
ALTER TABLE `shipping_address`
  ADD CONSTRAINT `shipping_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `shipping_address_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`);

--
-- Constraints for table `shipping_region_zips`
--
ALTER TABLE `shipping_region_zips`
  ADD CONSTRAINT `shipping_region_zips_ibfk_1` FOREIGN KEY (`zone_region_id`) REFERENCES `shipping_zones_regions` (`id`);

--
-- Constraints for table `shipping_zip_codes_forbid`
--
ALTER TABLE `shipping_zip_codes_forbid`
  ADD CONSTRAINT `shipping_zip_codes_forbid_ibfk_1` FOREIGN KEY (`region_id`) REFERENCES `shipping_zones_regions` (`id`);

--
-- Constraints for table `shipping_zones`
--
ALTER TABLE `shipping_zones`
  ADD CONSTRAINT `shipping_zones_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shipping_zones_regions`
--
ALTER TABLE `shipping_zones_regions`
  ADD CONSTRAINT `shipping_zones_regions_ibfk_1` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`),
  ADD CONSTRAINT `shipping_zones_regions_ibfk_2` FOREIGN KEY (`zone_id`) REFERENCES `shipping_zones` (`id`);

--
-- Constraints for table `shops`
--
ALTER TABLE `shops`
  ADD CONSTRAINT `shops_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `shops_ibfk_2` FOREIGN KEY (`default_lang_id`) REFERENCES `languages` (`id`);

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
-- Constraints for table `shop_courier_classes`
--
ALTER TABLE `shop_courier_classes`
  ADD CONSTRAINT `shop_courier_classes_ibfk_1` FOREIGN KEY (`shop_courier_id`) REFERENCES `shop_couriers` (`id`);

--
-- Constraints for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  ADD CONSTRAINT `shop_currencies_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_currencies_ibfk_2` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`);

--
-- Constraints for table `shop_disable_zip_codes`
--
ALTER TABLE `shop_disable_zip_codes`
  ADD CONSTRAINT `shop_disable_zip_codes_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_eulas`
--
ALTER TABLE `shop_eulas`
  ADD CONSTRAINT `shop_eulas_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_eulas_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `shop_giftwrap`
--
ALTER TABLE `shop_giftwrap`
  ADD CONSTRAINT `shop_giftwrap_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

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
-- Constraints for table `shop_manufacturers`
--
ALTER TABLE `shop_manufacturers`
  ADD CONSTRAINT `shop_manufacturers_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_over_weight_ship_rules`
--
ALTER TABLE `shop_over_weight_ship_rules`
  ADD CONSTRAINT `shop_over_weight_ship_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_over_weight_ship_rules_ibfk_2` FOREIGN KEY (`shipping_class_id`) REFERENCES `shop_courier_classes` (`id`),
  ADD CONSTRAINT `shop_over_weight_ship_rules_ibfk_3` FOREIGN KEY (`zone_id`) REFERENCES `shipping_zones` (`id`);

--
-- Constraints for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  ADD CONSTRAINT `shop_reviews_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `shop_styling`
--
ALTER TABLE `shop_styling`
  ADD CONSTRAINT `shop_styling_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_suppliers`
--
ALTER TABLE `shop_suppliers`
  ADD CONSTRAINT `shop_suppliers_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_tax_region_rules`
--
ALTER TABLE `shop_tax_region_rules`
  ADD CONSTRAINT `shop_tax_region_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_tax_region_rules_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`),
  ADD CONSTRAINT `shop_tax_region_rules_ibfk_4` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `shop_tax_rules`
--
ALTER TABLE `shop_tax_rules`
  ADD CONSTRAINT `shop_tax_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_tax_rules_ibfk_3` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `shop_tax_zipcode_rules`
--
ALTER TABLE `shop_tax_zipcode_rules`
  ADD CONSTRAINT `shop_tax_zipcode_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_tax_zipcode_rules_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`),
  ADD CONSTRAINT `shop_tax_zipcode_rules_ibfk_4` FOREIGN KEY (`product_category_id`) REFERENCES `product_categories` (`id`);

--
-- Constraints for table `shop_translations`
--
ALTER TABLE `shop_translations`
  ADD CONSTRAINT `shop_translations_ibfk_1` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`),
  ADD CONSTRAINT `shop_translations_ibfk_2` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_weight_cod_rules`
--
ALTER TABLE `shop_weight_cod_rules`
  ADD CONSTRAINT `shop_weight_cod_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_weight_cod_rules_ibfk_2` FOREIGN KEY (`shipping_class_id`) REFERENCES `shop_courier_classes` (`id`);

--
-- Constraints for table `shop_weight_ship_rules`
--
ALTER TABLE `shop_weight_ship_rules`
  ADD CONSTRAINT `shop_weight_ship_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_weight_ship_rules_ibfk_2` FOREIGN KEY (`shipping_class_id`) REFERENCES `shop_courier_classes` (`id`),
  ADD CONSTRAINT `shop_weight_ship_rules_ibfk_3` FOREIGN KEY (`zone_id`) REFERENCES `shipping_zones` (`id`);

--
-- Constraints for table `suppliers_supplies`
--
ALTER TABLE `suppliers_supplies`
  ADD CONSTRAINT `suppliers_supplies_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `suppliers_supplies_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `shop_suppliers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
