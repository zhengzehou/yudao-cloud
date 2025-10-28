package cn.iocoder.yudao.module.im.controller.app.chat.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;

import lombok.Data;

import java.util.List;

@Data
@ExcelIgnoreUnannotated
public class ChatRespVO {

   Integer cursor;
   Boolean isLast;
   List<?> list;

}