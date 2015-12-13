-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: appartement
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `appartement`
--

DROP TABLE IF EXISTS `appartement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appartement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(150) DEFAULT NULL,
  `surface` int(11) DEFAULT NULL,
  `montant` double DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `proprietaire` varchar(50) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  KEY `proprietaire` (`proprietaire`),
  KEY `ville` (`ville`),
  CONSTRAINT `appartement_ibfk_1` FOREIGN KEY (`type`) REFERENCES `type` (`id`),
  CONSTRAINT `appartement_ibfk_2` FOREIGN KEY (`proprietaire`) REFERENCES `proprietaire` (`login`),
  CONSTRAINT `appartement_ibfk_3` FOREIGN KEY (`ville`) REFERENCES `ville` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appartement`
--

LOCK TABLES `appartement` WRITE;
/*!40000 ALTER TABLE `appartement` DISABLE KEYS */;
INSERT INTO `appartement` VALUES (1,'rue georges',154,150000,'T2','kelia','paris','image_01.jpg'),(2,'rue pierre marie 548',587,254580,'studio','lui','nanterre','image_02.jpg'),(3,'hello',6587541,78451,'T3','lol','paris','image_01.jpg'),(4,'rue tired',98,7845451,'T5','kelia','paris','image_02.jpg'),(5,'rue louis 5',5,7845451,'T1','k','maison alfort','image_01.jpg'),(301,'azeaze',50,150000.3,'studio','bob','maison alfort','image_01.jpg'),(302,'30 rue des accacia',77,150000,'T3','bob','maison alfort','image_01.jpg');
/*!40000 ALTER TABLE `appartement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employe`
--

DROP TABLE IF EXISTS `employe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) DEFAULT NULL,
  `login` varchar(15) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employe`
--

LOCK TABLES `employe` WRITE;
/*!40000 ALTER TABLE `employe` DISABLE KEYS */;
INSERT INTO `employe` VALUES (1,'admin','admin','admin'),(2,'med','med','med'),(3,'senthil','senthil','senthil');
/*!40000 ALTER TABLE `employe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprietaire`
--

DROP TABLE IF EXISTS `proprietaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proprietaire` (
  `login` varchar(50) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(70) DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprietaire`
--

LOCK TABLES `proprietaire` WRITE;
/*!40000 ALTER TABLE `proprietaire` DISABLE KEYS */;
INSERT INTO `proprietaire` VALUES ('autre','autre','autre','87542','autre'),('bob','x','x','4447','bob'),('k','k','kk','123654','k'),('kelia','sh','p','2','kelia'),('lol','mm','yu','56002','lol'),('lui','b','b','777','lui'),('mehdi','helin','mehdi','0160834052','mehdi'),('pom','bruno','bruno','1234567890','pom'),('proprio','a','a','12457','proprio');
/*!40000 ALTER TABLE `proprietaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `id` varchar(10) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `pourcentage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES ('studio','une piece',8),('T1','une piece principale',8),('T2','deux pieces dont une chambre',8),('T3','trois pieces',5),('T4','quatre pieces',5),('T5','cinq pieces',3);
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendu`
--

DROP TABLE IF EXISTS `vendu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `montant` int(11) DEFAULT NULL,
  `id_appartement` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendu`
--

LOCK TABLES `vendu` WRITE;
/*!40000 ALTER TABLE `vendu` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ville`
--

DROP TABLE IF EXISTS `ville`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ville` (
  `id` varchar(50) NOT NULL,
  `codepostal` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ville`
--

LOCK TABLES `ville` WRITE;
/*!40000 ALTER TABLE `ville` DISABLE KEYS */;
INSERT INTO `ville` VALUES ('maison alfort',94),('nanterre',92),('paris',75);
/*!40000 ALTER TABLE `ville` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-13 16:56:29
