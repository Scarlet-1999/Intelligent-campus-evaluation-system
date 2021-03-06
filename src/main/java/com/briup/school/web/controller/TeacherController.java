package com.briup.school.web.controller;


import com.briup.school.bean.Teacher;
import com.briup.school.service.ITeacherService;
import com.briup.school.util.Message;
import com.briup.school.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@Api(description = "教师管理")
public class TeacherController  {
    @Autowired
    private ITeacherService teacherService;

    @GetMapping("/findAll")
    @ApiOperation("查询所有")
    public Message findAll(){
        List<Teacher>list=teacherService.findAll();

        return MessageUtil.success(list);
    }

    @GetMapping("/findByNameGender")
    @ApiOperation("通过姓名或性别查询")
    public Message findByNameGender(String key,String word){
        List<Teacher> list=teacherService.findByNameGenden(key,word);
        return MessageUtil.success(list);
    }


    @GetMapping("/deleteByid")
    @ApiOperation("通过id删除")
    public Message deleteByid(int id){
        teacherService.deleteByid(id);
        return MessageUtil.success();
    }

    @GetMapping("/deleteByids")
    @ApiOperation("批量删除")
    public Message deleteByids(int[] ids){


        teacherService.delelteByids(ids);
        return MessageUtil.success();
    }


    @PostMapping("/add")
    @ApiOperation("添加教师")
    public Message add(Teacher teacher){
        teacherService.addOrUpdate(teacher);
        return MessageUtil.success();
    }


    @PostMapping("/update")
    @ApiOperation("修改教师")
    public Message update(Teacher teacher){

        teacherService.addOrUpdate(teacher);

        return MessageUtil.success();


    }



}
