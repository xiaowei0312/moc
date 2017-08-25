package com.sram.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "moc_integral_rule_task")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_integral_rule_task")
public class IntegralRuleTask extends BaseEntity {

	private Long memberId;

	private IntegralRule integralRule;

	private Integer accumulativeAcount;

	private String urlPath;

	private String appUrlPath;

	private Boolean isReceive;

	@Column(name = "member_id")
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "integral_rule_id")
	public IntegralRule getIntegralRule() {
		return integralRule;
	}

	public void setIntegralRule(IntegralRule integralRule) {
		this.integralRule = integralRule;
	}

	public Integer getAccumulativeAcount() {
		return accumulativeAcount;
	}

	public void setAccumulativeAcount(Integer accumulativeAcount) {
		this.accumulativeAcount = accumulativeAcount;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getAppUrlPath() {
		return appUrlPath;
	}

	public void setAppUrlPath(String appUrlPath) {
		this.appUrlPath = appUrlPath;
	}

	public Boolean getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Boolean isReceive) {
		this.isReceive = isReceive;
	}

}
