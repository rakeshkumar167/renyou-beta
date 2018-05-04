-- MySQL dump 10.13  Distrib 5.7.19, for macos10.12 (x86_64)
--
-- Host: localhost    Database: renyou
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'','Philips'),(2,'','Syska'),(3,'','Neelkamal'),(4,'','RoyalOak'),(5,'','Jaguar'),(6,'','Kajaria'),(7,'','Hindware'),(8,'','Tu Casa'),(9,'','999Store');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `designer`
--

LOCK TABLES `designer` WRITE;
/*!40000 ALTER TABLE `designer` DISABLE KEYS */;
INSERT INTO `designer` VALUES (1,'234, Cubbon Park','Bangalore','Décor Dreams is India\'s leading Interior Designing company, currently serving in Bangalore, Hyderabad, Pune and Mumbai. We offer end to end Interior and Home decor solutions tailored for flats/villas/independent houses. Our goal is to provide excellent \"Customer Experience\" and deliver their \"Dream Home\" in smooth and timely manner. Decor Dreams Homes comes with 5 year warranty and 45 Days delivery promise!\r\n\r\nWe undertake projects ranging from Designing, Modular Kitchens, Full House Interior to Turnkey Solutions for Apartments/Villas. Below are the key products/services offered by us:\r\n- Modular Kitchens\r\n- Wardrobes & Storage Units\r\n- Beds and other furniture\r\n- Wall paneling & Partitions\r\n- False Ceiling\r\n- Light and Paint Solutions\r\n- Wallpapers & Artistic work\r\n- Stone Cladding and Wall decor\r\n- Wooden flooring\r\n- Tiling & Minor civil modifications\r\n- Home decor and more\r\n\r\nServices Provided\r\n3D Rendering, Bathroom Design, Bedroom Design, Custom Bathroom Vanities, Custom Blinds & Shades, Custom Bookcases, Custom Cabinets, Custom Entertainment Centers, Custom Furniture, Custom Home Bars, Custom Kitchen Cabinets, Custom Shelving, Custom Walk-in Wardrobes, Dining Room Design, Drafting, Furniture Selection, Home Office Design, Home Theater Design, Interior Design, Kids Bedroom Design, Kitchen Design, Kitchen Renovation & Remodeling, Lighting Design, Living Room Design, Playroom Design, Space Planning, Staircase Design, Wardrobe Design, Dinning Room Design','Décor Dreams',''),(2,'34, Street 56','Bangalore','End your quest for high style and ultra-modern signature modular home interiors with ASENSE’s creative fusions imbibed with exceptional detailing! \r\n\r\nAsense is a celebration of concepts and ideas that offer a refined touch of elite customization to interior designing. Based in Bangalore, this design house embodies elegance and innovative style in its interior designing of residential apartments and modular kitchens. The firm is driven by an unwavering zeal to deliver optimum quality and design value in every project.\r\n\r\nServices Provided\r\nCustom Furniture, Interior Design, Kitchen Design\r\n','asense',''),(3,'23, Park avenue','Bangalore','S-Cube stands for (1) Customised design, (2) Quality Implementation and (3) Commitment and Customer satisfaction : \r\n1. Customised Design : is evolved keeping in mind the objective of the customer. Understanding requirements, needs, likes n dislikes, profile & preferences, usage patterns are Core to design thinking for each customer and each member of the family. We combine customer inputs, utility requirements and aesthetic to create the design output relevant to every individual customer.\r\n2. Quality Implementation : Based on the design finalized, a detailed project plan and procurement plan is drawn up. For procurement the design is broken down to its basic elements and timely sourcing planned keeping in mind the best in class components across wood, ply, laminate, veneer, hardware, electrical - internal and external fittings, gypboard, steel & aluminium, glass & mirror, natural stones - granites, marbles, sandstone, central air conditioners, furnitures, et al. Similarly, to ensure quality workmanship with respect to carpentry, electricals, polish, plumbing, tiling, false ceiling, painting, sofa making, flooring, etc we deploy skilled and experienced manpower and ensure they follow the processes laid down viz project plan, daily work schedule, quality control, supervision, etc to enable and ensure quality and on-time output.\r\n3. Commitment and Customer Satisfaction : Are the drivers of our business. We believe in keeping commitment and we strive to ensure that every completed project is an addition of a happy and reference-able customer. This is our simple but effective growth philosophy.\r\nPlease contact us at the following :\r\nCall Nanndini or Shantanu @ +91 90360 95211 or +91 98456 97271 or mail : nandinibose04@gmail.com / shantanu.bose1@gmail.com','S-Cube','');
/*!40000 ALTER TABLE `designer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,NULL,'project/1_coastal-living-room.jpg',NULL,NULL,1,NULL),(2,NULL,'project_space/2_coastal-bedroom.jpg',NULL,NULL,NULL,2),(3,NULL,'project_space/1_indian-rendering.jpg',NULL,NULL,NULL,1),(4,NULL,'project/2_contemporary-spaces.jpg',NULL,NULL,2,NULL),(5,NULL,'project_space/3_contemporary-kitchen.jpg',NULL,NULL,NULL,3),(6,NULL,'project_space/3_contemporary-kitchen1.jpg',NULL,NULL,NULL,3),(7,NULL,'project_space/4_modern-living-room.jpg',NULL,NULL,NULL,4),(8,NULL,'project_space/5_contemporary-kids.jpg',NULL,NULL,NULL,5),(9,NULL,'project/3_skylark-living-room.jpg',NULL,NULL,3,NULL),(10,NULL,'designer/1_ileana.jpeg',1,NULL,NULL,NULL),(11,NULL,'designer/2_kareena.jpg',2,NULL,NULL,NULL),(12,NULL,'designer/3_priya.jpg',3,NULL,NULL,NULL);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'','Mosaic Multicolor Metal Table Lamp by Tu Casa',8,7),(2,'','Teak Wood Starnet Table Lamp ',8,7),(3,'','Fibre 70 x 0.8 x 30 Inch Abstract Blue Lines Framed Art Panels ',9,12);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_attribute`
--

LOCK TABLES `product_attribute` WRITE;
/*!40000 ALTER TABLE `product_attribute` DISABLE KEYS */;
INSERT INTO `product_attribute` VALUES (1,'Color',1),(2,'Weight',1),(3,'Dimensions',1);
/*!40000 ALTER TABLE `product_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_attribute_type`
--

LOCK TABLES `product_attribute_type` WRITE;
/*!40000 ALTER TABLE `product_attribute_type` DISABLE KEYS */;
INSERT INTO `product_attribute_type` VALUES (2,'Functional'),(1,'Physical');
/*!40000 ALTER TABLE `product_attribute_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,'','Furniture',NULL),(2,'','Wooden Furnitures',1),(3,'','Plastic Furniture',1),(4,'','Emulsions',NULL),(5,'','Tiles',NULL),(6,'','Bathroom Fixtures',NULL),(7,'','Lightings',NULL),(8,'','Decorative Lights',7),(9,'','Special Lightings',7),(10,'','WoodWork',NULL),(11,'','Wardrobe',10),(12,'','WallArt',NULL);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_category_to_product_attribute_rel`
--

LOCK TABLES `product_category_to_product_attribute_rel` WRITE;
/*!40000 ALTER TABLE `product_category_to_product_attribute_rel` DISABLE KEYS */;
INSERT INTO `product_category_to_product_attribute_rel` VALUES (1,1,1),(2,3,1),(3,2,2),(4,3,2),(5,1,2),(6,3,3),(7,2,3),(8,1,3);
/*!40000 ALTER TABLE `product_category_to_product_attribute_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_space_to_product_rel`
--

LOCK TABLES `product_space_to_product_rel` WRITE;
/*!40000 ALTER TABLE `product_space_to_product_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_space_to_product_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `product_to_product_attribute_rel`
--

LOCK TABLES `product_to_product_attribute_rel` WRITE;
/*!40000 ALTER TABLE `product_to_product_attribute_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_to_product_attribute_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'This Sobha City apartment was designed and executed by Decor Dreams. It was a turnkey project involving all the Interior elements.\r\nProject Year: 2015\r\nProject Cost: ?10,00,001 - ?20,00,000\r\nCountry: India\r\nPIN Code: 560064','Bangalore','Sobha City ','','Completed','','Modern',1),(2,'Project Year: 2017\r\nProject Cost: ?20,00,001 - ?50,00,000\r\nCountry: India\r\nPIN Code: 560035','Bangalore','JR Greenwich Villas','','Completed','','Contemporary',1),(3,'Project Year: 2017\r\nProject Cost: ?10,00,001 - ?20,00,000','Bangalore','Appartment in Skylark Esta','\0','Completed','Medieval','Modern',2);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_space`
--

LOCK TABLES `project_space` WRITE;
/*!40000 ALTER TABLE `project_space` DISABLE KEYS */;
INSERT INTO `project_space` VALUES (1,'','Bedroom 1','',1,1),(2,'','Bedroom 2','',1,1),(3,'','Kitchen','',2,5),(4,'','Living room','',2,2),(5,'','Kids fantasy room','',2,6);
/*!40000 ALTER TABLE `project_space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `space`
--

LOCK TABLES `space` WRITE;
/*!40000 ALTER TABLE `space` DISABLE KEYS */;
INSERT INTO `space` VALUES (1,'','Bedroom',1),(2,'','Living room',1),(3,'','Lobby',5),(4,'','Dining Area',1),(5,'','Kitchen',1),(6,'','Kids room',1);
/*!40000 ALTER TABLE `space` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `space_type`
--

LOCK TABLES `space_type` WRITE;
/*!40000 ALTER TABLE `space_type` DISABLE KEYS */;
INSERT INTO `space_type` VALUES (2,'Commercial'),(5,'Hotel'),(3,'Mall'),(4,'Office'),(1,'Residential'),(6,'Restaurant');
/*!40000 ALTER TABLE `space_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-05  1:23:02
