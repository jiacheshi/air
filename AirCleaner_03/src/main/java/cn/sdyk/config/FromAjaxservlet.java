package cn.sdyk.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FromAjaxservlet extends HttpServlet{
	@RequestMapping("/FromAjaxservlet")
  protected void doGet(HttpServletRequest req, HttpServletResponse resp,String latlon) throws ServletException, IOException {

		resp.setHeader("Content-type", "text/html;charset=UTF-8");

		resp.setCharacterEncoding("UTF-8"); 
		System.out.println(latlon);
    try {
      //创建默认连接
      CloseableHttpClient httpClient = HttpClients.createDefault();
      //创建HttpGet对象,处理get请求,转发到A站点
      HttpGet httpGet = new HttpGet("https://free-api.heweather.net/s6/weather/now?location="+latlon+"&key=38cedb0fb9ad4f4d81cc1ac69a113970");  //执行
      CloseableHttpResponse response = httpClient.execute(httpGet);
      int code = response.getStatusLine().getStatusCode();
      //获取状态
      System.out.println("http请求结果为:"+code);
      if(code == 200){ //获取A站点返回的结果
    
        String result = EntityUtils.toString(response.getEntity());

    
        //把结果返回给B站点str
        resp.getWriter().print(result);
        System.out.println(result);
      }
      response.close();
      httpClient.close();
    } catch (Exception e) {
    }
  }
	@RequestMapping("FromAjaxservlet1")
	  protected void doGet1(HttpServletRequest req, HttpServletResponse resp,String latlon) throws ServletException, IOException {
	    try {
	      //创建默认连接
	      CloseableHttpClient httpClient = HttpClients.createDefault();
	      //创建HttpGet对象,处理get请求,转发到A站点
	      HttpGet httpGet = new HttpGet("https://free-api.heweather.net/s6/weather/now?location="+latlon+"&key=38cedb0fb9ad4f4d81cc1ac69a113970");  //执行
	      CloseableHttpResponse response = httpClient.execute(httpGet);
	      int code = response.getStatusLine().getStatusCode();
	      //获取状态
	      System.out.println("http请求结果为:"+code);
	      if(code == 200){ //获取A站点返回的结果
	    
	        String result = EntityUtils.toString(response.getEntity());

	    
	        //把结果返回给B站点str
	        resp.getWriter().print(result);
	        System.out.println(result);
	      }
	      response.close();
	      httpClient.close();
	    } catch (Exception e) {
	    }
	  }
}
