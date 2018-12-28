package tun.int2jpg.spm.bean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import tun.int2jpg.hibernate.model.User;
import tun.int2jpg.hibernate.model.UserPleasure;
import tun.int2jpg.hibernate.service.HibernateService;

import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
public class ApplicationBean {

    private String message;

    private String userName;
    private String password;
    private Character pleasure;

    private BarChartModel pleasureReportModel;

    public void loginSignup(boolean login) {
        try {
            if(getUserName() == null
                    || getUserName().trim().equals("")
                    || getPassword() == null
                    || getPassword().trim().equals("")){
                setMessage("Username and Password must be given!");
                return;
            }

            if(login){
                getCurrentUser().setLogined(false);
                User dbUser = HibernateService.getObject(User.class, getUserName());
                if (dbUser == null) {
                    setMessage("No User!");
                } else {
                    if (!dbUser.getPassword().equals(getPassword())) {
                        setMessage("Wrong Password!");
                    } else {
                        setCurrentUser(dbUser);
                    }
                }
            }
            else{
                User user = new User();
                user.setUserName(getUserName());
                user.setPassword(getPassword());
                HibernateService.saveObject(user);

                User dbUser = HibernateService.getObject(User.class, getUserName());
                setCurrentUser(dbUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
            setMessage("Error!");
        }
    }

    public void logout(){
        if(getCurrentUser() != null && getCurrentUser().isLogined()){
            SessionBean.currentUser = new User();
            setMessage("Success");
        }
    }

    public void savePleasure() {
        try {
            if(getPleasure() == null){
                setMessage("Pleasure must be selected!");
                return;
            }

            UserPleasure userPleasure = new UserPleasure();
            userPleasure.setDate(new Date());
            userPleasure.setPleasure(getPleasure());
            userPleasure.setUser(getCurrentUser());
            HibernateService.saveObject(userPleasure);

            User dbUser = HibernateService.getObject(User.class, getCurrentUser().getUserName());
            setCurrentUser(dbUser);

            setMessage("Successful");

        } catch (Exception e) {
            e.printStackTrace();
            setMessage("Error!");
        }
    }

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

    public Character getPleasure() {
        return pleasure;
    }

    public void setPleasure(Character pleasure) {
        this.pleasure = pleasure;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getCurrentUser(){
        if(SessionBean.currentUser == null){
            SessionBean.currentUser = new User();
        }
        return SessionBean.currentUser;
    }

    public User setCurrentUser(User user){
        SessionBean.currentUser = user;
        SessionBean.currentUser.setLogined(true);

        List<UserPleasure> userPleasureReport = (List<UserPleasure>) HibernateService.listObjects("select new UserPleasure(p.pleasure,count(p.id)) from UserPleasure p where p.user.userName='"+user.getUserName()+"' group by p.pleasure");
        if(userPleasureReport != null && userPleasureReport.size() > 0){
            Map<Character, Long> values = new HashMap<Character, Long>();
            for (UserPleasure userPleasure : userPleasureReport) {
                values.put(userPleasure.getPleasure(),userPleasure.getPleasureCount());
            }
            createPleasureReportModel(values);
        }

        setMessage("Welcome " + getCurrentUser().getUserName());
        return SessionBean.currentUser;
    }

    public BarChartModel getPleasureReportModel() {
        return pleasureReportModel;
    }

    public void setPleasureReportModel(BarChartModel pleasureReportModel) {
        this.pleasureReportModel = pleasureReportModel;
    }

    /**
     * Init pleasure report model with values
     * @param values
     * @return
     */
    private BarChartModel initPleasureReportModel(Map<Character,Long> values) {
        BarChartModel model = new BarChartModel();

        ChartSeries pleasures = new ChartSeries();
        pleasures.setLabel("Pleasures");

        for(Map.Entry<Character, Long> entry : values.entrySet()) {
            pleasures.set(entry.getKey(), entry.getValue());
        }

        model.addSeries(pleasures);
        return model;
    }

    /**
     * Creates pleasure bar chart model
     * @param values
     */
    private void createPleasureReportModel(Map<Character,Long> values) {
        pleasureReportModel = initPleasureReportModel(values);

        pleasureReportModel.setTitle("Pleasure Report");
        pleasureReportModel.setLegendPosition("ne");

        Axis xAxis = pleasureReportModel.getAxis(AxisType.X);
        xAxis.setLabel("Pleasure");

        Axis yAxis = pleasureReportModel.getAxis(AxisType.Y);
        yAxis.setLabel("Count");
        yAxis.setMin(0);
        yAxis.setMax(50);
    }
}