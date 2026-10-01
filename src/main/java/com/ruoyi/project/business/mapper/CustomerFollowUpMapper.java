package com.ruoyi.project.business.mapper;

import java.util.List;
import com.ruoyi.project.business.domain.CustomerFollowUp;

public interface CustomerFollowUpMapper
{
    CustomerFollowUp selectCustomerFollowUpById(Long followUpId);
    List<CustomerFollowUp> selectCustomerFollowUpList(CustomerFollowUp followUp);
    int insertCustomerFollowUp(CustomerFollowUp followUp);
    int updateCustomerFollowUp(CustomerFollowUp followUp);
    int deleteCustomerFollowUpByIds(Long[] followUpIds);
}
