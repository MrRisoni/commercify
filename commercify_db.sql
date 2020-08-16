-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 16, 2020 at 05:47 PM
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
-- Database: `commercify`
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

--
-- Dumping data for table `globe_regions`
--

INSERT INTO `globe_regions` (`id`, `country_code`, `title`) VALUES
(1, 'GR', 'Νησία Ιονίου'),
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
  `total` decimal(10,2) NOT NULL,
  `net` decimal(10,2) NOT NULL,
  `tax` decimal(10,2) NOT NULL,
  `shipping` decimal(10,2) NOT NULL,
  `success` tinyint(1) NOT NULL,
  `void` tinyint(1) NOT NULL,
  `refund` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `net_price` decimal(10,2) NOT NULL,
  `taxes` decimal(10,2) NOT NULL,
  `gift_cost` decimal(10,2) NOT NULL,
  `success` tinyint(1) NOT NULL,
  `void` tinyint(1) NOT NULL,
  `refund` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE `order_status` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `payment_methods`
--

CREATE TABLE `payment_methods` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `payment_methods`
--

INSERT INTO `payment_methods` (`id`, `title`) VALUES
(1, 'Cash on delivery'),
(2, 'Credit Card'),
(3, 'Bank transfer'),
(4, 'Paypal'),
(5, 'Western Union');

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
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `category_id` bigint(20) UNSIGNED NOT NULL,
  `currency_id` bigint(20) UNSIGNED NOT NULL,
  `manufacturer_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `code` varchar(120) NOT NULL,
  `SKU` varchar(50) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `thumbnail_url` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `normal_price` decimal(10,2) NOT NULL,
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
  `disable_cod` tinyint(1) NOT NULL,
  `gift_wrap_cost` decimal(10,2) NOT NULL,
  `visible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `shop_id`, `category_id`, `currency_id`, `manufacturer_id`, `code`, `SKU`, `img_url`, `thumbnail_url`, `price`, `normal_price`, `discount_percent`, `kilos`, `dim_l`, `dim_w`, `dim_h`, `active`, `stock`, `created`, `updated`, `taxable`, `disable_cod`, `gift_wrap_cost`, `visible`) VALUES
