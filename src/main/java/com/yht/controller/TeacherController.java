package com.yht.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yht.entity.ClassEntity;
import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import com.yht.entity.Teacher;
import com.yht.service.ICurriculumService;
import com.yht.service.ITeacherService;
import com.yht.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TeacherController
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 19:29
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private ICurriculumService curriculumService;

    /**
     * 教师列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/teacherList")
    public String studentList(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询所有教师
        List<Teacher> teacherList = teacherService.selectAll();
        //查询教师带课数量
        for(Teacher teacher : teacherList){
            teacher.setClassCount(curriculumService.selectByTeacherNo(teacher.getTeacherNo()).size());
        }
        //得到总数
        int total = (int) new PageInfo<>(teacherList).getTotal();
        page.setTotal(total);
        //返回数据到页面
        model.addAttribute("page",page);
        model.addAttribute("teacherList", teacherList);
        return "teacher/teacher";
    }

    /**
     * 跳转到教师分析页面
     * @return
     */
    @RequestMapping("/teacherAnalysis")
    public String teacherAnalysis(){
        return "teacher/teacherAnalysis";
    }

    /**
     * 添加教师
     * @param attributes
     * @param teacher
     * @return
     */
    @RequestMapping("/addTeacher")
    public String addTeacher(RedirectAttributes attributes, Teacher teacher){
        //插入数据
        teacherService.insertTeacher(teacher);
        attributes.addFlashAttribute("msg", "添加成功");
        return "redirect:/teacher/teacherList";
    }

    /**
     * 删除教师
     * @param attributes
     * @param id
     * @return
     */
    @RequestMapping("/deleteTeacher")
    public String deleteTeacher(RedirectAttributes attributes, Integer id){
        //根据id删除教师
        teacherService.deleteById(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/teacher/teacherList";
    }

    /**
     * 跳转至修改教师信息界面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/teacherDetail")
    public String curriculumDetail(Model model, Integer id){
        //根据id查询教师信息
        Teacher teacher = teacherService.selectById(id);
        //传递数据到教师详情页面
        model.addAttribute("teacher", teacher);
        return "teacher/teacherDetail";
    }

    /**
     * 修改教师信息
     * @param attributes
     * @param teacher
     * @return
     */
    @RequestMapping("/modifyTeacher")
    public String modifyTeacher(RedirectAttributes attributes, Teacher teacher){
        //更新教师信息
        teacherService.updateTeacher(teacher);
        attributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/teacher/teacherList";

    }

    /**
     * 根据条件（姓名、职工号、职称）查询教师
     * @param model
     * @param page
     * @param searchName
     * @param searchTeacherNo
     * @param searchProfessor
     * @param attributes
     * @return
     */
    @RequestMapping("/searchByCondition")
    public String searchByCondition(Model model, Page page, String searchName, String searchTeacherNo, String searchProfessor,RedirectAttributes attributes){
        //判断查询条件
        if(searchName == "" && searchTeacherNo == "" && searchProfessor == ""){
            //查询条件为空
            attributes.addFlashAttribute("msg", "请输入查询条件");
            return "redirect:/teacher/teacherList";
        }else{
            //查询条件不为空
            List<Teacher> teacherList = teacherService.selectByCondition(searchName, searchTeacherNo, searchProfessor);
            //判断结果集是否为0
            if(teacherList.size() > 0){
                PageHelper.offsetPage(page.getStart(),page.getCount());
                int total = (int) new PageInfo<>(teacherList).getTotal();
                page.setTotal(total);
                model.addAttribute("page",page);
                model.addAttribute("teacherList", teacherList);
            }else{
                model.addAttribute("msg", "查询结果为空");
            }
            return "teacher/teacher";
        }
    }

    /**
     * 导出教师信息到excel
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/exportTeacherData")
    public void exportTeacherData(HttpServletResponse response) throws UnsupportedEncodingException {
        //从数据库中查询到数据
        List<Teacher> teacherList = teacherService.selectAll();
        for(Teacher teacher : teacherList){
            teacher.setClassCount(curriculumService.selectByTeacherNo(teacher.getTeacherNo()).size());
        }
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("teacherNo", "教工号");
        writer.addHeaderAlias("name", "教师姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("gender", "性别(0 男 1 女)");
        writer.addHeaderAlias("professor", "职称");
        writer.addHeaderAlias("phone", "手机号");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("classCount", "带课数");
        //只导出设置了别名的列
        writer.setOnlyAlias(true);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, "教师信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(teacherList, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //dateString.xls是弹出下载对话框的文件名，用日期作为文件名称
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        response.setHeader("Content-Disposition","attachment;filename="+dateString+".xls");
        ServletOutputStream out= null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }
}