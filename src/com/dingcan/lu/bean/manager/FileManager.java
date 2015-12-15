package com.dingcan.lu.bean.manager;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dingcan.lu.bean.FoodBean;
import com.dingcan.lu.servlet.LuServerServlet;

public class FileManager {
	private String contextPath;

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {
			contextPath = LuServerServlet.webPath;
			System.out.println("contextPath:" + contextPath);
			DiskFileItemFactory diskFactory = new DiskFileItemFactory();
			diskFactory.setSizeThreshold(14 * 1024);
			diskFactory.setRepository(new File(contextPath));

			ServletFileUpload upload = new ServletFileUpload(diskFactory);
			upload.setSizeMax(50 * 1024 * 1024);
			List<FileItem> fileItems = upload.parseRequest(request);
			Iterator<FileItem> iter = fileItems.iterator();
			FileItem fileItem = null;
			String flag = null;
			String foodname = null;
			double foodprice = 0;
			double foodsale = 0;
			// double saleprice=0;
			int id = -1;
			String filename = null;
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {

					if (isStringNotEmpty(item.getString())) {

						if (item.getFieldName().equals("flag")) {
							flag = item.getString();
						} else if (item.getFieldName().equals("foodname")) {
							foodname = isoToUtf8(item.getString());
						} else if (item.getFieldName().equals("foodprice")) {
							foodprice = Double.valueOf(item.getString());
						} else if (item.getFieldName().equals("foodsale")) {
							foodsale = Double.valueOf(item.getString());
						} else if (item.getFieldName().equals("id")) {
							id = Integer.parseInt(item.getString());
						}

					}

				} else {
					System.out.println("文件 :name=" + item.getName());
					filename = item.getName();
					fileItem = item;
					
				}
			}
			// saleprice=foodprice*foodsale;
			FoodBean foodBean = new FoodBean(foodname, foodprice, foodsale,
					filename, "");
			foodBean.setId(id);
			System.out.println("flag=" + flag);
			if (flag != null && flag.equals("updateFood")) {
				 updateFood(foodBean);
				
			} else if (flag != null && flag.equals("addFood")) {
				addFood(foodBean);
			}
			processUploadFile(fileItem);
		} catch (Exception e) {

			System.out.println("上传失败 ...");
			e.printStackTrace();

		}

	}

	private void updateFood(FoodBean foodBean) {
		OrderBeanManager.getInstance().updateFoodBean(foodBean);
	}

	private void addFood(FoodBean bean) {
		OrderBeanManager.getInstance().addFoodBean(bean);
	}

	private boolean isStringNotEmpty(String string) {

		if (string != null && !string.equals("")) {
			return true;
		}
		return false;
	}

	private String isoToUtf8(String string) {
		try {
			return new String(string.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private void processFormField(FileItem item) {
		// System.out.println(item.getName());
		String value = item.getString();
		if (item.getFieldName().equals("foodname")) {
			value = isoToUtf8(value);
		}
		System.out.println("name:" + item.getFieldName());
		System.out.println("value:" + value);
	}

	private void processUploadFile(FileItem item) throws Exception {
		if(item==null){
			return;
		}
		String filename = item.getName();
		System.out.println("文件名：" + filename);
		int index = filename.lastIndexOf("\\");
		filename = filename.substring(index + 1, filename.length());
		long fileSize = item.getSize();
		if ("".equals(filename) && fileSize == 0) {
			return;
		}
		File uploadFile = new File(contextPath + "images\\" + filename);
		item.write(uploadFile);
		System.out.println(uploadFile.getAbsolutePath());
	}
}
