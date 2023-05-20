package com.exhibition.mapper;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.ExTag;
import com.exhibition.entity.Tag;
import com.exhibition.service.IExService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ExhibitionTest {
    @Autowired
    private ExMapper exMapper;
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ExTagMapper exTagMapper;

    @Autowired
    private ExMapper exMapper;


    @Test
    public void testGetAllOrganizer(){
        List<String> organizers = exMapper.getAllOrganizers();
        organizers.forEach(System.out::println);
    }
    @Test
    public void testExhibitionSelect(){
        List<Exhibition> list= exMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    public void testTagSelect(){
        List<Tag> list = tagMapper.selectList(null);
        list.forEach(System.out::println);
    }
    @Test
    public void testExhibitionTagSelect(){
        List<ExTag> list= exTagMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void searchByKeywordTest(){
        List<Exhibition> exhibitions = exMapper.searchByKeyword("北京");
        exhibitions.forEach(System.out::println);
    }
}
