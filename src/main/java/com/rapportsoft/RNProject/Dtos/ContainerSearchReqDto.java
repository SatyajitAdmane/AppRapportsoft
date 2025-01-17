package com.rapportsoft.RNProject.Dtos;

public class ContainerSearchReqDto {
	
	private String companyId;
	private String branchId;
	private String finYear;
	private String containerNo;
	
	
	
	
	public ContainerSearchReqDto() {
		super();
		
	}
	public ContainerSearchReqDto(String companyId, String branchId, String finYear, String containerNo) {
		super();
		this.companyId = companyId;
		this.branchId = branchId;
		this.finYear = finYear;
		this.containerNo = containerNo;
	}
	public String getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(String comapnyId) {
		this.companyId = comapnyId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getFinYear() {
		return finYear;
	}
	public void setFinYear(String finYear) {
		this.finYear = finYear;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	
	

}
