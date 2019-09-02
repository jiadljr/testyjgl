package com.qkby.proj.entity;

import java.util.Date;

public class ProjectInformation {
	private Integer id;
	private String projCode;// 项目编号
	private String projName;// 项目名称
	private Integer projType;// 项目类型
	private String contCode;// 合同编号
	private String amtFrom;// 资金来源
	private double projAmt;// 项目金额
	private Date dateStart;// 项目开始时间
	private Date dateEnd;// 项目结束时间
	private Date dateReal;// 项目实际完成时间
	private Integer idProjManager;// 项目结束时间
	private Integer idUserCreate;// 项目添加人
	private Date dateCreate;// 项目添加时间
	private Integer idUserUpdate;// 项目修改人
	private Date dateUpdate;// 项目修改时间
	private float projSpeed;// 项目进度
	private Integer pf;// 是否延期（0：正常 1：延期）
	private Integer projStatus;// 项目状态
	private String projExplain;// 项目说明
	private Integer ds;// 是否删除（0：正常 1：删除）
	private String vs;// 时间戳
	private String projPath;// 项目文件夹
	private Integer idDept;// 所属部门
	private Integer projBoard;// 项目看板
	private String extend1;
	private String extend2;
	private String extend3;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Integer getProjType() {
		return projType;
	}

	public void setProjType(Integer projType) {
		this.projType = projType;
	}

	public String getContCode() {
		return contCode;
	}

	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	
	

	public String getAmtFrom() {
		return amtFrom;
	}

	public void setAmtFrom(String amtFrom) {
		this.amtFrom = amtFrom;
	}

	public double getProjAmt() {
		return projAmt;
	}

	public void setProjAmt(double projAmt) {
		this.projAmt = projAmt;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	
	public Date getDateReal() {
		return dateReal;
	}

	public void setDateReal(Date dateReal) {
		this.dateReal = dateReal;
	}

	public Integer getIdUserCreate() {
		return idUserCreate;
	}

	public void setIdUserCreate(Integer idUserCreate) {
		this.idUserCreate = idUserCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Integer getIdUserUpdate() {
		return idUserUpdate;
	}

	public void setIdUserUpdate(Integer idUserUpdate) {
		this.idUserUpdate = idUserUpdate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public float getProjSpeed() {
		return projSpeed;
	}

	public void setProjSpeed(float projSpeed) {
		this.projSpeed = projSpeed;
	}
	

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Integer getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(Integer projStatus) {
		this.projStatus = projStatus;
	}

	public String getProjExplain() {
		return projExplain;
	}

	public void setProjExplain(String projExplain) {
		this.projExplain = projExplain;
	}

	
	public Integer getDs() {
		return ds;
	}

	public void setDs(Integer ds) {
		this.ds = ds;
	}

	public String getVs() {
		return vs;
	}

	public void setVs(String vs) {
		this.vs = vs;
	}

	
	public String getProjPath() {
		return projPath;
	}

	public void setProjPath(String projPath) {
		this.projPath = projPath;
	}

	public Integer getIdDept() {
		return idDept;
	}

	public void setIdDept(Integer idDept) {
		this.idDept = idDept;
	}

	public Integer getProjBoard() {
		return projBoard;
	}

	public void setProjBoard(Integer projBoard) {
		this.projBoard = projBoard;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}

	public Integer getIdProjManager() {
		return idProjManager;
	}

	public void setIdProjManager(Integer idProjManager) {
		this.idProjManager = idProjManager;
	}

	
}
