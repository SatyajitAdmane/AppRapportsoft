package com.rapportsoft.RNProject.Dtos;

import java.util.Objects;

public class AppIgmDto {
	
	private String igmTransId;
	private String igmNo;
	private String containerNo;
	private String containerSize;
	private String containerType;
	

	public AppIgmDto() {
		
	}


	public AppIgmDto(String igmTransId, String igmNo, String containerNo, String containerSize, String containerType) {
		
		this.igmTransId = igmTransId;
		this.igmNo = igmNo;
		this.containerNo = containerNo;
		this.containerSize = containerSize;
		this.containerType = containerType;
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

	
	@Override
	public String toString() {
		return "AppIgmDto [igmTransId=" + igmTransId + ", igmNo=" + igmNo + ", containerNo=" + containerNo
				+ ", containerSize=" + containerSize + ", containerType=" + containerType + "]";}


	@Override
	public int hashCode() {
		return Objects.hash(containerNo, containerSize, containerType, igmNo, igmTransId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppIgmDto other = (AppIgmDto) obj;
		return Objects.equals(containerNo, other.containerNo) && Objects.equals(containerSize, other.containerSize)
				&& Objects.equals(containerType, other.containerType) && Objects.equals(igmNo, other.igmNo)
				&& Objects.equals(igmTransId, other.igmTransId);
	}
	
	
	
	

}
