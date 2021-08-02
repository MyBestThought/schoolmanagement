package com.yht.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yht.entity.ClassEntity;
import com.yht.entity.Curriculum;
import com.yht.entity.Teacher;
import com.yht.service.IClassService;
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
import java.util.Date;
import java.util.List;

/**
 * @ClassName ClassController
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/29 8:37
 */
@Controller
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private IClassService classService;
    @Autowired
    private IStudentService studentService;

    /**
     * 班级列表
     * @param model
     * @param page
     * @return
     */
    @RequestMapping("/classAllList")
    public String classList(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //得到所有班级信息
        List<ClassEntity> classEntityList = classService.selectAll();
        //得到班级人数
        for(ClassEntity classEntity : classEntityList){
            classEntity.setStuCount(studentService.selectByCondition("","", classEntity.getClassNo()).size());
        }
        int total = (int) new PageInfo<>(classEntityList).getTotal();
        page.setTotal(total);
        model.addAttribute("page",page);
        model.addAttribute("classEntityList", classEntityList);
        return "classDir/classList";
    }

    /**
     * 添加班级信息
     * @param classEntity
     * @param attributes
     * @return
     */
    @RequestMapping("/addClass")
    public String addCurriculum(ClassEntity classEntity, RedirectAttributes attributes){
        //插入数据
        classService.insertClass(classEntity);
        attributes.addFlashAttribute("msg", "添加成功");
        return "redirect:/class/classAllList";
    }

    /**
     * 跳转至班级分析页面
     * @return
     */
    @RequestMapping("/classAnalysis")
    public String addCurriculum(){
        return "/classDir/classAnalysis";
    }

    /**
     * 根据条件（班级号、专业）查询班级信息
     * @param model
     * @param page
     * @param searchMajor
     * @param searchClassNo
     * @param attributes
     * @return
     */
    @RequestMapping("/searchByCodition")
    public String searchByCodition(Model model, Page page, String searchMajor, String searchClassNo, RedirectAttributes attributes){
        //判断查询条件
        if(searchMajor == "" && searchClassNo == "" ){
            attributes.addFlashAttribute("msg", "请输入查询条件");
            return "redirect:/class/classAllList";
        }else{
            //得到满足条件的集合
            List<ClassEntity> classEntityList = classService.selectByCondition(searchMajor, searchClassNo);
            if(classEntityList.size() > 0){
                PageHelper.offsetPage(page.getStart(),page.getCount());
                int total = (int) new PageInfo<>(classEntityList).getTotal();
                page.setTotal(total);
                model.addAttribute("page",page);
                model.addAttribute("classEntityList", classEntityList);
            }else{
                model.addAttribute("msg", "查询结果为空");
            }
            return "classDir/classList";

        }
    }

    /**
     * 删除班级信息
     * @param attributes
     * @param id
     * @return
     */
    @RequestMapping("/deleteClass")
    public String deleteClass(RedirectAttributes attributes, Integer id){
        //根据id删除班级
        classService.deleteById(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/class/classAllList";
    }

    /**
     * 跳转至班级详情页
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/classDetail")
    public String curriculumDetail(Model model, Integer id){
        //根据id查询班级信息
        ClassEntity classEntity = classService.selectById(id);
        model.addAttribute("classEntity", classEntity);
        return "/classDir/classDetail";
    }

    /**
     * 修改班级信息
     * @param classEntity
     * @param attributes
     * @return
     */
    @RequestMapping("/modifyClass")
    public String modifyClass(ClassEntity classEntity, RedirectAttributes attributes){
        //更新数据
        classService.updateClass(classEntity);
        attributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/class/classAllList";
    }

    /**
     * 导出班级数据到excel
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/exportClassData")
    public void exportClassData(HttpServletResponse response) throws UnsupportedEncodingException {
        //从数据库中查询到数据
        List<ClassEntity> classEntityList = classService.selectAll();
        for(ClassEntity classEntity : classEntityList){
            classEntity.setStuCount(studentService.selectByCondition("","", classEntity.getClassNo()).size());
        }
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("classNo", "班级号");
        writer.addHeaderAlias("major", "专业");
        writer.addHeaderAlias("classGrade", "班级等级");
        writer.addHeaderAlias("isExcellent", "卓越班(0 否 1 是)");
        writer.addHeaderAlias("stuCount", "学生数");
        //只导出设置了别名的列
        writer.setOnlyAlias(true);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(4, "班级信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(classEntityList, true);
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