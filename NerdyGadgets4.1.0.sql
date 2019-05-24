/* dropped huidige db en alle data daar in! verwijderd ook de user */
drop database if exists nerdygadgets;
DROP USER IF EXISTS 'MonitoringsApplicatie'@'localhost';

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/* database maken en gebruiken */
CREATE DATABASE IF NOT EXISTS `NerdyGadgets` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;

/* Leverancierslijst tabel maken en vullen met data van leverancier */
CREATE TABLE `NerdyGadgets`.`leverancierslijst` (
  `itemID` INT NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `prijs` DOUBLE NOT NULL,
  `beschikbaarheid` DOUBLE NOT NULL,
  PRIMARY KEY (`ItemID`));

use NerdyGadgets;
INSERT INTO leverancierslijst (Naam, Type, Prijs, Beschikbaarheid)
VALUES	("pfSense", "firewall", 2000.00, 99.999),
		("DBloadbalancer", "loadbalancer", 2000.00, 99.999),
		("HAL9001DB", "DBserver", 5100.00, 90),
		("HAL9002DB", "DBserver", 7700.00, 95),
		("HAL9003DB", "DBserver", 12200.00, 98),
		("HAL9001W", "Webserver", 2200.00, 80),
		("HAL9002W", "Webserver", 3200.00, 90),
		("HAL9003W", "Webserver", 5100.00, 95);
		
/* gebruiker aanmaken, met select en insert rechten */
CREATE USER 'MonitoringsApplicatie'@'localhost' IDENTIFIED BY 'VeiligWachtwoord';
GRANT SELECT, INSERT, DELETE ON nerdygadgets.* TO 'MonitoringsApplicatie'@'localhost';

/*Component tabel maken, en vullen met data van ons PoC */
CREATE TABLE `component` (
  `componentID` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `itemID` int(11) NOT NULL,
  `componentNaam` varchar(45) NOT NULL UNIQUE,
  `IPv4` varchar(45) NOT NULL UNIQUE,
  `IPv6` varchar(45) NOT NULL UNIQUE,
  `OS` varchar(45) NOT NULL,
  FOREIGN KEY (itemID) REFERENCES leverancierslijst(itemID)
);

INSERT INTO component (ItemID, componentNaam, IPv4, IPv6, OS)
VALUES (1, "pfSense", "192.168.1.1", "fd00::", "FreeBSD"),
(6, "web1", "192.168.10.2", "fd00:0:0:1::2", "Ubuntu"),
(7, "web2", "192.168.10.3", "fd00:0:0:1::3", "Ubuntu"),
(2, "dbl1", "192.168.20.2", "fd00:0:0:2::2", "Ubuntu"),
(3, "db1", "192.168.20.10", "fd00:0:0:2::10", "Ubuntu"),
(4, "db2", "192.168.20.11", "fd00:0:0:2::11", "Ubuntu");

/*Netwerk tabel maken, waar een configuratie en bijbehorende gegevens in staan */
CREATE TABLE `netwerk` (
  `netwerkID` int(11) NOT NULL PRIMARY KEY,
  `datum` date NOT NULL,
  `beschikbaarheidspercentage` double NOT NULL,
  `naam` varchar(45) NOT NULL,
  `prijs` double NOT NULL
);

/*Netwerkregel tabel maken, waar voor iedere server in een netwerk een line in wordt toegeoegd */
CREATE TABLE `netwerkregel` (
  `netwerkregelID` int(11) NOT NULL AUTO_INCREMENT, 	
  `netwerkID` int(11) NOT NULL,
  `itemID` int(11) NOT NULL,
  PRIMARY KEY (`netwerkregelID`),
  FOREIGN KEY (itemID) REFERENCES leverancierslijst(itemID),
  FOREIGN KEY (netwerkID) REFERENCES netwerk(netwerkID)
);
