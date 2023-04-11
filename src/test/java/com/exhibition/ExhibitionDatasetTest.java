package com.exhibition;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.User;
import com.exhibition.mapper.ExhibitionMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ExhibitionDatasetTest {
    @Autowired
    private ExhibitionMapper exhibitionMapper;

    @Test
    public void testSelect(){
        List<Exhibition> userList = exhibitionMapper.selectList(null);
        Assert.assertEquals(2,userList.size());
        userList.forEach(System.out::println);
    }
}
