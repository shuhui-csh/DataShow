package gd.dshow.http;

public class HttpURL {

	public HttpURL() {
		// TODO Auto-generated constructor stub
	}

	//public static String BaseURL = "http://10.21.32.128:8080/reportserver/";
	// 基本的URL地址，即测试时服务器的ip地址
	public static String BaseURL = "http://222.200.98.187:8080/reportserver/";
	// 登录URL
	public static String Login = BaseURL + "login.action";
	// 获取报表组的URL
	public static String ReportGroupURL = BaseURL + "reportgroup.action";
	// 获取某一报表组下的所有报表的字符串的URL
	public static String BaseReportListURL = BaseURL
			+ "reportlist.action?groupid=";
}
