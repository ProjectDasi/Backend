CREATE DATABASE  IF NOT EXISTS `dasi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dasi`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dasi
-- ------------------------------------------------------
-- Server version	8.0.36

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

--
-- Table structure for table `certification`
--

DROP TABLE IF EXISTS `certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certification` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resume_id` int NOT NULL,
  `acquisition_date` date NOT NULL,
  `certification_name` varchar(50) NOT NULL,
  `issuing_authority` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_resume2_idx` (`resume_id`),
  CONSTRAINT `fk_resume2` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certification`
--

LOCK TABLES `certification` WRITE;
/*!40000 ALTER TABLE `certification` DISABLE KEYS */;
INSERT INTO `certification` VALUES (1,1,'2024-06-21','정보처리기사','q-net'),(2,1,'2024-06-16','SQLD','q-net');
/*!40000 ALTER TABLE `certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resume_id` int NOT NULL,
  `education_start` date NOT NULL,
  `education_end` date NOT NULL,
  `school` varchar(50) NOT NULL,
  `major` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_resume4_idx` (`resume_id`),
  CONSTRAINT `fk_resume4` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,1,'2019-03-01','2024-02-27','부산대학교','정보컴퓨터공학');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `region_id` int NOT NULL,
  `preference_id` int NOT NULL,
  `login_id` varchar(45) NOT NULL,
  `password` text NOT NULL,
  `role` enum('ROLE_MEMBER','ROLE_COMPANY','ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_MEMBER',
  PRIMARY KEY (`id`),
  KEY `fk_region1_idx` (`region_id`),
  KEY `fk_preference1_idx` (`preference_id`),
  CONSTRAINT `fk_preference1` FOREIGN KEY (`preference_id`) REFERENCES `preference` (`id`),
  CONSTRAINT `fk_region1` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'이영인','010-5195-1766',1,1,'youngin','$2a$10$yH.je.UljtoocMl..EJgQO.n93SeXTevR1v12tyCvOcJj7znn8ZSq','ROLE_MEMBER');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preference`
--

DROP TABLE IF EXISTS `preference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preference` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preference`
--

