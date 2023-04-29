package com.exhibition;

import com.exhibition.controller.CalenderController;
import com.exhibition.entity.SubExhibition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SampleTest {
    @Autowired
    private CalenderController controller;

    @Test
    public void testSelect(){
        int userid=2;
        String src="2023-04-01";
        String dst="2024-04-30";
        String pro="";
        String ci="";
        String ar="";
        String ve="国家典籍";
        String tag="";


        List<SubExhibition> ex=controller.calendarSelect(userid,src,dst,ve,tag,pro,ci,ar);
        System.out.println(ex);
    }
}
