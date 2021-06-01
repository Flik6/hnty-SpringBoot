package com.coco360.service;

import com.alibaba.fastjson.JSONObject;
import com.coco360.pojo.DrawPicture;
import com.coco360.pojo.PictureInfo;
import com.coco360.pojo.RespMsg;
import com.coco360.util.HttpClientUtil;
import com.coco360.util.LittleUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static com.coco360.util.LittleUtils.editPicture;

@Service("todayUtilsServices")
public class TodayUtilsServices {

    @Value("${notice}")
    public String notice;

    @Value("${titleName}")
    public String title;

    /**
     * 获取公告  spring启动时 从类加载路径下读取config.properties 文件内读取公告信息
     * @return
     */
    public RespMsg getNotice(){
        return new RespMsg(200,title,notice);
    }

    /**
     * 创建今日校园图片 通过前端传入参数 经过springMVC 包装为java Bean对象 返回创建后的图片
     * @param pictureInfo  java Bean 对象
     * @return 返回编辑后图片的base64值
     * @throws Exception
     */
    public String createPicture(PictureInfo pictureInfo) throws Exception {
        Date date = new Date();
        String format = new SimpleDateFormat("当前时间:yyyy-M-dd HH:mm:ss").format(date);
        URL resource = LittleUtils.class.getClassLoader().getResource("static/images/today.jpg");
        ArrayList infoLists = new ArrayList<>();
        infoLists.add(new DrawPicture(225, 1020, pictureInfo.getBeginTime().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(0, 0, 0)).toArray());
        infoLists.add(new DrawPicture(225, 1085, pictureInfo.getEndTime().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(0, 0, 0)).toArray());
        infoLists.add(new DrawPicture(255, 1215, pictureInfo.getPhoneNumber(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(225, 1280, pictureInfo.getReason(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(225, 1345, pictureInfo.getPosition(), 30, new Color(30, 144, 255)).toArray());
        infoLists.add(new DrawPicture(240, 1475, pictureInfo.getDestination(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(140, 1890, pictureInfo.getOwnName(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(863, 1890, pictureInfo.getInitiateAnApplication().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(365, 1995, pictureInfo.getCounselorSName(), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(863, 1995, pictureInfo.getReceiveApplication().replace("T", " ").replace(new SimpleDateFormat("yyyy-").format(new Date()), ""), 30, new Color(151, 153, 152)).toArray());
        infoLists.add(new DrawPicture(315, 640, format, 30, new Color(255, 255, 255)).toArray());
        infoLists.add(new DrawPicture(805, 1045, pictureInfo.getDuration(), 35, new Color(59, 137, 235)).toArray());
        BufferedImage image = editPicture(resource, infoLists);
        String base64 = LittleUtils.ImageToBase64(image);
        return base64;

    }


}
