
package com.example.mobilhotelqr.PojoModels.UserInfo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class  UserInfoModel {

    @SerializedName("user_info")
    @Expose
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
