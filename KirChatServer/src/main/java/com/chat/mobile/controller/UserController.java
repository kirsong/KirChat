package com.chat.mobile.controller;

import com.chat.mobile.Define;
import com.chat.mobile.service.UserService;
import com.chat.mobile.vo.ResultJsonVO;
import com.chat.mobile.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/mobile")
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * 계정 만들 기
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/createUser")
    @ResponseBody
    public ResultJsonVO createUser(@RequestParam("id")String id, @RequestParam("name")String name){

        ResultJsonVO resultJsonVO=new ResultJsonVO();

        int result=userService.createUser(id,name);
        if (result>0){
            resultJsonVO.setCode(Define.SUCCESS_CODE);
        }else{
            resultJsonVO.setCode(Define.FAILD_CODE);
        }

        return resultJsonVO;

    }

}
