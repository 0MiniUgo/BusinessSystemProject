-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: desafio_albert
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_client`
--

DROP TABLE IF EXISTS `tb_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_client` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_client`
--

LOCK TABLES `tb_client` WRITE;
/*!40000 ALTER TABLE `tb_client` DISABLE KEYS */;
INSERT INTO `tb_client` VALUES (1,'Hamilton Hanna'),(2,'Charleen Batstone'),(3,'Corny Gossart'),(4,'Allyn Kubatsch'),(5,'Emory Reade'),(6,'Rory Sprankling'),(7,'Edgardo Noyes'),(8,'Lazar Paylor'),(9,'Dacey Starrs'),(10,'Fergus Keenan');
/*!40000 ALTER TABLE `tb_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_client_orders`
--

DROP TABLE IF EXISTS `tb_client_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_client_orders` (
  `client_id` bigint NOT NULL,
  `orders_id` bigint NOT NULL,
  UNIQUE KEY `UK_m9g8oe22ua46gtpo26mahtks9` (`orders_id`),
  KEY `FKtod0tmiwx0bihco3n750di932` (`client_id`),
  CONSTRAINT `FKg7b58qkrhu566y3dchpogk4ml` FOREIGN KEY (`orders_id`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `FKtod0tmiwx0bihco3n750di932` FOREIGN KEY (`client_id`) REFERENCES `tb_client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_client_orders`
--

LOCK TABLES `tb_client_orders` WRITE;
/*!40000 ALTER TABLE `tb_client_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_client_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order` (
  `id` bigint NOT NULL,
  `client_id` bigint DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7c9of0p9ogx0w8sfrebt4n9kk` (`client_id`),
  KEY `FKj016jvq9orxa9lvcaayec2d32` (`payment_id`),
  CONSTRAINT `FK7c9of0p9ogx0w8sfrebt4n9kk` FOREIGN KEY (`client_id`) REFERENCES `tb_client` (`id`),
  CONSTRAINT `FKj016jvq9orxa9lvcaayec2d32` FOREIGN KEY (`payment_id`) REFERENCES `tb_payment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (1,1,2),(2,3,1),(3,3,1);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order_item`
--

DROP TABLE IF EXISTS `tb_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order_item` (
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FK4h5xid5qehset7qwe5l9c997x` (`product_id`),
  CONSTRAINT `FK4h5xid5qehset7qwe5l9c997x` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`),
  CONSTRAINT `FKgeobgl2xu916he8vhljktwxnx` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order_item`
--

LOCK TABLES `tb_order_item` WRITE;
/*!40000 ALTER TABLE `tb_order_item` DISABLE KEYS */;
INSERT INTO `tb_order_item` VALUES (16,3,4,1),(21,3,5,2),(21,3,5,3);
/*!40000 ALTER TABLE `tb_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_payment`
--

DROP TABLE IF EXISTS `tb_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_payment` (
  `id` bigint NOT NULL,
  `instant_pay` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_payment`
--

LOCK TABLES `tb_payment` WRITE;
/*!40000 ALTER TABLE `tb_payment` DISABLE KEYS */;
INSERT INTO `tb_payment` VALUES (1,'2021-12-06 03:00:00'),(2,'2021-12-13 03:00:00'),(3,'2021-10-19 03:00:00'),(4,'2022-03-20 03:00:00'),(5,'2022-06-06 03:00:00'),(6,'2022-06-29 03:00:00'),(7,'2022-04-15 03:00:00'),(8,'2021-11-26 03:00:00'),(9,'2021-11-30 03:00:00'),(10,'2022-04-04 03:00:00');
/*!40000 ALTER TABLE `tb_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_product` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES (1,'dicyclomine hydrochloride',22),(2,'ETHYL ALCOHOL',12),(3,'MORPHINE SULFATE',16),(4,'pancrelipase',16),(5,'Camphor (Synthetic), Menthol, and Methyl Salicylate',21),(6,'Azelastine Hydrochloride',25),(7,'alprazolam',16),(8,'Acetaminophen and Codeine',37),(9,'Aluminum hydroxide, Magnesium hydroxide, Simethicone',25),(10,'Oxygen',63);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'desafio_albert'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-26 17:32:00
