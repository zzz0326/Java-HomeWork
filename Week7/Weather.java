package com.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

@Controller
@RequestMapping("/hi")
public class Weather {

    //进入到指定网页
    @RequestMapping("/hi")
    public String say1(Model model)throws Exception{
        String apiUrl = "http://t.weather.sojson.com/api/weather/city/101030100";
        //开始请求
        URL url= new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        //这里转换为String，带上包名，怕你们引错包
        String result = org.apache.commons.io.IOUtils.toString(input,"utf-8");
        model.addAttribute("data",100);
        model.addAttribute("weather",result);
        JSONObject object = new JSONObject();
        //string
        object.put("string","string");
        //int
        object.put("int",2);
        //boolean
        object.put("boolean",true);
        //array
        List<Integer> integers = Arrays.asList(1,2,3);
        object.put("list",integers);
        //null

        return "say";
    }

    //返回数据
    @RequestMapping(value = "/say.do",method = RequestMethod.POST)
    @ResponseBody
    public void say(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String date= request.getParameter("date");
        System.out.println(date);
        //获取前台传来的参数
        String apiUrl = "http://t.weather.sojson.com/api/weather/city/101030100";
        //开始请求
        URL url= new URL(apiUrl);
        URLConnection open = url.openConnection();
        InputStream input = open.getInputStream();
        //这里转换为String，带上包名，怕你们引错包
        String result = org.apache.commons.io.IOUtils.toString(input,"utf-8");
        //输出
        //result = JSONObject.toJSONString(result);
        String[] weather = result.split("\\{");
        //分割得到的天气数据
        System.out.println(result);
        //运用正则表达式查找对应的日期
        String key = ".*"+date+".*";
        for(int i = 0;i<weather.length;i++){
            if(Pattern.matches(key,weather[i])){
                System.out.println(weather[i]);
                PrintWriter printWriter = null;
                try {
                    printWriter = response.getWriter();

                    JSONObject jo = new JSONObject();
                    jo.put("data",weather[i]);
                    printWriter.print(jo.toString());
                    //传数据回前台
                    return ;

                } catch (IOException ex) {
                    //Logger.getLogger(HelloController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (null != printWriter) {
                        printWriter.flush();
                        printWriter.close();
                    }
                }

            }

        }
        return;
    }

    //有ResponseBody 后返回的不是页面 是对象
    @RequestMapping("/say1.do")
    @ResponseBody
    public String say2(Model model){
        System.out.println("1111111111111111111111111111");
        String string = "sdasdas";
        model.addAttribute("data",string);
        return string;
    }
}
