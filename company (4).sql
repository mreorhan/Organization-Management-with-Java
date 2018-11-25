-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 25 Kas 2018, 21:27:08
-- Sunucu sürümü: 5.7.19
-- PHP Sürümü: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `company`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `balancesheet`
--

DROP TABLE IF EXISTS `balancesheet`;
CREATE TABLE IF NOT EXISTS `balancesheet` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `createdBy` int(255) NOT NULL,
  `income` decimal(65,0) NOT NULL,
  `expense` decimal(65,0) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `balancesheet`
--

INSERT INTO `balancesheet` (`id`, `createdBy`, `income`, `expense`, `date`, `description`) VALUES
(1, 45, '120000', '11000', '2018-01-17', 'Employee Salary and Marketing'),
(2, 45, '170000', '90000', '2018-02-17', 'Employee Salary and Marketing'),
(3, 45, '113456', '85000', '2018-03-23', 'Employee Salary and Marketing'),
(4, 45, '162750', '120000', '2018-04-14', 'Employee Salary and Marketing'),
(5, 45, '120000', '70000', '2018-01-17', 'Employee Salary and Marketing'),
(6, 45, '170000', '90000', '2018-02-17', 'Employee Salary and Marketing'),
(7, 45, '113456', '85000', '2018-03-23', 'Employee Salary and Marketing'),
(8, 45, '162750', '120000', '2018-04-14', 'Employee Salary and Marketing'),
(9, 45, '120000', '70000', '2018-01-17', 'Employee Salary and Marketing'),
(10, 45, '170000', '90000', '2018-02-17', 'Employee Salary and Marketing'),
(11, 45, '113456', '85000', '2018-03-23', 'Employee Salary and Marketing'),
(12, 45, '162750', '120000', '2018-04-14', 'Employee Salary and Marketing'),
(13, 45, '19800', '151000', '2018-05-16', 'Employee Salary and Marketing'),
(14, 45, '171002', '111111', '2018-06-13', 'Employee Salary and Marketing'),
(15, 45, '198201', '111200', '2018-07-18', 'Employee Salary and Marketing'),
(16, 45, '516511', '250014', '2018-08-18', 'Employee Salary and Marketing'),
(17, 45, '744114', '350012', '2018-08-10', 'Employee Salary and Marketing'),
(18, 45, '955222', '400022', '2018-09-21', 'Employee Salary and Marketing');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `departmenttype`
--

DROP TABLE IF EXISTS `departmenttype`;
CREATE TABLE IF NOT EXISTS `departmenttype` (
  `DepartmentID` int(255) NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(255) NOT NULL,
  PRIMARY KEY (`DepartmentID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `departmenttype`
--

INSERT INTO `departmenttype` (`DepartmentID`, `DepartmentName`) VALUES
(1, 'Software'),
(2, 'Human Resources'),
(3, 'Technic Support'),
(4, 'Management'),
(5, 'Accounting'),
(6, 'Standart Employee');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `jobtype`
--

DROP TABLE IF EXISTS `jobtype`;
CREATE TABLE IF NOT EXISTS `jobtype` (
  `JobID` int(255) NOT NULL AUTO_INCREMENT,
  `JobName` varchar(255) NOT NULL,
  PRIMARY KEY (`JobID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `jobtype`
--

INSERT INTO `jobtype` (`JobID`, `JobName`) VALUES
(1, 'Human Resoruce'),
(2, 'Manager'),
(3, 'Security'),
(4, 'Software Engineer'),
(5, 'Test Developer'),
(6, 'Accountant'),
(7, 'Software Architect'),
(8, 'Analyst'),
(9, 'Data Scientist'),
(10, 'Marketing');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `PersonID` int(255) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `BirthDate` date NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`PersonID`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `person`
--

INSERT INTO `person` (`PersonID`, `Name`, `LastName`, `BirthDate`, `username`, `password`) VALUES
(3, 'Emin', 'Borandağ', '1980-01-01', 'emin', '123'),
(149, 'teste', 'orhan', '2018-10-26', 'teste', '1'),
(153, 'Emre', 'Orhan', '1997-06-16', 'emre', '1234'),
(154, 'Berk', 'Ergün', '2018-11-01', 'hasan', '1234'),
(155, 'Ömer', 'Güney', '1999-11-05', 'omer', '1234'),
(156, 'Ahmet', 'Erkovan', '2018-11-12', 'ahmet', '1234');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `personnel`
--

DROP TABLE IF EXISTS `personnel`;
CREATE TABLE IF NOT EXISTS `personnel` (
  `PersonnelID` int(255) NOT NULL,
  `DepartmentID` int(255) NOT NULL,
  `JobID` int(255) NOT NULL,
  `Salary` decimal(30,0) NOT NULL,
  `recruitmentDate` date NOT NULL,
  `id` int(255) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`PersonnelID`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `personnel`
--

INSERT INTO `personnel` (`PersonnelID`, `DepartmentID`, `JobID`, `Salary`, `recruitmentDate`, `id`) VALUES
(3, 5, 6, '7000', '2018-11-01', 12),
(153, 1, 2, '1234', '2018-10-19', 10),
(154, 1, 4, '4567', '2018-11-01', 11),
(155, 3, 10, '5000', '2018-11-01', 13),
(156, 1, 2, '9000', '2018-11-05', 14);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `productID` int(255) NOT NULL AUTO_INCREMENT,
  `productName` varchar(255) NOT NULL,
  `productDescription` varchar(255) NOT NULL,
  `productStartingDate` date NOT NULL,
  `createdBy` varchar(50) NOT NULL,
  `projectLeader` varchar(50) NOT NULL,
  `productDueDate` date NOT NULL,
  `isActive` varchar(8) NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `products`
--

INSERT INTO `products` (`productID`, `productName`, `productDescription`, `productStartingDate`, `createdBy`, `projectLeader`, `productDueDate`, `isActive`) VALUES
(3, 'asd', 'asdasd', '2018-11-01', 'Emre ORHAN', 'Emre ORHAN', '2018-11-27', '1'),
(4, 'zxczc', 'qqwaewqe', '2018-11-19', 'Emre ORHAN', 'Emre ORHAN', '2018-11-21', '1'),
(5, 'asdwqewqe', 'qqwaewqe', '2018-11-19', 'Emre ORHAN', 'Emre ORHAN', '2018-11-21', '1'),
(6, 'asd', 'q', '2018-11-07', 'Emre ORHAN', 'Emre ORHAN', '2018-11-17', '1'),
(24, '1', '1', '2018-11-01', 'EmreORHAN', 'Berk', '2018-11-16', '1'),
(25, '1', '1', '2018-11-01', 'EmreORHAN', 'Berk', '2018-11-16', '0'),
(26, '1', '1', '2018-11-01', 'EmreORHAN', 'Emre', '2018-11-22', '0'),
(27, '1', '1', '2018-11-01', 'EmreORHAN', 'Emre', '2018-11-22', '1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
