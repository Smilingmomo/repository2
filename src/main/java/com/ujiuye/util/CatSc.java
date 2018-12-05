package com.ujiuye.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ujiuye.bean.Sc;
import com.ujiuye.service.ScService;

public class CatSc {

	public static void main(String[] args) throws Exception, IOException {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/beans_core.xml",
				"springmvc/springmvc.xml");
		ScService service = context.getBean(ScService.class);
		for (int i = 1; i < 36; i++) {
			CloseableHttpClient client = HttpClients.createDefault();
			// 创建get请求对象
			HttpGet get = new HttpGet("http://www.xinfadi.com.cn/marketanalysis/0/list/" + i + ".shtml?prodname=大白菜");
			// 执行get请求 返回响应对象
			HttpResponse response = client.execute(get);
			// 获取返回的状态码
			int code = response.getStatusLine().getStatusCode();
			if (code == 200) {
				// 获取返回内容
				HttpEntity entity = response.getEntity();
				// EntityUtils解码
				String html = EntityUtils.toString(entity, "UTF-8");
				Document document = Jsoup.parse(html);
				Elements elements = document.select(".hq_table").first().select("tr");
				elements.remove(0);
				List<Sc> list = new ArrayList<Sc>();
				for (Element element : elements) {
					String text = element.text();
					String[] str = text.split(" ");
					Sc sc = new Sc();
					sc.setName(str[0]);
					sc.setLowprice(Float.parseFloat(str[1]));
					sc.setAvgprice(Float.parseFloat(str[2]));
					sc.setHprice(Float.parseFloat(str[3]));
					sc.setGuige(str[4]);
					sc.setUnit(str[5]);
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					sc.setCreatdate(format.parse(str[6]));
					list.add(sc);
				}
				service.saveSc(list);
			}
			client.close();
		}
		System.out.println("save ok");
	}
}
