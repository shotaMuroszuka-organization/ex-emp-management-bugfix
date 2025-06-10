package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.example.form.InsertEmployeeForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	@ModelAttribute
	public InsertEmployeeForm setInsertForm(){
		return new InsertEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@GetMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@GetMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@PostMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}

	@PostMapping("/search")
	public String searchEmployees(String name, Model model, RedirectAttributes redirectAttributes) {
		List<Employee> employeeList = employeeService.searchEmployee(name);
		if (employeeList.isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "1件もありませんでした");
			employeeList = employeeService.showList();
			model.addAttribute("employeeList", employeeList);
			return "redirect:/employee/showList";
		}
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	@GetMapping("/toInsert")
	public String toInsert() {
		return "employee/insert";
	}

	@PostMapping("/insert")
	public String insert(@Validated InsertEmployeeForm form, BindingResult result) throws IOException {
//		System.out.println(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img");

		if(result.hasErrors()){
			return toInsert();
		}

		Employee employee = new Employee();
		BeanUtils.copyProperties(form, employee);
		String fileName = form.getImage().getOriginalFilename();
		employee.setImage(fileName);
		System.out.println(employee);
		if (!form.getImage().isEmpty()){
			String uploadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img";
			Path destPath = Paths.get(uploadDir, fileName);
			form.getImage().transferTo(destPath.toFile());
		}

		employeeService.insert(employee);
		return "redirect:/employee/toInsert";
	}
}
