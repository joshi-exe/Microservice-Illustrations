package com.yash.msdemo.controller;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yash.msdemo.constant.Constants;
import com.yash.msdemo.model.Product;
import com.yash.msdemo.model.ProductForm;
import com.yash.msdemo.model.User;
import com.yash.msdemo.model.UserForm;

/**
 * @author Yash Joshi
 * @date created on 13-Sep-2021
 */
@Controller
public class ClientController {

	@Inject
	DemoFeignClient demoFeignClient;

	@GetMapping("/register")
	public String register(ModelMap map) {
		map.addAttribute("userForm", new UserForm());
		return "register";
	}

	@PostMapping("/register")
	public String doRegister(@Valid @ModelAttribute() UserForm userForm, BindingResult result, ModelMap map) {
		String responsePage = "";
		if (result.hasErrors()) {
			userForm.setMessage(null);
			map.addAttribute("userForm", userForm);
			responsePage = "register";
		} else {
			if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
				userForm.setMessage(Constants.PASSWORD_ERROR_MESSAGE);
				userForm.setIsFailure(true);
				map.addAttribute("userForm", userForm);
				responsePage = "register";
				return responsePage;
			}
			User saveUser = new User();
			saveUser.setUserName(userForm.getUserName());
			saveUser.setPassword(userForm.getPassword());
			saveUser.setConfirmPassword(userForm.getConfirmPassword());
			try {
				demoFeignClient.saveUser(saveUser);
				UserForm loginForm = new UserForm();
				loginForm.setMessage(Constants.REGISTER_SUCCESS_MESSAGE);
				loginForm.setIsFailure(false);
				map.addAttribute("userForm", loginForm);
				responsePage = "login";
			} catch (Exception e) {
				userForm.setMessage(Constants.USERNAME_ERROR_MESSAGE);
				userForm.setIsFailure(true);
				map.addAttribute("userForm", userForm);
				responsePage = "register";
			}
		}
		return responsePage;
	}

	@GetMapping("/")
	public String login(ModelMap map) {
		map.addAttribute("userForm", new UserForm());
		return "login";
	}

	@PostMapping(value = "/login")
	public String doLogin(@Valid @ModelAttribute UserForm userForm, BindingResult result, ModelMap map) {
		String resultForm = "";
		if (result.hasErrors()) {
			userForm.setMessage(null);
			map.addAttribute("userForm", userForm);
			resultForm = "login";
		} else {
			User u = new User();
			u.setUserName(userForm.getUserName());
			u.setPassword(userForm.getPassword());
			try {
				User user = demoFeignClient.authenticateUser(u);
				map.addAttribute("user", user);
				resultForm = "redirect:/home/" + user.getId();
			} catch (Exception e) {
				UserForm loginForm = new UserForm();
				loginForm.setMessage(Constants.LOGIN_FAILURE_MESSAGE);
				loginForm.setIsFailure(true);
				map.addAttribute("userForm", loginForm);
				resultForm = "login";
			}
		}
		return resultForm;
	}

	@GetMapping("/home/{id}")
	public String home(@PathVariable Long id, ModelMap map) {
		User user = demoFeignClient.getUser(id);
		List<Product> products;
		try {
			products = demoFeignClient.getProductsWithUser();
		} catch (Exception e) {
			// handle exception as reqd
			products = Collections.emptyList();
		}
		map.addAttribute("user", user);
		map.addAttribute("products", products);
		return "index";
	}

	@GetMapping("/buy/{userId}/{productId}")
	public String home(@PathVariable Long userId, @PathVariable Long productId) {
		demoFeignClient.buyProduct(productId, userId);
		return "redirect:/home/" + userId;
	}

	@GetMapping("/myProducts/{id}")
	public String myProducts(@PathVariable Long id, ModelMap map) {
		User user = demoFeignClient.getUser(id);
		try {
			List<Product> products = demoFeignClient.getAllUserProducts(id);
			map.addAttribute("user", user);
			map.addAttribute("products", products);
		} catch (Exception e) {
			map.addAttribute("user", user);
			map.addAttribute("products", Collections.emptyList());

		}
		return "myProducts";
	}

	@GetMapping("/addProduct/{id}")
	public String addProduct(@PathVariable Long id, ModelMap map) {
		User user = demoFeignClient.getUser(id);
		map.addAttribute("user", user);
		map.addAttribute("productForm", new ProductForm());
		return "addProduct";
	}

	@PostMapping(value = "/add/{id}")
	public String addProduct(@PathVariable Long id, @Valid @ModelAttribute ProductForm productForm,
			BindingResult result, ModelMap map) {
		if (result.hasErrors()) {
			productForm.setMessage(null);
			map.addAttribute("productForm", productForm);
		} else {
			Product p = new Product();
			p.setName(productForm.getName());
			p.setQty(productForm.getQty());
			p.setOrigin(productForm.getOrigin());
			try {
				demoFeignClient.saveProduct(p);
				ProductForm pf = new ProductForm();
				pf.setMessage(Constants.PRODUCT_SAVED_SUCCESS);
				pf.setIsFailure(false);
				map.addAttribute("productForm", pf);
			} catch (Exception e) {
				String msg = Constants.ERROR_MESSAGE;
				if (e.getMessage().contains(Constants.PRODUCT_ADD_ERROR)) {
					msg = Constants.PRODUCT_ADD_ERROR;
				}
				ProductForm pf = new ProductForm();
				pf.setMessage(msg);
				pf.setIsFailure(true);
				map.addAttribute("productForm", pf);
			}
		}
		User user = demoFeignClient.getUser(id);
		map.addAttribute("user", user);
		return "addProduct";
	}

	@GetMapping("/password/{id}")
	public String changePassword(@PathVariable Long id, ModelMap map) {
		User user = demoFeignClient.getUser(id);
		UserForm uf = new UserForm();
		uf.setId(user.getId());
		uf.setUserName(user.getUserName());
		map.addAttribute("userForm", uf);
		return "changePassword";
	}

	@PostMapping("password/update")
	public String changePassword(@Valid @ModelAttribute UserForm userForm, BindingResult result, ModelMap map) {
		String responsePage = "changePassword";
		if (result.hasErrors()) {
			userForm.setMessage(null);
			map.addAttribute("userForm", userForm);
		} else {
			if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
				userForm.setMessage(Constants.PASSWORD_ERROR_MESSAGE);
				userForm.setIsFailure(true);
				map.addAttribute("userForm", userForm);
				return responsePage;
			}
			User u = new User();
			u.setId(userForm.getId());
			u.setUserName(userForm.getUserName());
			u.setPassword(userForm.getPassword());
			u.setConfirmPassword((userForm.getConfirmPassword()));
			UserForm uf = new UserForm();
			try {
				demoFeignClient.updateUser(u);
				uf.setMessage(Constants.PASSWORD_CHANGE_SUCCESS);
				uf.setIsFailure(false);
			} catch (Exception e) {
				uf.setMessage(Constants.ERROR_MESSAGE);
				uf.setIsFailure(true);
			}
			uf.setId(userForm.getId());
			uf.setUserName(userForm.getUserName());
			map.addAttribute("userForm", uf);
		}
		return responsePage;
	}

}
