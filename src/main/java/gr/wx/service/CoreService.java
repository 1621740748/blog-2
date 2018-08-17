package gr.wx.service;

import gr.wx.message.TextMessage;
import gr.wx.utils.MessageUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Service("coreService")
public class CoreService {

    /**
     * 处理微信发来的请求
     */
    public String processRequest(HttpServletRequest request){
        String respXml = null;
        //默认返回数据
        String respContent = "未知消息类型";

        try {
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            //System.out.println(requestMap);
            //发送方账号
            String fromUserName = requestMap.get("FromUserName");
            //System.out.println(fromUserName);
            //开发者微信号
            String toUesrName = requestMap.get("ToUserName");
            //System.out.println(toUesrName);
            //消息类型
            String msgType = requestMap.get("MsgType");
            //System.out.println(msgType);
            //消息id
            Long msgId = Long.parseLong(requestMap.get("MsgId"));

            //回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUesrName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgId(msgId);
            textMessage.setMsgType(MessageUtil.REQUEST_TYPE_TEXT);

            if (msgType.equals(MessageUtil.REQUEST_TYPE_TEXT)){
                respContent = "你瞅啥？咋滴，不服啊？";
            }
            textMessage.setContent(respContent);
            respXml = MessageUtil.messageToXml(textMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return respXml;
    }
}
