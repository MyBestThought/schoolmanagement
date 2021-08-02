package com.yht.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import com.yht.entity.Teacher;
import com.yht.service.ICurriculumService;
import com.yht.service.IStudentService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CurriculumController
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/26 15:29
 */
@Controller
@RequestMapping("/curriculum")
public class CurriculumController {
    @Autowired
    private ICurriculumService curriculumService;

    /**
     * 课程列表
     * @param model
     * @param page
     * @param stuNo
     * @param teacherNo
     * @return
     */
    @RequestMapping("/curriculumList")
    public String curriculumList(Model model, Page page, String stuNo, String teacherNo){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //查询所有课程信息
        List<Curriculum> curriculumList = new ArrayList<>();
        if(stuNo != null){
            curriculumList = curriculumService.selectByStuNo(stuNo);
        }else if(teacherNo != null){
            curriculumList = curriculumService.selectByTeacherNo(teacherNo);
        }else{
            curriculumList = curriculumService.selectAll();
        }
        int total = (int) new PageInfo<>(curriculumList).getTotal();
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("curriculumList", curriculumList);
        return "curriculum/curriculum";
    }

    /**
     * 跳转至课程分析界面
     * @return
     */
    @RequestMapping("/curriculumAnalysis")
    public String curriculumAnalysis(){

        return "curriculum/curriculumAnalysis";
    }

    /**
     * 根据条件（课程名、课程号）查询课程
     * @param model
     * @param page
     * @param searchClassNo
     * @param searchClassName
     * @param attributes
     * @return
     */
    @RequestMapping("/searchByCodition")
    public String searchByCodition(Model model, Page page, String searchClassNo, String searchClassName, RedirectAttributes attributes){
        //判断查询条件是否为空
        if(searchClassName == "" && searchClassNo == "" ){
            attributes.addFlashAttribute("msg", "请输入查询条件");
            return "redirect:/curriculum/curriculumList";
        }else{
            //得到课程信息
            List<Curriculum> curriculumList = curriculumService.selectByCondition(searchClassName, searchClassNo);
            //判断结果集是否为0
            if(curriculumList.size() > 0){
                PageHelper.offsetPage(page.getStart(),page.getCount());
                int total = (int) new PageInfo<>(curriculumList).getTotal();
                page.setTotal(total);
                model.addAttribute("page",page);
                model.addAttribute("curriculumList", curriculumList);
            }else{
                model.addAttribute("msg", "查询结果为空");
            }
            return "curriculum/curriculum";

        }
    }

    /**
     * 添加课程
     * @param curriculum
     * @param attributes
     * @return
     */
    @RequestMapping("/addCurriculum")
    public String addCurriculum(Curriculum curriculum, RedirectAttributes attributes){
        //插入数据
        curriculumService.insertCurriculum(curriculum);
        attributes.addFlashAttribute("msg", "添加成功");
        return "redirect:/curriculum/curriculumList";
    }

    /**
     * 删除课程信息
     * @param attributes
     * @param id
     * @return
     */
    @RequestMapping("/deleteCurriculum")
    public String deleteCurriculum(RedirectAttributes attributes, Integer id){
        //根据id删除课程
        curriculumService.deleteById(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/curriculum/curriculumList";
    }

    /**
     * 跳转至课详情页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/curriculumDetail")
    public String curriculumDetail(Model model, Integer id){
        //根据id查询课程详情
        Curriculum curriculum = curriculumService.selectById(id);
        //返回数据
        model.addAttribute("curriculum", curriculum);
        return "/curriculum/curriculumDetail";
    }

    /**
     * 修改课程信息
     * @param curriculum
     * @param attributes
     * @return
     */
    @RequestMapping("/modifyCurriculum")
    public String modifyCurriculum(Curriculum curriculum, RedirectAttributes attributes){
        //更新数据
        curriculumService.updateCurriculum(curriculum);
        attributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/curriculum/curriculumList";
    }

    /**
     * 导出课程信息到excel
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/exportCurriculumData")
    public void exportCurriculumData(HttpServletResponse response) throws UnsupportedEncodingException {
        //从数据库中查询到数据
        List<Curriculum> curriculumList = curriculumService.selectAll();
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("classNo", "课程号");
        writer.addHeaderAlias("className", "课程名");
        writer.addHeaderAlias("classRoom", "教室");
        writer.addHeaderAlias("classHour", "课时");
        writer.addHeaderAlias("classGrade", "课程等级");
        //只导出设置了别名的列
        writer.setOnlyAlias(true);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(4, "课程信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(curriculumList, true);
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