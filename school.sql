-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: sb_school
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `t_class`
--

DROP TABLE IF EXISTS `t_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `classNo` varchar(20) NOT NULL COMMENT '班级号',
  `major` varchar(20) NOT NULL COMMENT '专业',
  `classGrade` int(11) DEFAULT NULL COMMENT '班级评价',
  `isExcellent` bit(1) DEFAULT b'0' COMMENT '0 不是卓越班 1 是卓越班',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_class`
--

LOCK TABLES `t_class` WRITE;
/*!40000 ALTER TABLE `t_class` DISABLE KEYS */;
INSERT INTO `t_class` VALUES (1,'20010101','计算机科学与技术',7,''),(3,'20010102','物联网工程',6,'\0'),(4,'20010103','物联网工程',10,''),(5,'20010104','网络工程',6,'\0'),(6,'20010105','探测与制导',6,'\0'),(7,'20010106','软件工程',6,'\0');
/*!40000 ALTER TABLE `t_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_curriculum`
--

DROP TABLE IF EXISTS `t_curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_curriculum` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `classNo` varchar(20) NOT NULL COMMENT '课程号',
  `className` varchar(10) NOT NULL COMMENT '课程名称',
  `classRoom` varchar(6) NOT NULL COMMENT '上课教室',
  `classHour` int(4) DEFAULT NULL COMMENT '课时',
  `classGrade` int(11) DEFAULT NULL COMMENT '课程评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_curriculum`
--

LOCK TABLES `t_curriculum` WRITE;
/*!40000 ALTER TABLE `t_curriculum` DISABLE KEYS */;
INSERT INTO `t_curriculum` VALUES (1,'002001','数据库','教1-103',32,7),(2,'002002','计算机网络','教4-510',32,7),(3,'001004','大学物理','教2-211',64,6),(4,'001002','数理统计','教3-306',48,7),(5,'002005','数据结构','教1-410',32,9),(6,'002009','操作系统','教3-109',32,8);
/*!40000 ALTER TABLE `t_curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student`
--

DROP TABLE IF EXISTS `t_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `icon` varchar(30) DEFAULT NULL COMMENT '学生头像',
  `stuNo` varchar(20) NOT NULL COMMENT '学生学号',
  `name` varchar(20) NOT NULL COMMENT '学生姓名',
  `gender` bit(1) DEFAULT b'0' COMMENT '0 男 1女',
  `classNo` varchar(10) DEFAULT NULL COMMENT '学生所在班级',
  `birth` datetime DEFAULT NULL COMMENT '生日',
  `phone` varchar(20) DEFAULT NULL COMMENT '学生手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '学生邮箱',
  `address` varchar(20) DEFAULT NULL COMMENT '学生家庭住址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student`
--

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` VALUES (1,'/userImg/timg.jpg','2006210658','ly','\0','20010101','2019-09-05 00:00:00','13712341234','ly@163.com','陕西省:西安市:高陵区'),(2,'/userImg/33.jpg','2006210660','zq','\0','20010101','2010-06-25 00:00:00','18109109010','zq@163.com','浙江省:杭州市:上城区'),(3,'/userImg/bei.jpg','2006210688','wf','\0','20010104','1999-09-21 00:00:00','18109123015','wf@163.com','山东省:潍坊市:潍城区'),(4,'/userImg/sport.jpg','2006210620','zl','','20010102','2019-09-12 00:00:00','17790246712','zl@163.com','四川省:自贡市:自流井区'),(6,'/userImg/nv.jpg','2006210451','赵晓','','20010103','2015-04-10 00:00:00','171652189031','zx@163.com','宁夏回族自治区:固原市:隆德县'),(7,'/userImg/bei.jpg','2006310004','李雪','\0','20010105','2010-07-19 00:00:00','19724106721','李雪','湖北省:宜昌市:西陵区'),(8,'/userImg/sport.jpg','2006310256','杨旭','','20010106','2006-04-28 00:00:00','15185493716','杨旭@163.com','新疆维吾尔自治区:和田地区:和田市'),(9,'/userImg/bei.jpg','2006211591','杨过','','20010102','2007-04-16 00:00:00','17149237194','yg@163.com','天津市:天津市市辖区:和平区'),(10,'/userImg/nv.jpg','2006210154','张康','\0','20010106','2000-09-13 00:00:00','13416897542','13416897542@163.com','河南省:鹤壁市:淇滨区'),(11,'/userImg/sport.jpg','2018249483','张旭','','20010103','1996-03-23 00:00:00','19127305372','zhangxu@163.com','贵州省:贵阳市:白云区'),(12,'/userImg/bei.jpg','2106497531','杨倩','\0','1001','1990-11-10 00:00:00','15429749164','yq@163.com','浙江省:杭州市:上城区');
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_studentclass`
--

DROP TABLE IF EXISTS `t_studentclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_studentclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `stuNo` varchar(20) NOT NULL COMMENT '学生学号',
  `classNo` varchar(20) NOT NULL COMMENT '班级号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_studentclass`
--

LOCK TABLES `t_studentclass` WRITE;
/*!40000 ALTER TABLE `t_studentclass` DISABLE KEYS */;
INSERT INTO `t_studentclass` VALUES (1,'2006210658','002001'),(2,'2006210658','001002'),(3,'2006210658','002009'),(4,'2006210660','002009'),(5,'2006210660','002001'),(6,'2006210660','001004'),(7,'2006210451','002005'),(8,'2006210620','002002'),(9,'2006210620','001004'),(10,'2006210688','001004'),(11,'2006210154','002009'),(12,'2006210154','002002'),(13,'2006210154','001004'),(14,'2006210154','001002'),(15,'2006211591','002001'),(16,'2006211591','001002'),(17,'2006310256','002001'),(18,'2006310256','002002'),(19,'2006310256','001004'),(20,'2006310256','001002'),(21,'2006310256','002005'),(22,'2006310256','002009'),(23,'2006310004','002005'),(24,'2006310004','002009'),(25,'2106497531','002005'),(26,'2106497531','002009'),(27,'2106497531','001004'),(28,'2106497531','001002'),(29,'2018249483','001002');
/*!40000 ALTER TABLE `t_studentclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_teacher`
--

DROP TABLE IF EXISTS `t_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacherNo` varchar(20) NOT NULL COMMENT '教工号',
  `name` varchar(20) NOT NULL COMMENT '教师姓名',
  `age` int(11) DEFAULT NULL COMMENT '教师年龄',
  `gender` bit(1) DEFAULT b'0' COMMENT '0 男 1 女',
  `professor` varchar(20) DEFAULT NULL COMMENT '职称',
  `phone` varchar(20) DEFAULT NULL COMMENT '教师手机号',
  `email` varchar(20) DEFAULT NULL COMMENT '教师邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_teacher`
--

LOCK TABLES `t_teacher` WRITE;
/*!40000 ALTER TABLE `t_teacher` DISABLE KEYS */;
INSERT INTO `t_teacher` VALUES (2,'1987102101','张方',47,'','教授','1378290394','zf@163.com'),(3,'1991032601','王磊',42,'\0','副教授','13937530382','13937530382@163.com'),(4,'1981091811','海荣',50,'','导师','13785492461','hr@163.com'),(5,'1991052416','怡悦',46,'','教授','19148657261','yy@163.com'),(6,'2001041806','李思嘉',39,'','教授','17649213154','17649213154@163.com'),(7,'20021011','章家栋',45,'\0','副教授','18159746183','zjd@163.com'),(8,'1991032601','王磊',32,'\0','副教授','13937530382','13937530382@163.com'),(9,'20021011','章家栋',55,'\0','副教授','18159746183','zjd@163.com'),(10,'2001041806','李思嘉',29,'','教授','17649213154','17649213154@163.com');
/*!40000 ALTER TABLE `t_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_teacherclass`
--

DROP TABLE IF EXISTS `t_teacherclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_teacherclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `teacherNo` varchar(20) NOT NULL COMMENT '教工号',
  `classNo` varchar(20) NOT NULL COMMENT '课程号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_teacherclass`
--

LOCK TABLES `t_teacherclass` WRITE;
/*!40000 ALTER TABLE `t_teacherclass` DISABLE KEYS */;
INSERT INTO `t_teacherclass` VALUES (1,'1987102101','001002'),(2,'1987102101','002002'),(3,'1981091811','002005'),(4,'2001041806','002009'),(5,'1981091811','001001'),(6,'20021011','002009'),(7,'1981091811','002001'),(8,'20021011','002002'),(9,'1991032601','002001'),(10,'1981091811','002002');
/*!40000 ALTER TABLE `t_teacherclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `identity` bit(1) DEFAULT NULL COMMENT '0 用户 1 管理员',
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'tom','c4ca4238a0b923820dcc509a6f75849b','tom@163.com','\0','13712121010');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-02 17:36:26
