package test;

import bean.PageBean;
import dao.FileDaoI;
import entity.File;
import org.junit.Test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import static org.junit.Assert.*;

public class FileDaoITest extends FileDaoI {

    FileDaoI fileDaoI=new FileDaoI();
    @Test
    public void upload() {
        Date date=new Date();
        java.sql.Date time=new java.sql.Date(date.getTime());
        System.err.println(fileDaoI.upload("test1.txt","d://filename/filename",time));
    }

    @Test
    public void delete() {
        System.err.println(fileDaoI.delete(2));
    }

    @Test
    public void search() {
        File file=fileDaoI.search(2);
        System.out.println(file.toString());
    }

   /* @Test
    public void all() {
    }*/

    @Test
    public void page() {
        PageBean pageBean=fileDaoI.page(1);
        List<File> list=pageBean.getList();
        for(File file:list){
            System.out.println(file.toString());
        }
    }
}