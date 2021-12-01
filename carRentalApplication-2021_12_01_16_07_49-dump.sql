-- MariaDB dump 10.19  Distrib 10.4.21-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: cardb
-- ------------------------------------------------------
-- Server version	10.4.21-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agreements`
--

DROP TABLE IF EXISTS `agreements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agreements` (
  `idAgreement` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `idCar` int(11) NOT NULL,
  `departure_location` int(11) NOT NULL,
  `deliver_location` int(11) DEFAULT NULL,
  `departure_date` datetime NOT NULL,
  `deliver_date` datetime DEFAULT NULL,
  `Penalties` int(11) DEFAULT NULL,
  `Remarks` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAgreement`),
  UNIQUE KEY `idAgreements_UNIQUE` (`idAgreement`),
  KEY `agreement:clients_idx` (`idClient`),
  KEY `agreement:cars_idx` (`idCar`),
  KEY `agreement:locations_idx` (`departure_location`,`deliver_location`),
  KEY `agr:loc_idx` (`deliver_location`),
  CONSTRAINT `agr:loc` FOREIGN KEY (`deliver_location`) REFERENCES `locations` (`idLocation`),
  CONSTRAINT `agreements:cars` FOREIGN KEY (`idCar`) REFERENCES `cars` (`idCar`),
  CONSTRAINT `agreements:clients` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`),
  CONSTRAINT `agreements:locations` FOREIGN KEY (`departure_location`) REFERENCES `locations` (`idLocation`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agreements`
--

