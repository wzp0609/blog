package com.personal.blog.modules.data;

import com.personal.blog.modules.entity.Permission;

import java.util.LinkedList;
import java.util.List;

/**
 * 权限允许
 * @author weizp
 */
public class PermissionTree extends Permission {

    private List<PermissionTree> items;

    public List<PermissionTree> getItems() {
        return items;
    }

    public void setItems(List<PermissionTree> items) {
        this.items = items;
    }

    public void addItem(PermissionTree item){
        if(this.items == null){
            this.items = new LinkedList<>();
        }
        this.items.add(item);
    }
}
