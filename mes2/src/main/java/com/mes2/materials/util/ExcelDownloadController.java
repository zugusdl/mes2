package com.mes2.materials.util;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.materials.domain.PurchaseDTO;
import com.mes2.materials.service.PurchaseService;

@Controller
@RequestMapping(value = "/materials/*")
public class ExcelDownloadController {

	@Inject
	private PurchaseService pService;
	
	@GetMapping(value = "/materials")
	public ResponseEntity<byte[]> materials(HttpServletResponse response, PurchaseDTO pdto) throws Exception {
	    Workbook workbook = ExcelUtils.createWorkbook();
	    List<PurchaseDTO> data = pService.getAllPurchaseData(pdto);
	    ExcelUtils.addDataToWorkbook(workbook, data);

	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    workbook.write(byteArrayOutputStream);
	    byte[] excelBytes = byteArrayOutputStream.toByteArray();
	    workbook.close();

	    String filename = URLEncoder.encode("자재_리스트.xlsx", StandardCharsets.UTF_8);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

	    return ResponseEntity.ok().headers(headers).contentLength(excelBytes.length).body(excelBytes);
	}

}