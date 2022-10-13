package com.neu.azuresql.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.neu.azuresql.mapper.PeopleMapper;
import com.neu.azuresql.pojo.po.People;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
@Api(tags = "people")
@ApiSupport(order = 1)
public class PeopleController {

    @Autowired
    private PeopleMapper peopleMapper;


    @GetMapping("/fetchList")
    @ApiOperation(value = "获取所有人")
    @ApiOperationSupport(order = 1)
    public List<People> fetchList(){
        List<People> peopleList = peopleMapper.selectList(null);
        return peopleList;
    }

    @GetMapping("/fetchByName/{name}")
    @ApiOperation(value = "根据名字查询")
    @ApiOperationSupport(order = 2)
    public List<People> fetchByName(@PathVariable String name){
        List<People> peopleList = peopleMapper.selectList(new LambdaQueryWrapper<People>().eq(People::getName,name));
        return peopleList;
    }



    @PutMapping("/add")
    @ApiOperation(value = "添加人")
    @ApiOperationSupport(order = 3)
    public String add(@RequestBody People people){
        peopleMapper.insert(people);
        return "add success";
    }

    @PostMapping("/updateByName")
    @ApiOperation(value = "根据名字更新人")
    @ApiOperationSupport(order = 4)
    public String updateByName(@RequestBody People people){
        peopleMapper.update(people, new LambdaQueryWrapper<People>().eq(People::getName, people.getName()));
        return "update success";
    }

    @DeleteMapping("/deleteByName/{name}")
    @ApiOperation(value = "根据名字删除人")
    @ApiOperationSupport(order = 5)
    public String deleteByName(@PathVariable String name){
        peopleMapper.delete(new LambdaQueryWrapper<People>().eq(People::getName, name));
        return "delete success";
    }

}
