package com.ruoyi.project.business.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ruoyi.project.business.domain.CustomerFollowUp;
import com.ruoyi.project.business.mapper.CustomerFollowUpMapper;
import com.ruoyi.project.business.service.impl.CustomerFollowUpServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerFollowUpServiceImplTests
{
    @Mock
    private CustomerFollowUpMapper followUpMapper;

    @InjectMocks
    private CustomerFollowUpServiceImpl followUpService;

    @Test
    void insertShouldSupplyDefaultsAndCreateTime()
    {
        CustomerFollowUp followUp = new CustomerFollowUp();
        followUp.setCustomerName("张女士");
        followUp.setOrderNo("ORDER-001");
        when(followUpMapper.insertCustomerFollowUp(followUp)).thenReturn(1);

        assertEquals(1, followUpService.insert(followUp));
        assertEquals("0", followUp.getStatus());
        assertEquals("1", followUp.getPriority());
        assertNotNull(followUp.getCreateTime());
        verify(followUpMapper).insertCustomerFollowUp(followUp);
    }
}