LOCK TABLES `agreements` WRITE;
/*!40000 ALTER TABLE `agreements` DISABLE KEYS */;
INSERT INTO `agreements` VALUES (1,1,1,1,7,'2020-12-06 00:00:00','2021-12-01 00:00:00',NULL,NULL),(2,2,3,4,5,'2020-12-10 00:00:00','2020-12-11 00:00:00',NULL,NULL),(3,3,3,5,6,'2020-12-12 00:00:00','2020-12-13 00:00:00',NULL,NULL),(7,1,3,3,7,'2021-12-01 00:00:00','2021-12-01 00:00:00',NULL,NULL);
/*!40000 ALTER TABLE `agreements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `idCar` int(11) NOT NULL AUTO_INCREMENT,
  `VIN` varchar(17) NOT NULL,
  `Manufacturer` int(11) NOT NULL,
  `Model` int(11) NOT NULL,
  `Production_Year` int(11) NOT NULL,
  `Price_per_km` double NOT NULL,
  `Location` int(11) NOT NULL,
  `Battery_level` varchar(45) NOT NULL,
  PRIMARY KEY (`idCar`),
  UNIQUE KEY `idCar_UNIQUE` (`idCar`),
  UNIQUE KEY `VIN_UNIQUE` (`VIN`),
  KEY `cars:manufacturers_idx` (`Manufacturer`),
  KEY `cars:models_idx` (`Model`),
  KEY `cars:locations_idx` (`Location`),
  CONSTRAINT `cars:locations` FOREIGN KEY (`Location`) REFERENCES `locations` (`idLocation`),
  CONSTRAINT `cars:manufacturers` FOREIGN KEY (`Manufacturer`) REFERENCES `manufacturers` (`idManufacturer`),
  CONSTRAINT `cars:models` FOREIGN KEY (`Model`) REFERENCES `models` (`idModel`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'WME4540311B036244',1,1,2020,1.56,1,'87'),(2,'WMWZC5C50EWP38658',2,2,2020,1.87,2,'100'),(3,'VF1KW28B550278370',3,4,2019,1.43,7,'50'),(4,'SJNFAAJ10U2931577',4,3,2019,1.3,4,'98'),(5,'WBAGG83461DN81194',5,5,2017,1.61,5,'25'),(6,'5YJ3E1EAXHF000316',6,6,2018,2,6,'100'),(7,'SJNF2AJ13U2931577',4,3,2020,1.4,6,'74');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `PIC` varchar(45) DEFAULT NULL,
  `Last_name` varchar(45) NOT NULL,
  `First_name` varchar(45) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Driver_License_exp_date` datetime NOT NULL,
  `Subscription` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE KEY `idClients_UNIQUE` (`idClient`),
  UNIQUE KEY `ID_UNIQUE` (`PIC`),
  KEY `client:subscription_idx` (`Subscription`),
  CONSTRAINT `client:subscription` FOREIGN KEY (`Subscription`) REFERENCES `subscriptions` (`idSubscription`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'1000702034998','Popescu','Radu','str. Laminoristilor 8A, sectorul 3, Bucuresti','2020-12-30 00:00:00',NULL,'cristi','cristian',NULL),(2,'5000305908765','Grigore','Alexandru','Calea Tabacarilor nr.13, Cluj-Napoca','2024-03-03 00:00:00',NULL,'1','2',NULL),(3,'6090909123456','Vadim','Ramona','str. Trandafirilor nr.3, bloc D5 scara C, ap.68, Dej','2022-03-18 00:00:00',NULL,'3','4',NULL),(4,'1234567891234','dfasdf','asfdasas','fasdfasldfas','2020-02-02 00:00:00',NULL,'cristian','Cristian.2021#','sdfasfas.@asdfas.com');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_types`
--

DROP TABLE IF EXISTS `location_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_types` (
  `idLocation_type` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idLocation_type`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_types`
--

LOCK TABLES `location_types` WRITE;
/*!40000 ALTER TABLE `location_types` DISABLE KEYS */;
INSERT INTO `location_types` VALUES (1,'Company_eParkings'),(2,'Public_eParkings');
/*!40000 ALTER TABLE `location_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locations` (
  `idLocation` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  `capacity` varchar(45) NOT NULL,
  PRIMARY KEY (`idLocation`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `locations:loc_types_idx` (`type`),
  CONSTRAINT `locations:loc_types` FOREIGN KEY (`type`) REFERENCES `location_types` (`idLocation_type`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Strada Tasnad 22',2,'10'),(2,'Strada Panait Cerna',2,'3'),(3,'Strada Fabricii de Zahar 12',2,'20'),(4,'Strada Bucuresti 88',1,'50'),(5,'Strada Campu Painii-Clujana',1,'25'),(6,'Strada Dunarii 143-147',1,'30'),(7,'Ceanu Mare',1,'19'),(8,'Bulevardul Transilvaniei 48',1,'17'),(9,'Strada Vasile Goldis  13',1,'13'),(10,'Piata Romana ',2,'14'),(11,'Strada Sfintii Voievozi 61-47',1,'10'),(12,'Bulevardul Marasti 2-14-Arcul de Triumf',2,'18'),(13,'Piata 1848',1,'23'),(14,'VIVO Cluj',1,'28'),(15,'Strada Avram Iancu 170-Primaria Floresti',2,'3'),(16,'Calea Someseni',1,'5'),(17,'Strada Parcului 7-Sannicoara',2,'9'),(18,'Strada Vlahuta 30-40',1,'6'),(19,'Piata Unirii',2,'20'),(20,'Strada Plopilor 71-67',2,'32'),(21,'Strada Unirii 12 Chinteni',1,'21'),(22,'Piata Marasti',2,'30');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturers`
--

DROP TABLE IF EXISTS `manufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturers` (
  `idManufacturer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idManufacturer`),
  UNIQUE KEY `idManufacturer_UNIQUE` (`idManufacturer`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturers`
--

LOCK TABLES `manufacturers` WRITE;
/*!40000 ALTER TABLE `manufacturers` DISABLE KEYS */;
INSERT INTO `manufacturers` VALUES (5,'BMW'),(2,'MINI'),(4,'Nissan'),(3,'Renault'),(1,'Smart'),(6,'Tesla');
/*!40000 ALTER TABLE `manufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `models` (
  `idModel` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `manufacturer_id` int(11) NOT NULL,
  PRIMARY KEY (`idModel`),
  KEY `models:manufacturers_idx` (`manufacturer_id`),
  CONSTRAINT `models:manufacturers` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`idManufacturer`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES (1,'ForTwo EQ',1),(2,'Cooper SE',2),(3,'eLeaf',4),(4,'Zoe',3),(5,'i3',5),(6,'Model 3',6);
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription_type`
--

DROP TABLE IF EXISTS `subscription_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription_type` (
  `idSubscriptions` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `duration` int(11) NOT NULL,
  `available_km` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`idSubscriptions`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_type`
--

LOCK TABLES `subscription_type` WRITE;
/*!40000 ALTER TABLE `subscription_type` DISABLE KEYS */;
INSERT INTO `subscription_type` VALUES (1,'22go',2,200,150),(2,'72go',7,700,500),(3,'Honeymoon',30,5000,1000);
/*!40000 ALTER TABLE `subscription_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriptions` (
  `idSubscription` int(11) NOT NULL AUTO_INCREMENT,
  `idClient` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`idSubscription`),
  UNIQUE KEY `idClient_UNIQUE` (`idClient`),
  KEY `subscriptions:sub_types_idx` (`type`),
  CONSTRAINT `subscriptions:clients` FOREIGN KEY (`idClient`) REFERENCES `clients` (`idClient`),
  CONSTRAINT `subscriptions:sub_types` FOREIGN KEY (`type`) REFERENCES `subscription_type` (`idSubscriptions`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptions`
--

LOCK TABLES `subscriptions` WRITE;
/*!40000 ALTER TABLE `subscriptions` DISABLE KEYS */;
INSERT INTO `subscriptions` VALUES (1,1,'2020-12-05 00:00:00',1),(2,2,'2020-12-06 00:00:00',2),(3,3,'2020-12-07 00:00:00',3);
/*!40000 ALTER TABLE `subscriptions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-01 16:07:50
