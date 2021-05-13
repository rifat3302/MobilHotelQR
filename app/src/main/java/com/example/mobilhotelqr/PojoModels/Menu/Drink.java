
package com.example.mobilhotelqr.PojoModels.Menu;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Drink {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("menu_id")
    @Expose
    private Integer menuId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("turkish_name")
    @Expose
    private String turkishName;
    @SerializedName("sub_category")
    @Expose
    private Integer subCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getTurkishName() {
        return turkishName;
    }

    public void setTurkishName(String turkishName) {
        this.turkishName = turkishName;
    }

    public Integer getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Integer subCategory) {
        this.subCategory = subCategory;
    }

}
