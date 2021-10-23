package model;

public class Category {
    private Long id;
    private String name;

    public Long getCategoryId() {
        return id;
    }

    public void setCategoryId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String name) {
        this.name = name;
    }
}
