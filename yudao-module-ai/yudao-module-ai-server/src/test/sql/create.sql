CREATE TABLE `ai_api_key` (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
      `name` varchar(256) NOT NULL COMMENT '名称',
      `api_key` varchar(256) NULL COMMENT '密钥',
      `platform` varchar(64) NOT NULL COMMENT '平台',
      `url` varchar(128) NOT NULL COMMENT 'API 地址',
      `status` int NULL DEFAULT 0 COMMENT '状态',

      `creator` varchar(64) DEFAULT '' COMMENT '创建者',
      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `updater` varchar(64) DEFAULT '' COMMENT '更新者',
      `update_time` datetime NULL  COMMENT '更新时间',
      `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
      `tenant_id` bigint NOT NULL DEFAULT '0',
      PRIMARY KEY (`id`)
) COMMENT='AI API 秘钥';

CREATE TABLE `ai_chat_role` (
        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
        `name` varchar(256) NOT NULL COMMENT '名称',
        `avatar` varchar(256) NULL COMMENT '角色头像',
        `category` varchar(64) NULL COMMENT '角色分类',
        `description` varchar(3000) NULL COMMENT '角色描述',
        `system_message` varchar(3000) NULL COMMENT '角色设定',
        `user_id` bigint NOT NULL default 0 COMMENT '用户编号',
        `model_id` varchar(512) NOT NULL COMMENT '模型编号',
        `knowledge_ids` varchar(3000) NULL COMMENT '引用的知识库编号列表',
        `tool_ids` varchar(512) NULL COMMENT '引用的工具编号列表',
        `mcp_client_names` varchar(3000) NULL COMMENT '引用的 MCP Client 名字列表',
        `public_status` bit(1) NULL DEFAULT 0 COMMENT '是否公开',
        `sort` int NULL DEFAULT 0 COMMENT '排序值',
        `status` int NULL DEFAULT 0 COMMENT '状态',

        `creator` varchar(64) DEFAULT '' COMMENT '创建者',
        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        `updater` varchar(64) DEFAULT '' COMMENT '更新者',
        `update_time` datetime NULL  COMMENT '更新时间',
        `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
        `tenant_id` bigint NOT NULL DEFAULT '0',
        PRIMARY KEY (`id`)
) COMMENT='AI 聊天角色';
CREATE INDEX ai_chat_role_name_IDX USING BTREE ON ai_chat_role (name);
CREATE INDEX ai_chat_role_user_id_IDX USING BTREE ON ai_chat_role (user_id);
CREATE INDEX ai_chat_role_create_time_IDX USING BTREE ON ai_chat_role (create_time);


CREATE TABLE `ai_model` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `key_id` bigint NOT NULL COMMENT 'API 秘钥编号',
    `name` varchar(256) NULL COMMENT '模型名称',
    `model` varchar(64) NULL COMMENT '模型标志',
    `platform` varchar(64) NULL COMMENT '平台',
    `type` int NULL COMMENT '类型',
    `sort` int NULL DEFAULT 0 COMMENT '排序值',
    `status` int NULL DEFAULT 0 COMMENT '状态',
    `temperature` float NOT NULL default 0.0 COMMENT '温度参数',
    `max_tokens` int NOT NULL COMMENT '单条回复的最大 Token 数量',
    `max_contexts` int NULL COMMENT '上下文的最大 Message 数量',

    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NULL  COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) COMMENT='AI MODEL';

