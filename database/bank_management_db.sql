-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 27, 2026 at 04:10 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank_management_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `account_number` varchar(255) NOT NULL,
  `account_type` enum('BUSINESS','CURRENT','SAVINGS') NOT NULL,
  `balance` decimal(15,2) NOT NULL,
  `opening_date` date NOT NULL,
  `client_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `account_number`, `account_type`, `balance`, `opening_date`, `client_id`) VALUES
(1, 'RS1001', 'SAVINGS', '150000.00', '2026-06-23', 1),
(2, 'RS1002', 'CURRENT', '85000.00', '2026-06-23', 1),
(3, 'RS1003', 'BUSINESS', '220000.00', '2026-06-23', 2),
(4, 'RS1004', 'SAVINGS', '50000.00', '2026-06-23', 3),
(5, 'RS1005', 'CURRENT', '120000.00', '2026-06-23', 3);

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `address`, `name`) VALUES
(1, 'Belgrade, Serbia', 'UniCredit Bank');

-- --------------------------------------------------------

--
-- Table structure for table `card`
--

CREATE TABLE `card` (
  `id` bigint(20) NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `card_type` enum('CREDIT','DEBIT') NOT NULL,
  `expiry_date` date NOT NULL,
  `client_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `card`
--

INSERT INTO `card` (`id`, `card_number`, `card_type`, `expiry_date`, `client_id`) VALUES
(1, '1111222233334444', 'DEBIT', '2029-12-31', 1),
(2, '5555666677778888', 'CREDIT', '2028-06-30', 2),
(3, '9999000011112222', 'DEBIT', '2030-03-31', 3);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `id` bigint(20) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `bank_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `date_of_birth`, `email`, `first_name`, `last_name`, `bank_id`) VALUES
(1, '2003-05-15', 'nikola@gmail.com', 'Nikola', 'Jovanovic', 1),
(2, '1998-08-20', 'marko@gmail.com', 'Marko', 'Petrovic', 1),
(3, '1995-03-10', 'ana@gmail.com', 'Ana', 'Ilic', 1);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `salary` decimal(15,2) NOT NULL,
  `bank_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `first_name`, `last_name`, `salary`, `bank_id`) VALUES
(1, 'Petar', 'Petrovic', '120000.00', 1),
(2, 'Milan', 'Nikolic', '110000.00', 1),
(3, 'Jelena', 'Jovic', '80000.00', 1),
(4, 'Ivana', 'Markovic', '85000.00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `loan`
--

CREATE TABLE `loan` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `interest_rate` decimal(5,2) NOT NULL,
  `loan_status` enum('ACTIVE','DEFAULTED','PAID_OFF') NOT NULL,
  `client_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan`
--

INSERT INTO `loan` (`id`, `amount`, `interest_rate`, `loan_status`, `client_id`) VALUES
(1, '500000.00', '5.50', 'ACTIVE', 1),
(2, '300000.00', '6.20', 'ACTIVE', 2),
(3, '800000.00', '4.90', 'PAID_OFF', 3);

-- --------------------------------------------------------

--
-- Table structure for table `loan_approval`
--

CREATE TABLE `loan_approval` (
  `id` bigint(20) NOT NULL,
  `approval_date` date NOT NULL,
  `approval_status` enum('APPROVED','PENDING','REJECTED') NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `loan_id` bigint(20) NOT NULL,
  `manager_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `loan_approval`
--

INSERT INTO `loan_approval` (`id`, `approval_date`, `approval_status`, `comment`, `loan_id`, `manager_id`) VALUES
(1, '2026-06-23', 'APPROVED', 'Approved', 1, 1),
(2, '2026-06-23', 'PENDING', 'Waiting', 2, 2),
(3, '2026-06-23', 'APPROVED', 'Approved with low risk', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

CREATE TABLE `manager` (
  `bonus` decimal(15,2) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`bonus`, `id`) VALUES
('30000.00', 1),
('25000.00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `teller`
--

CREATE TABLE `teller` (
  `counter_number` int(11) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teller`
--

INSERT INTO `teller` (`counter_number`, `id`) VALUES
(1, 3),
(2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `transaction_date` date NOT NULL,
  `transaction_type` enum('DEPOSIT','TRANSFER','WITHDRAWAL') NOT NULL,
  `account_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `amount`, `description`, `transaction_date`, `transaction_type`, `account_id`) VALUES
(1, '5000.00', 'Salary payment', '2026-06-23', 'DEPOSIT', 1),
(2, '2000.00', 'ATM withdrawal', '2026-06-23', 'WITHDRAWAL', 1),
(3, '10000.00', 'Transfer to savings', '2026-06-23', 'TRANSFER', 2),
(4, '1500.00', 'Cash deposit', '2026-06-23', 'DEPOSIT', 3),
(5, '3000.00', 'Shopping', '2026-06-23', 'WITHDRAWAL', 3),
(6, '7000.00', 'Monthly transfer', '2026-06-23', 'TRANSFER', 4),
(7, '4000.00', 'Bonus payment', '2026-06-23', 'DEPOSIT', 5),
(8, '2500.00', 'Bills payment', '2026-06-23', 'WITHDRAWAL', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK66gkcp94endmotfwb8r4ocxm9` (`account_number`),
  ADD KEY `FKkm8yb63h4ownvnlrbwnadntyn` (`client_id`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKby1nk98m2hq5onhl68bo09sc1` (`card_number`),
  ADD KEY `FKnvnei204yqg1f980ne5doh3y8` (`client_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbfgjs3fem0hmjhvih80158x29` (`email`),
  ADD KEY `FKim95abd01ot21q2dn9mpxo7nc` (`bank_id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKicptso0qh4vmwt76bgf5hm9p6` (`bank_id`);

--
-- Indexes for table `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK62s5k229ouak16t2k5pvq4n16` (`client_id`);

--
-- Indexes for table `loan_approval`
--
ALTER TABLE `loan_approval`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK4i003lc87l1qvxy5ge9s74u4g` (`loan_id`),
  ADD KEY `FKg53a2km96i4sry4tt5kruniyd` (`manager_id`);

--
-- Indexes for table `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teller`
--
ALTER TABLE `teller`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6g20fcr3bhr6bihgy24rq1r1b` (`account_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `card`
--
ALTER TABLE `card`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `loan`
--
ALTER TABLE `loan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `loan_approval`
--
ALTER TABLE `loan_approval`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FKkm8yb63h4ownvnlrbwnadntyn` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `FKnvnei204yqg1f980ne5doh3y8` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKim95abd01ot21q2dn9mpxo7nc` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FKicptso0qh4vmwt76bgf5hm9p6` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`id`);

--
-- Constraints for table `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `FK62s5k229ouak16t2k5pvq4n16` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `loan_approval`
--
ALTER TABLE `loan_approval`
  ADD CONSTRAINT `FK4i003lc87l1qvxy5ge9s74u4g` FOREIGN KEY (`loan_id`) REFERENCES `loan` (`id`),
  ADD CONSTRAINT `FKg53a2km96i4sry4tt5kruniyd` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`);

--
-- Constraints for table `manager`
--
ALTER TABLE `manager`
  ADD CONSTRAINT `FKobm0udnqxv4jiddvi5oykkgdr` FOREIGN KEY (`id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `teller`
--
ALTER TABLE `teller`
  ADD CONSTRAINT `FKgrvluoc520i07if27jrvo6mu6` FOREIGN KEY (`id`) REFERENCES `employee` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK6g20fcr3bhr6bihgy24rq1r1b` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
