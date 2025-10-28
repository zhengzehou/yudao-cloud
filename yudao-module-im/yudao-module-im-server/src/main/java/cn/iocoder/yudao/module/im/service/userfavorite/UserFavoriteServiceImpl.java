package cn.iocoder.yudao.module.im.service.userfavorite;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.im.controller.admin.userfavorite.vo.*;
import cn.iocoder.yudao.module.im.dal.dataobject.userfavorite.UserFavoriteDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.im.dal.mysql.userfavorite.UserFavoriteMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 我的收藏 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserFavoriteServiceImpl implements UserFavoriteService {

    @Resource
    private UserFavoriteMapper userFavoriteMapper;

    @Override
    public Long createUserFavorite(UserFavoriteSaveReqVO createReqVO) {
        // 插入
        UserFavoriteDO userFavorite = BeanUtils.toBean(createReqVO, UserFavoriteDO.class);
        userFavoriteMapper.insert(userFavorite);

        // 返回
        return userFavorite.getId();
    }

    @Override
    public void updateUserFavorite(UserFavoriteSaveReqVO updateReqVO) {
        // 校验存在
        validateUserFavoriteExists(updateReqVO.getId());
        // 更新
        UserFavoriteDO updateObj = BeanUtils.toBean(updateReqVO, UserFavoriteDO.class);
        userFavoriteMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserFavorite(Long id) {
        // 校验存在
        validateUserFavoriteExists(id);
        // 删除
        userFavoriteMapper.deleteById(id);
    }

    @Override
        public void deleteUserFavoriteListByIds(List<Long> ids) {
        // 删除
        userFavoriteMapper.deleteByIds(ids);
        }


    private void validateUserFavoriteExists(Long id) {
        if (userFavoriteMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public UserFavoriteDO getUserFavorite(Long id) {
        return userFavoriteMapper.selectById(id);
    }

    @Override
    public PageResult<UserFavoriteDO> getUserFavoritePage(UserFavoritePageReqVO pageReqVO) {
        return userFavoriteMapper.selectPage(pageReqVO);
    }

}