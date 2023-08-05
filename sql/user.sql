use inventory;
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `salt` varchar(255) NOT NULL,
                        `admin` int(1) DEFAULT '0',
                        `create_time` datetime DEFAULT NULL,
                        `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                        `deleted` int(1) DEFAULT '0',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY uk_username (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT 'USER';