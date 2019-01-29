package entity;

import java.sql.Date;

/**
 * 上传文件实体类
 */
public class File {

    private int id; //文件编号
    private String name; //文件名称
    private String path; //文件保存路径
    private Date time; //操作时间
    private int status; //文件状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                ", status=" + status +
                '}';
    }
}
