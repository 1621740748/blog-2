package gr.wx.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import gr.wx.model.message.TextMessage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    //请求消息类类型
    //文本
    public  static final String REQUEST_TYPE_TEXT = "text";

    //其他类型后期再写
    //...

    /**
     * 解析微信发送的请求
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        //读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        //获取xml根节点
        Element root = document.getRootElement();
        //得到根节点所有子节点
        List<Element> elementList = root.elements();

        //遍历节点
        for(Element e: elementList){
            map.put(e.getName(), e.getText());
        }
        //释放资源
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 扩展xstream ，支持cdata
     */
    private static XStream xStream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                boolean cdata = true;

                @Override
                public void startNode(String name,Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else{
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 文本转换为xml
     * @param textMessage
     * @return
     */
    public static String messageToXml(TextMessage textMessage){
        xStream.alias("xml", textMessage.getClass());
        return xStream.toXML(textMessage);
    }
}