CREATE TABLE `ai_tool` (
   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
   `name` varchar(256) NOT NULL COMMENT '名称',
   `description` varchar(3000) NULL COMMENT '工具描述',
   `status` int NULL DEFAULT 0 COMMENT '状态',

   `creator` varchar(64) DEFAULT '' COMMENT '创建者',
   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `updater` varchar(64) DEFAULT '' COMMENT '更新者',
   `update_time` datetime NULL  COMMENT '更新时间',
   `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
   `tenant_id` bigint NOT NULL DEFAULT '0',
   PRIMARY KEY (`id`)
) COMMENT='AI 工具';
CREATE INDEX ai_tool_name_IDX USING BTREE ON ai_tool (name);
CREATE INDEX ai_tool_create_time_IDX USING BTREE ON ai_tool (create_time);


CREATE TABLE `ai_chat_conversation` (
        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
        `user_id` bigint NOT NULL COMMENT '用户编号',
        `title` varchar(256) NOT NULL COMMENT '对话标题',
        `pinned` bit(1) NULL DEFAULT 0 COMMENT '是否置顶',
        `pinned_time` datetime NULL  COMMENT '置顶时间',
        `role_id` bigint NULL COMMENT '角色编号',
        `model_id` bigint NULL COMMENT '模型编号',
        `model` varchar(3000) NULL COMMENT '模型标志',
        `system_message` varchar(3000) NULL COMMENT '角色设定',
        `temperature` float NOT NULL default 0.0 COMMENT '温度参数',
        `max_tokens` int NOT NULL COMMENT '单条回复的最大 Token 数量',
        `max_contexts` int NULL COMMENT '上下文的最大 Message 数量',

        `creator` varchar(64) DEFAULT '' COMMENT '创建者',
        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        `updater` varchar(64) DEFAULT '' COMMENT '更新者',
        `update_time` datetime NULL  COMMENT '更新时间',
        `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
        `tenant_id` bigint NOT NULL DEFAULT '0',
        PRIMARY KEY (`id`)
) COMMENT='AI Chat 对话';
CREATE INDEX ai_chat_conversation_user_id_IDX USING BTREE ON ai_chat_conversation (user_id);
CREATE INDEX ai_chat_conversation_title_IDX USING BTREE ON ai_chat_conversation (title);
CREATE INDEX ai_chat_conversation_create_time_IDX USING BTREE ON ai_chat_conversation (create_time);


CREATE TABLE `ai_chat_message` (
       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
       `conversation_id` bigint NOT NULL COMMENT '对话编号',
       `reply_id` bigint NOT NULL COMMENT '回复消息编号',
       `type` varchar(64) NOT NULL COMMENT '类型',
       `role_id` bigint NULL COMMENT '角色编号',
       `user_id` bigint NOT NULL COMMENT '用户编号',
       `model_id` bigint NULL COMMENT '模型编号',
       `model` varchar(64) NULL COMMENT '模型标志',
       `content` text NOT NULL COMMENT '聊天内容',
       `reasoning_content` text NULL COMMENT '推理内容',
       `use_context` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否携带上下文',
       `segment_ids` text NULL COMMENT '知识库段落编号数组',
       `web_search_pages` text NULL COMMENT '联网搜索的网页内容数组',
       `attachment_urls` text NULL COMMENT '附件 URL 数组',

       `creator` varchar(64) DEFAULT '' COMMENT '创建者',
       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `updater` varchar(64) DEFAULT '' COMMENT '更新者',
       `update_time` datetime NULL  COMMENT '更新时间',
       `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
       `tenant_id` bigint NOT NULL DEFAULT '0',
       PRIMARY KEY (`id`)
) COMMENT='AI Chat 消息';
CREATE INDEX ai_chat_message_conversation_id_IDX USING BTREE ON ai_chat_message (conversation_id);
CREATE INDEX ai_chat_message_reply_id_IDX USING BTREE ON ai_chat_message (reply_id);
CREATE INDEX ai_chat_message_user_id_IDX USING BTREE ON ai_chat_message (user_id);
CREATE INDEX ai_chat_message_create_time_IDX USING BTREE ON ai_chat_message (create_time);

CREATE TABLE `ai_image` (
            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
            `user_id` bigint NOT NULL COMMENT '用户编号',
            `prompt` text NOT NULL COMMENT '提示词',
            `platform` varchar(64) NULL COMMENT '平台',
            `model_id` varchar(512) NOT NULL COMMENT '模型编号',
            `model` varchar(64) NULL COMMENT '模型标志',
            `width` int NULL  COMMENT '图片宽度',
            `height` int NULL  COMMENT '图片高度',
            `status` int NULL DEFAULT 0 COMMENT '状态',
            `finish_time` datetime NULL  COMMENT '完成时间',
            `error_message` varchar(3000) NULL COMMENT '绘画错误信息',
            `pic_url` varchar(3000) NULL COMMENT '图片地址',
            `public_status` bit(1) NULL DEFAULT 0 COMMENT '是否公开',
            `options` varchar(2000) DEFAULT '' COMMENT '绘制参数，不同 platform 的不同参数',
            `buttons` varchar(64) DEFAULT '' COMMENT 'buttons 按钮',
            `task_id` varchar(64) DEFAULT '' COMMENT '任务编号',

            `creator` varchar(64) DEFAULT '' COMMENT '创建者',
            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
            `updater` varchar(64) DEFAULT '' COMMENT '更新者',
            `update_time` datetime NULL  COMMENT '更新时间',
            `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
            `tenant_id` bigint NOT NULL DEFAULT '0',
            PRIMARY KEY (`id`)
) COMMENT='AI 绘画';
CREATE INDEX ai_image_id_IDX USING BTREE ON ai_image (user_id);
CREATE INDEX ai_image_create_time_IDX USING BTREE ON ai_image (create_time);

