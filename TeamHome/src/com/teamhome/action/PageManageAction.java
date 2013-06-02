package com.teamhome.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/page")
@ParentPackage("json-interceptor")
@Results({ @Result(name = "success", type = "json", params = { "root", "action" }) })
public class PageManageAction extends ActionSupport {

	private static final long serialVersionUID = 4692841065970954607L;

	private ActionContext ctx = ActionContext.getContext();// ������
	private HttpServletRequest request = (HttpServletRequest) ctx
			.get(ServletActionContext.HTTP_REQUEST);// ��ȡrequest
	private ServletContext sc = request.getSession().getServletContext();

	private String css;// ���ڻ�ȡcss�ļ�
	private String html;// ���ڻ�ȡhtml�ļ�
	private boolean success;

	// ��ȡ��index.css
	@Action(value = "indexCss")
	public String indexCss() {
		css = fileRead(sc.getRealPath("/css") + "\\index.css");
		return SUCCESS;
	}

	// ��ȡindex��html����
	@Action(value = "indexHtml")
	public String indexHtml() {
		html = fileRead(sc.getRealPath("") + "\\index.html");
		return SUCCESS;
	}

	@Action(value = "changeIndexCss")
	public String changeIndexCss() {
		success = writeFile(css, sc.getRealPath("/css") + "\\index.css");
		return SUCCESS;
	}

	@Action(value = "changeIndexHtml")
	public String changeIndexHtml() {
		success = writeFile(html, sc.getRealPath("") + "\\index.html");
		return SUCCESS;
	}

	public String getHtml() {
		return html;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public boolean isSuccess() {
		return success;
	}

	// �ļ���ȡ�������ļ�·������
	private String fileRead(String name) {
		String fileStr = "";
		File f = new File(name);// ��ȡ��index.css·��
		InputStreamReader isr = null;
		BufferedReader reader = null;
		try {
			isr = new InputStreamReader(new FileInputStream(f), "utf-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				fileStr += tempString + '\n'; // ��ÿһ�ж��ӵ�String��
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return fileStr;
	}

	private boolean writeFile(String content, String path) {
		try {
			// ��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter writer = new FileWriter(path);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
