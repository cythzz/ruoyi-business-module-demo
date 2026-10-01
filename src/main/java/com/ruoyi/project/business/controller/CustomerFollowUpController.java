package com.ruoyi.project.business.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.annotation.RateLimiter;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.interceptor.annotation.RepeatSubmit;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.business.domain.CustomerFollowUp;
import com.ruoyi.project.business.service.ICustomerFollowUpService;

/** 客户回访业务接口，演示 RuoYi 权限注解、审计日志和分页封装。 */
@RestController
@RequestMapping("/business/follow-up")
public class CustomerFollowUpController extends BaseController
{
    private final ICustomerFollowUpService followUpService;

    public CustomerFollowUpController(ICustomerFollowUpService followUpService)
    {
        this.followUpService = followUpService;
    }

    @PreAuthorize("@ss.hasPermi('business:followup:list')")
    @RateLimiter(time = 1, count = 20)
    @GetMapping("/list")
    public TableDataInfo list(CustomerFollowUp followUp)
    {
        startPage();
        List<CustomerFollowUp> list = followUpService.selectList(followUp);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('business:followup:query')")
    @GetMapping("/{followUpId}")
    public AjaxResult getInfo(@PathVariable Long followUpId)
    {
        return success(followUpService.selectById(followUpId));
    }

    @PreAuthorize("@ss.hasPermi('business:followup:add')")
    @Log(title = "客户回访", businessType = BusinessType.INSERT)
    @RepeatSubmit(interval = 5000, message = "回访记录正在提交，请勿重复操作")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody CustomerFollowUp followUp)
    {
        followUp.setCreateBy(getUsername());
        return toAjax(followUpService.insert(followUp));
    }

    @PreAuthorize("@ss.hasPermi('business:followup:edit')")
    @Log(title = "客户回访", businessType = BusinessType.UPDATE)
    @RepeatSubmit(interval = 5000, message = "回访记录正在更新，请勿重复操作")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody CustomerFollowUp followUp)
    {
        followUp.setUpdateBy(getUsername());
        return toAjax(followUpService.update(followUp));
    }

    @PreAuthorize("@ss.hasPermi('business:followup:remove')")
    @Log(title = "客户回访", businessType = BusinessType.DELETE)
    @RepeatSubmit(interval = 5000, message = "删除请求正在处理，请勿重复操作")
    @DeleteMapping("/{followUpIds}")
    public AjaxResult remove(@PathVariable Long[] followUpIds)
    {
        return toAjax(followUpService.deleteByIds(followUpIds));
    }
}
