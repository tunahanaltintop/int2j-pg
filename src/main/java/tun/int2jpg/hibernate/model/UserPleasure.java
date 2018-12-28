package tun.int2jpg.hibernate.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sp_user_pleasure")
public class UserPleasure {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "p_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_name")
    User user;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "pleasure", nullable = false)
    private Character pleasure;

    @Transient
    private Long pleasureCount;

    public UserPleasure(){

    }

    public UserPleasure(Character pleasure, Long pleasureCount){
        this.pleasure = pleasure;
        this.pleasureCount = pleasureCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Character getPleasure() {
        return pleasure;
    }

    public void setPleasure(Character pleasure) {
        this.pleasure = pleasure;
    }

    public Long getPleasureCount() {
        return pleasureCount;
    }

    public void setPleasureCount(Long pleasureCount) {
        this.pleasureCount = pleasureCount;
    }
}