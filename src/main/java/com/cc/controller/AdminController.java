package com.cc.controller;


import com.cc.codeutil.IVerifyCodeGen;
import com.cc.codeutil.SimpleCharVerifyCodeGenImpl;
import com.cc.codeutil.VerifyCode;
import com.cc.entity.Admin;
import com.cc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("异常处理");
        }
    }

    @PostMapping("/verify")
    public String verify(Admin admin, HttpServletRequest request, Model model) {
        String id = admin.getId();
        String password = admin.getPassword();
        String code = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("VerifyCode");
        System.out.println(code);
        System.out.println(realCode);
        if (!realCode.toLowerCase().equals(code.toLowerCase())) {
            model.addAttribute("message", "验证码不正确");
            return "login";
        }

        if(adminService.verifyUser(id,password)) {
            session.setAttribute("admin", admin);
            return "main";
        }
        model.addAttribute("message","账号或者密码错误");
        return "login";
    }


    @RequestMapping("/register")
    public String register() {
        return "register";
    }


    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }



    @PostMapping("/addadmin")
    public String addadmin(Admin admin, Model model) {

        String id = admin.getId();
        String password = admin.getPassword();

        if(adminService.addUser(id,password)) {
            model.addAttribute("message", "注册成功！");
        }else {
            model.addAttribute("message", "注册失败，请重新注册！");
        }
        return "login";
    }
}
