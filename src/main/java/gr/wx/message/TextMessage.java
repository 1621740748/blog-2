package gr.wx.message;

/**
 * 文本消息内容
 */
public class TextMessage extends BaseMessage{

    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
