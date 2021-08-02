package com.yht.controller;

import com.yht.entity.User;
import com.yht.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/18 22:47
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 登录逻辑
     * @param user
     * @param verifyInput
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public ModelAndView login(User user, @RequestParam String verifyInput, HttpSession session, RedirectAttributes attributes){
        //验证码
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        ModelAndView mv = new ModelAndView();
        //验证码校验
        if (random == null) {
            System.out.println("验证码错误");
            mv.addObject("msg", "验证码错误");
            mv.setViewName("index");
            return mv;
        }
        //判断验证码是否相等
        if(random.equals(verifyInput)){
            //根据用户名和密码查询用户
            User loginUser = userService.selectByNameAndPwd(user);
            if(loginUser != null){
                //查询成功，判断身份是否相等
                if(loginUser.getIdentity() != user.getIdentity()){
                    //不相等，则提示信息
                    mv.addObject("msg", "你不是"+(user.getIdentity() == 1 ? "管理员" : "用户"));
                    mv.setViewName("index");
                }else{
                    //相等，登陆成功
                    // session.setAttribute("loginUsername", user.getName());
                    session.setAttribute("user", user);
                    mv.setViewName("main");
                }
            } else{
                //查询失败，提示未注册
                mv.addObject("msg", "当前用户未注册");
                mv.setViewName("index");
            }
        }else{
            //验证码错误。，返回登录页
            System.out.println("验证码错误");
            mv.addObject("msg", "验证码错误");
            mv.setViewName("index");
        }
        // mv.setViewName("main");
        return mv;
    }

    /**
     * 跳转至注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 注册逻辑
     * @param user
     * @param rePassword
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register(User user, String rePassword){
        ModelAndView mv = new ModelAndView();
        //对密码校验
        if(StringUtils.isEmpty(user.getPassword())){
            mv.setViewName("register");
            mv.addObject("msg", "密码不能为空");
            return mv;
        }
        //判断两次输入的密码是否一致
        if(user.getPassword().equals(rePassword)){
            //判断用户是否已经注册
            User registerUser = userService.selectByName(user.getName());
            if(registerUser != null){
                mv.setViewName("register");
                mv.addObject("msg", "当前用户名已经注册");
                return mv;
            }else{
                //插入数据，跳转至登录页
                userService.insertUser(user);
                mv.setViewName("index");
                mv.addObject("msg", "注册成功");
            }
        }else{
            //两次输入的密码不一致，跳转至注册页面
            mv.setViewName("register");
            mv.addObject("msg", "两次输入的密码不一致");
        }
        return mv;
    }

    /**
     * 公共请求的处理
     * @param request
     * @return
     */
    @RequestMapping(value = "/toPage",method = RequestMethod.GET)
    public  String toPage(HttpServletRequest request){
        //获取请求参数
        String url= request.getParameter("url");
        return  url;
    }
}