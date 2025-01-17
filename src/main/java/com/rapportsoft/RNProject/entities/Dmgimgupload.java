package com.rapportsoft.RNProject.entities;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "dmgimgupload")
@IdClass(DmgimguploadId.class)
public class Dmgimgupload {
	@Id
	@Column(name = "Sr_No", nullable = false)
    private int srNo;
	
	@Id
	@Column(name = "Company_Id", nullable = false, length = 6)
    private String companyId;
	
	@Id
	@Column(name = "Branch_Id", nullable = false, length = 6)
    private String branchId;
	
	@Id
	@Column(name = "Fin_Year", nullable = false, length = 4)
    private String finYear;
	
	@Id
	@Column(name = "IGM_Trans_Id", nullable = false, length = 10)
    private String igmTransId;
	
	@Id
	@Column(name = "Igm_no", nullable = false, length = 10)
    private String igmNo;
	
	@Id
	@Column(name = "Container_No", nullable = false, length = 11)
    private String containerNo;
	
	@Column(name = "Container_Size", nullable = false, length = 6)
    private String containerSize;
	
	@Column(name = "Container_Type", nullable = false, length = 6)
    private String containerType;
	
	@Column(name = "Uploaded_Image", nullable = false, length = 200)
    private String uploadedImage;
	
	@Column(name = "Status", nullable = false, length = 1)
    private String status = "A";
	
	@Column(name = "Created_By", length = 50)
    private String createdBy;
	
	@Column(name = "Created_Date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
	
	
	

	public Dmgimgupload() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Dmgimgupload(int srNo, String companyId, String branchId, String finYear, String igmTransId, String igmNo,
			String containerNo, String containerSize, String containerType, String uploadedImage, String status,
			String createdBy, Date createdDate) {
		super();
		this.srNo = srNo;
		this.companyId = companyId;
		this.branchId = branchId;
		this.finYear = finYear;
		this.igmTransId = igmTransId;
		this.igmNo = igmNo;
		this.containerNo = containerNo;
		this.containerSize = containerSize;
		this.containerType = containerType;
		this.uploadedImage = uploadedImage;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
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




	public String getContainerSize() {
		return containerSize;
	}




	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}




	public String getContainerType() {
		return containerType;
	}




	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}




	public String getUploadedImage() {
		return uploadedImage;
	}




	public void setUploadedImage(String uploadedImage) {
		this.uploadedImage = uploadedImage;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public java.util.Date getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}




	@Override
	public int hashCode() {
		return Objects.hash(branchId, companyId, containerNo, containerSize, containerType, createdBy, createdDate,
				finYear, igmNo, igmTransId, srNo, status, uploadedImage);
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dmgimgupload other = (Dmgimgupload) obj;
		return Objects.equals(branchId, other.branchId) && Objects.equals(companyId, other.companyId)
				&& Objects.equals(containerNo, other.containerNo) && Objects.equals(containerSize, other.containerSize)
				&& Objects.equals(containerType, other.containerType) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(createdDate, other.createdDate) && Objects.equals(finYear, other.finYear)
				&& Objects.equals(igmNo, other.igmNo) && Objects.equals(igmTransId, other.igmTransId)
				&& srNo == other.srNo && Objects.equals(status, other.status)
				&& Objects.equals(uploadedImage, other.uploadedImage);
	}




	@Override
	public String toString() {
		return "Dmgimgupload [srNo=" + srNo + ", companyId=" + companyId + ", branchId=" + branchId + ", finYear="
				+ finYear + ", igmTransId=" + igmTransId + ", igmNo=" + igmNo + ", containerNo=" + containerNo
				+ ", containerSize=" + containerSize + ", containerType=" + containerType + ", uploadedImage="
				+ uploadedImage + ", status=" + status + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ "]";
	}
	
	
	
	

}
