package in.jayanth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="RECORDS")
public class Records {
	
	@Id
	@Column(name="PLAN_ID")
	@GeneratedValue
	private Integer planId;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_STATUS")
	private String planStatus;
	
	@Column(name="PLAN_SDATE")
	private Date planSdate;
	
	@Column(name="PLAN_EDATE")
	private Date planEdate;
	
	@Column(name="BENFIT_AMOUNT")
	private Integer benfitAmt;
	

}
