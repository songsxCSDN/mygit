package com.baidu.aip.test;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.test.BaiDuOCRBean.Words_result;

public class ORCTest {
	
	//百度orc接口应用参数,个人申请百度账号创建应用拿到这三个参数
	public static final String APP_ID = "11047558";
    public static final String API_KEY = "0VxUUW3PB6bCvwaGZF1LOhLj";
    public static final String SECRET_KEY = "USQiQSAL5Bj7aEanKtGcRqL7GY0c2Psc";

	public static void main(String[] args) throws Exception {
		//调用百度识别文字接口方法,将参数传递进去
		AipOcr aipOcr = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
		//添加图片路径
		String imgData = "ta.jpg";
		//转换一下json格式
		HashMap<String, String> options= new HashMap<String, String>();
		//调用接口
		org.json.JSONObject jsonObject = aipOcr.basicGeneral(imgData ,options);
		System.out.println(jsonObject.toString(2));
		//System.out.println(jsonObject);
		//json数据转换成javabean对象
		BaiDuOCRBean baiDuOCRBean = com.alibaba.fastjson.JSONObject.toJavaObject(JSON.parseObject(jsonObject.toString()), BaiDuOCRBean.class);
		//遍历拿到word里面的值
		List<Words_result> list = baiDuOCRBean.getWords_result();
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).getWords());
			//System.out.println(list.get(i).getWords().getClass());
			
			if("身高(cm)".equals(list.get(i).getWords())){
				
				System.out.println("身高:"+list.get(i+1).getWords());
			}
			else if("体重(kg)".equals(list.get(i).getWords())){
				
				System.out.println("体重:"+list.get(i+1).getWords());
			}
			else if("体重指数".equals(list.get(i).getWords())){
				
				System.out.println("体重指数:"+list.get(i+1).getWords());
			}
			else if("初步诊断".equals(list.get(i).getWords())){
				
				System.out.println("初步诊断:"+list.get(i+1).getWords());
			}
		}
	}
}
