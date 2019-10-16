package com.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 这里导入了一个Model类
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;


import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/hi")
public class HiController {



    public String say() {

        JSONObject object = new JSONObject();
        object.put("string","string");
        //int
        object.put("int",2);
        //boolean
        object.put("boolean",true);
        //array
        List<Integer> integers = Arrays.asList(1,2,3);
        object.put("list",integers);
        //null
        object.put("null",null);
        return object.toString();
    }
    @RequestMapping("/say")
    @ResponseBody
    public static String json() throws Exception{
        //参数url化
        String city = java.net.URLEncoder.encode("北京", "utf-8");

        //拼地址
        String apiUrl = "http://t.weather.sojson.com/api/weather/city/101030100";
        //开始请求
        URL url= new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        //这里转换为String，带上包名，怕你们引错包
        String result = org.apache.commons.io.IOUtils.toString(input,"utf-8");
        //输出
        //result = JSONObject.toJSONString(result);
        System.out.println(result);
        return result;
    }

}
