package com.bobo.demo.knife4j.controller;

import com.bobo.demo.knife4j.model.ReturnResult;
import com.bobo.demo.knife4j.model.User;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "ApiImplicitParam注解学习")
@RestController
@RequestMapping(value = "/param")
public class ApiImplicitParamController {

    /**
     * @ApiImplicitParams() 用于方法，包含一组参数说明，包含多个 @ApiImplicitParam
     * @ApiImplicitParam() 用于方法上，对单个参数的说明
     * name–参数名
     * value–参数说明
     * defaultValue-参数的默认值
     * required-是否必传 默认false
     * dataType–参数数据类型，默认String,其它值dataType="int"
     * paramType–参数放在那个地方，
     * 比如：
     *      header->获取参数，@RequestHeader
     *      query->获取参数，@RequestParam
     *      path->(用于restful接口)获取参数，@PathVariable
     *      body->(请求体)获取参数，@RequestBody User user
     *      提交的参数是这个User对象的一个json，然后会自动解析到对应的字段上去，
     *      也可以通过流的形式接收当前的请求数据，但是这个和上面的接收方式仅能使用一个
     *      （用@RequestBody之后流就会关闭了）
     *      form->以表单形式提交
     *
     * example–举例说明
     */
    @ApiOperation(value = "1-@RequestParam",notes = "query中取参数")
    @ApiOperationSupport(order = 1, author = "zhangxibo")
    @ApiImplicitParam(name = "param",value = "参数",required = true,paramType = "query",defaultValue = "张三")
    @GetMapping("/learnQuery")
    public ResponseEntity<String> param(@RequestParam(value = "param")String name){
        return ResponseEntity.ok("hello:"+name);
    }

    @ApiOperation(value = "2-@RequestHeader",notes = "header中取参数")
    @ApiOperationSupport(order = 2, author = "zhangxibo")
    @ApiImplicitParam(name = "param",value = "参数",required = true,paramType = "header",defaultValue = "1234567890")
    @GetMapping("/learnHead")
    public ResponseEntity<String> head(@RequestHeader(value = "param")String token){
        return ResponseEntity.ok("请求抬头取值:"+token);
    }

    @ApiOperation(value = "3-@PathVariable",notes = "path中取参数")
    @ApiOperationSupport(order = 3, author = "zhangxibo")
    @ApiImplicitParam(name = "param",value = "参数",required = true,paramType = "path",defaultValue = "18")
    @GetMapping("/learnPath/{param}")
    public ResponseEntity<String> path(@PathVariable(value = "param")String age){
        return ResponseEntity.ok("hello:"+age+"的姑娘");
    }

    /**
     * @RequestBody接收参数，需要使用post请求
     * @param returnResult
     * @return
     */
    @ApiOperation(value = "4-@RequestBody",notes = "body中取参数",response = ReturnResult.class)
    @ApiOperationSupport(order = 4, author = "zhangxibo")
    @ApiImplicitParam(name = "returnResult",value = "第一个参数",required = true,dataType = "ReturnResult",paramType = "body")
    @PostMapping("/learnBody")
    public ReturnResult paramBody(@RequestBody ReturnResult returnResult){
        return returnResult;
    }

    /**
     * @RequestBody接收参数，需要使用post请求
     * @return
     */
    @ApiOperation(value = "4-@RequestBody-map",notes = "body中取参数",response = Map.class)
//    @ApiOperationSupport(order = 4, author = "zhangxibo")
    @ApiImplicitParam(name = "map",value = "第一个参数",required = true,dataType = "Map",paramType = "body")
    @PostMapping("/learnBody-map")
    public Map paramBody(@RequestBody Map<String,String> map){
        return map;
    }

    @ApiOperation(value = "5-@ApiImplicitParams",notes = "多个参数")
    @ApiOperationSupport(order = 5, author = "zhangxibo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param1",value = "第一个参数",required = true,dataType = "String",defaultValue = "张三"),
            @ApiImplicitParam(name = "param2",value = "第二个参数",required = true,dataType = "int",defaultValue = "18",paramType = "path"),
            @ApiImplicitParam(name = "param3",value = "第三个参数",required = true,dataType = "String",defaultValue = "0123456789",paramType = "header")
    })
    @GetMapping("/learnParams/{param2}")
    public ResponseEntity<String> params(
            @RequestParam(value = "param1")String name,
            @PathVariable(value = "param2")int age,
            @RequestHeader(value = "param3")String token){
        return ResponseEntity.ok("hello:"+name+",你"+age+"岁了?"+"\r\n我的签名是："+token);
    }


    @ApiOperation(value = "5-@ApiImplicitParams-object",notes = "多个参数-对象接收")
    @ApiOperationSupport(order = 5, author = "zhangxibo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "名字",required = true,dataType = "String",defaultValue = "张三"),
            @ApiImplicitParam(name = "age",value = "年龄",required = true,dataType = "int",defaultValue = "18"),
            @ApiImplicitParam(name = "sex",value = "性别",required = true,dataType = "String",defaultValue = "男")
    })
    @GetMapping("/learnParams/obj")
    public ResponseEntity<String> params(User user){
        return ResponseEntity.ok(user.toString());
    }

    /**
     * @RequestBody接收参数，需要使用post请求
     * @param name
     * @param age
     * @param token
     * @param returnResult
     * @return
     */
    @ApiOperation(value = "6-@ApiImplicitParams",notes = "多个参数和body")
    @ApiOperationSupport(order = 6, author = "zhangxibo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param1",value = "第一个参数",required = true,dataType = "String",defaultValue = "张三"),
            @ApiImplicitParam(name = "param2",value = "第二个参数",required = true,dataType = "int",defaultValue = "18",paramType = "path"),
            @ApiImplicitParam(name = "param3",value = "第三个参数",required = true,dataType = "String",defaultValue = "0123456789",paramType = "header"),
            @ApiImplicitParam(name = "returnResult",value = "body参数",required = true,dataType = "ReturnResult",paramType = "body")
    })
    @PostMapping("/learnParams/body/{param2}")
    public ResponseEntity<String> paramsAndBody(
            @RequestParam(value = "param1")String name,
            @PathVariable(value = "param2")int age,
            @RequestHeader(value = "param3")String token,
            @RequestBody ReturnResult returnResult){
        String s = returnResult.toString();
        return ResponseEntity.ok("hello:"+name+",你"+age+"岁了?"+"\r\n我的签名是："+token+"\r\n"+s);
    }

    /**
     * 不建议使用
     * 注意：使用knife4j 管理参数
     * 当ApiImplicitParam中的参数和@RequestParam中的参数不一致时，knife4j会把两个地方的参数加起来展示在UI中
     * 目前设置忽略参数不生效
     * @param map
     * @return
     */
    @ApiOperation(value = "7-@ApiImplicitParams-Map",notes = "多个参数")
    @ApiOperationSupport(order = 7, author = "zhangxibo",ignoreParameters = {"map"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param1",value = "第一个参数",required = true,dataType = "String",defaultValue = "张三"),
            @ApiImplicitParam(name = "param2",value = "第二个参数",required = true,dataType = "int",defaultValue = "18"),
            @ApiImplicitParam(name = "param3",value = "第三个参数",required = true,dataType = "String",defaultValue = "0123456789")
    })
    @GetMapping("/learnParams/map")
    public ResponseEntity<String> params(@RequestParam Map<String,Object> map){
        Object param1 = map.get("param1");
        Object param2 = map.get("param2");
        Object param3 = map.get("param3");
        return ResponseEntity.ok(map.toString());
    }
}
