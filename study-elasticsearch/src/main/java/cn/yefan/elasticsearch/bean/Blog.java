package cn.yefan.elasticsearch.bean;

import java.util.Date;

/**
 * @author yefan
 * @date 2018/01/11
 */
public class Blog {

    private Integer id;
    private String title;
    private Date postTime;
    private String content;

    public Blog() {
    }

    public Blog(Integer id, String title, Date postTime, String content) {
        this.id = id;
        this.title = title;
        this.postTime = postTime;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
