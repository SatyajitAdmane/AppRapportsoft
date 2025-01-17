package com.rapportsoft.RNProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rapportsoft.RNProject.Dtos.AppIgmDto;
import com.rapportsoft.RNProject.Dtos.ContainerSearchReqDto;
import com.rapportsoft.RNProject.repository.AppIgmRepository;
import java.util.*;
import java.util.stream.Collectors;



@RestController
@CrossOrigin("*")
@RequestMapping("/containerData")
public class AppIgmController {
	
	@Autowired
	private AppIgmRepository appIgmRepo;
	

	@PostMapping("/getconatinersearchdata")
	public List<AppIgmDto> getContainerSearchData(@RequestBody ContainerSearchReqDto containerSearchReqDto){
		
		System.out.println("data"+ containerSearchReqDto.getCompanyId()+containerSearchReqDto.getBranchId()+
				containerSearchReqDto.getContainerNo());
		
		List<Object[]> result  = appIgmRepo.searchByContainerNo(containerSearchReqDto.getCompanyId(),containerSearchReqDto.getBranchId(),
				containerSearchReqDto.getContainerNo());
		

		return  result.stream().map(record -> {
            AppIgmDto dto = new AppIgmDto();
            dto.setIgmNo((String) record[0]);
            dto.setIgmTransId((String) record[1]);
            dto.setContainerNo((String) record[2]);
            dto.setContainerSize((String) record[3]);
            dto.setContainerType((String) record[4]);
            return dto;
        }).collect(Collectors.toList());
		
	}
	
	@PostMapping("/igmUpload")
	public void uploadfiledata (@RequestParam("files") MultipartFile[] files) {
		
		System.out.println("enered in img");
		
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			}
		
		
		
	}
	
	
	

}
