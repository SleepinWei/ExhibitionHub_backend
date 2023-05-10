package com.exhibition.controller;


import com.exhibition.entity.Tag;
import com.exhibition.mapper.ExMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ExhibitionControllerTest {
    @Autowired
    private ExMapper exMapper;

    @Autowired
    private ExhibitionController control;
    @Test
    public void testSearchTagById(){
        List<Tag> tags = control.searchTagById(1);
        for(Tag tag: tags){
            System.out.println(tag.getName());
        }
    }
}
