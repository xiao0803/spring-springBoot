package com.example.entity;

/**
 * 通投白名单
 * Created by xiaolj@jiguang.cn on 2018/11/21.
 */

import java.io.Serializable;

//import lombok.Data;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
//@Data
public class UnTargetForm implements Serializable {
	private static final long serialVersionUID = 185436618450022490L;
	/**
	 * 通投ID（当通投状态为开启时，此字段必须有值）
	 */
    private Long unTargetsId;
	/**
	 * 通投状态;1:开启、0:关闭
	 */
    @NotNull(message = "通投状态不能为空")
    private Integer onOff;
	/**
	 * 客户ID
	 */
    @NotNull(message = "广告活动ID不能为空")
    private Long activityId;
	/**
	 * 操作者ID（当通投状态为开启时，此字段必须有值）
	 */
    private Long operId;
    
    
	public Long getUnTargetsId() {
		return unTargetsId;
	}
	public void setUnTargetsId(Long unTargetsId) {
		this.unTargetsId = unTargetsId;
	}
	public Integer getOnOff() {
		return onOff;
	}
	public void setOnOff(Integer onOff) {
		this.onOff = onOff;
	}
	public Long getActivityId() {
		return activityId;
	}
	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}
	public Long getOperId() {
		return operId;
	}
	public void setOperId(Long operId) {
		this.operId = operId;
	}
    
}







