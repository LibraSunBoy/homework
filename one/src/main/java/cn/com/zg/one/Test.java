package cn.com.zg.one;

import org.apache.logging.log4j.util.Strings;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
public class Test {

    //计算签名的算法
    public static String GetSignString(Map<String, String> sortedParams, String appSecret)
    {

        //把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();

        for(Map.Entry<String, String> entry : sortedParams.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            if (Strings.isNotBlank(key) && Strings.isNotBlank(value))
            {
                query.append(key).append(value);
            }
        }
        query.append(appSecret);

        //调用MD5生成签名
        String signString = EncoderByMd5(query.toString());

        return signString;
    }


    /**
     * MD5加密
     * @param buf
     * @return
     */
    public static String EncoderByMd5(String buf) {
        try {
            MessageDigest digist = MessageDigest.getInstance("MD5");
            byte[] rs = digist.digest(buf.getBytes("UTF-8"));
            StringBuffer digestHexStr = new StringBuffer();
            for (int i = 0; i < 16; i++) {
                digestHexStr.append(byteHEX(rs[i]));
            }
            return digestHexStr.toString();
        } catch (Exception e) {
        }
        return null;
    }


    /**
     * 将字节转化为16进制字符
     * @param ib
     * @return
     */
    public static String byteHEX(byte ib) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s.toUpperCase();
    }


    public static String sendGet(String strUrl, String requestParams) {
        System.out.println("sendGet strUrl:" + strUrl);
        System.out.println("sendGet requestParams:" + requestParams);

        String responseParams = "";
        BufferedReader bufferedReader = null;
        try {
            String strRequestUrl = strUrl + "?" + requestParams;
            System.out.println("strRequestUrl:"+strRequestUrl);
            URL url = new URL(strRequestUrl);
            URLConnection urlConnection = url.openConnection();    // 打开与 URL 之间的连接

            // 设置通用的请求属性
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            urlConnection.connect();    // 建立连接

            Map<String, List<String>> map = urlConnection.getHeaderFields();    // 获取所有响应头字段

            // 使用BufferedReader输入流来读取URL的响应
            bufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                responseParams += strLine;
            }
        } catch (Exception e) {
            System.out.println("sendGet Exception:"+e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("sendPost responseParams:"+ responseParams);

        return responseParams;
    }


    public static void  main(String[] args){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        simpleDateFormat.format(System.currentTimeMillis())
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("app_key","202202261123425020633");
//        map.put("timestamp",simpleDateFormat.format(System.currentTimeMillis()));
//        //获取签名
//        String sign = GetSignString(map, "0F4335DC232FE8BEC76E0E486473A9A7521CBA04");
//        String url="http://newshop.tiger8.com.cn/OpenAPI/TigerShop.ITrade.GetSoldTrades/";
//        String param="app_key=202202261123425020633&buyer_uname=&end_created=&page_no=&page_size=&start_created=&status=&timestamp="+simpleDateFormat.format(System.currentTimeMillis())+"&sign="+sign;
        //发送get请求
        String s = sendGet("http://api.xiangxing365.com:32693/appservice/cms/index/v440", "");


    }
}
