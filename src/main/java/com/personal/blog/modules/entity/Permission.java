package com.personal.blog.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限值
 *
 * @author weizp
 */
@Entity
@Table(name = "shiro_permission")
@Data
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "parent_id", updatable = false)
    private long parentId;
    
    @Column(nullable = false, unique = true, length = 32)
    private String name;

    @Column(length = 140)
    private String description;

    private int weight;

    @Version
    private Integer version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
