package kmitl.lab08.montita58070114.espresso;

import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public void clearAll() {
        userInfoList.clear();
    }

    private List<UserInfo> userInfoList;
}
