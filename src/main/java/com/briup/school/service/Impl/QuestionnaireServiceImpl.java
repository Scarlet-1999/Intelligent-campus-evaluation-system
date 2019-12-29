package com.briup.school.service.Impl;

import com.briup.school.bean.Questionnaire;
import com.briup.school.bean.QuestionnaireExample;
import com.briup.school.mapper.QuestionnaireMapper;
import com.briup.school.service.IQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements IQuestionnaireService {
    @Autowired
    private QuestionnaireMapper questionnaireMapper;
    @Override
    public List<Questionnaire> findAll() throws RuntimeException {
        QuestionnaireExample questionnaireExample= new QuestionnaireExample();
        List<Questionnaire> questionnaires =
                questionnaireMapper.selectByExample(questionnaireExample);
        return questionnaires;
    }
}
