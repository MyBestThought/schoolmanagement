package com.yht.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yht.entity.ClassEntity;
import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import com.yht.service.IClassService;
import com.yht.service.ICurriculumService;
import com.yht.service.IStudentService;
import com.yht.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName StudentController
 * @Description TODO
 * @Author YHT
 * @Date 2021/7/24 8:28
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClassService classService;

    /**
     * 列出所有学生记录
     * @param model
     * @param page
     * @param classNo
     * @return
     */
    @RequestMapping("/studentList")
    public String studentList(Model model, Page page, String classNo){
        //分页插件
        PageHelper.offsetPage(page.getStart(),page.getCount());
        //结果集
        List<Student> studentList = new ArrayList<>();
        //如果班级号为空，则表明是查询所有学生信息的请求，反之则是根据班级号查询该班所有学生信息
        if(classNo != null){
            studentList = studentService.selectByCondition("", "", classNo);
        }else{
            studentList = studentService.selectAll();
        }
        //根据学号得到每个学生的选课数
        for (Student student : studentList){
            List<String> stringList = studentService.selectClassCount(student.getStuNo());
            student.setClassCount(stringList.size());
        }
        //得到记录总数
        int total = (int) new PageInfo<>(studentList).getTotal();
        //设置页的总数
        page.setTotal(total);
        //向页面返回数据
        model.addAttribute("page",page);
        model.addAttribute("studentList", studentList);
        return "student/student";
    }

    /**
     * 跳转至修改学生信息界面，将该学生的信息填充到修改界面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/studentDetail")
    public String studentDetail(Integer id, Model model){
        //根据id查询到学生信息
        Student student = studentService.selectById(id);
        List<ClassEntity> classEntityList = classService.selectAll();
        model.addAttribute("classEntityList", classEntityList);
        //将数据返回到页面
        model.addAttribute("student", student);
        return "/student/studentDetail";
    }

    /**
     * 根据条件（姓名、学号、班级号）查询学生信息
     * @param model
     * @param page
     * @param searchName
     * @param searchStuNo
     * @param searchClassNo
     * @param attributes
     * @return
     */
    @RequestMapping("/selectByCondition")
    public String selectByCondition(Model model, Page page,String searchName, String searchStuNo, String searchClassNo, RedirectAttributes attributes){
        //判断条件是否为空
        if(searchName == "" &&
                searchStuNo == ""
                && searchClassNo == "" ){
            //提示查询条件为空
            attributes.addFlashAttribute("msg", "请输入查询条件");
            return "redirect:/student/studentList";
        }else{
            //根据条件查找学生信息
            List<Student> studentList = studentService.selectByCondition(searchName, searchStuNo, searchClassNo);
            //查询结果为空，页面显示提示信息。不为空，则显示记录
            if(studentList.size() > 0){
                //根据学号得到每个学生的选课数
                for (Student student : studentList){
                    List<String> stringList = studentService.selectClassCount(student.getStuNo());
                    student.setClassCount(stringList.size());
                }
                PageHelper.offsetPage(page.getStart(),page.getCount());
                int total = (int) new PageInfo<>(studentList).getTotal();
                page.setTotal(total);
                model.addAttribute("page",page);
                model.addAttribute("studentList", studentList);
            }else{
                model.addAttribute("msg", "查询结果为空");
            }
            return "student/student";

        }
    }

    /**
     * 修改学生信息
     * @param attributes
     * @param student
     * @param file
     * @param province
     * @param city
     * @param district
     * @return
     */
    @RequestMapping("/modifyStudent")
    public String modifyStudent(RedirectAttributes attributes, Student student, MultipartFile file, String province, String city, String district){
        //学生头像。如果页面提交过来的不为空，则替换，如果为空，则仍然是源头像
        if (!file.isEmpty()) {
            /**
             * 上传图片
             */
            //图像存放路径
            String filePath = "D:/Java/项目/MyDo/schoolmanagement/src/main/resources/static/userImg";
            //图片名称
            String fileName = file.getOriginalFilename();
            File targetFile = new File(filePath, fileName);
            //创建文件路径
            targetFile.getParentFile().mkdirs();
            targetFile.delete();
            try {
                file.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            student.setIcon("/userImg/"+fileName);
        }else {
            //设置源头像
            Student searchStudent = studentService.selectById(student.getId());
            student.setIcon(searchStudent.getIcon());
        }
        //学生地址
        String address = province + ":" + city + ":" + district;
        student.setAddress(address);
        studentService.updateStudent(student);
        attributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/student/studentList";
    }

    /**
     * 跳转至学生分析页面
     * @return
     */
    @RequestMapping("/studentAnalysis")
    public String studentScore(){
        return "/student/studentAnalysis";
    }

    /**
     * 跳转至调添加学生页面
     * @param model
     * @return
     */
    @RequestMapping("/toAddStudent")
    public String toAddStudent(Model model){
        //得到班级号。返回到页面
        List<ClassEntity> classEntityList = classService.selectAll();
        model.addAttribute("classEntityList", classEntityList);
        return "/student/addStudent";
    }

    /**
     * 添加学生
     * @param attributes
     * @param student
     * @param file
     * @param province
     * @param city
     * @param district
     * @return
     */
    @RequestMapping("/addStudent")
    public String addStudent(RedirectAttributes attributes, Student student, MultipartFile file, String province, String city, String district){
        //学生头像
        String filePath = "D:/Java/项目/MyDo/schoolmanagement/src/main/resources/static/userImg";
        String fileName = file.getOriginalFilename();
        File targetFile = new File(filePath, fileName);
        targetFile.getParentFile().mkdirs();
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        student.setIcon("/userImg/"+fileName);
        //学生地址
        String address = province + ":" + city + ":" + district;
        student.setAddress(address);
        //插入数据库
        studentService.insertStudent(student);
        attributes.addFlashAttribute("msg", "添加成功");
        return "redirect:/student/studentList";
    }

    /**
     * 删除学生
     * @param attributes
     * @param id
     * @return
     */
    @RequestMapping("/deleteStudent")
    public String deleteStudent(RedirectAttributes attributes, Integer id){
        //根据id删除学生
        studentService.deleteById(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/student/studentList";
    }

    /**
     * 导出学生数据，存入excel
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/exportStudentData")
    public void exportStudentData(HttpServletResponse response) throws UnsupportedEncodingException {
        //从数据库中查询到数据
        List<Student> studentList = studentService.selectAll();
        for (Student student : studentList){
            List<String> stringList = studentService.selectClassCount(student.getStuNo());
            student.setClassCount(stringList.size());
        }
        System.out.println(studentList);
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("stuNo", "学号");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("gender", "性别(0 男 1女)");
        writer.addHeaderAlias("classNo", "班级");
        writer.addHeaderAlias("birth", "生日");
        writer.addHeaderAlias("phone", "手机号");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("address", "家庭住址");
        writer.addHeaderAlias("classCount", "选课数");
        //只导出设置了别名的列
        writer.setOnlyAlias(true);
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(8, "学生信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(studentList, true);
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