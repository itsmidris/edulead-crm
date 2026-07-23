package com.itsmidris.edulead_crm.course.controller;

import com.itsmidris.edulead_crm.course.dto.request.CreateCourseRequest;
import com.itsmidris.edulead_crm.course.dto.request.UpdateCourseRequest;
import com.itsmidris.edulead_crm.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseViewController {

    private final CourseService courseService;

    public CourseViewController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("activeMenu", "course");
        return "course/list";
    }

    @GetMapping("/add")
    public String showAddCourseForm(Model model) {
        model.addAttribute("courseForm",new CreateCourseRequest());
        model.addAttribute("activeMenu", "course");
        model.addAttribute("isEdit", false);
        return "course/form";
    }

    @PostMapping("/add")
    public String saveCourse(@Valid @ModelAttribute("courseForm") CreateCourseRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("activeMenu", "course");
            model.addAttribute("isEdit", false);
            return "course/form";
        }
        courseService.createCourse(request);
        return "redirect:/courses?success";
    }

    @GetMapping("/edit/{id}")
    public String showEditCourseForm(@PathVariable Long id, Model model) {
        model.addAttribute("courseId", id);
        model.addAttribute("courseForm", courseService.getCourseForUpdate(id));
        model.addAttribute("activeMenu", "course");
        model.addAttribute("isEdit", true);
        return "course/form";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id, @Valid @ModelAttribute("courseForm")UpdateCourseRequest request, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("courseId", id);
            model.addAttribute("activeMenu", "course");
            model.addAttribute("isEdit", true);
            return "course/form";
        }
        courseService.updateCourse(id, request);
        return "redirect:/courses?updated";
    }

    @GetMapping("/{id}")
    public String viewCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("activeMenu", "course");
        return "course/view";
    }

    @PostMapping("/deactivate/{id}")
    public String deactivateCourse(@PathVariable Long id) {
        courseService.disableCourse(id);
        return "redirect:/courses?deleted";
    }
}
