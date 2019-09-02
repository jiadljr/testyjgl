package com.qkby.sysmanage.entity;
/**
 * 每组人员的实体类
 * @author Administrator
 *
 */
public class SysDutyOrderInfo {
      private Integer id;
      private String groupName;
      private String idUserGroup;
      private String nameUserGroup;
      private String extend1;
      private String extend2;
      private String extend3;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getIdUserGroup() {
		return idUserGroup;
	}
	public void setIdUserGroup(String idUserGroup) {
		this.idUserGroup = idUserGroup;
	}
	public String getNameUserGroup() {
		return nameUserGroup;
	}
	public void setNameUserGroup(String nameUserGroup) {
		this.nameUserGroup = nameUserGroup;
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
	public SysDutyOrderInfo(int id, String groupName, String idUserGroup, String nameUserGroup, String extend1,
			String extend2, String extend3) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.idUserGroup = idUserGroup;
		this.nameUserGroup = nameUserGroup;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public SysDutyOrderInfo() {
		super();
	}
	@Override
	public String toString() {
		return "SysDutyOrderInfo [id=" + id + ", groupName=" + groupName + ", idUserGroup=" + idUserGroup
				+ ", nameUserGroup=" + nameUserGroup + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3="
				+ extend3 + "]";
	}
	  
      
}
