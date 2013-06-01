package com.askvikrant.noticeboard;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.askvikrant.noticeboard.business.BusinessLogic;
import com.askvikrant.noticeboard.model.Attachment;

/**
 * Servlet implementation class DownloaderServlet
 */
public class DownloaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final int BUFSIZE = 4096;
	private BusinessLogic businessLogic = null;
	private BusinessLogic getBusinessLogic(){
		if (businessLogic == null) {
			businessLogic = new BusinessLogic();
		}
		return businessLogic;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloaderServlet() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("DownloaderServlet: constructor");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("DownloaderServlet: init");
		getBusinessLogic();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		businessLogic = getBusinessLogic();
		int length = 0;

		ServletOutputStream outStream = response.getOutputStream();
		String attachmentId = request.getParameter("attachment_id");
		Attachment attachment = businessLogic.getAttachment(attachmentId);
		File file = attachment.getFile();
		String fileName = file.getName();
		String[] tokens = fileName.split("\\s");
		fileName = "";
		for(int i=0;i<tokens.length;i++){
			fileName = fileName +"_"+ tokens[i];
		}
		System.out.println("Filename: "+fileName);
		String contentType = attachment.getContentType();

		response.setContentType(contentType);
		response.setHeader("Content-Disposition",
				"attachment;filename="+fileName);
		response.setContentLength((int)file.length());

		byte[] byteBuffer = new byte[BUFSIZE];
		DataInputStream in = new DataInputStream(new FileInputStream(attachment.getFile()));

		// reads the file's bytes and writes them to the response stream
		while ((in != null) && ((length = in.read(byteBuffer)) != -1))
		{
			outStream.write(byteBuffer,0,length);
		}

		in.close();
		outStream.flush();
		outStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
