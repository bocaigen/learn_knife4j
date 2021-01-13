package com.bobo.demo.knife4j.controller;

import com.bobo.demo.knife4j.model.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 注解：@Api
 * 1、使用在类上;
 * 2、标识这个类是swagger的资源;
 * 3、属性: tags–表示说明, value–也是说明，可以使用tags替代;
 * 4、tags可以有多个值，value只能有一个值
 *
 * 生成的api文档会根据tags分类，直白的说就是这个controller中的所有接口生成的接口文档都会在tags这个list下；tags如果有多个值，会生成多个list，每个list都显示所有接口
 *
 * @Api(tags = "列表1")
 * @Api(tags = {"列表1","列表2"})
 */
//@Api(tags = "学习knife4j注释")
@Api(tags = {"学习knife4j注释","学习knife4j注释-2"})
@RestController
public class IndexController {

    /**
     * 注解：@ApiOperation()
     * 1、用于方法
     * 2、表示一个http请求的操作
     * 3、属性：value用于方法描述，notes用于提示内容 ，tags可以重新分组（视情况而用）
     */
    @ApiOperation(value = "@ApiOperation",notes = "用在方法上，表示一个http请求",tags = "新分组")
    @GetMapping("/learnApiOperation")
    public ResponseEntity<String> sayHi(){
        return ResponseEntity.ok("hello:girl");
    }

    @ApiOperation(value = "ApiOperation的response属性",notes = "注解ApiOperation的response属性",tags = "新分组",response = ReturnResult.class)
    @ApiImplicitParams ({
        @ApiImplicitParam(name = "param1",value = "第一个参数",required = true,dataType = "String",defaultValue = "张三"),
        @ApiImplicitParam(name = "param2",value = "第二个参数",required = true,dataType = "int",defaultValue = "18",paramType = "query"),
        @ApiImplicitParam(name = "param3",value = "第三个参数",required = true,dataType = "String",defaultValue = "0123456789",paramType = "header")
    })
    @GetMapping("/learnReturnResult/{param2}")
    public ResponseEntity<ReturnResult> returnResult(
            @RequestParam(value = "param1")String name,
            @PathVariable(value = "param2")int age,
            @RequestHeader(value = "param3")String token){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        map.put("token",token);
        return ResponseEntity.ok(ReturnResult.success(map));
    }

    @ApiOperation(value = "ApiOperation的response属性",notes = "注解ApiOperation的response属性",tags = "新分组",response = ReturnResult.class)
    @ApiImplicitParam(name = "param1",value = "参数",required = true,dataType = "String",defaultValue = "张三")
    @GetMapping("/learnReturnResult2")
    public ReturnResult returnResult2(@RequestParam(value = "param1")String name){
        return ReturnResult.success(name);
    }


}
