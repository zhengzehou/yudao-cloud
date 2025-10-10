CREATE TABLE IF NOT EXISTS market_activity
(
    id                    bigint(20)  NOT NULL AUTO_INCREMENT,
    title                 varchar(50) NOT NULL,
    activity_type         tinyint(4)  NOT NULL,
    status                tinyint(4)  NOT NULL,
    start_time            datetime    NOT NULL,
    end_time              datetime    NOT NULL,
    invalid_time          datetime,
    delete_time           datetime,
    time_limited_discount varchar(2000),
    full_privilege        varchar(2000),
    creator               varchar(64)          DEFAULT '',
    create_time           datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater               varchar(64)          DEFAULT '',
    update_time           datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted               bit         NOT NULL DEFAULT FALSE,
    tenant_id             bigint(20)  NOT NULL,
    PRIMARY KEY (id)
) COMMENT '促销活动';

CREATE TABLE IF NOT EXISTS promotion_coupon_template
(
    id                   bigint   NOT NULL AUTO_INCREMENT,
    name                 varchar(256)  NOT NULL,
    description                 varchar(512)  NULL,
    status               int      NOT NULL,
    total_count          int      NOT NULL,
    take_limit_count     int      NOT NULL,
    take_type            int      NOT NULL,
    use_price            int      NOT NULL,
    product_scope        int      NOT NULL,
    product_scope_values      varchar(512),
    validity_type        int      NOT NULL,
    valid_start_time     datetime,
    valid_end_time       datetime,
    fixed_start_term     int,
    fixed_end_term       int,
    discount_type        int      NOT NULL,
    discount_percent     int,
    discount_price       int,
    discount_limit_price int,
    take_count           int      NOT NULL DEFAULT 0,
    use_count            int      NOT NULL DEFAULT 0,
    creator              varchar(64)           DEFAULT '',
    create_time          datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater              varchar(64)           DEFAULT '',
    update_time          datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted              bit      NOT NULL DEFAULT FALSE,
    tenant_id             bigint(20)  NOT NULL,
    PRIMARY KEY (id)
    ) COMMENT '优惠劵模板';

CREATE TABLE IF NOT EXISTS promotion_coupon
(
    id                   bigint   NOT NULL AUTO_INCREMENT,
    template_id          bigint   NOT NULL,
    name                 varchar(256)  NOT NULL,
    status               int      NOT NULL,
    user_id              bigint   NOT NULL,
    take_type            int      NOT NULL,
    use_price             int      NOT NULL,
    valid_start_time     datetime NOT NULL,
    valid_end_time       datetime NOT NULL,
    product_scope        int      NOT NULL,
    product_scope_values      varchar(512),
    discount_type        int      NOT NULL,
    discount_percent     int,
    discount_price       int,
    discount_limit_price int,
    use_order_id         bigint,
    use_time             datetime,
    creator              varchar(64)           DEFAULT '',
    create_time          datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater              varchar(64)           DEFAULT '',
    update_time          datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted              bit      NOT NULL DEFAULT FALSE,
    tenant_id             bigint(20)  NOT NULL,
    PRIMARY KEY (id)
) COMMENT '优惠劵';

