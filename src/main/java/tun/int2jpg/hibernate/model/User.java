package tun.int2jpg.hibernate.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sp_user")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(targetEntity=UserPleasure.class, mappedBy="user", fetch=FetchType.LAZY)
    private List<UserPleasure> userPleasureList;

    @Transient
    private boolean logined = false;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogined() {
        return logined;
    }

    public void setLogined(boolean logined) {
        this.logined = logined;
    }

    public List<UserPleasure> getUserPleasureList() {
        return userPleasureList;
    }

    public void setUserPleasureList(List<UserPleasure> userPleasureList) {
        this.userPleasureList = userPleasureList;
    }
}