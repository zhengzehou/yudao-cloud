package cn.iocoder.yudao.module.promotion.controller.app.diy;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.promotion.controller.app.diy.vo.AppDiyTemplatePropertyRespVO;
import cn.iocoder.yudao.module.promotion.convert.diy.DiyTemplateConvert;
import cn.iocoder.yudao.module.promotion.dal.dataobject.diy.DiyPageDO;
import cn.iocoder.yudao.module.promotion.dal.dataobject.diy.DiyTemplateDO;
import cn.iocoder.yudao.module.promotion.enums.diy.DiyPageEnum;
import cn.iocoder.yudao.module.promotion.service.diy.DiyPageService;
import cn.iocoder.yudao.module.promotion.service.diy.DiyTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.findFirst;

@Tag(name = "用户 APP - 装修模板")
@RestController
@RequestMapping("/promotion/diy-template")
@Validated
public class AppDiyTemplateController {

    @Resource
    private DiyTemplateService diyTemplateService;
    @Resource
    private DiyPageService diyPageService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    Object globalDiyTemplate = null;
    Long preLoadDiyTempTime = null;
    Random random = new Random();
    @GetMapping("/used")
    @Operation(summary = "使用中的装修模板")
    @PermitAll
    public CommonResult<Object> getUsedDiyTemplate(Integer s) {
        if(s != null && s == 1){
            DiyTemplateDO diyTemplate = diyTemplateService.getUsedDiyTemplate();
            return success(buildVo(diyTemplate));
        }
        // 1分钟内直接使用全局对象,+10秒随机数，避免缓存雪崩
        if(preLoadDiyTempTime != null && System.currentTimeMillis() - preLoadDiyTempTime < 60 * 1000 + random.nextInt(1000)*10){
            if(globalDiyTemplate != null){
                return success(globalDiyTemplate);
            }
        }
        Object respVO = JsonUtils.parseObject(stringRedisTemplate.opsForValue().get("diy-template-used"),Object.class);
        if(respVO == null) {
            DiyTemplateDO diyTemplate = diyTemplateService.getUsedDiyTemplate();
            respVO = buildVo(diyTemplate);
            stringRedisTemplate.opsForValue().set("diy-template-used", JsonUtils.toJsonString(respVO), 60, TimeUnit.MINUTES);
        }
        if(respVO != null) {
            globalDiyTemplate = respVO;
            preLoadDiyTempTime = System.currentTimeMillis();
        }
        return success(respVO);
    }

    @GetMapping("/get")
    @Operation(summary = "获得装修模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PermitAll
    public CommonResult<Object> getDiyTemplate(@RequestParam("id") Long id) {
        Object respVO = JsonUtils.parseObject(stringRedisTemplate.opsForValue().get("diy-template-used_"+id),Object.class);
        if(respVO == null) {
            DiyTemplateDO diyTemplate = diyTemplateService.getDiyTemplate(id);
            respVO = buildVo(diyTemplate);
            stringRedisTemplate.opsForValue().set("diy-template-used_"+id, JsonUtils.toJsonString(respVO), 60, TimeUnit.MINUTES);
            if(diyTemplate.getUsed()){
                stringRedisTemplate.opsForValue().set("diy-template-used", JsonUtils.toJsonString(respVO), 60, TimeUnit.MINUTES);
            }
        }
        return success(respVO);
    }

    private AppDiyTemplatePropertyRespVO buildVo(DiyTemplateDO diyTemplate) {
        if (diyTemplate == null) {
            return null;
        }
        // 查询模板下的页面
        List<DiyPageDO> pages = diyPageService.getDiyPageByTemplateId(diyTemplate.getId());
        String home = findFirst(pages, page -> DiyPageEnum.INDEX.getName().equals(page.getName()), DiyPageDO::getProperty);
        String user = findFirst(pages, page -> DiyPageEnum.MY.getName().equals(page.getName()), DiyPageDO::getProperty);
        // 拼接返回
        return DiyTemplateConvert.INSTANCE.convertPropertyVo2(diyTemplate, home, user);
    }

}
