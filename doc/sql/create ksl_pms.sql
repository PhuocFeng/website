CREATE TABLE `pms_product` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                               `code` varchar(255) DEFAULT NULL COMMENT '型号',
                               `main_picture` varchar(255) DEFAULT NULL COMMENT '图片URL',
                               `color` VARCHAR(255) DEFAULT NULL COMMENT '颜色',
                               `particle_size` VARCHAR(255) DEFAULT NULL COMMENT '粒径',
                               `material` VARCHAR(255) DEFAULT NULL COMMENT '基材',
                               `series` VARCHAR(255) DEFAULT NULL COMMENT '系列',
                               `enable` TINYINT(3) UNSIGNED DEFAULT '0' COMMENT '是否启用 1=启用，0=不启用',
                               `gmt_create` datetime DEFAULT NULL COMMENT '数据创建时间',
                               `gmt_modified` datetime DEFAULT NULL COMMENT '数据最后修改时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4  COMMENT='产品数据表';