package com.personal.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论
 *
 * @author weizp
 */
@Entity
@Table(name = "t_comment", indexes = {
        @Index(name = "IK_POST_ID", columnList = "post_id")
})
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 父评论ID
     */
    private long pid;

    /**
     * 所属内容ID
     */
    @Column(name = "post_id")
    private long postId;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    @Column(name = "created")
    private Date created;

    @Column(name = "author_id")
    private long authorId;

    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
