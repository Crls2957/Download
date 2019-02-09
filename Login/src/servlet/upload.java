package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.io.File.separator;

/**
 * 接收上传文件的请求
 */
public class upload extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接收上传文件的请求");
        String message = "";
        //创建文件保存目录
        String savePath = this.getServletContext().getRealPath("/WEB-INF/savefile");
        File saveFile = new File(savePath);
        if (!saveFile.exists() || !saveFile.isDirectory()) {
            saveFile.mkdirs();
        }
        System.out.println("文件保存目录的绝对路径："+savePath);
        //创建缓冲区目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/tempfile");
        File tempFile = new File(tempPath);
        if (!tempFile.exists()) {
            tempFile.mkdirs();
        }
        System.out.println("文件缓冲区目录的绝对路径："+tempPath);
        //创建DiskFileItemFactory工厂对象
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

        //设置缓冲区大小为100KB
        diskFileItemFactory.setSizeThreshold(1024 * 100);

        //设置缓冲区目录
        diskFileItemFactory.setRepository(tempFile);

        //创建文件上传解析器
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        //解决上传时的中文乱码
        servletFileUpload.setHeaderEncoding("UTF-8");
        //设置单个上传文件最大值为10MB
        servletFileUpload.setSizeMax(1024 * 1024 * 10);
        try {
            //封装解析的请求集合
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {

                } else {
                    //获得上传文件的名称
                    //不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是
                    // 带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    String fileName = fileItem.getName();
                    System.out.println("上传文件的名称："+fileName);
                    if (fileName == null || fileName.equals("")) {
                        continue;
                    }
                    String name = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    System.out.println("截取后的名称："+name);

                    /*//fileItem的文件输入流
                    InputStream inputStream = fileItem.getInputStream();*/
                    //文件保存名称
                    String saveFileName = makeFileName(name);
                    System.out.println("文件保存的名称："+saveFileName);
                    //得到真正的文件保存目录
                    String realSavePath = makePath(saveFileName, savePath);
                    System.out.println("真正的文件保存目录："+realSavePath);

                    //创建文件保存目录
                    File file=new File(realSavePath,saveFileName);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    System.out.println("最后总创建的文件目录路径："+file.getPath());
                    //创建文件输出流
                    FileOutputStream fileOutputStream = new FileOutputStream(file);

                    /*//创建缓冲区
                    byte[] buffer = new byte[1024];

                    int len = -1;
                    while ((len = inputStream.read(buffer))!=-1 ) {
                        System.out.println(new String(buffer,0,len));
                        fileOutputStream.write(buffer, 0, len);
                    }

                    inputStream.close();
                    fileOutputStream.close();*/

                    IOUtils.copy(fileItem.getInputStream(),fileOutputStream);
                    message = "文件上传成功";
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            message = "单个文件超过最大值10M";
            req.getSession().setAttribute("message", message);
            req.getRequestDispatcher("sucess.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            message = "文件上传失败";
            req.getSession().setAttribute("message", message);
            req.getRequestDispatcher("sucess.jsp").forward(req, resp);
        }
        req.getSession().setAttribute("message", message);
        req.getRequestDispatcher("sucess.jsp").forward(req, resp);
    }

    //通过UUID设置唯一值，防止文件被覆盖
    private String makeFileName(String name) {
        return UUID.randomUUID().toString() + "_" + name;
    }

    //对文件分流
    private String makePath(String filename, String savePath) {
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf;  //0--15
        int dir2 = (hashcode & 0xf0) >> 4;  //0-15
        //构造新的保存目录
        String dir = savePath + separator + dir1 + separator + dir2;  //upload\2\3  upload\3\5
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        System.out.println("创建分组的保存文件目录名："+file.getPath());
        //如果目录不存在
        if (!file.exists()) {
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
}


