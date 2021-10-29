package in.jayanth.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.jayanth.model.Records;

public interface RecordsService {
	
	public List<Records> getAllRecords();

	boolean createPdf(List<Records> records, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createExcel(List<Records> records, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
}
