package gr.blog.model;

import java.io.Serializable;

public class BlogCategory implements Serializable {
    private Integer id;

    private String categoryName;

    private Integer pid;

    private String sort;

    private String description;

    private BlogCategory parentCategory;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlogCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(BlogCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String order) {
        this.sort = order == null ? null : order.trim();
    }
}