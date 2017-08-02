CREATE DATABASE  IF NOT EXISTS `samplespring` /*!40100 DEFAULT CHARACTER SET utf8 */;

 use mysql;
 create user 'samplespring'@'localhost' identified by 'samplespringpw';
 GRANT ALL PRIVILEGES ON samplespring.* TO 'samplespring'@'localhost' with grant option;
 select * from user;
