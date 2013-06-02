CREATE DATABASE  IF NOT EXISTS `noticeboard` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `noticeboard`;
-- MySQL dump 10.13  Distrib 5.5.28, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: noticeboard
-- ------------------------------------------------------
-- Server version	5.5.28-0ubuntu0.12.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `_attachment`
--

DROP TABLE IF EXISTS `_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_attachment` (
  `id` varchar(20) NOT NULL,
  `file_name` varchar(200) NOT NULL,
  `file_blob` longblob NOT NULL,
  `notice_id` varchar(20) NOT NULL,
  `content_type` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_attachment`
--

LOCK TABLES `_attachment` WRITE;
/*!40000 ALTER TABLE `_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_notice`
--

DROP TABLE IF EXISTS `_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_notice` (
  `id` varchar(20) NOT NULL,
  `title` varchar(1000) NOT NULL,
  `body` text NOT NULL,
  `date` datetime NOT NULL,
  `user_id` varchar(20) NOT NULL,
  `department` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_notice`
--

LOCK TABLES `_notice` WRITE;
/*!40000 ALTER TABLE `_notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `_notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_configuration`
--

DROP TABLE IF EXISTS `_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_configuration` (
  `param_name` varchar(100) NOT NULL,
  `param_value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`param_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_configuration`
--

LOCK TABLES `_configuration` WRITE;
/*!40000 ALTER TABLE `_configuration` DISABLE KEYS */;
INSERT INTO `_configuration` VALUES ('default_days','Last 7 days'),('root_password','askvikrant.com');
/*!40000 ALTER TABLE `_configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_last_used_id`
--

DROP TABLE IF EXISTS `_last_used_id`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_last_used_id` (
  `table_name` varchar(200) NOT NULL,
  `id_value` varchar(20) NOT NULL,
  PRIMARY KEY (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_last_used_id`
--

LOCK TABLES `_last_used_id` WRITE;
/*!40000 ALTER TABLE `_last_used_id` DISABLE KEYS */;
INSERT INTO `_last_used_id` VALUES ('_attachment','A0000000000000000096'),('_notice','N0000000000000000081');
/*!40000 ALTER TABLE `_last_used_id` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_user`
--

DROP TABLE IF EXISTS `_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_user` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `user_role` varchar(100) NOT NULL,
  `department` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user`
--

LOCK TABLES `_user` WRITE;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_quick_link`
--

DROP TABLE IF EXISTS `_quick_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_quick_link` (
  `name` varchar(100) NOT NULL,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_quick_link`
--

LOCK TABLES `_quick_link` WRITE;
/*!40000 ALTER TABLE `_quick_link` DISABLE KEYS */;
INSERT INTO `_quick_link` VALUES ('askVikrant.com','http://askVikrant.com'),('Google','http://google.co.in'),('Yahoo','http://yahoo.com');
/*!40000 ALTER TABLE `_quick_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `_department`
--

DROP TABLE IF EXISTS `_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `_department` (
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_department`
--

LOCK TABLES `_department` WRITE;
/*!40000 ALTER TABLE `_department` DISABLE KEYS */;
INSERT INTO `_department` VALUES ('Central Library'),('Human Resources'),('IT Support'),('Registrar Office');
/*!40000 ALTER TABLE `_department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-04-14 17:14:46
