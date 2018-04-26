-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: kirchatdb
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `chating_content_chat`
--

DROP TABLE IF EXISTS `chating_content_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chating_content_chat` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(45) NOT NULL,
  `user_id` varchar(45) NOT NULL COMMENT '보낸 사용자 ID',
  `content` varchar(45) DEFAULT NULL,
  `submit_date` datetime DEFAULT NULL,
  `receive_user_id` varchar(45) DEFAULT NULL COMMENT '받는 사용자 ID\n',
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chating_content_chat`
--

LOCK TABLES `chating_content_chat` WRITE;
/*!40000 ALTER TABLE `chating_content_chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chating_content_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chating_room_chat`
--

DROP TABLE IF EXISTS `chating_room_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chating_room_chat` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` varchar(45) NOT NULL,
  `room_name` varchar(45) DEFAULT NULL,
  `create_user_id` varchar(45) NOT NULL,
  `target_user_id` varchar(45) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`index`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chating_room_chat`
--

LOCK TABLES `chating_room_chat` WRITE;
/*!40000 ALTER TABLE `chating_room_chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chating_room_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_push_chat`
--

DROP TABLE IF EXISTS `device_push_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_push_chat` (
  `INDEX` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `push_token` varchar(45) DEFAULT NULL,
  `device_os` varchar(45) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`INDEX`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_push_chat`
--

LOCK TABLES `device_push_chat` WRITE;
/*!40000 ALTER TABLE `device_push_chat` DISABLE KEYS */;
INSERT INTO `device_push_chat` VALUES (1,'11','a','a',NULL),(2,'22','b','b',NULL);
/*!40000 ALTER TABLE `device_push_chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_chat`
--

DROP TABLE IF EXISTS `user_chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_chat` (
  `index` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`index`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_chat`
--

LOCK TABLES `user_chat` WRITE;
/*!40000 ALTER TABLE `user_chat` DISABLE KEYS */;
INSERT INTO `user_chat` VALUES (1,'11','aa',NULL),(2,'22','bb',NULL),(3,'33','cc',NULL),(4,'44','he',NULL),(5,'45','he',NULL);
/*!40000 ALTER TABLE `user_chat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-26 17:07:31
