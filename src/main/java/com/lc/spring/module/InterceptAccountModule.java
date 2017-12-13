package com.lc.spring.module;


import com.lc.crm.util.TimeUtil;
import com.lc.spring.entity.Metric;
import com.lc.spring.entity.Metrics;
import com.lc.spring.service.AbnormalIPService;
import com.lc.spring.service.HitAccountService;
import com.lc.spring.service.LoginCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/interceptAccount")
public class InterceptAccountModule {

    @Autowired
    private HitAccountService hitAccountService;

    @Autowired
    private AbnormalIPService abnormalIPService;

    @Autowired
    private LoginCaptchaService loginCaptchaService;

    @RequestMapping(path = "/getPageView", method = RequestMethod.POST)
    public Map<String, Object> getPageView(@RequestBody Map<String, Object> m) {

        Map<String, Object> response = new HashMap<>();

        if (null == m.get("startTime") || "".equals(m.get("startTime")) || null == m.get("endTime")
                || "".equals(m.get("endTime"))) {
            Date date = new Date();
            m.put("startTime", TimeUtil.getOnWeekStartTime(date).getTime() / 1000);
            m.put("endTime", TimeUtil.getEndTime(date).getTime() / 1000);
        }

        long startTime = Long.valueOf(String.valueOf(m.get("startTime"))).longValue();
        long endTime = Long.valueOf(String.valueOf(m.get("endTime"))).longValue();

        //  疑似被盗
        response.put("hitAccountView", getHitAccountMetric(startTime, endTime));

        // 攻击IP
        response.put("abnormalIpView",getAbnormalIPMetric(startTime  ,endTime) );

        //分类拦截
        response.put("loginCaptchaIpView", getLoginCaptchaIPMetric(startTime,  endTime));

        return response;
    }

    /**
     * 疑似账号被盗 折线统计图显示
     * @param startTime
     * @param endTime
     * @return
     */
    private Map<String, Object> getHitAccountMetric(long startTime, long endTime) {

        List<Metric> hitAccountlist = hitAccountService.getAccountCount(startTime, endTime);

        String[] hitAccountArray = {"疑似账号被盗"};

        List<String> hitAccountTimeList = new ArrayList<>();
        List<List<Float>> hitAccountSpendList = new ArrayList<List<Float>>();
        List<Float> hitAccountSpend = new ArrayList<Float>();
        for (int i = 0; i < hitAccountlist.size(); i++) {
            hitAccountTimeList.add(hitAccountlist.get(i).getName());
            hitAccountSpend.add(Float.valueOf(String.valueOf(hitAccountlist.get(i).getValue())));
        }
        hitAccountSpendList.add(hitAccountSpend);

        return createEchartsOptions(hitAccountArray, hitAccountTimeList, hitAccountSpendList);
    }

    /**
     * 攻击IP
     * @param startTime
     * @param endTime
     * @return
     */
    private Map<String, Object> getAbnormalIPMetric(long startTime, long endTime) {
        //攻击IP模块
        List<Metrics> abnormalIPList = abnormalIPService.getAbnormalIPCount(startTime, endTime);

        String[] abnormalIPArray = {"POP","WEB"};

        List<List<Float>> abnormalIPSpendList = new ArrayList<List<Float>>();
        List<String> abnormalIPTimeList = new ArrayList<>();
        for (int i = 0; i < abnormalIPArray.length; i++) {
            List<Float> hit = new ArrayList<Float>();
            abnormalIPSpendList.add(i, hit);
        }
        //先确定x轴
        for (Metrics metrics : abnormalIPList) {
            if (!abnormalIPTimeList.contains(metrics.getName())) {
                abnormalIPTimeList.add(metrics.getName());
                for (List<Float> f : abnormalIPSpendList) {
                    f.add(0f);
                }
            }
        }

        for (Metrics metrics : abnormalIPList) {
            for (int i = 0; i < abnormalIPArray.length; i++) {
                if (metrics.getCategory().equals(abnormalIPArray[i])) {
                    abnormalIPSpendList.get(i).set(abnormalIPTimeList.indexOf(metrics.getName()), Float.valueOf(String.valueOf(metrics.getValue())));
                }
            }
        }

        return createEchartsOptions(abnormalIPArray, abnormalIPTimeList, abnormalIPSpendList);


    }

