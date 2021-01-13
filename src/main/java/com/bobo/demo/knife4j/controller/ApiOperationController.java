package com.bobo.demo.knife4j.controller;

import com.bobo.demo.knife4j.model.ReturnResult;
import com.bobo.demo.knife4j.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "ApiOperation注解学习")
@RestController
@RequestMapping(value = "/oper")
public class ApiOperationController {


    /**
     * 注解：@ApiOperation()
     * 1、用于方法
     * 2、表示一个http请求的操作
     * 3、属性：
     * value：列表名称，用于方法描述，
     * notes: 接口描述，用于提示内容
     * tags：分组列表名称，可以重新分组（视情况而用）
     *
     */
    @ApiOperation(value = "查找用户",notes = "根据用户名查找用户",tags = "用户模块")
    @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "张三")
    @GetMapping("/findUser")
    public ResponseEntity<String> findUser(String username){
        return ResponseEntity.ok("查找用户："+username);
    }

    @ApiOperation(value = "添加用户",notes = "添加用户数据",tags = "用户模块",response = ReturnResult.class)
//    @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "张三")
    @PostMapping("/insertUser")
    public ReturnResult insertUser(User user){
        return ReturnResult.success(user);
    }

    @ApiOperation(value = "修改用户",tags = "用户模块")
    @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "张三")
    @GetMapping("/modifyUser")
    public ResponseEntity<String> modifyUser(String username){
        return ResponseEntity.ok("修改用户："+username);
    }

    @ApiOperation(value = "删除用户",tags = "用户模块")
    @ApiImplicitParam(name = "username",value = "用户名",defaultValue = "张三")
    @GetMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(String username){
        return ResponseEntity.ok("删除用户："+username);
    }

    @ApiOperation(value = "查找订单",tags = "订单模块")
    @ApiImplicitParam(name = "orderCode",value = "订单号",defaultValue = "001")
    @GetMapping("/findOrder")
    public ResponseEntity<String> findOrder(String orderCode){
        return ResponseEntity.ok("查找订单"+orderCode);
    }

    @ApiOperation(value = "添加订单",tags = "订单模块")
    @ApiImplicitParam(name = "username",value = "下单账号",defaultValue = "18937196209")
    @PostMapping("/insertOrder")
    public ResponseEntity<String> insertOrder(String username){
        return ResponseEntity.ok("添加订单"+username);
    }

    @ApiOperation(value = "修改订单",tags = "订单模块")
    @ApiImplicitParam(name = "orderCode",value = "订单号",defaultValue = "002")
    @GetMapping("/modifyOrder")
    public ResponseEntity<String> modifyOrder(String orderCode){
        return ResponseEntity.ok("修改订单"+orderCode);
    }

    @ApiOperation(value = "删除订单",notes = "根据订单Id删除订单",tags = "订单模块")
    @ApiImplicitParam(name = "id",value = "订单Id",defaultValue = "1")
    @GetMapping("/deleteOrder")
    public ResponseEntity<String> deleteOrder(Long id){
        return ResponseEntity.ok("删除订单："+id);
    }
}
