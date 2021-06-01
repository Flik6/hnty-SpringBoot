package com.coco360.controller;

import com.coco360.pojo.PictureInfo;
import com.coco360.pojo.RespMsg;
import com.coco360.service.RemoteComputerServices;
import com.coco360.service.TodayUtilsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class TodaySchoolController {
    @Autowired
    RemoteComputerServices remoteComputerServices;

    @Autowired
    TodayUtilsServices todayUtilsServices;

    @RequestMapping("/captureScreen")
    @ResponseBody
    public ModelAndView captureScreen(ModelAndView modelAndView){
        String base64 = remoteComputerServices.captureScreen();
        modelAndView.addObject("base64",base64);
        modelAndView.setViewName("captureScreen");
        return modelAndView;
    }

    @PostMapping("/getPicture")
    @ResponseBody
    public RespMsg getPicture(HttpSession httpSession, PictureInfo pictureInfo) throws Exception {
        String base64 = todayUtilsServices.createPicture(pictureInfo);
        httpSession.setAttribute("base64",base64);
        httpSession.setAttribute("username",pictureInfo.getOwnName());

//        return modelAndView;
        return new RespMsg(200,"success",base64);
    }

    @RequestMapping(value = "/notice")
    @ResponseBody
    public RespMsg getNotice(){
        return todayUtilsServices.getNotice();
    }
}
