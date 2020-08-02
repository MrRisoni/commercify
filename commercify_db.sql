-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 02, 2020 at 08:38 AM
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
-- Table structure for table `currencies`
--

CREATE TABLE `currencies` (
  `cur_id` bigint(10) UNSIGNED NOT NULL,
  `cur_title` varchar(55) NOT NULL,
  `cur_symbol` varchar(5) NOT NULL,
  `cur_rate` decimal(6,2) UNSIGNED DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `languages`
--

CREATE TABLE `languages` (
  `lan_id` bigint(10) UNSIGNED NOT NULL,
  `lan_title` varchar(55) NOT NULL,
  `lan_code` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `title` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `title`, `created_at`) VALUES
(2, 'V1_create_reviews', '2020-08-01 20:35:56'),
(3, 'V2_create_shop_categories', '2020-08-01 20:36:56'),
(6, 'V3_create_shop_categories_children', '2020-08-01 20:46:26'),
(7, 'V5_create_user_roles', '2020-08-01 20:49:11'),
(8, 'V4_create_shop_managers', '2020-08-01 20:49:11'),
(9, 'V6_create_languages', '2020-08-01 20:51:24'),
(12, 'V7_create_shop_languages', '2020-08-02 11:18:20'),
(13, 'V8_create_currencies', '2020-08-02 11:22:20'),
(14, 'V9_shop_currencies', '2020-08-02 11:23:17'),
(16, 'V10_create_products', '2020-08-02 11:35:49'),
(17, 'V11_create_products_tags', '2020-08-02 11:38:21');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `pro_id` bigint(10) UNSIGNED NOT NULL,
  `pro_shop_id` bigint(10) UNSIGNED NOT NULL,
  `pro_category_id` bigint(10) UNSIGNED NOT NULL,
  `pro_currncy_id` bigint(10) UNSIGNED NOT NULL,
  `pro_code` varchar(65) NOT NULL,
  `pro_title` varchar(85) NOT NULL,
  `pro_price` decimal(10,2) UNSIGNED DEFAULT 0.00,
  `pro_discount_percent` decimal(5,2) UNSIGNED DEFAULT 0.00,
  `pro_kilos` decimal(5,2) UNSIGNED DEFAULT 0.00,
  `pro_length` decimal(6,2) UNSIGNED DEFAULT 0.00 COMMENT 'in cm',
  `pro_width` decimal(6,2) UNSIGNED DEFAULT 0.00 COMMENT 'in cm',
  `pro_heigth` decimal(6,2) UNSIGNED DEFAULT 0.00 COMMENT 'in cm',
  `pro_active` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `pro_stock` bigint(10) UNSIGNED NOT NULL,
  `pro_created_at` datetime NOT NULL,
  `pro_updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_categories`
--

CREATE TABLE `product_categories` (
  `prc_id` bigint(10) UNSIGNED NOT NULL,
  `prc_title` varchar(65) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product_tags`
--

CREATE TABLE `product_tags` (
  `prtg_id` bigint(10) UNSIGNED NOT NULL,
  `prtg_product_id` bigint(10) UNSIGNED NOT NULL,
  `prtg_tag` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `rew_id` bigint(10) UNSIGNED NOT NULL,
  `rew_user_id` bigint(10) UNSIGNED NOT NULL,
  `rew_shops_id` bigint(10) UNSIGNED NOT NULL,
  `rew_created_at` datetime NOT NULL,
  `rew_stars` decimal(2,1) UNSIGNED DEFAULT 0.0,
  `rew_comments` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shops`
--

CREATE TABLE `shops` (
  `shop_id` bigint(20) UNSIGNED NOT NULL,
  `shop_title` bigint(20) UNSIGNED NOT NULL,
  `shop_owner_id` bigint(20) UNSIGNED NOT NULL,
  `shop_created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_belongs_categories`
--

CREATE TABLE `shop_belongs_categories` (
  `sht_id` bigint(10) UNSIGNED NOT NULL,
  `sht_shop_id` bigint(10) UNSIGNED NOT NULL,
  `sht_category_id` bigint(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_categories`
--

CREATE TABLE `shop_categories` (
  `cat_id` bigint(10) UNSIGNED NOT NULL,
  `cat_title` varchar(65) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_currencies`
--

CREATE TABLE `shop_currencies` (
  `shcr_id` bigint(10) UNSIGNED NOT NULL,
  `shcr_shop_id` bigint(10) UNSIGNED NOT NULL,
  `shcr_currency_id` bigint(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_languages`
--

CREATE TABLE `shop_languages` (
  `shl_id` bigint(10) UNSIGNED NOT NULL,
  `shl_shop_id` bigint(10) UNSIGNED NOT NULL,
  `shl_language_id` bigint(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `shop_managers`
--

CREATE TABLE `shop_managers` (
  `shm_id` bigint(10) UNSIGNED NOT NULL,
  `shm_shop_id` bigint(10) UNSIGNED NOT NULL,
  `shm_manager_id` bigint(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `usr_id` bigint(20) UNSIGNED NOT NULL,
  `usr_email` varchar(55) NOT NULL,
  `usr_created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `rol_id` bigint(10) UNSIGNED NOT NULL,
  `rol_title` varchar(35) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `currencies`
--
ALTER TABLE `currencies`
  ADD PRIMARY KEY (`cur_id`);

--
-- Indexes for table `languages`
--
ALTER TABLE `languages`
  ADD PRIMARY KEY (`lan_id`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `title` (`title`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`pro_id`);

--
-- Indexes for table `product_categories`
--
ALTER TABLE `product_categories`
  ADD PRIMARY KEY (`prc_id`);

--
-- Indexes for table `product_tags`
--
ALTER TABLE `product_tags`
  ADD PRIMARY KEY (`prtg_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`rew_id`);

--
-- Indexes for table `shops`
--
ALTER TABLE `shops`
  ADD PRIMARY KEY (`shop_id`),
  ADD KEY `shop_owner_id` (`shop_owner_id`);

--
-- Indexes for table `shop_belongs_categories`
--
ALTER TABLE `shop_belongs_categories`
  ADD PRIMARY KEY (`sht_id`);

--
-- Indexes for table `shop_categories`
--
ALTER TABLE `shop_categories`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indexes for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  ADD PRIMARY KEY (`shcr_id`);

--
-- Indexes for table `shop_languages`
--
ALTER TABLE `shop_languages`
  ADD PRIMARY KEY (`shl_id`);

--
-- Indexes for table `shop_managers`
--
ALTER TABLE `shop_managers`
  ADD PRIMARY KEY (`shm_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`usr_id`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`rol_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `currencies`
--
ALTER TABLE `currencies`
  MODIFY `cur_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `languages`
--
ALTER TABLE `languages`
  MODIFY `lan_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `pro_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_categories`
--
ALTER TABLE `product_categories`
  MODIFY `prc_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product_tags`
--
ALTER TABLE `product_tags`
  MODIFY `prtg_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `rew_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shops`
--
ALTER TABLE `shops`
  MODIFY `shop_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_belongs_categories`
--
ALTER TABLE `shop_belongs_categories`
  MODIFY `sht_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_categories`
--
ALTER TABLE `shop_categories`
  MODIFY `cat_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_currencies`
--
ALTER TABLE `shop_currencies`
  MODIFY `shcr_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_languages`
--
ALTER TABLE `shop_languages`
  MODIFY `shl_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `shop_managers`
--
ALTER TABLE `shop_managers`
  MODIFY `shm_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `usr_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `rol_id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `shops`
--
ALTER TABLE `shops`
  ADD CONSTRAINT `shops_ibfk_1` FOREIGN KEY (`shop_owner_id`) REFERENCES `users` (`usr_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