CREATE TABLE `ai_knowledge` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name` varchar(256) NOT NULL COMMENT '知识库名称',
    `description` varchar(3000) NOT NULL COMMENT '知识库描述',
    `embedding_model` varchar(64) NULL COMMENT '模型标识',
    `embedding_model_id` varchar(512) NOT NULL COMMENT '向量模型编号',
    `top_k` int NULL  COMMENT 'topK',
    `similarity_threshold` float NOT NULL  COMMENT '相似度阈值',
    `status` int NULL DEFAULT 0 COMMENT '状态',

    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NULL  COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) COMMENT='AI 知识库';

CREATE INDEX ai_knowledge_create_time_IDX USING BTREE ON ai_knowledge (create_time);

CREATE TABLE `ai_knowledge_document` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
     `knowledge_id` bigint NOT NULL COMMENT '知识库编号',
     `name` varchar(256) NOT NULL COMMENT '知识库名称',
     `content` varchar(3000) NOT NULL COMMENT '内容',
     `url` varchar(253) NULL COMMENT '文件 URL',
     `tokens` int NULL  COMMENT '文档 token 数量',
     `content_length` int NULL DEFAULT 0 COMMENT '文档长度',
     `segment_max_tokens` int NULL DEFAULT 0 COMMENT '分片最大 Token 数',
     `retrieval_count` int NULL DEFAULT 0 COMMENT '召回次数',

     `creator` varchar(64) DEFAULT '' COMMENT '创建者',
     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updater` varchar(64) DEFAULT '' COMMENT '更新者',
     `update_time` datetime NULL  COMMENT '更新时间',
     `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
     `tenant_id` bigint NOT NULL DEFAULT '0',
     PRIMARY KEY (`id`)
) COMMENT='AI 知识库-文档';
CREATE INDEX ai_knowledge_document_knowledge_id_IDX USING BTREE ON ai_knowledge_document (knowledge_id);
CREATE INDEX ai_knowledge_document_create_time_IDX USING BTREE ON ai_knowledge_document (create_time);

CREATE TABLE `ai_knowledge_segment` (
        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
        `knowledge_id` bigint NOT NULL COMMENT '知识库编号',
        `document_id` bigint NOT NULL COMMENT '文档编号',
        `content` varchar(3000) NOT NULL COMMENT '切片内容',
        `vector_id` varchar(253) NULL COMMENT '向量库的编号',
        `tokens` int NULL  COMMENT '文档 token 数量',
        `content_length` int NULL DEFAULT 0 COMMENT '文档长度',
        `retrieval_count` int NULL DEFAULT 0 COMMENT '召回次数',
        `status` int NULL DEFAULT 0 COMMENT '状态',

        `creator` varchar(64) DEFAULT '' COMMENT '创建者',
        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
        `updater` varchar(64) DEFAULT '' COMMENT '更新者',
        `update_time` datetime NULL  COMMENT '更新时间',
        `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
        `tenant_id` bigint NOT NULL DEFAULT '0',
        PRIMARY KEY (`id`)
) COMMENT='AI 知识库-文档分段';
CREATE INDEX ai_knowledge_segment_document_id_IDX USING BTREE ON ai_knowledge_segment (document_id);
CREATE INDEX ai_knowledge_segment_knowledge_id_IDX USING BTREE ON ai_knowledge_segment (knowledge_id);
CREATE INDEX ai_knowledge_segment_create_time_IDX USING BTREE ON ai_knowledge_segment (create_time);

