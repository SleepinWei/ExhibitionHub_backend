package com.exhibition.infomanage;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.ExhibitionTag;
import com.exhibition.entity.Tag;
import com.exhibition.mapper.ExhibitionMapper;
import com.exhibition.mapper.ExhibitionTagMapper;
import com.exhibition.mapper.TagMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ExhibitionDatabaseTest {
    @Autowired
    private ExhibitionMapper exhibitionMapper;
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ExhibitionTagMapper exTagMapper;

    @Test
    public void testExhibitionSelect(){
        List<Exhibition> list= exhibitionMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    public void testTagSelect(){
        List<Tag> list = tagMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    public void testExhibitionTagSelect(){
        List<ExhibitionTag> list= exTagMapper.selectList(null);
        list.forEach(System.out::println);
    }
}
