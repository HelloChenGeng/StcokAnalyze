package cn.cg.tool;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ChenGeng on 2017/12/5.
 */
public class UrlLoader {

    static Logger logger = LoggerFactory.getLogger(UrlLoader.class);

    public static JSONObject loadAsJSONObject(String url){
        return loadAsJSONObject(url, "utf-8");
    }

    public static String loadAsString(String urlString){
        return loadAsString(urlString, "utf-8");
    }

    public static JSONObject loadAsJSONObject(String url, String charSet){
        String resultString = loadAsString(url, charSet);
        JSONObject result = null;
        result = JSONObject.parseObject(resultString);
        return result;
    }

    public static String loadAsString(String urlString, String charSet){
        String result = null;
        try {
            URL url = new URL(urlString);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader inReader = new InputStreamReader(in,charSet);
            StringBuffer sb = new StringBuffer();
            byte[] bs = new byte[1024];
            char[] cs = new char[1024];
            int length = 0;
            /**
             * 没经解码直接读，可能出现乱码
             */
//            while ( (length = in.read(bs,0,1024))!=-1){
//                sb.append(new String(bs,0,length));
//            }

            /**
             * 改用Reader读取
             */
            while((length = inReader.read(cs,0,1024))!=-1){
                sb.append(cs,0,length);
            }
            in.close();
            result = sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("输入异常，错误原因：{}",e.getMessage());
            e.printStackTrace();
        }
        logger.info("url地址{}读取成功", urlString);
        return result;
    }

}
