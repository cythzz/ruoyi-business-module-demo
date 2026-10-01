package com.ruoyi.project.business.domain;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.framework.aspectj.lang.annotation.Sensitive;
import com.ruoyi.framework.aspectj.lang.enums.DesensitizedType;

/** 客户回访记录 business_customer_follow_up。 */
public class CustomerFollowUp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long followUpId;
    private String customerName;
    @Sensitive(desensitizedType = DesensitizedType.PHONE)
    private String mobile;
    private String orderNo;
    private String status;
    private String priority;
    private String ownerName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date nextContactTime;

    public Long getFollowUpId() { return followUpId; }
    public void setFollowUpId(Long followUpId) { this.followUpId = followUpId; }

    @NotBlank(message = "客户姓名不能为空")
    @Size(max = 50, message = "客户姓名不能超过50个字符")
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    @Size(max = 20, message = "手机号不能超过20个字符")
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    @NotBlank(message = "订单号不能为空")
    @Size(max = 64, message = "订单号不能超过64个字符")
    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String orderNo) { this.orderNo = orderNo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public Date getNextContactTime() { return nextContactTime; }
    public void setNextContactTime(Date nextContactTime) { this.nextContactTime = nextContactTime; }
}
