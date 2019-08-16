package com.zsj.controller;

import com.zsj.dto.HomeworkIsSubmitDTO;
import com.zsj.dto.HomeworkRankDTO;
import com.zsj.entity.Homework;
import com.zsj.entity.User;
import com.zsj.entity.UserHomework;
import com.zsj.service.HomeworkService;
import com.zsj.utils.Keyutils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zsj55
 */
@RestController
@RequestMapping("hcontroller")
public class HomeworkController {
    @Resource
    HomeworkService homeworkService;

    @RequestMapping("all")
    public List<Homework> findByTypeAndOpenTime(HttpServletRequest request){
        String type = request.getParameter("type");
        Date date = new Date();
        java.sql.Date openDate = new java.sql.Date(date.getTime());
        return homeworkService.findAll(type, openDate);
    }

    @RequestMapping("add")
    public Map add(HttpServletRequest request){
        boolean rs = false;
        Map map = new HashMap();
        Homework homework = new Homework();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        String date = request.getParameter("opentime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date ot = sdf.parse(date);
            java.sql.Date openTime = new java.sql.Date(ot.getTime());
            homework.setHid(Keyutils.genUniqueKey());
            homework.setContent(content);
            homework.setTitle(title);
            homework.setType(type);
            homework.setOpenTime(openTime);
            if(homeworkService.add(homework).getTitle() != null){
                rs = true;
                map.put("result",rs);
            }
            else{
                map.put("result",rs);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("getbydateandtype")
    public List<Homework> getByDate(HttpServletRequest request){
        List<Homework> homeworkList = new ArrayList<Homework>();
        String getDate = request.getParameter("opentime");
        String type = request.getParameter("type");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(getDate);
            java.sql.Date openTime = new java.sql.Date(date.getTime());
            homeworkList = homeworkService.findAll(type,openTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return homeworkList;
    }

    @RequestMapping("delete")
    public Map deleteById(HttpServletRequest request){
        Map map = new HashMap();
        boolean rs = false;
        List<String> idList = new ArrayList<String>();
        String id = request.getParameter("id");
        idList.add(id);
        homeworkService.deleteById(id);
        if(homeworkService.findById(idList).size() == 0){
            rs = true;
            map.put("result",rs);
        }
        else{
            map.put("result",rs);
        }
        return map;
    }

    @RequestMapping("update")
    public Map update(HttpServletRequest request){
        Map map = new HashMap();
        boolean rs = false;
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        String d = request.getParameter("openTime");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date getDate = sdf.parse(d);
            java.sql.Date openTime = new java.sql.Date(getDate.getTime());
            Homework homework = new Homework();
            homework.setOpenTime(openTime);
            homework.setType(type);
            homework.setTitle(title);
            homework.setContent(content);
            homework.setHid(id);
            if(homeworkService.update(homework) != null){
                rs = true;
                map.put("result",rs);
            }
            else{
                map.put("result",rs);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("submitdetail")
    public List<HomeworkIsSubmitDTO> getHomeworkDetailByHid(HttpServletRequest request){
        String hid = request.getParameter("id");
        return homeworkService.getHomeworkDetailByHid(hid);
    }

    @RequestMapping("rank")
    public List<HomeworkRankDTO> rank(HttpServletRequest request){
        String type = request.getParameter("typeserarch");
        return homeworkService.getHomeworkRankByType(type);
    }

    @RequestMapping("upload")
    public Map fileUpload(@RequestParam MultipartFile file, HttpServletRequest request){
        Map map = new HashMap();
        boolean rs = false;
        if(!file.isEmpty()){
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            String savePath = "C:\\Users\\zsj55\\Desktop\\上传文件";
            File dest = new File(savePath+File.separator+fileName);
            try {
                file.transferTo(dest);
                rs = true;
                map.put("result",rs);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("result",rs);
            }
        }
        else if (file.isEmpty()){
            map.put("result",rs);
        }
        return map;
    }


    @RequestMapping("addmiddletable")
    public Map addMiddleTable(HttpServletRequest request){
        String uid = request.getParameter("uid");
        String hid = request.getParameter("hid");
        System.out.println(uid);
        System.out.println(hid);
        Map map = new HashMap();
        boolean rs = false;
        UserHomework uh = new UserHomework();
        uh.setHomeworkmasterId(hid);
        uh.setUserId(uid);
        UserHomework userHomework = homeworkService.addMiddleTable(uh);
        if(userHomework.getUserId() != null && userHomework.getHomeworkmasterId() != null){
            rs = true;
            map.put("result",rs);
        }
        else{
            map.put("result",rs);
        }
        return map;
    }



    /*@RequestMapping("submitdetail/{hid}")
    public List<HomeworkIsSubmitDTO> getHomeworkDetailByHid(@PathVariable("hid") String hid){
        return homeworkService.getHomeworkDetailByHid(hid);
    }*/
}
