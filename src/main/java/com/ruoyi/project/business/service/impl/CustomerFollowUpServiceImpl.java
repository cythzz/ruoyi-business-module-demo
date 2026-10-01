package com.ruoyi.project.business.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.business.domain.CustomerFollowUp;
import com.ruoyi.project.business.mapper.CustomerFollowUpMapper;
import com.ruoyi.project.business.service.ICustomerFollowUpService;

@Service
public class CustomerFollowUpServiceImpl implements ICustomerFollowUpService
{
    private final CustomerFollowUpMapper followUpMapper;

    public CustomerFollowUpServiceImpl(CustomerFollowUpMapper followUpMapper)
    {
        this.followUpMapper = followUpMapper;
    }

    @Override
    public CustomerFollowUp selectById(Long followUpId) { return followUpMapper.selectCustomerFollowUpById(followUpId); }

    @Override
    public List<CustomerFollowUp> selectList(CustomerFollowUp followUp) { return followUpMapper.selectCustomerFollowUpList(followUp); }

    @Override
    public int insert(CustomerFollowUp followUp)
    {
        followUp.setCreateTime(DateUtils.getNowDate());
        if (followUp.getStatus() == null) followUp.setStatus("0");
        if (followUp.getPriority() == null) followUp.setPriority("1");
        return followUpMapper.insertCustomerFollowUp(followUp);
    }

    @Override
    public int update(CustomerFollowUp followUp)
    {
        followUp.setUpdateTime(DateUtils.getNowDate());
        return followUpMapper.updateCustomerFollowUp(followUp);
    }

    @Override
    public int deleteByIds(Long[] followUpIds) { return followUpMapper.deleteCustomerFollowUpByIds(followUpIds); }
}
