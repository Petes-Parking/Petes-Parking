-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: carparkingsp
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`carparkingsp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `carparkingsp`;
--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(755) DEFAULT NULL,
  `first_name` varchar(755) DEFAULT NULL,
  `from_booking_date` datetime(6) DEFAULT NULL,
  `from_time` varchar(755) DEFAULT NULL,
  `last_name` varchar(755) DEFAULT NULL,
  `parking_name` varchar(755) DEFAULT NULL,
  `phone_number` varchar(755) DEFAULT NULL,
  `slot` bigint DEFAULT NULL,
  `slot_id` bigint DEFAULT NULL,
  `status` varchar(755) DEFAULT NULL,
  `to_booking_date` datetime(6) DEFAULT NULL,
  `to_time` varchar(755) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'delep@mailinator.com','Raja','2023-01-27 00:00:00.000000','11:11','Wilcox','Parking 3','+1 (832) 524-8356',2,17,'Canceled','2023-01-27 00:00:00.000000','00:22'),(2,'buly@mailinator.com','Melodie','2023-01-27 00:00:00.000000','19:20','Burke','Parking 4','+1 (593) 325-8711',2,23,'Cancel','2023-01-27 00:00:00.000000','20:21'),(3,'buly@mailinator.com','Melodie','2023-01-27 00:00:00.000000','19:21','Burke','Parking 4','+1 (593) 325-8711',4,25,'Canceled','2023-01-27 00:00:00.000000','20:21');
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(755) DEFAULT NULL,
  `number_of_slot` bigint DEFAULT NULL,
  `parking_name` varchar(755) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'ljkl kjlk',5,'Parking 1'),(2,'ljkl kjlk',10,'Parking 2'),(3,'ljkl kjlk',6,'Parking 3'),(4,' dsfdsf dsfsdf',8,'Parking 4');
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slots`
--

DROP TABLE IF EXISTS `slots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `slots` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parking_id` bigint DEFAULT NULL,
  `slot` bigint DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slots`
--

LOCK TABLES `slots` WRITE;
/*!40000 ALTER TABLE `slots` DISABLE KEYS */;
INSERT INTO `slots` VALUES (1,1,1,_binary ''),(2,1,2,_binary ''),(3,1,3,_binary ''),(4,1,4,_binary ''),(5,1,5,_binary ''),(6,2,1,_binary ''),(7,2,2,_binary ''),(8,2,3,_binary ''),(9,2,4,_binary ''),(10,2,5,_binary ''),(11,2,6,_binary ''),(12,2,7,_binary ''),(13,2,8,_binary ''),(14,2,9,_binary ''),(15,2,10,_binary ''),(16,3,1,_binary ''),(17,3,2,_binary ''),(18,3,3,_binary ''),(19,3,4,_binary ''),(20,3,5,_binary ''),(21,3,6,_binary ''),(22,4,1,_binary ''),(23,4,2,_binary ''),(24,4,3,_binary ''),(25,4,4,_binary ''),(26,4,5,_binary ''),(27,4,6,_binary ''),(28,4,7,_binary ''),(29,4,8,_binary '');
/*!40000 ALTER TABLE `slots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dob` varchar(255) DEFAULT NULL,
  `email` varchar(755) DEFAULT NULL,
  `first_name` varchar(755) DEFAULT NULL,
  `gender` varchar(755) DEFAULT NULL,
  `last_name` varchar(755) DEFAULT NULL,
  `password` varchar(755) DEFAULT NULL,
  `phone_number` varchar(755) DEFAULT NULL,
  `user_role` varchar(755) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1992-08-08','Admin@gmail.com','Admin','Other','Adminn','Admin@123','8888888589','Admin'),(3,'2013-12-01','quqy@mailinator.com','Lewis','Female','Barnes','Pa$$w0rd!','+1 (193) 856-1114','User'),(4,'2011-04-23','delep@mailinator.com','Raja','Other','Wilcox','Pa$$w0rd!','+1 (832) 524-8356','User'),(5,'1994-06-05','buly@mailinator.com','Melodie','Other','Burkeeeeee','Pa$$w0rd!','+1 (593) 325-8711','User');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-27 19:30:53
