package com.rapportsoft.RNProject.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

public class DmgimguploadId   implements Serializable{
	

    private int srNo;
	
    private String companyId;
	
    private String branchId;
	
    private String finYear;
	
    private String igmTransId;
	
    private String igmNo;
	
    private String containerNo;
    
  

	public DmgimguploadId() {
		super();
		// TODO Auto-generated constructor stub
	}




	public DmgimguploadId(int srNo, String companyId, String branchId, String finYear, String igmTransId, String igmNo,
			String containerNo) {
		super();
		this.srNo = srNo;
		this.companyId = companyId;
		this.branchId = branchId;
		this.finYear = finYear;
		this.igmTransId = igmTransId;
		this.igmNo = igmNo;
		this.containerNo = containerNo;
	}




	public int getSrNo() {
		return srNo;
	}




	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}




	public String getCompanyId() {
		return companyId;
	}




	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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




	public String getIgmTransId() {
		return igmTransId;
	}




	public void setIgmTransId(String igmTransId) {
		this.igmTransId = igmTransId;
	}




	public String getIgmNo() {
		return igmNo;
	}




	public void setIgmNo(String igmNo) {
		this.igmNo = igmNo;
	}




	public String getContainerNo() {
		return containerNo;
	}




	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}




	@Override
	public int hashCode() {
		return Objects.hash(branchId, companyId, containerNo, finYear, igmNo, igmTransId, srNo);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DmgimguploadId other = (DmgimguploadId) obj;
		return Objects.equals(branchId, other.branchId) && Objects.equals(companyId, other.companyId)
				&& Objects.equals(containerNo, other.containerNo) && Objects.equals(finYear, other.finYear)
				&& Objects.equals(igmNo, other.igmNo) && Objects.equals(igmTransId, other.igmTransId)
				&& srNo == other.srNo;
	}




	@Override
	public String toString() {
		return "DmgimguploadId [srNo=" + srNo + ", companyId=" + companyId + ", branchId=" + branchId + ", finYear="
				+ finYear + ", igmTransId=" + igmTransId + ", igmNo=" + igmNo + ", containerNo=" + containerNo + "]";
	}
    
    
    

}
