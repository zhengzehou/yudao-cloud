package cn.iocoder.yudao.module.im.service.usertags;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.usertags.vo.UserTagsPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.usertags.vo.UserTagsSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.usertags.UserTagsDO;
import cn.iocoder.yudao.module.im.dal.mysql.usertags.UserTagsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 用户标签库 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserTagsServiceImpl implements UserTagsService {

    @Resource
    private UserTagsMapper userTagsMapper;

    @Override
    public Long createUserTags(UserTagsSaveReqVO createReqVO) {
        // 插入
        UserTagsDO userTags = BeanUtils.toBean(createReqVO, UserTagsDO.class);
        userTagsMapper.insert(userTags);

        // 返回
        return userTags.getId();
    }

    @Override
    public void updateUserTags(UserTagsSaveReqVO updateReqVO) {
        // 校验存在
        validateUserTagsExists(updateReqVO.getId());
        // 更新
        UserTagsDO updateObj = BeanUtils.toBean(updateReqVO, UserTagsDO.class);
        userTagsMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserTags(Long id) {
        // 校验存在
        validateUserTagsExists(id);
        // 删除
        userTagsMapper.deleteById(id);
    }

    @Override
        public void deleteUserTagsListByIds(List<Long> ids) {
        // 删除
        userTagsMapper.deleteByIds(ids);
        }


    private void validateUserTagsExists(Long id) {
        if (userTagsMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserTagsDO getUserTags(Long id) {
        return userTagsMapper.selectById(id);
    }

    @Override
    public PageResult<UserTagsDO> getUserTagsPage(UserTagsPageReqVO pageReqVO) {
        return userTagsMapper.selectPage(pageReqVO);
    }

}