package com.ruoyi.project.business.service;

import java.util.List;
import com.ruoyi.project.business.domain.CustomerFollowUp;

public interface ICustomerFollowUpService
{
    CustomerFollowUp selectById(Long followUpId);
    List<CustomerFollowUp> selectList(CustomerFollowUp followUp);
    int insert(CustomerFollowUp followUp);
    int update(CustomerFollowUp followUp);
    int deleteByIds(Long[] followUpIds);
}