CREATE TABLE `ai_workflow` (
       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
       `name` varchar(256) NOT NULL COMMENT '工作流名称',
       `code` varchar(64) NOT NULL COMMENT '工作流标识',
       `graph`  text NULL COMMENT '工作流模型 JSON 数据',
       `status` int NULL DEFAULT 0 COMMENT '状态',

       `creator` varchar(64) DEFAULT '' COMMENT '创建者',
       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `updater` varchar(64) DEFAULT '' COMMENT '更新者',
       `update_time` datetime NULL  COMMENT '更新时间',
       `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
       `tenant_id` bigint NOT NULL DEFAULT '0',
       PRIMARY KEY (`id`)
) COMMENT='AI 工作流';
CREATE INDEX ai_workflow_create_time_IDX USING BTREE ON ai_workflow (create_time);

CREATE TABLE `ai_write` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `user_id` bigint NOT NULL COMMENT '用户编号',
    `type` int NOT NULL COMMENT '写作类型',
    `platform`  varchar(64) NULL COMMENT '平台',
    `model_id` bigint NOT NULL COMMENT '模型编号',
    `model`  varchar(64) NULL COMMENT '模型',
    `prompt`  varchar(3000) NULL COMMENT '生成内容提示',
    `generated_content` text NULL COMMENT '生成的内容',
    `original_content`  text NULL COMMENT '原文',
    `length` int NOT NULL COMMENT '长度提示词',
    `format` int NOT NULL COMMENT '格式提示词',
    `tone` int NOT NULL COMMENT '语气提示词',
    `language` int NULL DEFAULT 0 COMMENT '语言提示词',
    `error_message` varchar(3000) DEFAULT '' COMMENT '错误信息',

    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NULL  COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) COMMENT='AI 写作';
CREATE INDEX ai_write_user_id_IDX USING BTREE ON ai_write (user_id);
CREATE INDEX ai_write_create_time_IDX USING BTREE ON ai_write (create_time);

CREATE TABLE `ai_mind_map` (
       `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
       `user_id` bigint NOT NULL COMMENT '用户编号',
       `platform`  varchar(64) NULL COMMENT '平台',
       `model_id` bigint NOT NULL COMMENT '模型编号',
       `model`  varchar(64) NULL COMMENT '模型',
       `prompt`  varchar(3000) NULL COMMENT '生成内容提示',
       `generated_content` text NULL COMMENT '生成的内容',
       `error_message` varchar(3000) DEFAULT '' COMMENT '错误信息',

       `creator` varchar(64) DEFAULT '' COMMENT '创建者',
       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
       `updater` varchar(64) DEFAULT '' COMMENT '更新者',
       `update_time` datetime NULL  COMMENT '更新时间',
       `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
       `tenant_id` bigint NOT NULL DEFAULT '0',
       PRIMARY KEY (`id`)
) COMMENT='AI 写作';
CREATE INDEX ai_mind_map_user_id_IDX USING BTREE ON ai_mind_map (user_id);
CREATE INDEX ai_mind_map_create_time_IDX USING BTREE ON ai_mind_map (create_time);