LOCK TABLES `preference` WRITE;
/*!40000 ALTER TABLE `preference` DISABLE KEYS */;
INSERT INTO `preference` VALUES (1,'REAL'),(2,'INVEST'),(3,'ARTIST'),(4,'SOCIAL'),(5,'ENTERPRISE'),(6,'CONVENTION');
/*!40000 ALTER TABLE `preference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `region` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subregion` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1,'전체'),(2,'기장군'),(3,'동래구'),(4,'사상구'),(5,'수영구'),(6,'중구'),(7,'강서구'),(8,'남구'),(9,'부산진구'),(10,'사하구'),(11,'연제구'),(12,'해운대구'),(13,'금정구'),(14,'동구'),(15,'북구'),(16,'서구'),(17,'영도구');
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resume`
--

DROP TABLE IF EXISTS `resume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resume` (
  `id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `photo` text,
  `name` varchar(10) NOT NULL,
  `birth_date` date NOT NULL,
  `address` varchar(150) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `emergency_contact` varchar(20) NOT NULL,
  `emergency_relationship` varchar(10) NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_member_idx` (`member_id`),
  CONSTRAINT `fk_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resume`
--

LOCK TABLES `resume` WRITE;
/*!40000 ALTER TABLE `resume` DISABLE KEYS */;
INSERT INTO `resume` VALUES (1,1,NULL,'이영인','2000-08-11','부산광역시 금정구','010-5195-1234','youngin5757@gmail.com','010-5195-5678','가족','2024-08-15 06:15:40');
/*!40000 ALTER TABLE `resume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resume_id` int NOT NULL,
  `training_start` date NOT NULL,
  `training_end` date NOT NULL,
  `training_name` varchar(50) NOT NULL,
  `training_institution` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_resume3_idx` (`resume_id`),
  CONSTRAINT `fk_resume3` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
INSERT INTO `training` VALUES (1,1,'2024-02-07','2024-07-27','k-digital 교육','부산대학교');
/*!40000 ALTER TABLE `training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work` (
  `id` int NOT NULL AUTO_INCREMENT,
  `source` varchar(30) NOT NULL,
  `company` varchar(30) NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` varchar(100) DEFAULT NULL,
  `salary` varchar(20) DEFAULT NULL,
  `signup_date` datetime NOT NULL,
  `due_date` varchar(20) NOT NULL,
  `region_name` varchar(100) DEFAULT NULL,
  `career` varchar(100) DEFAULT NULL,
  `education` varchar(100) DEFAULT NULL,
  `work_type` varchar(100) DEFAULT NULL,
  `work_category` varchar(100) DEFAULT NULL,
  `link` text,
  `contact` varchar(50) DEFAULT NULL,
  `work_hours` varchar(100) DEFAULT NULL,
  `details` text,
  `email` varchar(100) DEFAULT NULL,
  `certification` varchar(100) DEFAULT NULL,
  `preference_id` int DEFAULT NULL,
  `region_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_region2_idx` (`region_id`),
  KEY `fk_preference2_idx` (`preference_id`),
  CONSTRAINT `fk_preference2` FOREIGN KEY (`preference_id`) REFERENCES `preference` (`id`),
  CONSTRAINT `fk_region2` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (1,'국민 일자리 벼룩시장','광안다찌','광안다찌 주방, 홀 직원 구인',NULL,'2,400,000원','2024-08-14 00:00:00','상시채용','부산 수영구 광안해변로344번길 17-20','무관','무관','정규직','주방·주방보조·설거지, 주방장·조리사, 서빙·포장','http://www.findjob.co.kr/job/view/0113143200094094/WJ010310/','010-3474-0753','09:00~16:00\n휴게시간 포함, 휴게30분',NULL,NULL,NULL,NULL,5),(2,'국민 일자리 벼룩시장','광안다찌','광안다찌 주방, 홀 직원 구인',NULL,'2,400,000원','2024-08-14 00:00:00','상시채용','부산 수영구 광안해변로344번길 17-20','무관','무관','정규직','주방·주방보조·설거지, 주방장·조리사, 서빙·포장','http://www.findjob.co.kr/job/view/0113143200094094/WJ010310/','010-3474-0753','09:00~16:00\n휴게시간 포함, 휴게30분',NULL,NULL,NULL,NULL,5),(3,'국민 일자리 벼룩시장','광안다찌','광안다찌 주방, 홀 직원 구인',NULL,'2,400,000원','2024-08-14 00:00:00','상시채용','부산 수영구 광안해변로344번길 17-20','무관','무관','정규직','주방·주방보조·설거지, 주방장·조리사, 서빙·포장','http://www.findjob.co.kr/job/view/0113143200094094/WJ010310/','010-3474-0753','09:00~16:00\n휴게시간 포함, 휴게30분',NULL,NULL,NULL,NULL,5),(4,'국민 일자리 벼룩시장','이바구국밥','국밥집 야간 주방 파트1명 주3~4일근무 급여및 조건 면접시결정 주례 냉정역4번출구',NULL,'9,860원','2024-08-14 00:00:00','상시채용','부산 사상구 가야대로 355','무관','무관','계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 서빙·포장','http://www.findjob.co.kr/job/view/0113143933084084/WJ010310/','010-4892-3440\n문자문의','21:30~09:30\n휴게시간 포함, 면접시 협의',NULL,NULL,NULL,NULL,4),(5,'국민 일자리 벼룩시장','올리브푸드','구내식당 조리보조 /조리원 화목토/ 조리보조 일요일/ 조리원(조리가능자) 금요일 일당11만',NULL,'2,650,000원','2024-08-14 00:00:00','상시채용','부산 기장군 기장읍 기장대로 313','년수무관','무관','정규직, 계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113134684094094/WJ010310/','010-5177-9181','03:00~12:00\n협의가능, 휴게시간 포함, 파트별참조',NULL,NULL,NULL,NULL,2),(6,'국민 일자리 벼룩시장','올리브푸드','구내식당 조리보조 /조리원 화목토/ 조리보조 일요일/ 조리원(조리가능자) 금요일 일당11만',NULL,'2,650,000원','2024-08-14 00:00:00','상시채용','부산 기장군 기장읍 기장대로 313','년수무관','무관','정규직, 계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113134684094094/WJ010310/','010-5177-9181','03:00~12:00\n협의가능, 휴게시간 포함, 파트별참조',NULL,NULL,NULL,NULL,2),(7,'국민 일자리 벼룩시장','올리브푸드','구내식당 조리보조 /조리원 화목토/ 조리보조 일요일/ 조리원(조리가능자) 금요일 일당11만',NULL,'2,650,000원','2024-08-14 00:00:00','상시채용','부산 기장군 기장읍 기장대로 313','년수무관','무관','정규직, 계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113134684094094/WJ010310/','010-5177-9181','03:00~12:00\n협의가능, 휴게시간 포함, 파트별참조',NULL,NULL,NULL,NULL,2),(8,'국민 일자리 벼룩시장','거창맷돌순두부','홀알바 15시30분~20시30분//주방보조알바 11시30분~16시30분',NULL,'회사내규에 따름','2024-08-14 00:00:00','상시채용','부산 남구 유엔로 221','무관','무관','위촉직','서빙·포장, 주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113143151094094/WJ010310/','051-623-6204','협의가능\n협의가능',NULL,NULL,NULL,NULL,8),(9,'국민 일자리 벼룩시장','거창맷돌순두부','홀알바 15시30분~20시30분//주방보조알바 11시30분~16시30분',NULL,'회사내규에 따름','2024-08-14 00:00:00','상시채용','부산 남구 유엔로 221','무관','무관','위촉직','서빙·포장, 주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113143151094094/WJ010310/','051-623-6204','협의가능\n협의가능',NULL,NULL,NULL,NULL,8),(10,'국민 일자리 벼룩시장','올리브푸드','구내식당 조리보조 단기알바가능자 15시~20시 주6일 월155만',NULL,'1,550,000원','2024-08-14 00:00:00','상시채용','부산 기장군 철마면 반송로 979','무관','무관','계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113143879094094/WJ010310/','010-9147-0222','15:00~20:00',NULL,NULL,NULL,NULL,2),(11,'국민 일자리 벼룩시장','올리브푸드','구내식당 조리보조 단기알바가능자 15시~20시 주6일 월155만',NULL,'1,550,000원','2024-08-14 00:00:00','상시채용','부산 기장군 철마면 반송로 979','무관','무관','계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113143879094094/WJ010310/','010-9147-0222','15:00~20:00',NULL,NULL,NULL,NULL,2),(12,'국민 일자리 벼룩시장','올리브푸드','구내식당 조리보조 단기알바가능자 15시~20시 주6일 월155만',NULL,'1,550,000원','2024-08-14 00:00:00','상시채용','부산 기장군 철마면 반송로 979','무관','무관','계약직, 아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113143879094094/WJ010310/','010-9147-0222','15:00~20:00',NULL,NULL,NULL,NULL,2),(13,'국민 일자리 벼룩시장','금강SR테크','고무프레스 생산직 사원(경력직) 모집합니다, 상여금지급 (명절,여름휴가비)',NULL,'12,000원','2024-08-14 00:00:00','상시채용','부산 강서구 경전철로39번길 38','무관','무관','정규직, 계약직','생산·제조·조립·인쇄, 기계·설비','http://www.findjob.co.kr/job/view/0006435057081180/WJ010310/','010-9199-8203','08:30~17:30',NULL,NULL,NULL,NULL,7),(14,'국민 일자리 벼룩시장','금강SR테크','고무프레스 생산직 사원(경력직) 모집합니다, 상여금지급 (명절,여름휴가비)',NULL,'12,000원','2024-08-14 00:00:00','상시채용','부산 강서구 경전철로39번길 38','무관','무관','정규직, 계약직','생산·제조·조립·인쇄, 기계·설비','http://www.findjob.co.kr/job/view/0006435057081180/WJ010310/','010-9199-8203','08:30~17:30',NULL,NULL,NULL,NULL,7),(15,'국민 일자리 벼룩시장','(주)동남리싸이클링','작업기사,관리자및 플라스틱선별직원(하이카,압축차 운전)',NULL,'회사내규에 따름','2024-08-14 00:00:00','상시채용','부산 금정구 동천로 53','무관','무관','위촉직','청소·미화·방역, 서비스 기타, 화물·중장비·특수차','http://www.findjob.co.kr/job/view/0113119591094094/WJ010310/','010-6365-0783\n문자문의','협의가능\n협의가능',NULL,NULL,NULL,NULL,13),(16,'국민 일자리 벼룩시장','(주)동남리싸이클링','작업기사,관리자및 플라스틱선별직원(하이카,압축차 운전)',NULL,'회사내규에 따름','2024-08-14 00:00:00','상시채용','부산 금정구 동천로 53','무관','무관','위촉직','청소·미화·방역, 서비스 기타, 화물·중장비·특수차','http://www.findjob.co.kr/job/view/0113119591094094/WJ010310/','010-6365-0783\n문자문의','협의가능\n협의가능',NULL,NULL,NULL,NULL,13),(17,'국민 일자리 벼룩시장','(주)동남리싸이클링','작업기사,관리자및 플라스틱선별직원(하이카,압축차 운전)',NULL,'회사내규에 따름','2024-08-14 00:00:00','상시채용','부산 금정구 동천로 53','무관','무관','위촉직','청소·미화·방역, 서비스 기타, 화물·중장비·특수차','http://www.findjob.co.kr/job/view/0113119591094094/WJ010310/','010-6365-0783\n문자문의','협의가능\n협의가능',NULL,NULL,NULL,NULL,13),(18,'국민 일자리 벼룩시장','올리브푸드','일요일고정 조리실장 헬퍼 경력자우대 새벽2시~오전11시 일당17만원',NULL,'170,000원','2024-08-14 00:00:00','상시채용','부산 기장군 기장읍 기장대로 313','경력','무관','아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113141886094094/WJ010310/','010-5177-9181','02:00~11:00',NULL,NULL,NULL,NULL,2),(19,'국민 일자리 벼룩시장','올리브푸드','일요일고정 조리실장 헬퍼 경력자우대 새벽2시~오전11시 일당17만원',NULL,'170,000원','2024-08-14 00:00:00','상시채용','부산 기장군 기장읍 기장대로 313','경력','무관','아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113141886094094/WJ010310/','010-5177-9181','02:00~11:00',NULL,NULL,NULL,NULL,2),(20,'국민 일자리 벼룩시장','올리브푸드','일요일고정 조리실장 헬퍼 경력자우대 새벽2시~오전11시 일당17만원',NULL,'170,000원','2024-08-14 00:00:00','상시채용','부산 기장군 기장읍 기장대로 313','경력','무관','아르바이트(파트타임)','주방·주방보조·설거지, 요리·서빙 기타','http://www.findjob.co.kr/job/view/0113141886094094/WJ010310/','010-5177-9181','02:00~11:00',NULL,NULL,NULL,NULL,2),(21,'국민 일자리 벼룩시장','마루돼지국밥','주방 보조 직원 구합니다',NULL,'2,600,000원','2024-08-14 00:00:00','상시채용','부산 금정구 개좌로 59-12','무관','무관','정규직','주방·주방보조·설거지','http://www.findjob.co.kr/job/view/0113143857094094/WJ010310/','010-9536-1028','11:00~22:00',NULL,NULL,NULL,NULL,13),(22,'국민 일자리 벼룩시장','마루돼지국밥','주방 보조 직원 구합니다',NULL,'2,600,000원','2024-08-14 00:00:00','상시채용','부산 금정구 개좌로 59-12','무관','무관','정규직','주방·주방보조·설거지','http://www.findjob.co.kr/job/view/0113143857094094/WJ010310/','010-9536-1028','11:00~22:00',NULL,NULL,NULL,NULL,13),(23,'국민 일자리 벼룩시장','마루돼지국밥','주방 보조 직원 구합니다',NULL,'2,600,000원','2024-08-14 00:00:00','상시채용','부산 금정구 개좌로 59-12','무관','무관','정규직','주방·주방보조·설거지','http://www.findjob.co.kr/job/view/0113143857094094/WJ010310/','010-9536-1028','11:00~22:00',NULL,NULL,NULL,NULL,13),(24,'국민 일자리 벼룩시장','금풍대구탕','홀서빙 정직원 모집',NULL,'3,000,000원 이상','2024-08-14 00:00:00','상시채용','부산 해운대구 반여로155번나길 107','무관','무관','정규직','서빙·포장','http://www.findjob.co.kr/job/view/0113143088094094/WJ010310/','051-915-7474','09:00~21:00\n휴게시간 포함, (휴게시간2시간)',NULL,NULL,NULL,NULL,12),(25,'국민 일자리 벼룩시장','금풍대구탕','홀서빙 정직원 모집',NULL,'3,000,000원 이상','2024-08-14 00:00:00','상시채용','부산 해운대구 반여로155번나길 107','무관','무관','정규직','서빙·포장','http://www.findjob.co.kr/job/view/0113143088094094/WJ010310/','051-915-7474','09:00~21:00\n휴게시간 포함, (휴게시간2시간)',NULL,NULL,NULL,NULL,12),(26,'부산경영자총협회','제이에스테크','제이에스테크','산업기계조립 구인 공고','월급 300만원 (협의)','2024-08-09 00:00:00','최대한 빨리','부산 강서구 현장파견시 경남 함안 근무',NULL,NULL,NULL,'현장 산업기계 조립 (발전소 밸브 등) 크기가 큰 밸브들 조립 (체력요함)','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000353&page=0','문의 051)647-5186','월~금 08:00-17:00 (잔업 빈번함) 휴게시간 10시,13시 각 10분씩 점심시간 12:00-13:00',NULL,'greenss_dy@naver.com',NULL,NULL,7),(27,'부산경영자총협회','아쿠아셀(주)','아쿠아셀(주)','환경기계설비 연구개발 구인 공고','월급 200만원 이상 (협의)','2024-08-09 00:00:00','최대한 빨리','부산 강서구',NULL,NULL,NULL,'환경기계설비 연구개발 및 공사 프로젝트 매니저 업무 수행','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000352&page=0','문의 051)647-5186','월~금 08:00-17:00 휴게시간 12:00-13:00',NULL,'parkjmdes@naver.com',NULL,NULL,7),(28,'부산경영자총협회','웰니스병원','웰니스병원','기타보건의료종사원 구인 공고','최저시급','2024-08-09 00:00:00','최대한 빨리','부산 연제구',NULL,NULL,NULL,'기타 보건의료 종사원 의료기구 및 병실 베드 관리, 환자 이동 및 보조','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000351&page=0','문의 051)647-5186','주 6일 40시간 (스케줄 협의)',NULL,'uwellness@daum.net',NULL,NULL,11),(29,'부산경영자총협회','수요양병원','수요양병원','조리사 구인 공고','월금 260만원 (협의)','2024-08-08 00:00:00','최대한 빨리','부산 부산진구',NULL,NULL,NULL,'조리사','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000350&page=0','문의 051)647-5186','주 5일 06:00-18:00 (협의)',NULL,'soo11915@naver.com',NULL,NULL,9),(30,'부산경영자총협회','효성프라요양병원','효성프라요양병원','간호조무사 구인 공고','최저임금 이상 (협의)','2024-08-06 00:00:00','최대한 빨리','부산 사상구',NULL,NULL,NULL,'간호조무사','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000349&page=0','문의 051)647-5186','월~일 3교대 근무',NULL,'ppaarrkk77@daum.net',NULL,NULL,4),(31,'부산경영자총협회','진흥산업','진흥산업','변압기 용접원 구인 공고','월 330만원-370만 (협의)','2024-08-06 00:00:00','최대한 빨리','부산 강서구',NULL,NULL,NULL,'변압기 내 외부 용접','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000348&page=0','문의 051)647-5186','월~금 07:30-18:30 토 (격주) 07:30-16:00',NULL,'jhjh4693@naver.com',NULL,NULL,7),(32,'부산경영자총협회','수변최고 해운대점','수변최고 해운대점','조리사 구인 공고','월급 206만원 (협의)','2024-08-02 00:00:00','최대한 빨리','부산 해운대구',NULL,NULL,NULL,'조리사 (유관경력 우대)','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000347&page=0','문의 051)647-5186','주 5일 8시간 (스케줄근무)',NULL,'ksbk1970@naver.com',NULL,NULL,12),(33,'부산경영자총협회','수변최고 미포점','수변최고 미포점','조리사 구인 공고','월급 206만원 (협의)','2024-08-02 00:00:00','최대한 빨리','부산 부산진구',NULL,NULL,NULL,'조리사 (유관경력 우대)','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000346&page=0','문의 051)647-5186','주 5일 8시간 (스케줄근무)',NULL,'ksbk1970@naver.com',NULL,NULL,9),(34,'부산경영자총협회','수변최고 롯데점','수변최고 롯데점','조리사 구인 공고','월급 206만원 (협의)','2024-08-02 00:00:00','최대한 빨리','부산 부산진구',NULL,NULL,NULL,'조리사 (유관경력 우대)','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000345&page=0','문의 051)647-5186','주 5일 8시간 (스케줄근무)',NULL,'ksbk1970@naver.com',NULL,NULL,9),(35,'부산경영자총협회','금수복국 롯데점','금수복국 롯데점','조리사 구인 공고','월급 206만원 (협의)','2024-08-02 00:00:00','최대한 빨리','부산 부산진구',NULL,NULL,NULL,'조리사 (유관경력 우대)','https://bsefapp.co.kr/board/joboffer/details/?schCode=KODT000000000344&page=0','문의 051)647-5186','주 5일 8시간 (스케줄근무)',NULL,'ksbk1970@naver.com',NULL,NULL,9),(36,'워크넷','정가득재가노인복지센터','재가요양보호사 채용합니다',NULL,'시급 9,860원 ~ 9,860원','2024-08-14 00:00:00','채용시까지','부산광역시 사하구 감천로89번길 1, 빌라 (감천동)','관계없음','학력무관','주 3일 근무\n(주 소정근로시간: 9시간)','방문 복지서비스 제공업','https://www.work.go.kr/empInfo/empInfoSrch/detail/empDetailAuthView.do?searchInfoType=VALIDATION&callPage=detail&wantedAuthNo=KF11472408140001&rtnTarget=list1','051-970-2351',NULL,'<td> - 4등급 할머니<br/>- 13:00~16:00 / 주3일,1일 3시간<br/>- 일반적인 케어 등<br/><br/>* 자세한 문의 사항은 051-970-2351로 해 주세요. </td>',NULL,'요양보호사(필수)',NULL,10),(37,'워크넷','강남재가복지센터','재가요양보호사 채용',NULL,'시급 9,860원 이상','2024-08-14 00:00:00','채용시까지','부산광역시 금정구 동현로 89, , (부곡동, 늘푸른아파트)','관계없음','학력무관','주 3일 근무','방문 복지서비스 제공업','https://www.work.go.kr/empInfo/empInfoSrch/detail/empDetailAuthView.do?searchInfoType=VALIDATION&callPage=detail&wantedAuthNo=KJBA002408140002&rtnTarget=list2',NULL,NULL,'<td> [부곡동] 재가요양보호사 채용<br/><br/>- 4등급 할머니 (80대 초반, 독거)<br/>- 주3일 근무, 오전 8시~11시<br/>- 금정구 동현로 89 (부곡동, 시영아파트)<br/>- 시급 : 9,860원 (주휴수당 1,972원, 연차수당 768원 별도지급)<br/>- 일상생활지원 및 신체활동, 정서지원, 가사지원 등<br/><br/>**지원을 희망하시는 분은 051-414-1008 (강남재가복지센터)로 문의주세요<br/><br/>● 실제 서비스 제공기간에 따라 임금을 계산하여 지급하는 형태로 채용 시 근로자마다 주 소정근로시간이나 임금, 근무(예정)지 등에 차이가 있을 수 있음. </td>',NULL,'요양보호사1급(필수)',NULL,13),(38,'워크넷','주식회사 지엔비','용호동 아파트 보안대원 채용',NULL,'월급 256만원 ~ 256만원','2024-08-14 00:00:00','2024-10-13','부산광역시 남구 용주로 36, .. (용호동, 데시앙 해링턴 플레이스 파크시티)','경력 (최소 1년 0 개월 이상) 우대','학력무관','주 4일 근무\n(주 소정근로시간: 40시간)','사업시설 유지․관리 서비스업','https://www.work.go.kr/empInfo/empInfoSrch/detail/empDetailAuthView.do?searchInfoType=VALIDATION&callPage=detail&wantedAuthNo=KJKR002408140019&rtnTarget=list3',NULL,NULL,'<td> - 용호동 아파트 보안대원 채용<br/>- 신임경비교육 이수 필수<br/>- 컴퓨터 사용가능자(기본)<br/>- 3교대..주주야야비비(주간08;00~18:00,야간18:00~익일08:00)/ 휴게시간(주간 1시간, 야간 1.5시간)<br/>-급여:2,566,980원<br/>-지원문의 031-8045-4213<br/>- 인근거주자 및 즉시 출근가능자 우대<br/> </td>',NULL,'관계없음',NULL,8),(39,'워크넷','바른마음재가복지센터','어르신 다정하게 보살펴 주실 요양보호사 구합니다.',NULL,'시급 9,860원 ~ 9,860원','2024-08-14 00:00:00','채용시까지','부산광역시 사하구 감천항로190번길 47-3 (구평동)','경력 (최소 0년 6 개월 이상) 필수','학력무관','주 5일 근무\n(주 소정근로시간: 15시간)','사회복지 서비스업','https://www.work.go.kr/empInfo/empInfoSrch/detail/empDetailAuthView.do?searchInfoType=VALIDATION&callPage=detail&wantedAuthNo=KJBK002408140001&rtnTarget=list4','051-220-5696',NULL,'<td> - 사하구 감천항로 190번길 47-3<br/>- 4등급/55년생/여자 어르신<br/>- 13:00~16:00/주5일, 3시간<br/>- 시급;12,400원(수당포함)<br/>*경력 6개월 이상 필수*<br/><br/>※실제 서비스 제공기간에 따라 임금을 계산하여 지급하는 형태로 채용시 근로자마다 주 소정근로시간이나 임금, 근무(예정)지 등에 차이가 있을 수 있음<br/> </td>',NULL,'요양보호사(필수)\n기타 : 치매교육이수증',NULL,10),(40,'워크넷','가연재가복지센터','재가요양보호사 구인 (긴급)',NULL,'시급 9,860원 이상','2024-08-14 00:00:00','2024-09-13','부산광역시 북구 효열로 265, . (금곡동, 한솔아파트)','관계없음','학력무관','주 3일 근무\n(주 소정근로시간: 12시간)','방문 복지서비스 제공업','https://www.work.go.kr/empInfo/empInfoSrch/detail/empDetailAuthView.do?searchInfoType=VALIDATION&callPage=detail&wantedAuthNo=KJBI002408140002&rtnTarget=list5',NULL,NULL,'<td> [금곡한솔아파트] 재가요양보호사 구인 <br/><br/>- 2등급 86세 할머니 (가족동거, 근무시간대 어르신 혼자 계심)<br/>- 주 3일 / 오후 13~17시 (화,수,목) <br/>- 신체 및 가사지원<br/>- 중증수당 및 주휴수당 등 발생시 별도지급<br/><br/>- 지원 문의 : 051-865-4073 (가연재가복지센터) </td>',NULL,'요양보호사(필수)',NULL,15),(41,'장노년일자리지원센터','기장군청','2024년도 가축전염병 선제대응 기간제근로자 채용',NULL,NULL,'2024-08-19 00:00:00','2024-08-20','부산 기장군',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3225','로그인 후 조회가능로그인',NULL,'세부내용 농업정책과에서 2024년 가축전염병 이동방역 기간제 근로자 채용계획을 다음과 같이 공고합니다.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>■ 채용개요<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 공고기간 : 2024. 8. 12.(월) ~ 8. 19.(월)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 접수기간 : 2024. 8. 19.(월) ~ 8. 20.(화) 09:00~18:00 (점심시간 제외)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 접수방법 : 군청 방문접수(8층 농업정책과)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 면접일자 : 응시자 전원 면접 심사 2024. 8. 22(목) 10:00 예정 (※ 변경사항 발생 시 개별통지)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용인원 : 2명<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용기간 : 2024. 9. 2. ~ 12. 31. (※가축전염병 발생상황 및 예산 사정에 따라 변동 가능)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 주요업무 : 가축전염병 방역차량 운행, 축산농가 소독, 가축전염병 방역초소 및 방역창고 관리, 관내 가축전염병 발생 시 살처분 현장 업무 등<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 최종합격자발표 : 2024. 8. 23.(금), 홈페이지 공고 및 개별통보<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>■ 기타 세부사항 : 붙임 공고문 참조<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>** 문의사항 : 기장군청 농업정책과 축산동물보호팀(☎051-709-4401)',NULL,NULL,NULL,2),(42,'장노년일자리지원센터','부산광역시 농업기술센터','부산광역시농업기술센터 기간제노동자 채용 공고 (지역특화농업기술정보화인력지원)',NULL,NULL,'2024-08-13 00:00:00','2024-08-21','부산 강서구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3224','로그인 후 조회가능로그인',NULL,'세부내용 부산광역시농업기술센터 소속 「지역특화농업기술정보화인력지원 기간제노동자」채용계획을 다음과 같이 공고합니다.<br/><br/> 1. 공 고 명: 지역특화농업기술정보화인력지원 기간제노동자 채용 공고 2. 채용인원: 기간제노동자 1명 3. 근무기간: 2024. 8. 29.(목) ~ 2024. 12. 17.(화) 4. 담당업무: 농업기술센터 홈페이지 관리, 동영상 제작, 농업인 교육 및 인재양성팀 업무지원 5. 근무장소: 부산광역시 강서구 공항로 1285, 부산광역시농업기술센터 인재양성팀 6. 원서접수: 2024. 8. 13.(화) ~ 2024. 8. 21.(수), 방문 또는 이메일 접수(hat0268@korea.kr) 7. 문의처: 농업기술센터 인재양성팀 채용담당자 051)970-3765<br/> ** 기타 자세한 내용은 첨부문서 참고 바랍니다.<br/>',NULL,NULL,NULL,7),(43,'장노년일자리지원센터','동래구청','부산광역시 동래구 시간선택제임기제공무원(불법 광고물 단속 정비) 채용 계획 재공고',NULL,NULL,'2024-08-26 00:00:00','2024-08-28','부산 동래구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3223','로그인 후 조회가능로그인',NULL,'세부내용 부산광역시 동래구에서 근무할 시간선택제임기제공무원(불법 광고물 단속 정비)을 다음과 같이 공개 모집하오니 유능하신 분들의 많은 지원을 바랍니다.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>1. 채용분야 : 불법광고물 단속 정비/ 시간선택제 임기제마급<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 불법 광고물 단속 정비<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 기타 불법 불법 광고물 관련 업무<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 민원처리 및 수거물 관리 포함<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br/>2. 채용인원 : 1명<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br/>3. 응시자격 (공통)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○「지방공무원법」 제31조(결격사유), 「지방공무원 임용령」 제65조<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>(부정행위자 등에 대한 조치) 및 「부패방지 및 국민권익위원회의<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>설치와 운영에 관한 법률」제82조(비위면직자 등의 취업제한) 등 기타 법령에<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>의하여 응시자격이 정지되지 아니한 자<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 공고일 현재 만 18세 이상으로 남자의 경우 병역을 필하거나 면제 받은 자<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br/>4. 채용분야 자격요건<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 1종 보통 이상의 자동차 운전면허 소지자로, 운전이 가능하며 업무수행(보행·운전 등)에 지장이 없고, 1년 이상 임용 예정 직무 분야의 실무경력이 있는 사람<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 관련분야 실무경력: 국가, 지방자치단체, 공공기관 등에서 임용 분야 주요 업무와 관련성이 인정되는 경력<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 근무 경력은 경력증명서 상 근무 기간과 담당업무를 명시한 경우에 한함.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br/>5. 접수방법<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 접수기간: 2024.08.26~2024.08.28/ 마감일 근무시간(18:00)까지 접수, 점심시간(12:00~13:00) 제외<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 접수방법: 직접방문 또는 우편접수(접수 마감일 18:00까지 도착분)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/> 우편접수자는 우편발송 후 전화 요망<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 접 수 처: 동래구 총무과<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br/>6. 근무부서: 동래구 도시재생과<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 직무관련 문의: 동래구 도시재생과(☎ 051-550-4622)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 그 외 채용절차 관련 문의: 동래구 총무과(☎ 051-550-4102/4104)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 그 외 기타 자세한 사항은 붙임 공고문 참조',NULL,NULL,NULL,3),(44,'장노년일자리지원센터','부산광역시의료원','부산광역시의료원 행정처장 공개 모집',NULL,NULL,'2024-08-12 00:00:00','2024-08-27','부산 연제구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3222','로그인 후 조회가능로그인',NULL,'세부내용 부산광역시 공고 제2024 - 2597호 「지방의료원의 설립 및 운영에 관한 법률」제8조(임원)에 의하여 병원 운영에 관한 학식과 경험이 풍부하고 부산광역시 부산의료원 이사회를 효율적으로 운영해 나갈 능력 있는 상근이사를 모시고자 아래과 같이 공고합니다. <br/><br/> 1. 공모분야 및 인원 가. 직위 : 행정처장(상근이사) 나. 인원 : 1명 다. 임기 : 임명일로부터 3년 2. 직무내용 및 직무수행 요건 가. 직무내용 - 행정처 업무를 관장하고, 그 소속 직원의 업무 지도·감독 - 이사회의 구성원으로 의료원 업무에 관한 중요사항 심의·의결 나. 직무수행요건 - 혁신과 변화 주도 능력, 리더십, 전문성, 의료원 운영 및 경영개선 의지, 종합적 판단 및 정책결정 능력, 조직구성원 등의 역량 극대화, 윤리(청렴)성, 봉사정신 등 <br/> 3. 응모자격 가. 지방의료원의 설립 및 운영에 관한 법률」 제11조(임원의 결격사유)에 해당하지 아니한 자로 아래 요건 중 하나를 충족하여야 한다. - 국가 또는 지방공무원 5급 이상으로 5년 이상 재직한 자 - 300병상 이상 규모의 종합병원에서 팀장(과장)급 이상으로 5년이상 재직한 자 - 기타 각 호에 준하는 경력과 능력이 있다고 인정되는 자 <br/> 4. 보수 : 의료원의 보수 규정 및 연봉제 운영 규정 등이 정하는 보수와 기타 직무수행에 따르는 실비 지급<br/> 5. 지원서류 접수 가. 접수기간 : ‘24. 8. 12.(월) ~ ’24. 8. 27.(화) * 09:00∼18:00(토요일·공휴일 제외) ※ 지원서류 : 부산광역시 홈페이지(www.busan.go.kr) 및 부산의료원 홈페이지(www.busanmc.or.kr)에 게시 나. 접수방법 : 전자우편, 방문접수 또는 등기우편(접수마감 전까지 도착분에 한함) 다. 접 수 처 : 우)47545 부산광역시 연제구 중앙대로 1001 (연산동) 부산광역시청 공공기관담당관 임원 임명 담당자(051-888-2112) 또는 renzhi@korea.kr(전자우편 접수처) 라. 제출서류 - 지원서, 자기소개서, 직무수행계획서 각 1부(소정 서식) - 경력증명서, 최종학력증명서, 자격증 사본 등 증빙서류 각 1부 - 개인정보 수집 및 이용 동의서 1부(소정 서식) - 부패방지권익위법상 비위면직자 등 취업제한 관련 체크리스트(소정 서식)<br/><br/>제출 서식 및 기타 자세한 사항은 붙임 공고문과 첨부파일을 참고하시기 바랍니다.<br/>2024년 8월 12일<br/>부산광역시의료원 임원추천위원회 위원장<br/>',NULL,NULL,NULL,11),(45,'장노년일자리지원센터','기장군청','2024년 기장군 토지정보과 기간제근로자 채용',NULL,NULL,'2024-08-09 00:00:00','2024-08-19','부산 기장군',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3221','로그인 후 조회가능로그인',NULL,'세부내용 부산광역시 기장군 토지정보과 지적팀 기간제근로자 채용을 다음과 같이 공고합니다.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용인원: 1명<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용기간<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 2024. 9. 2(월). ~ 2024. 12. 31.(화)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 예산한도에 따라 변경가능<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용분야<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 지적기준점 실태조사 및 지적 전산자료 정비<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용방법: 공개채용을 통한 서류심사 및 면접심사<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용일정(※ 사정에 의해 변동될 수 있음)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 공고 및 원서접수: 2024. 8. 9.(금) ~ 8. 19.(월)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 서류합격자 발표: 2024. 8. 21.(수)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 면접심사: 2024. 8. 23.(금)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 최종합격자 발표: 2024. 8. 27.(화)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 세부내용은 첨부된 공고문을 참조하시기 바라며, 문의사항은 지적팀☎051)709-4775로 연락주시기 바랍니다.',NULL,NULL,NULL,2),(46,'장노년일자리지원센터','기장군의회','기장군의회 기간제근로자(민원응대 및 사무보조) 채용',NULL,NULL,'2024-08-09 00:00:00','2024-08-16','부산 기장군',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3220','로그인 후 조회가능로그인',NULL,'세부내용 부산광역시 기장군의회에서 민원응대 및 사무보조 할 기간제근로자를 채용코자 다음과 같이 모집 공고합니다.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용기간 : 2024. 9. 2. ~ 2025. 2. 28.(6개월)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용인원 : 1명<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 근무내용 : 민원응대 및 사무보조<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 채용방법 : 서류전형 및 면접심사를 통한 공개채용<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 원서접수 : 인터넷접수 (1minj327@korea.kr)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>○ 문의사항 : 의회사무과 의정팀(☎709-5034)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 채용기간, 지원자격, 채용일정 등 기타 세부내용은 첨부된 공고문을 참조하시기 바랍니다.',NULL,NULL,NULL,2),(47,'장노년일자리지원센터','영도구청','영도구 시간선택제임기제공무원 채용계획 공고',NULL,NULL,'2024-08-20 00:00:00','2024-08-23','부산 영도구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3219','로그인 후 조회가능로그인',NULL,'세부내용 * 생애초기 건강관리사업 대상자 등록 및 가정 방문<br/>* 고위험 가구 자원 연계 및 관리<br/>* 위기가구 사례관리 및 통합사례회의 등<br/>* 그 외 보건소장이 정하는 업무<br/>',NULL,NULL,NULL,17),(48,'장노년일자리지원센터','연제구보건소(연산6동 마을건강센터)','2024년 연제구 마을건강센터 기간제근로자(마을간호사) 채용 공고',NULL,NULL,'2024-08-07 00:00:00','2024-08-16','부산 연제구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3218','로그인 후 조회가능로그인',NULL,'세부내용 2024년 연제구보건소 마을건강센터의 원활한 업무 추진을 위해 아래와 같이 채용 공고합니다.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>2024.08.07.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>연제구보건소장<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/><br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>가. 채용분야 : 연산6동 마을건강센터 기간제근로자(마을간호사)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>나. 채용인원 : 1명(마을건강센터별 마을간호사 1명)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>다. 채용기간: 2024. 9. 1. ~ 2025. 4. 30. (8개월)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>라. 서류 접수기간 : 2024. 8. 6.(수) ~ 8. 16.(금) 18:00 한<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>마. 서류 접수방법 : 직접 방문, 우편, 이메일(택 1)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>바. 채용방법 : 서류 및 면접심사(구 홈페이지를 통한 공개전형)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>사. 문의: 연제구보건소 마을건강센터 담당자 051-665-5455<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 세부내용은 첨부된 공고문을 필히 참고하시기 바랍니다.',NULL,NULL,NULL,11),(49,'장노년일자리지원센터','기장군도시관리공단 정관노인복지관','2024년 정관노인복지관 프로그램 강사 신규채용',NULL,NULL,'2024-08-07 00:00:00','2024-08-16','부산 기장군',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3217','로그인 후 조회가능로그인',NULL,'세부내용 2024년 정관노인복지관 프로그램 강사 신규채용 공고<br/><br/>기장군도시관리공단 정관노인복지관 프로그램 강사를 다음과 같이 모집합니다.<br/><br/>○ 모집기간<br/>- 2024. 8. 7.(수) ~ 2024. 8. 16.(금)<br/>○ 모집인원<br/>- 프로그램 강사 : 1명(세부내용 붙임참조)<br/>? 관련 자격증 소지자 및 1년 이상 경력자<br/>○ 모집연령<br/>- 만18세 이상<br/>○ 제출서류 : 이력서 1부, 개인정보이용동의서 1부, 자격 관련 증빙서류 각 1부<br/>○ 심사방법<br/>- 1차 : 서류자격 적격여부 심사<br/>- 2차 : 면접시험<br/>○ 접수장소 : 정관노인복지관 프로그램실 (접수마감일 : 2024년 8월 16일(금) 까지)<br/>(부산광역시 기장군 정관읍 정관중앙로 83-14)<br/>○ 면접일시 및 장소<br/>- 2024. 8. 21.(수) / 1차 합격자에 한하여 개별 통지<br/>○ 기타사항 및 준비서류 : 붙임『공고문』참조<br/>○ 관련문의 : 정관노인복지관(792-4920)<br/>',NULL,NULL,NULL,2),(50,'장노년일자리지원센터','해운대구청','해운대구 통합사례관리사  공개채용공고',NULL,NULL,'2024-08-12 00:00:00','2024-08-14','부산 해운대구',NULL,NULL,'무기계약직',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3216','로그인 후 조회가능로그인',NULL,'세부내용 - 채용분야 : 통합사례관리사 공무직 근로자<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 채용기간 : 24.9.1.(예정)~ 정년(퇴직)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 주요업무 : 통합사례관리사업, 대민상담지원, 맞춤형 서비스 연계, 보건복지부 129이관콜 업무처리<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 채용방법 : 제1차 서류전형 ? 직무능력평가 ? 제2차 면접 심사<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 응시자격 : 첨부파일 꼼꼼히 확인 바랍니다.<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 우대사항<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>사례관리 경력 : - 공고일 기준 10년 이내 경력만 인정<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 재가대상자에 한해 실시한 사례관리에 한함<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 경력(재직)증명서에 직무 내용이 기재된 경우만 인정<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>국가유공자 : - 「국가유공자 등 예우 및 지원에 관한 법률」 제31조(채용시험의 가점 등) 및 같은 법 시행령 제48조<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>- 채용일정<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>? 서류접수 시간 : 09:00 ~ 18:00 (12:00 ~ 13:00 점심시간 제외)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>? 접수처 : 부산광역시 해운대구 센텀중앙로 170, 문화복합센터(재송동) 5층, 복지정책과 희망복지팀 (☏051-749-5696)<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>? 직무능력평가 : 직무능력평가 후 2차 면접(모집인원의 3배수) 진행<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 2배수인 경우 적합 여부 심사 후, 적격인 경우 합격자로 결정, 동점자가 있는 경우 전원 선발<br style=\'font-family: \"Noto Sans\", \"Noto Sans KR\", GmarketSans, \"맑은 고딕\", MalgunGothic, sans-serif; color: rgb(102, 102, 102); font-size: 18px; text-wrap: wrap; background-color: rgba(255, 255, 255, 0.8);\'/>※ 제출서류 및 유의사항 - 첨부파일 [공고문] 확인 후 지원바랍니다.',NULL,NULL,NULL,12),(51,'장노년일자리지원센터','연제구청','2024년 하반기 병리검사실 기간제 근로자(임상병리사) 채용 공고',NULL,NULL,'2024-08-09 00:00:00','2024-08-16','부산 연제구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3215','로그인 후 조회가능로그인',NULL,'세부내용 부산광역시 연제구 보건소에서 근무할 병리검사실 기간제 근로자(임상병리사) 채용을 다음과 같이 공고합니다.<br/><br/>1. 공 고 명 : 2024년 하반기 병리검사실 기간제 근로자(임상병리사) 채용 공고<br/>2. 공고기간 : 2024. 8. 7. ~ 8. 16.<br/>3. 채용인원 : 1명<br/>4. 접수기간 : 2024. 8. 9. ~ 8. 16.<br/>5. 세부내용: 붙임 공고문 참조<br/>',NULL,NULL,NULL,11),(52,'장노년일자리지원센터','영도구청','2024년 제2차 영도해녀문화전시관 기간제근로자 채용 공고',NULL,NULL,'2024-08-07 00:00:00','2024-08-16','부산 영도구',NULL,NULL,'기간제',NULL,'https://www.busan50plus.or.kr/job/gojob/view?seq=3214','로그인 후 조회가능로그인',NULL,'세부내용 * 전시관 관리 운영 및 방문객 안내<br/>* 전시관 내외 환경정비<br/>* 전시관 프로그램 및 행사 지원<br/>* 기타 전시관 전반 관리업무 등<br/>',NULL,NULL,NULL,17);
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_experience`
--

DROP TABLE IF EXISTS `work_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_experience` (
  `id` int NOT NULL AUTO_INCREMENT,
  `resume_id` int NOT NULL,
  `work_start` date NOT NULL,
  `work_end` date NOT NULL,
  `company` varchar(45) NOT NULL,
  `work_description` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_resume1_idx` (`resume_id`),
  CONSTRAINT `fk_resume1` FOREIGN KEY (`resume_id`) REFERENCES `resume` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_experience`
--

LOCK TABLES `work_experience` WRITE;
/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` VALUES (1,1,'2022-04-15','2022-12-27','부산대산하협력단','부산대 전산업무');
/*!40000 ALTER TABLE `work_experience` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-15 15:32:55
