package cn.iocoder.yudao.module.im.service.friendpost;

import cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.friendpost.vo.FriendPostPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.friendpost.vo.FriendPostSaveReqVO;
import cn.iocoder.yudao.module.im.dal.dataobject.friendpost.FriendPostDO;
import cn.iocoder.yudao.module.im.dal.mysql.friendpost.FriendPostMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 朋友圈动态 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class FriendPostServiceImpl implements FriendPostService {

    @Resource
    private FriendPostMapper friendPostMapper;

    @Override
    public Long createFriendPost(FriendPostSaveReqVO createReqVO) {
        // 插入
        FriendPostDO friendPost = BeanUtils.toBean(createReqVO, FriendPostDO.class);
        friendPostMapper.insert(friendPost);

        // 返回
        return friendPost.getId();
    }

    @Override
    public void updateFriendPost(FriendPostSaveReqVO updateReqVO) {
        // 校验存在
        validateFriendPostExists(updateReqVO.getId());
        // 更新
        FriendPostDO updateObj = BeanUtils.toBean(updateReqVO, FriendPostDO.class);
        friendPostMapper.updateById(updateObj);
    }

    @Override
    public void deleteFriendPost(Long id) {
        // 校验存在
        validateFriendPostExists(id);
        // 删除
        friendPostMapper.deleteById(id);
    }

    @Override
        public void deleteFriendPostListByIds(List<Long> ids) {
        // 删除
        friendPostMapper.deleteByIds(ids);
        }


    private void validateFriendPostExists(Long id) {
        if (friendPostMapper.selectById(id) == null) {
            throw exception(GlobalErrorCodeConstants.NOT_FOUND);
        }
    }

    @Override
    public FriendPostDO getFriendPost(Long id) {
        return friendPostMapper.selectById(id);
    }

    @Override
    public PageResult<FriendPostDO> getFriendPostPage(FriendPostPageReqVO pageReqVO) {
        return friendPostMapper.selectPage(pageReqVO);
    }

}