-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_examenes_spring_boot
-- ------------------------------------------------------
-- Server version	9.0.1

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `categoria_id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categoria_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (71,'CT Baracoa','Baracoa'),(72,'CA Maisi','Maisí'),(73,'CA Imias','Imías'),(74,'CT Yateras','Yateras'),(75,'CT San Antonio del Sur','San Antonio'),(76,'CT Caimanera','Caimanera'),(77,'GTMO','Guantánamo'),(78,'NP','Niceto Peres'),(79,'MT','Manuel Tames'),(80,'CA El Salvador','El Salvador');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examenes`
--

DROP TABLE IF EXISTS `examenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `examenes` (
  `examen_id` bigint NOT NULL AUTO_INCREMENT,
  `activo` bit(1) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `numero_de_preguntas` varchar(255) DEFAULT NULL,
  `puntos_maximos` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `categoria_categoria_id` bigint DEFAULT NULL,
  `prioridad` varchar(255) DEFAULT NULL,
  `categoria_del_cliente` varchar(255) DEFAULT NULL,
  `organismo` varchar(255) DEFAULT NULL,
  `consejo_popular` varchar(255) DEFAULT NULL,
  `costo_de_instalacion` varchar(255) DEFAULT NULL,
  `cuota` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `enlace` varchar(255) DEFAULT NULL,
  `municipio` varchar(255) DEFAULT NULL,
  `no_adsl` varchar(255) DEFAULT NULL,
  `numero_de_solicitud` varchar(255) DEFAULT NULL,
  `seguimiento` varchar(255) DEFAULT NULL,
  `soliciud` varchar(255) DEFAULT NULL,
  `telefono_de_contacto` varchar(255) DEFAULT NULL,
  `estado_del_servicio` varchar(255) DEFAULT NULL,
  `velocidad` varchar(255) DEFAULT NULL,
  `solicitud` varchar(255) DEFAULT NULL,
  `fecha_de_solicitud` date DEFAULT NULL,
  `estado_de_calificacion_de_los_centros` varchar(255) DEFAULT NULL,
  `evaluacion` varchar(255) DEFAULT NULL,
  `fecha_respuesta_calificacion_operaciones` date DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `observaciones_especialista_de_operaciones` varchar(255) DEFAULT NULL,
  `fecha_de_ejecucion_estimadaaproponer` date DEFAULT NULL,
  `propuesta_de_soluion_tecnica` varchar(255) DEFAULT NULL,
  `tipo_de_recursosademandar` varchar(255) DEFAULT NULL,
  `observacion_esp_inversiones` varchar(255) DEFAULT NULL,
  `tipo_de_servicio` varchar(255) DEFAULT NULL,
  `instalada` varchar(255) DEFAULT NULL,
  `programa_proyecto` varchar(255) DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`examen_id`),
  KEY `FK9e3vkr595xf5ntcw0ih72lifw` (`categoria_categoria_id`),
  KEY `FKa027tdoo6kxwi3vhgobxn8j89` (`usuario_id`),
  CONSTRAINT `FK9e3vkr595xf5ntcw0ih72lifw` FOREIGN KEY (`categoria_categoria_id`) REFERENCES `categorias` (`categoria_id`),
  CONSTRAINT `FKa027tdoo6kxwi3vhgobxn8j89` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examenes`
--

