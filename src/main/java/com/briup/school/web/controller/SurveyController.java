package com.briup.school.web.controller;

import com.briup.school.bean.Questionnaire;
import com.briup.school.bean.Survey;
import com.briup.school.bean.ex.QuestionnaireEX;
import com.briup.school.bean.ex.SurveyEX;
import com.briup.school.service.IQuestionnaireService;
import com.briup.school.service.ISurveyService;
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
@RequestMapping("/survey")
@Api(description = "课调管理")
public class SurveyController {

    @Autowired
    private ISurveyService iSurveyService;

    @Autowired
    private IQuestionnaireService iQuestionnaireService;



    @GetMapping("/findall")
    @ApiOperation(value = "查看所有课调")
    public Message FindAll(){
        List<SurveyEX> list=iSurveyService.FindAll();
//        for(SurveyEX s:list){
//            System.out.println(s.getaClass().getName());
//            System.out.println(s.getCourse().getName());
//        }
        return MessageUtil.success(list);
    }


    @GetMapping("/findbycondition")
    @ApiOperation(value = "通过问卷名字条件查找")
    public Message FindByCondition(String key){
        List<Questionnaire> l=iQuestionnaireService.selectByName(key);

        List<SurveyEX> list=iSurveyService.FindByCondition(l);
        System.out.println(123);
        System.out.println(list);
        return MessageUtil.success(list);
    }

    @GetMapping("/findbyid")
    @ApiOperation(value = "预览")
    public Message FindById(int id){
        SurveyEX s=iSurveyService.FindById(id);
        QuestionnaireEX q=iQuestionnaireService.selectById(s.getQuestionnaireEX().getId());
        s.setQuestionnaireEX(q);
        return MessageUtil.success(s);
    }


    @PostMapping("/saveorupdate")
    @ApiOperation(value = "课调的添加与修改")
    public Message SaveOrUpdate(Survey survey){
        char info=iSurveyService.SaveOrUpdate(survey);
        //System.out.println(survey.getId());
        //System.out.println(survey.getDepartmentId());
        //System.out.println(survey==null);
        System.out.println(info);

        if (info=='1')
            return MessageUtil.success("不存在这个年级id");
        if (info=='2')
            return MessageUtil.success("不存在这个课程id");
        if (info=='3')
            return MessageUtil.success("不存在这个班级id");
        if (info=='4')
            return MessageUtil.success("不存在这个教师id");
        if (info=='5')
            return MessageUtil.success("不存在这个问卷id");
        if (info=='s')
            return MessageUtil.success("成功");
        else {
            return MessageUtil.success("失败了");
        }
    }

    @GetMapping("/deletebyid")
    @ApiOperation(value = "更据id删除")
    public Message DeleteById(int id){
        iSurveyService.DeleteById(id);
        return MessageUtil.success();
    }


    @GetMapping("/deletebatch")
    @ApiOperation(value = "批量删除")
    public Message DeleteBatch(int[] ids){
        for (int i:ids){
            iSurveyService.DeleteById(i);
        }
        return MessageUtil.success();
    }

}