CREATE TABLE IF NOT EXISTS promotion_reward_activity
(
    id              bigint   NOT NULL AUTO_INCREMENT,
    name            varchar(256)  NOT NULL,
    status          int      NOT NULL,
    start_time      datetime NOT NULL,
    end_time        datetime NOT NULL,
    remark          varchar(512),
    condition_type  int      NOT NULL,
    product_scope   int      NOT NULL,
    product_scope_values varchar(512),
    rules           varchar(3000),
    creator         varchar(64)           DEFAULT '',
    create_time     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater         varchar(64)           DEFAULT '',
    update_time     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted         bit      NOT NULL DEFAULT FALSE,
    tenant_id       bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '满减送活动';

CREATE TABLE IF NOT EXISTS promotion_discount_activity
(
    id          bigint   NOT NULL AUTO_INCREMENT,
    name        varchar(256)  NOT NULL,
    status      int      NOT NULL,
    start_time  datetime NOT NULL,
    end_time    datetime NOT NULL,
    remark      varchar(512),
    creator     varchar(64)           DEFAULT '',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater     varchar(64)           DEFAULT '',
    update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     bit      NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
) COMMENT '限时折扣活动';

CREATE TABLE IF NOT EXISTS promotion_seckill_activity
(
    id                 bigint   NOT NULL AUTO_INCREMENT,
    spu_id             bigint   NOT NULL,
    name               varchar(256)  NOT NULL,
    status             int      NOT NULL,
    remark             varchar(512),
    start_time         varchar(32)  NOT NULL,
    end_time           varchar(32)  NOT NULL,
    sort               int      NOT NULL,
    config_ids         varchar(256)  NOT NULL,
    order_count        int      NOT NULL,
    user_count         int      NOT NULL,
    total_price        int      NOT NULL,
    total_limit_count  int,
    single_limit_count int,
    stock              int,
    total_stock        int,
    creator            varchar(64)           DEFAULT '',
    create_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater            varchar(64)           DEFAULT '',
    update_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted            bit      NOT NULL DEFAULT FALSE,
    tenant_id          bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '秒杀活动';

CREATE TABLE IF NOT EXISTS promotion_seckill_config
(
    id          bigint   NOT NULL AUTO_INCREMENT,
    name        varchar(256)  NOT NULL,
    start_time  varchar(32)  NOT NULL,
    end_time    varchar(32)  NOT NULL,
    slider_pic_urls     varchar(3000)  NOT NULL,
    status      int      NOT NULL,
    creator     varchar(64)           DEFAULT '',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater     varchar(64)           DEFAULT '',
    update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     bit      NOT NULL DEFAULT FALSE,
    tenant_id   bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '秒杀时段配置';

CREATE TABLE IF NOT EXISTS promotion_combination_activity
(
    id                 bigint   NOT NULL AUTO_INCREMENT,
    name               varchar(256)  NOT NULL,
    spu_id             bigint,
    total_limit_count  int      NOT NULL,
    single_limit_count int      NOT NULL,
    start_time         varchar(32)  NOT NULL,
    end_time           varchar(32)  NOT NULL,
    user_size          int      NOT NULL,
    total_num          int      NOT NULL,
    success_num        int      NOT NULL,
    order_user_count   int      NOT NULL,
    virtual_group      int      NOT NULL,
    status             int      NOT NULL,
    limit_duration     int      NOT NULL,
    creator            varchar(64)           DEFAULT '',
    create_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater            varchar(64)           DEFAULT '',
    update_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted            bit      NOT NULL DEFAULT FALSE,
    tenant_id          bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '拼团活动';

CREATE TABLE IF NOT EXISTS promotion_article_category
(
    id          bigint   NOT NULL AUTO_INCREMENT,
    name        varchar(256)  NOT NULL,
    pic_url     varchar(255),
    status      int      NOT NULL,
    sort        int      NOT NULL,
    creator     varchar(64)           DEFAULT '',
    create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater     varchar(64)           DEFAULT '',
    update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted     bit      NOT NULL DEFAULT FALSE,
    tenant_id   bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '文章分类表';

CREATE TABLE IF NOT EXISTS promotion_article
(
    id               bigint   NOT NULL AUTO_INCREMENT,
    category_id      bigint   NOT NULL,
    title            varchar(256)  NOT NULL,
    author           varchar(128),
    pic_url          varchar(255)  NOT NULL,
    introduction     varchar(1024),
    browse_count     varchar(32),
    sort             int      NOT NULL,
    status           int      NOT NULL,
    spu_id           bigint   NOT NULL,
    recommend_hot    bit      NOT NULL,
    recommend_banner bit      NOT NULL,
    content          varchar(3000)  NOT NULL,
    creator          varchar(64)           DEFAULT '',
    create_time      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater          varchar(64)           DEFAULT '',
    update_time      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted          bit      NOT NULL DEFAULT FALSE,
    tenant_id        bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '文章管理表';

CREATE TABLE IF NOT EXISTS promotion_diy_template
(
    id                 bigint   NOT NULL AUTO_INCREMENT,
    name               varchar(256)  NOT NULL default 0,
    used               bit      NOT NULL,
    used_time          varchar(32),
    remark             varchar(512),
    preview_pic_urls   varchar(1024),
    property           text  NOT NULL,
    creator            varchar(64)           DEFAULT '',
    create_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater            varchar(64)           DEFAULT '',
    update_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted            bit      NOT NULL DEFAULT FALSE,
    tenant_id          bigint   NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
) COMMENT '装修模板';
CREATE TABLE IF NOT EXISTS promotion_diy_page
(
    id                 bigint   NOT NULL AUTO_INCREMENT,
    template_id        bigint   NULL,
    name               varchar(256)  NOT NULL,
    remark             varchar(512),
    preview_pic_urls   varchar(1024),
    property           text,
    creator            varchar(64)           DEFAULT '',
    create_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updater            varchar(64)           DEFAULT '',
    update_time        datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted            bit      NOT NULL DEFAULT FALSE,
    tenant_id          bigint   NOT NULL,
    PRIMARY KEY (id)
) COMMENT '装修页面';

CREATE TABLE IF NOT EXISTS promotion_banner (
    id BIGINT auto_increment NOT NULL,
    title varchar(256) NOT NULL COMMENT '标题',
    url varchar(256) NULL COMMENT '跳转链接',
    pic_url varchar(256) NULL COMMENT '图片链接',
    sort INT NULL COMMENT '排序',
    status INT DEFAULT 0 NULL COMMENT '状态',
    position INT DEFAULT 0 NULL COMMENT '定位',
    memo varchar(512) NULL COMMENT '备注',
    browse_count INT DEFAULT 0 NULL COMMENT '点击次数',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time DATETIME NULL,
    creator varchar(100) NULL,
    updater varchar(100) NULL,
    deleted BIT DEFAULT 0 NULL COMMENT '是否删除',
    tenant_id          bigint   NOT NULL,
    CONSTRAINT promotion_banner_pk PRIMARY KEY (id)
    )
    COMMENT='promotion_banner';

CREATE TABLE IF NOT EXISTS promotion_combination_record (
    id BIGINT auto_increment NOT NULL,
    activity_id BIGINT NOT NULL COMMENT '拼团活动编号',
    combination_price INT NOT NULL COMMENT '拼团商品单价',
    spu_id BIGINT NOT NULL COMMENT 'SPU 编号',
    spu_name varchar(256) NULL COMMENT '商品名字',
    pic_url varchar(256) NULL COMMENT '图片链接',
    sku_id BIGINT NOT NULL COMMENT 'SKU 编号',
    count INT NOT NULL COMMENT '购买的商品数量',
    user_id BIGINT DEFAULT 0 NULL COMMENT '用户编号',
    nickname varchar(512) NULL COMMENT '用户昵称',
    avatar varchar(512) NULL COMMENT '用户头像',
    head_id BIGINT NULL COMMENT '团长编号',
    status INT NULL COMMENT '开团状态',
    order_id BIGINT NULL COMMENT '订单编号',
    user_size INT NULL COMMENT '开团需要人数',
    user_count INT NULL COMMENT '已加入拼团人数',
    virtual_group BIT DEFAULT 0 COMMENT '是否虚拟成团',
    expire_time DATETIME NOT NULL COMMENT '过期时间',
    start_time DATETIME NOT NULL COMMENT '开始时间',
    end_time DATETIME NOT NULL COMMENT '结束时间（成团时间/失败时间）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time DATETIME NULL,
    creator varchar(100) NULL,
    updater varchar(100) NULL,
    deleted BIT DEFAULT 0 NULL COMMENT '是否删除',
    tenant_id BIGINT NOT NULL COMMENT '租户',
    CONSTRAINT promotion_combination_record_pk PRIMARY KEY (id)
    )
    COMMENT='拼团记录';

CREATE TABLE IF NOT EXISTS promotion_point_activity (
    id BIGINT auto_increment NOT NULL,
    spu_id BIGINT NOT NULL COMMENT 'SPU 编号',
    status INT NULL COMMENT '活动状态',
    remark varchar(512) NULL COMMENT '备注',
    sort INT NULL COMMENT '排序',
    stock INT NULL COMMENT '积分商城活动库存',
    total_stock INT NOT NULL COMMENT '积分商城活动总库存',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time DATETIME NULL,
    creator varchar(100) NULL,
    updater varchar(100) NULL,
    deleted BIT DEFAULT 0 NULL COMMENT '是否删除',
    tenant_id BIGINT NOT NULL COMMENT '租户',
    CONSTRAINT promotion_point_activity_pk PRIMARY KEY (id)
    )
    COMMENT='积分商城活动';
CREATE TABLE `promotion_discount_product` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
      `activity_id` bigint NOT NULL COMMENT '限时折扣活动的编号',
      `spu_id` bigint NOT NULL COMMENT '商品 SPU 编号',
      `sku_id` bigint NOT NULL COMMENT '商品 SKU 编号',
      `discount_type` int NOT NULL COMMENT '折扣类型',
      `discount_percent` int NOT NULL COMMENT '折扣百分比',
      `discount_price` int NOT NULL COMMENT '优惠金额，单位：分',
      `activity_name` varchar(256) NOT NULL COMMENT '活动标题',
      `activity_status` int NOT NULL COMMENT '活动状态',
      `activity_start_time` datetime NOT NULL COMMENT '活动开始时间点',
      `activity_end_time` datetime NOT NULL COMMENT '活动结束时间点',
      `creator` varchar(64) DEFAULT '' COMMENT '创建者',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `updater` varchar(64) DEFAULT '' COMMENT '更新者',
      `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
      `tenant_id` bigint NOT NULL DEFAULT '0',
      PRIMARY KEY (`id`)
) COMMENT='限时折扣商品';