LOCK TABLES `examenes` WRITE;
/*!40000 ALTER TABLE `examenes` DISABLE KEYS */;
INSERT INTO `examenes` VALUES (61,_binary '','Direccion Municipal de Deporte y Recreación','','','AZCUBA',76,'Alta','Proyectos_Priorizados','CANEC_SA','','we','t','Direccion municipal de deportes y recreacion','6767','','ewwe','4','DT',NULL,'34','Solicitud_Nueva','8Mbps','Alta','2024-10-23','No_Apto','Operaciones','2025-03-20','santo Se necesita de equipo tplink para su implementación','santo1',NULL,'d','iiii','pendiente a recursos',NULL,NULL,NULL,NULL),(62,_binary '','Consulado Habana','','','CONJUSOL',72,'Alta','Informatización_CTC','EMPA','gfg','as','f','ghhghg','dfdfdf','','f','1','VPCM',NULL,'sfsdfsdff','Con_OS_Siprec','4Mbps','Alta_Mig','2025-04-24','DI','Inversiones','2024-10-10','Parámetros del Cable incorrecto','Se requiere de cable UTP','2000-12-05','DAI','DAI!','000DAI',NULL,NULL,NULL,NULL),(63,_binary '','Educación Salud','','','EPCONS',71,'Baja','Sin_Clasificación','Fondo_Cubano','baitiquiri','trt','fd','ewewewewe','4433','','445df','22','DT',NULL,'2323','Solicitud_Nueva','155Mbps','CL','2025-04-02','DI','Operaciones','2025-04-15','w','w',NULL,'','','',NULL,NULL,NULL,NULL),(64,_binary '','dffdffddd','','','MES',73,'Alta','MIPYMES','MES','43443','rttrtr','trtrtr','56565','fdf54','','454','23','VPCM',NULL,'fgf','Solicitud_Nueva','2Mbps','CL','2025-04-03','DI','Operaciones','2024-11-11','','',NULL,'','','',NULL,NULL,NULL,NULL),(65,_binary '','tyyty','','','GAF',75,'Alta','Informatización_PCC','GAF','ddd','tyy','yty','fgtf','tytyt','','ttyyy','66','VPCM',NULL,'tytyyt','Con_OS_Siprec','4Mbps','Baja','2025-04-03','','Operaciones',NULL,'','',NULL,'','','',NULL,'Pendiente_de_Inlalacion',NULL,NULL),(66,_binary '','san','','','Otros',76,'Alta','MINED','Otro','r3','g','g','ereetr','dffdd','','f454','4','VPCM',NULL,'4gfgffg','Con_OS_Siprec','10Mbps','Alta','2025-04-03','DI','Operaciones','2025-04-11','Pendiente a FO','Se requieren 62m','2086-12-04','','','',NULL,NULL,NULL,NULL),(67,_binary '','RADIOCUBA','','','PCC',74,'Baja','Informatización_PCC','PCC','err','55','erer','erre','rer','','ere','34','DT',NULL,'43454','OK_Red_Móvil','20Mbps','Baja','2025-04-12','DI','Operaciones','2025-04-30','INA','ina1','2025-04-12','','','',NULL,NULL,NULL,NULL),(68,_binary '','citma','','','CITMA',80,'Baja','MIPYMES','CITMA','tr','fdfdf','4ghh','yuyu','454545','','4555','54','VPCM',NULL,'445','En_Proceso','10Mbps','CVIV','2025-02-10','','',NULL,'','','2025-04-13','','','',NULL,NULL,NULL,NULL),(69,_binary '','artex','','','Artex',73,'Baja','Proyectos_Priorizados','Artex','erer','cv','hjj','reere','tt','','trt','3','VPCM',NULL,'545','Solicitud_Nueva','80Mbps','CL','2025-02-25','No_Apto','Inversiones','2025-04-13','','','2025-04-13','','','',NULL,NULL,NULL,NULL),(70,_binary '','ujc salud','','','UJC',71,'Baja','Informatización_CTC','UJC','rerere','erer','rerer','wwwwwwwwwwwwwwwwwwwwwwwww','sddsd','','eew','34','DT',NULL,'2333434','En_Proceso','10Mbps','CVIV','2025-04-15','','',NULL,'','','2025-04-15','','','',NULL,NULL,NULL,NULL),(71,_binary '','Centro Comercial El Salvador Suministro Agropecuario','','','EMPA',80,'Alta','MINED','MINAG','Bayate','910','140','Bayate','','','212811112','100','VPCM',NULL,'','DI','2Mbps','Alta','2025-04-16','','',NULL,'','','2025-04-16','','','',NULL,NULL,NULL,NULL),(72,_binary '','Solvisión Internet','','','ICRT',77,'Baja','Proyectos_Priorizados','ICRT','Caribe','144','123','Calle 13 Norte #1151 Esq. 5 Oeste. ','GTED3435','','','1','DT',NULL,'3453535','OK','20Mbps','Alta','2024-02-06','','',NULL,'','','2025-04-17','','','','Dedicado_Internacional','Instalada','Otros_Programas',NULL);
/*!40000 ALTER TABLE `examenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preguntas`
--

DROP TABLE IF EXISTS `preguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preguntas` (
  `pregunta_id` bigint NOT NULL AUTO_INCREMENT,
  `contenido` varchar(5000) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `opcion2` varchar(255) DEFAULT NULL,
  `opcion3` varchar(255) DEFAULT NULL,
  `opcion4` varchar(255) DEFAULT NULL,
  `respuesta` varchar(255) DEFAULT NULL,
  `examen_examen_id` bigint DEFAULT NULL,
  `opcion1` varchar(255) DEFAULT NULL,
  `respuesta_dada` varchar(255) DEFAULT NULL,
  `estado_de_calificacion_de_los_centros` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `evaluacion` varchar(255) DEFAULT NULL,
  `observaciones_especialista_de_operaciones` varchar(255) DEFAULT NULL,
  `fecha_respuesta_calificacion_operciones` datetime(6) DEFAULT NULL,
  `fecha_respuesta_calificacion_operaciones` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`pregunta_id`),
  KEY `FK9g0sx7pv0vsvc4uksis4egp4j` (`examen_examen_id`),
  CONSTRAINT `FK9g0sx7pv0vsvc4uksis4egp4j` FOREIGN KEY (`examen_examen_id`) REFERENCES `examenes` (`examen_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--

LOCK TABLES `preguntas` WRITE;
/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `rol_id` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'NORMAL');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_rol` (
  `usuario_rol_id` bigint NOT NULL AUTO_INCREMENT,
  `rol_rol_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`usuario_rol_id`),
  KEY `FK7j1tyvjj1tv8gbq7n6f7efccc` (`rol_rol_id`),
  KEY `FKktsemf1f6awjww4da0ocv4n32` (`usuario_id`),
  CONSTRAINT `FK7j1tyvjj1tv8gbq7n6f7efccc` FOREIGN KEY (`rol_rol_id`) REFERENCES `roles` (`rol_id`),
  CONSTRAINT `FKktsemf1f6awjww4da0ocv4n32` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (1,1,1),(2,1,2),(3,2,3),(10,2,10),(11,2,11),(12,2,12),(13,2,13),(14,2,14),(15,2,15),(16,2,16),(17,2,17),(18,2,18),(19,2,19);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `perfil` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `ctlc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Girandy','santos.columbie@etecsa.cu',_binary '','santod','$2a$10$voB65MxwxHk3ydfhvf.lXOLG0b0qt0FnL9fJ3dgzrnBRQZ1TaFuwe','foto.pnp','59888006','santod',NULL),(2,'Girandy','santos.columbie@etecsa.cu',_binary '','santodgc','$2a$10$9T.mNHVq.1mD/s20zL0exuZP5.So2YL5bNdeugJAsCk2/hO0FrqSC','foto.pnp','59888006','santodgc',NULL),(3,'www','www@w.cu',_binary '','web','$2a$10$o3geM/0I7tLMfdhSUtItaOQv0dK5wU67JHZbxkx.Ri.zuHCjc1yDm','defaul.png','2313313233','www','null'),(10,'ccc','cc@ff.gh',_binary '','ccc','$2a$10$e/HpX.odTF8r29ye8Tl.0O1GiGsJPhVchSXmfRrrXgmMto4HsJyOC','defaul.png','345','ccc','baracoa'),(11,'nnn','n@nj',_binary '','nnn','$2a$10$JyHg/9GYQWwQ1kiF34gt.eWL6T0KHk1XXiBcHiVeBTpQ80nNVK2X.','defaul.png','345','n','Salvador'),(12,'nnn','n@njh',_binary '','nnn','$2a$10$ykZPybluLhMXCTiRapCtbuZ2rXA0YHs0U7fFk/McfXCI.ogEM535y','defaul.png','345','nh','Salvador'),(13,'nnn','n@njh',_binary '','nnn','$2a$10$eh4s.CeFYASSdS5cniksgO8ynvV90.SX6wCUYUGq5ic1meZBopSbm','defaul.png','345','nhm','Salvador'),(14,'nnn','n@njh',_binary '','nnn','$2a$10$WNdI3Bq1UE4K.WklAl5VGeDQU0n2UtNBKfGM64xAtzGEs/QPzE4Vm','defaul.png','345','nhmui','Salvador'),(15,'nnn','n@njh',_binary '','nnn','$2a$10$PswhR8Q5L.HGye6/617l0eOVJhHmFOF3V07OhbPS7tF15hhG49lj.','defaul.png','345','nhmui1','Salvador'),(16,'nnn','n@njh',_binary '','nnn','$2a$10$Q3uq4xaywBcSLWkfuSuTNeC36ShBw1.qO6hqnISpDr7D6puS00FNu','defaul.png','345','nhmui12','Salvador'),(17,'nnn','n@njh',_binary '','nnn','$2a$10$.T4KvBJqgSKIVbekWi6KzOoamD14dOawWABgO2fsxT5hrFrgTdSU2','defaul.png','345','nhmui1233333','Salvador'),(18,'nnn','n@njh',_binary '','nnn','$2a$10$Bu9PyUtYnUpYC700GCgl6.yl1Bo6u..CyooeyMWqxqzgFPLTEsKrO','defaul.png','345','nhmui1234','Salvador'),(19,'nnn','n@njh',_binary '','nnn','$2a$10$uxJGhjUHLc7R7jdrPeZlReS54ge6IG46F1ZHvtrvjxExJFX1DYNYW','defaul.png','345','nhmui12345','Salvador');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-04 17:33:52
