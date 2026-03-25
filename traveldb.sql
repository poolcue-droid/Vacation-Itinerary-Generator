-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 25, 2026 at 04:45 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `traveldb`
--

-- --------------------------------------------------------

--
-- Table structure for table `activities`
--

CREATE TABLE `activities` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `image` varchar(100) DEFAULT 'default_activity.jpg',
  `type` varchar(50) DEFAULT 'General'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `activities`
--

INSERT INTO `activities` (`id`, `name`, `location`, `cost`, `image`, `type`) VALUES
(1, 'Houseboat Ride', 'Kerala', 3000, 'houseboat_ride.jpg', 'Adventure'),
(2, 'Kathakali Show', 'Kerala', 500, 'kathakali_show.jpg', 'Culture'),
(3, 'Beach Visit', 'Kerala', 0, 'beach_visit.jpg', 'Relaxation'),
(4, 'Ayurvedic Spa', 'Kerala', 2000, 'ayurvedic_spa.jpg', 'Relaxation'),
(5, 'Food Tour', 'Delhi', 1500, 'food_tour.jpg', 'Culture'),
(6, 'Red Fort Visit', 'Delhi', 500, 'red_fort_visit.jpg', 'Culture'),
(7, 'India Gate Visit', 'Delhi', 0, 'india_gate_visit.jpg', 'Culture'),
(8, 'Museum Tour', 'Delhi', 300, 'museum_tour.jpg', 'Culture'),
(9, 'Paragliding', 'Manali', 2500, 'paragliding.jpg', 'Adventure'),
(10, 'River Rafting', 'Manali', 1800, 'river_rafting.jpg', 'Adventure'),
(11, 'Solang Valley Visit', 'Manali', 0, 'solang_valley_visit.jpg', 'Relaxation'),
(12, 'Trekking', 'Manali', 1200, 'trekking.jpg', 'Adventure');

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `image` varchar(100) DEFAULT 'default_hotel.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `location`, `price`, `image`) VALUES
(1, 'Leela Kovalam', 'Kerala', 9000, 'leela_kovalam.jpg'),
(2, 'Backwater Stay', 'Kerala', 2500, 'backwater_stay.jpg'),
(3, 'Green Palace', 'Kerala', 4000, 'green_palace.jpg'),
(4, 'Palm Resort', 'Kerala', 3500, 'palm_resort.jpg'),
(5, 'Taj Palace', 'Delhi', 8000, 'taj_palace.jpg'),
(6, 'Bloomrooms', 'Delhi', 3000, 'bloomrooms.jpg'),
(7, 'Hotel City Star', 'Delhi', 2500, 'hotel_city_star.jpg'),
(8, 'The Park Hotel', 'Delhi', 6000, 'the_park_hotel.jpg'),
(9, 'Snow Valley Resort', 'Manali', 5000, 'snow_valley_resort.jpg'),
(10, 'Mountain Inn', 'Manali', 2800, 'mountain_inn.jpg'),
(11, 'Hill View Stay', 'Manali', 2000, 'hill_view_stay.jpg'),
(12, 'Apple Resort', 'Manali', 4500, 'apple_resort.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activities`
--
ALTER TABLE `activities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
