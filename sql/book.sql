use inventory;
CREATE TABLE `book` (
                        `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                        `title` VARCHAR(255) default "",
                        `author` VARCHAR(255) default "",
                        `isbn` VARCHAR(32) default "",
                        `price` decimal(6,3) default 0,
                        `quantity` BIGINT(11) NOT NULL DEFAULT 0,
                        `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`))
    ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARACTER SET = utf8mb4 comment "BOOK";