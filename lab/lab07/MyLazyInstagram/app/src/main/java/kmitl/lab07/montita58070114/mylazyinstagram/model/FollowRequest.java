package kmitl.lab07.montita58070114.mylazyinstagram.model;

/**
 * Created by montitawichianchai on 10/20/2017 AD.
 */

public class FollowRequest {

    private String user;
    private boolean isFollow;

    public FollowRequest(String user, boolean isFollow){
        this.user = user;
        this.isFollow = isFollow;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }
}
