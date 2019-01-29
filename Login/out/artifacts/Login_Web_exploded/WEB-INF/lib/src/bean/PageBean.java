package bean;

import entity.File;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 */
public class PageBean {

    private int pageNow; //当前页数
    private int totalPage; //总页数
    private int lineCount; //总的数据条数
    private List<File> list=new ArrayList<>(); //文件集合


    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    public List<File> getList() {
        return list;
    }

    public void setList(List<File> list) {
        this.list = list;
    }
}