(1, 1, 1, 1, 1, 'SelmaLagerlefNielsHolgresson', '', '', '', '15.00', '0.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0, '0.00', 0),
(2, 1, 1, 1, 1, 'StringbergDromspel', '', '', '', '15.00', '0.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0, '0.00', 0),
(3, 1, 1, 1, 1, 'StringbergDamaskusI\r\n', '', '', '', '15.00', '0.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0, '0.00', 0),
(4, 1, 1, 1, 1, 'StringbergDamaskusII\r\n', '', '', '', '15.00', '0.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0, '0.00', 0),
(5, 1, 1, 1, 1, 'StringbergDamaskusIII\r\n', '', '', '', '15.00', '0.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0, '0.00', 0),
(6, 1, 1, 1, 1, 'StringbergSpoksSonaten\r\n', '', '', '', '15.00', '0.00', '0.00', '0.34', '25.00', '10.00', '8.00', 1, 15, '2020-08-03 11:52:31', '2020-08-03 11:52:31', 0, 0, '0.00', 0);

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
(4, 1, 0, 'Shoes');

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

--
-- Dumping data for table `product_description`
--

INSERT INTO `product_description` (`id`, `product_id`, `language_id`, `title`, `descr`) VALUES
(1, 1, 1, 'The wonderful journey of Niels Holgersson over Sweden', ''),
(2, 2, 1, 'Dreamplay', ''),
(3, 3, 1, 'Προς Δαμασκόν Ι', ''),
(4, 4, 1, 'Προς Δαμασκόν ΙΙ', ''),
(5, 5, 1, 'Προς Δαμασκόν ΙΙΙ', ''),
(6, 6, 1, 'Η Σονάτα των Φαντασμάτων', '');

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
(1, 1, 1, 2, '3.0', '', '2020-08-04 08:54:22');

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
  `default_lang_id` bigint(20) UNSIGNED NOT NULL DEFAULT 1,
  `created` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shops`
--

INSERT INTO `shops` (`id`, `title`, `owner_id`, `default_lang_id`, `created`) VALUES
(1, 'vivliofagos', 1, 1, '2020-08-03 08:22:05');

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
  `active` tinyint(1) NOT NULL,
  `cod_base_cost` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `shop_courier_classes`
--

INSERT INTO `shop_courier_classes` (`id`, `shop_courier_id`, `title`, `active`, `cod_base_cost`) VALUES
(1, 1, 'Standard', 1, '5.00'),
(2, 1, 'Express', 1, '10.00');

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
(1, 1, 'Asus');

-- --------------------------------------------------------

--
-- Table structure for table `shop_product_cateogory_taxes`
--

CREATE TABLE `shop_product_cateogory_taxes` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_category_id` bigint(20) UNSIGNED NOT NULL,
  `tax_code_id` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for table `shop_shipping_classes_regions`
--

CREATE TABLE `shop_shipping_classes_regions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `ship_class_id` bigint(20) UNSIGNED NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL,
  `active` tinyint(1) NOT NULL
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
-- Table structure for table `shop_tax_code_names`
--

CREATE TABLE `shop_tax_code_names` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `code` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_tax_region_rules`
--

CREATE TABLE `shop_tax_region_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `tax_code_id` bigint(20) UNSIGNED NOT NULL,
  `region_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(52) NOT NULL,
  `country_code` varchar(3) NOT NULL,
  `flat_cost` decimal(10,2) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_tax_rules`
--

CREATE TABLE `shop_tax_rules` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `tax_code_id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(52) NOT NULL,
  `country_code` varchar(3) NOT NULL,
  `flat_cost` decimal(10,2) NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `created` datetime NOT NULL,
  `updated` datetime DEFAULT NULL,
  `active` tinyint(1) NOT NULL
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
  `title` varchar(52) NOT NULL,
  `taxable` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `less_than_kg` decimal(10,2) NOT NULL,
  `less_equal` tinyint(1) NOT NULL,
  `over_than_kg` decimal(10,2) NOT NULL,
  `over_equal` tinyint(1) NOT NULL,
  `base_cost` decimal(10,2) NOT NULL,
  `charge` decimal(10,2) NOT NULL,
  `over_total_weight` decimal(10,2) NOT NULL,
  `for_each_kg` decimal(10,2) NOT NULL,
  `active` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Indexes for table `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`),
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
  ADD KEY `currency_id` (`currency_id`),
  ADD KEY `manufacturer_id` (`manufacturer_id`);

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
  ADD UNIQUE KEY `title` (`title`),
  ADD KEY `shop_id` (`shop_id`);

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
  ADD KEY `user_id` (`user_id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `product_id` (`product_id`);

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
-- Indexes for table `shop_product_cateogory_taxes`
--
ALTER TABLE `shop_product_cateogory_taxes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `shop_category_id` (`shop_category_id`,`tax_code_id`),
  ADD KEY `tax_code_id` (`tax_code_id`);

--
-- Indexes for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `shop_shipping_classes_regions`
--
ALTER TABLE `shop_shipping_classes_regions`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ship_class_id` (`ship_class_id`),
  ADD KEY `region_id` (`region_id`);

--
-- Indexes for table `shop_ship_zones`
--
ALTER TABLE `shop_ship_zones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`);

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
-- Indexes for table `shop_tax_code_names`
--
ALTER TABLE `shop_tax_code_names`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `shop_id` (`shop_id`,`code`);

--
-- Indexes for table `shop_tax_region_rules`
--
ALTER TABLE `shop_tax_region_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `region_id` (`region_id`),
  ADD KEY `tax_code_id` (`tax_code_id`);

--
-- Indexes for table `shop_tax_rules`
--
ALTER TABLE `shop_tax_rules`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shop_id` (`shop_id`),
  ADD KEY `tax_code_id` (`tax_code_id`);

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
  ADD KEY `shipping_class_id` (`shipping_class_id`);

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
-- AUTO_INCREMENT for table `billing_address`
--
ALTER TABLE `billing_address`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

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
-- AUTO_INCREMENT for table `globe_regions`
--
ALTER TABLE `globe_regions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `languages`
--
ALTER TABLE `languages`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_status`
--
ALTER TABLE `order_status`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `payment_methods`
--
ALTER TABLE `payment_methods`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `product_attributes`
--
ALTER TABLE `product_attributes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product_categories`
--
ALTER TABLE `product_categories`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product_description`
--
ALTER TABLE `product_description`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_managers`
--
ALTER TABLE `shop_managers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_manufacturers`
--
ALTER TABLE `shop_manufacturers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `shop_product_cateogory_taxes`
--
ALTER TABLE `shop_product_cateogory_taxes`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_shipping_classes_regions`
--
ALTER TABLE `shop_shipping_classes_regions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_ship_zones`
--
ALTER TABLE `shop_ship_zones`
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
-- AUTO_INCREMENT for table `shop_tax_code_names`
--
ALTER TABLE `shop_tax_code_names`
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
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `suppliers_supplies`
--
ALTER TABLE `suppliers_supplies`
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
-- Constraints for table `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `order_items_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_items_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `order_items_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `order_status` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`),
  ADD CONSTRAINT `products_ibfk_3` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`),
  ADD CONSTRAINT `products_ibfk_4` FOREIGN KEY (`manufacturer_id`) REFERENCES `shop_manufacturers` (`id`);

--
-- Constraints for table `product_attributes`
--
ALTER TABLE `product_attributes`
  ADD CONSTRAINT `product_attributes_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `product_attributes_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`);

--
-- Constraints for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD CONSTRAINT `product_categories_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

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
  ADD CONSTRAINT `product_reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `product_reviews_ibfk_3` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `product_reviews_ibfk_4` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

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
-- Constraints for table `shop_product_cateogory_taxes`
--
ALTER TABLE `shop_product_cateogory_taxes`
  ADD CONSTRAINT `shop_product_cateogory_taxes_ibfk_1` FOREIGN KEY (`shop_category_id`) REFERENCES `shop_belongs_categories` (`id`),
  ADD CONSTRAINT `shop_product_cateogory_taxes_ibfk_2` FOREIGN KEY (`tax_code_id`) REFERENCES `shop_tax_code_names` (`id`);

--
-- Constraints for table `shop_reviews`
--
ALTER TABLE `shop_reviews`
  ADD CONSTRAINT `shop_reviews_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_reviews_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `shop_shipping_classes_regions`
--
ALTER TABLE `shop_shipping_classes_regions`
  ADD CONSTRAINT `shop_shipping_classes_regions_ibfk_1` FOREIGN KEY (`ship_class_id`) REFERENCES `shop_courier_classes` (`id`),
  ADD CONSTRAINT `shop_shipping_classes_regions_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`);

--
-- Constraints for table `shop_ship_zones`
--
ALTER TABLE `shop_ship_zones`
  ADD CONSTRAINT `shop_ship_zones_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

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
-- Constraints for table `shop_tax_code_names`
--
ALTER TABLE `shop_tax_code_names`
  ADD CONSTRAINT `shop_tax_code_names_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`);

--
-- Constraints for table `shop_tax_region_rules`
--
ALTER TABLE `shop_tax_region_rules`
  ADD CONSTRAINT `shop_tax_region_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_tax_region_rules_ibfk_2` FOREIGN KEY (`region_id`) REFERENCES `globe_regions` (`id`),
  ADD CONSTRAINT `shop_tax_region_rules_ibfk_3` FOREIGN KEY (`tax_code_id`) REFERENCES `shop_tax_code_names` (`id`);

--
-- Constraints for table `shop_tax_rules`
--
ALTER TABLE `shop_tax_rules`
  ADD CONSTRAINT `shop_tax_rules_ibfk_1` FOREIGN KEY (`shop_id`) REFERENCES `shops` (`id`),
  ADD CONSTRAINT `shop_tax_rules_ibfk_2` FOREIGN KEY (`tax_code_id`) REFERENCES `shop_tax_code_names` (`id`);

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
  ADD CONSTRAINT `shop_weight_ship_rules_ibfk_2` FOREIGN KEY (`shipping_class_id`) REFERENCES `shop_courier_classes` (`id`);

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
