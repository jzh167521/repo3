package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
//@CrossOrigin// 开启控制器支持跨域访问
public class LabelController {
    @Autowired
    private LabelService labelService;


    /**
     * 查询所有
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> labelList = labelService.findAll();
        return new  Result(true, StatusCode.OK,"执行成功",labelList);
    }

    /**
     * 根据id查询
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public Result findById(@PathVariable("id") String id){
        Label byId = labelService.findById(id);
        return new Result(true,StatusCode.OK,"执行成功",byId);

    }
    /**
     * 保存
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){

        labelService.save(label);
        return new Result(true,StatusCode.OK,"执行成功");
    }
    /**
     * 更新
     */
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Result update(@RequestBody Label label,@PathVariable("id") String id){
        label.setId(id);
        labelService.update(label);

        return new Result(true,StatusCode.OK,"执行成功");
    }
    /**
     * 根据id删除
     */
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public Result delete(@PathVariable("id") String id){
        labelService.delete(id);
        return new Result(true,StatusCode.OK,"执行成功");
    }

    // 条件查询
    @RequestMapping(method = RequestMethod.POST,value = "/search")
    public Result findSearch(@RequestBody Label label){
        List<Label> labels = labelService.findByCondition(label);
        return new Result(true,StatusCode.OK,"执行成功",labels);
    }

    //条件查询带分页
    @RequestMapping(method = RequestMethod.POST,value = "/search/{page}/{size}")
    public Result findPage(@RequestBody Label  label,@PathVariable("page") int page,@PathVariable("size") int size){
        Page<Label> page1 = labelService.findPage(label, page, size);
        //创建自己定义的分页对象
        PageResult<Label> labelPageResult = new PageResult<>(page1.getTotalElements(),page1.getContent());

        return new Result(true,StatusCode.OK,"执行成功",labelPageResult);

    }
}
