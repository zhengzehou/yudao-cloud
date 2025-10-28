package cn.iocoder.yudao.module.im.service.userinfo;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoPageReqVO;
import cn.iocoder.yudao.module.im.controller.admin.userinfo.vo.UserInfoSaveReqVO;
import cn.iocoder.yudao.module.im.controller.app.chat.vo.UserVO;
import cn.iocoder.yudao.module.im.dal.dataobject.userinfo.UserInfoDO;
import cn.iocoder.yudao.module.im.dal.mysql.userinfo.UserInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.im.api.enums.ErrorCodeConstants.USER_NOT_EXISTS;


/**
 * 用户信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Long createUserInfo(UserInfoSaveReqVO createReqVO) {
        // 插入
        UserInfoDO userInfo = BeanUtils.toBean(createReqVO, UserInfoDO.class);
        userInfo.setPwd(encodePassword(userInfo.getPwd())); // 加密密码
        userInfoMapper.insert(userInfo);

        // 返回
        return userInfo.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDO createUserIfAbsent(String mobile, String registerIp, Integer terminal) {
        // 用户已经存在
        UserInfoDO user = userInfoMapper.selectByMobile(mobile);
        if (user != null) {
            return user;
        }
        // 用户不存在，则进行创建
        return createUser(mobile, null, null, registerIp, terminal);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfoDO createUser(String nickname, String avtar, String registerIp, Integer terminal) {
        return createUser(null, nickname, avtar, registerIp, terminal);
    }
    private UserInfoDO createUser(String mobile, String nickname, String avtar,
                                    String registerIp, Integer terminal) {
        // 生成密码
        String password = IdUtil.fastSimpleUUID();
        // 插入用户
        UserInfoDO user = new UserInfoDO();
        user.setPhone(mobile);
        user.setStatus(CommonStatusEnum.ENABLE.getStatus()); // 默认开启
        user.setPwd(encodePassword(password)); // 加密密码
        user.setRegisterIp(registerIp).setTerm(terminal);
        user.setNickName(nickname).setAvatar(avtar); // 基础信息
        if (StrUtil.isEmpty(nickname)) {
            // 昵称为空时，随机一个名字，避免一些依赖 nickname 的逻辑报错，或者有点丑。例如说，短信发送有昵称时~
            user.setNickName("用户" + RandomUtil.randomNumbers(6));
        }
        userInfoMapper.insert(user);

        // 发送 MQ 消息：用户创建
//        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//
//            @Override
//            public void afterCommit() {
//                memberUserProducer.sendUserCreateMessage(user.getId());
//            }
//
//        });
        return user;
    }
    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void updateUserInfo(UserInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateUserInfoExists(updateReqVO.getId());
        // 更新
        UserInfoDO updateObj = BeanUtils.toBean(updateReqVO, UserInfoDO.class);
        userInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserInfo(Long id) {
        // 校验存在
        validateUserInfoExists(id);
        // 删除
        userInfoMapper.deleteById(id);
    }

    @Override
        public void deleteUserInfoListByIds(List<Long> ids) {
        // 删除
        userInfoMapper.deleteByIds(ids);
        }


    private void validateUserInfoExists(Long id) {
        if (userInfoMapper.selectById(id) == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    @Override
    public UserInfoDO getUserInfo(Long id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public UserInfoDO getUserByMobile(String mobile) {
        return userInfoMapper.selectByMobile(mobile);
    }

    @Override
    public UserInfoDO getUserByEmail(String email) {
        return userInfoMapper.selectByEmail(email);
    }

    @Override
    public UserInfoDO getUserByUsername(String name) {
        return userInfoMapper.selectByUsername(name);
    }


    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public PageResult<UserInfoDO> getUserInfoPage(UserInfoPageReqVO pageReqVO) {
        return userInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<UserVO> selectFriendsPage(UserInfoPageReqVO pageReqVO) {
        return userInfoMapper.selectFriendsPage(pageReqVO);
    }

}