    /**
     * 分类拦截
     * @param startTime
     * @param endTime
     * @return
     */
    private Map<String, Object> getLoginCaptchaIPMetric(long startTime, long endTime) {

        //分类拦截数据
        List<Metrics> loginCaptchaList = loginCaptchaService.getLoginRateCount(startTime, endTime);
        //拦截数据总量
        List<Metric> loginCaptchaAllList = loginCaptchaService.getLoginRateAllCount(startTime, endTime);

        //x轴
        List<String> loginCaptchaTimeList = new ArrayList<>();

        List<List<Float>> loginCaptchaSpendList = new ArrayList<List<Float>>();
        String[] loginCaptchaArray = {"WEB","POP",""};

        for (int i = 0; i < loginCaptchaArray.length; i++) {
            List<Float> hit = new ArrayList<Float>();
            loginCaptchaSpendList.add(i, hit);
        }

        //从总量中确定x轴
        for (Metric metrics : loginCaptchaAllList) {
            if (!loginCaptchaTimeList.contains(metrics.getName())) {
                loginCaptchaTimeList.add(metrics.getName());
                for (List<Float> f : loginCaptchaSpendList) {
                    f.add(0f);
                }
            }

        }

        for (Metrics metrics : loginCaptchaList) {
            for (int i = 0; i < loginCaptchaArray.length; i++) {
                if (metrics.getCategory().equals(loginCaptchaArray[i])) {
                    loginCaptchaSpendList.get(i).set(loginCaptchaTimeList.indexOf(metrics.getName()), Float.valueOf(String.valueOf(metrics.getValue())));
                }
            }
        }

        loginCaptchaArray[loginCaptchaArray.length - 1] = "总量";
        List<Float> loginCaptchaAllSpend = new ArrayList<Float>();

        for (int i = 0; i < loginCaptchaAllList.size(); i++) {

            loginCaptchaAllSpend.add(Float.valueOf(String.valueOf(loginCaptchaAllList.get(i).getValue())));
            if (!loginCaptchaTimeList.contains((loginCaptchaAllList.get(i).getName()))) {
                loginCaptchaTimeList.add((loginCaptchaAllList.get(i).getName()));
            }

        }
        loginCaptchaSpendList.set(loginCaptchaArray.length - 1, loginCaptchaAllSpend);

        return  createEchartsOptions(loginCaptchaArray, loginCaptchaTimeList, loginCaptchaSpendList);

    }





    /**
     * 功能：构造echarts绘图需要的数据
     *
     * @return: 图例集合legendArray, 时间轴x坐标集合timeList, 平均花费时间（每分钟）集合
     * 其他参数在echarts文档中可查
     */
    private Map<String, Object> createEchartsOptions(String[] legendArray, List<String> timeList,
                                                     List<List<Float>> spendTimeList) {
        Map<String, Object> root = new HashMap<>();
        Map<String, Object> yAxis = new HashMap<>();
        Map<String, String> axisLabel = new HashMap<>();
        List<Map<String, Object>> dataZoom = new ArrayList<>();

        axisLabel.put("formatter", "{value} 条");
        yAxis.put("type", "value");
        yAxis.put("axisLabel", axisLabel);

        Map<String, Object> xAxis = new HashMap<>();
        xAxis.put("data", timeList);
        Map<String, Integer> splitLine = new HashMap<>();
        splitLine.put("interval", 0);
        xAxis.put("splitLine", splitLine);
        xAxis.put("type", "category");
        xAxis.put("boundaryGap", false);

        Map<String, Object> title = new HashMap<>();
        if (timeList.size() != 0) {
            title.put("text", new StringBuilder().append(timeList.get(0)).append(" ~ ")
                    .append(timeList.get(timeList.size() - 1)).toString());
        }

        title.put("show", false);
        // 区域缩放
        Map<String, Object> xDataZoom = new HashMap<>();
        xDataZoom.put("type", "inside");
        xDataZoom.put("xAxisIndex", 0);
        dataZoom.add(xDataZoom);
        Map<String, Object> legend = new HashMap<>();
        // for(int i=0;i<legendArray.length;i++){
        // //针对不同domain:port，提取后面的路径信息作为返回的图例名称
        // try {
        // URL url = new URL(legendArray[i]);
        // legendArray[i] = url.getPath().replaceFirst("/", "");
        // } catch (MalformedURLException e) {
        //
        // e.printStackTrace();
        // }
        // }
        // 因为有些url花费时间过长(500ms以上)，和其他普通访问url访问时间（约10-80ms）放在一起影响展示效果
        // 故用selected=false事先把这些预置隐藏，便于查看到更多结果，在前端页面上可设置重新展示

        legend.put("data", legendArray);

        List<Map<String, Object>> seriesArray = new ArrayList<>();
        for (int i = 0; i < spendTimeList.size(); i++) {
            Map<String, Object> series = new HashMap<>();
            series.put("data", spendTimeList.get(i));
            series.put("sampling", "average");
            series.put("name", legendArray[i]);
            series.put("type", "line");
            series.put("smooth", true);
            seriesArray.add(series);
        }

        Map<String, String> toolTip = new HashMap<>();
        toolTip.put("trigger", "axis");
        toolTip.put("formatter", "");

        Map<String, Boolean> toolbox = new HashMap<>();
        toolbox.put("show", false);

        // grid设置y坐标可以防止图例过多对图形造成覆盖
        Map<String, Integer> grid = new HashMap<>();
        grid.put("y", 100);

        root.put("title", title);
        root.put("xAxis", xAxis);
        root.put("yAxis", yAxis);
        root.put("dataZoom", dataZoom);
        root.put("legend", legend);
        root.put("series", seriesArray);
        root.put("tooltip", toolTip);
        root.put("toolbox", toolbox);
        root.put("grid", grid);

        return root;
    }


}
