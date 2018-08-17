package gr.wx.controller;

import gr.wx.service.CoreService;
import gr.wx.utils.CheckoutUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信公众号开发
 */
@Controller
@RequestMapping("/wx")
public class WxCoreController {

    @Autowired
    private CoreService coreService;

    @ApiOperation("确认请求来自微信")
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public String confirmFromWxRequest(HttpServletRequest request){
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        //System.out.println(signature+","+timestamp+","+nonce+","+echostr);
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && timestamp!=null && nonce != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    @ApiOperation("处理发来的消息")
    @RequestMapping(value = "", method=RequestMethod.POST)
    @ResponseBody
    public String receiveMessageFromWx(HttpServletRequest request){
        String respXml = coreService.processRequest(request);
        //System.out.println(respXml);
        return respXml;
    }
}
