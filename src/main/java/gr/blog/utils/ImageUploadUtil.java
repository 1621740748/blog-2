package gr.blog.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ImageUploadUtil {

    //图片类型
    private static List<String> imgTypes = new ArrayList<String>();

    static {
        imgTypes.add(".jpg");
        imgTypes.add(".jpeg");
        imgTypes.add(".bmp");
        imgTypes.add(".gif");
        imgTypes.add(".png");
    }

    /**
     * 先调用文件上传，再回调，显示预览效果
     * @param request
     * @param response
     * @param path
     */
    public static void ckeditor(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        String fileName = upload(request, path);
        System.out.println(request.getContextPath());
        String imageContextPath = request.getContextPath() + "/" + path + "/" + fileName;
        response.setContentType("text/html;charset= UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" +imageContextPath + "','')");
        out.println("</script>");
        out.flush();
        out.close();
//        Enumeration<String> enums = request.getParameterNames();
//        while(enums.hasMoreElements()){
//            System.out.println("param name is " + enums.nextElement());
//        }
    }

    /**
     * 文件上传处理方法
     * @param request
     * @param path
     * @return
     */
    private static String upload(HttpServletRequest request, String path) throws IOException {
        String fileName = null;
        //创建通用多部分文件处理器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
        //判断是否是有文件上传
        if (multipartResolver.isMultipart(request)){
            //有文件上传说明，当前request为MultipartHttpServletRequest
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //获取所有文件名
            Iterator<String> names = multiRequest.getFileNames();
            while (names.hasNext()) {
                MultipartFile file = multiRequest.getFile(names.next());
                if (file != null){
                    //取得上传文件的名称
                    String myFileName = file.getOriginalFilename();
                    //如果原始文件名为“”，则说明文件不存在
                    if (myFileName.trim() != ""){
                        String suffix = myFileName.substring(myFileName.lastIndexOf(".")).toLowerCase();
                        if (!imgTypes.contains(suffix)){
                            continue;
                        }
                        String realPath = request.getServletContext().getRealPath(path);
                        //System.out.println(realPath);
                        File realPathDirectory = new File(realPath);
                        if (!realPathDirectory.exists()){
                            realPathDirectory.mkdirs();
                        }
                        fileName = DateUtil.format(new Date(), DateUtil.DATE_FORMAT_1) + suffix;
                        File uploadFile = new File(realPathDirectory + "/" + fileName);
                        //System.out.println(uploadFile);
                        file.transferTo(uploadFile);
                    }
                }
            }
        }
        return fileName;
    }
}
