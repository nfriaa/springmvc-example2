
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user_role` (
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  CONSTRAINT `FK1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  CONSTRAINT `FK2` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- INSERT INTO `role` VALUES (1,'ROLE_USER');
-- INSERT INTO `role` VALUES (2,'ROLE_ADMIN');
-- 
-- INSERT INTO `user` VALUES (1, 'A', 'AA', 'a@aa.com', 'a', 'a', 1);
-- INSERT INTO `user` VALUES (2, 'B', 'BB', 'b@bb.com', 'b', 'b', 1);
-- 
-- INSERT INTO `user_role` VALUES (1,1);
-- INSERT INTO `user_role` VALUES (2,2);