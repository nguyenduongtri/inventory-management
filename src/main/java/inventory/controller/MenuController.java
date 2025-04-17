package inventory.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import inventory.model.Auth;
import inventory.model.Menu;
import inventory.model.Paging;
import inventory.model.Role;
import inventory.service.MenuService;
import inventory.service.RoleService;
import inventory.util.Constant;

@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;

	@GetMapping(value = { "/menu/list", "/menu/list" })
	public String redirect() {
		return "redirect:/menu/list/1";
	}

	@RequestMapping("/menu/list/{page}")
	public String menuList(Model model, @PathVariable("page") int page, @ModelAttribute("searchForm") Menu menu) {
		Paging paging = new Paging(10);
		paging.setIndexPage(page);
		List<Menu> menuList = menuService.getListMenu(paging, menu);
		List<Role> roles = roleService.getRoleList(null, null);
		Collections.sort(roles, (o1, o2) -> o1.getId() - o2.getId());

		for (Menu item : menuList) {
			Map<Integer, Integer> mapAuth = new TreeMap<>();
			for (Role role : roles) {
				mapAuth.put(role.getId(), 0);
			}
			for (Object obj : item.getAuths()) {
				Auth auth = (Auth) obj;
				mapAuth.put(auth.getRole().getId(), auth.getPermission());
			}
			item.setMapAuth(mapAuth);
		}

		model.addAttribute("pageInfo", paging);
		model.addAttribute("menuList", menuList);
		model.addAttribute("roles", roles);

		return "menu-list";
	}

	@GetMapping("/menu/change-status/{id}")
	public String change(Model model, @PathVariable("id") int id, HttpSession session) {
		try {
			menuService.changeStatus(id);
			session.setAttribute(Constant.MSG_SUCCESS, "Change status success!");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute(Constant.MSG_SUCCESS, "Change status has error!!!");
		}

		return "redirect:/menu/list";
	}
}
