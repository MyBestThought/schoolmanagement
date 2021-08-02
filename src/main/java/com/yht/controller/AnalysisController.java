package com.yht.controller;

import com.yht.entity.ClassEntity;
import com.yht.entity.Curriculum;
import com.yht.entity.Student;
import com.yht.entity.Teacher;
import com.yht.service.IClassService;
import com.yht.service.ICurriculumService;
import com.yht.service.IStudentService;
import com.yht.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AnalysisController
 * @Description TODO
 * @Author YHT
 * @Date 2021/8/1 8:51
 */
@RestController
public class AnalysisController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private ICurriculumService curriculumService;
    @Autowired
    private IClassService classService;

    /**
     * 学生的选课数量
     * @param id
     * @return
     */
    @RequestMapping("/studentCount")
    public Map<String, Object> getData(Integer id){
        //先查到所有学生，再查到所有学生的选课数，最后添加到map
        List<Student> studentList = studentService.selectAll();
        for(Student student :studentList){
            List<String> stringList = studentService.selectClassCount(student.getStuNo());
            student.setClassCount(stringList.size());
        }
        Map<String, Object> map = new HashMap<>();
        //names：学生姓名，counts：学生选课数
        String[] names = new String[studentList.size()];
        int[] counts = new int[studentList.size()];
        int i = 0;
        //存入数据
        for(Student student :studentList){
            names[i] = student.getName();
            counts[i] = student.getClassCount();
            i++;
        }
        map.put("names", names);
        map.put("counts", counts);
        return map;
    }

    /**
     * 学生的年龄范围
     * @param id
     * @return
     */
    @RequestMapping("/studentAge")
    public Map<String, Object> getData2(Integer id){
        List<Student> studentList = studentService.selectAll();
        List<Integer> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(Student student :studentList){
            String birth  = student.getBirth().substring(0,4);
            int val = 2021 - Integer.parseInt(birth);
            list.add(val);
        }
        String[] ageRanges = new String[4];
        ageRanges[0] = "18岁以下";
        ageRanges[1] = "18岁到24岁";
        ageRanges[2] = "24岁到30岁";
        ageRanges[3] = "30岁以上";
        int[] ageCounts = new int[4];
        for(int i : list){
            if(i < 18){
                ageCounts[0] += 1;
            }else if(i >=18 && i < 24){
                ageCounts[1] += 1;
            }else if(i >=24 && i < 30){
                ageCounts[2] += 1;
            }else if(i >= 30){
                ageCounts[3] += 1;
            }
        }
        map.put("ageRanges", ageRanges);
        map.put("ageCounts", ageCounts);
        return map;
    }

    /**
     * 老师代课数量
     * @return
     */
    @RequestMapping("/takeClassCount")
    public Map<String, Object> takeClassCount(){
        List<Teacher> teacherList = teacherService.selectAll();
        for(Teacher teacher : teacherList){
            teacher.setClassCount(curriculumService.selectByTeacherNo(teacher.getTeacherNo()).size());
        }
        String[] names = new String[teacherList.size()];
        int[] counts = new int[teacherList.size()];
        int i = 0;
        for(Teacher teacher :teacherList){
            names[i] = teacher.getName();
            counts[i] = teacher.getClassCount();
            i++;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("names", names);
        map.put("counts", counts);
        return map;
    }

    /**
     * 教师的年龄范围
     * @return
     */
    @RequestMapping("/teacherAge")
    public Map<String, Object> teacherAge(){
        List<Teacher> teacherList = teacherService.selectAll();
        Map<String, Object> map = new HashMap<>();
        String[] ageRanges = new String[4];
        ageRanges[0] = "30岁以下";
        ageRanges[1] = "30岁到40岁";
        ageRanges[2] = "40岁到55岁";
        ageRanges[3] = "55岁以上";
        int[] ageCounts = new int[4];
        for(Teacher teacher : teacherList){
            if(teacher.getAge() < 30){
                ageCounts[0] += 1;
            }else if(teacher.getAge() >=30 && teacher.getAge() < 40){
                ageCounts[1] += 1;
            }else if(teacher.getAge() >=40 && teacher.getAge() < 55){
                ageCounts[2] += 1;
            }else if(teacher.getAge() >= 55){
                ageCounts[3] += 1;
            }
        }
        map.put("ageRanges", ageRanges);
        map.put("ageCounts", ageCounts);
        return map;
    }

    /**
     * 课程等级
     * @return
     */
    @RequestMapping("/curriculumGrade")
    public Map<String, Object> curriculumGrade(){
        List<Curriculum> curriculumList = curriculumService.selectAll();
        String[] names = new String[curriculumList.size()];
        int[] grades = new int[curriculumList.size()];
        int i = 0;
        for(Curriculum curriculum :curriculumList){
            names[i] = curriculum.getClassName();
            grades[i] = curriculum.getClassGrade();
            i++;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("names", names);
        map.put("grades", grades);
        return map;
    }

    /**
     * 课程课时
     * @return
     */
    @RequestMapping("/curriculumTime")
    public Map<String, Object> curriculumTime(){
        List<Curriculum> curriculumList = curriculumService.selectAll();
        Map<Integer, Integer> myMap = new HashMap<>();
        for(Curriculum curriculum : curriculumList){
            if(myMap.containsKey(curriculum.getClassHour())){
                myMap.put(curriculum.getClassHour(), myMap.get(curriculum.getClassHour())+1);
            }else{
                myMap.put(curriculum.getClassHour(), 1);
            }
        }
        System.out.println(myMap);
        int[] hours = new int[myMap.size()];
        int[] counts = new int[myMap.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
            hours[i] = entry.getKey();
            counts[i] = entry.getValue();
            i++;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("hours", hours);
        map.put("counts", counts);
        return map;
    }

    /**
     * 班级等级
     * @return
     */
    @RequestMapping("/classGrade")
    public Map<String, Object> classGrade(){
        List<ClassEntity> classEntityList = classService.selectAll();
        String[] names = new String[classEntityList.size()];
        int[] grades = new int[classEntityList.size()];
        int i = 0;
        for(ClassEntity classEntity :classEntityList){
            names[i] = classEntity.getMajor();
            grades[i] = classEntity.getClassGrade();
            i++;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("names", names);
        map.put("grades", grades);
        return map;
    }

    /**
     * 班级人数
     * @return
     */
    @RequestMapping("/classCount")
    public Map<String, Object> classCount(){
        List<ClassEntity> classEntityList = classService.selectAll();
        for(ClassEntity classEntity : classEntityList){
            classEntity.setStuCount(studentService.selectByCondition("","", classEntity.getClassNo()).size());
        }
        String[] names = new String[classEntityList.size()];
        int[] counts = new int[classEntityList.size()];
        int i = 0;
        for(ClassEntity classEntity :classEntityList){
            names[i] = classEntity.getMajor();
            counts[i] = classEntity.getStuCount();
            i++;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("names", names);
        map.put("counts", counts);
        return map;
    }
}