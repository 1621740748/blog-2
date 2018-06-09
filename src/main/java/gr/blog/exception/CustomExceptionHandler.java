package gr.blog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义异常处理类
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * 前台异常处理默认处理器
     */
    @ExceptionHandler(value = FontException.class)
    public ModelAndView fontDefaultHandler(Exception e, HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", "请求路径：" + request.getRequestURI());
        mv.setViewName("error");
        return mv;
    }
}
