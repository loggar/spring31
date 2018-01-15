package com.loggar.component.generic;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.loggar.util.common.StringUtil;
import com.loggar.util.generic.GenericUtil;

public abstract class GenericDomainController<T, S extends GenericService<T>> {
	@Autowired ApplicationContext applicationContext;

	protected S service;

	private Class<T> domainClass;
	private Class<S> serviceClass;

	private String lowerDomainName;
	private String upperDomainName;
	
	protected String viewPath;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void setSpringBeans() {
		int index = 0;
		domainClass = (Class<T>) GenericUtil.getClassOfGenericTypeIn(getClass(), index++);
		serviceClass = (Class<S>) GenericUtil.getClassOfGenericTypeIn(getClass(), index++);

		setDomainName();

		service = applicationContext.getBean(serviceClass);
	}

	private void setDomainName() {
		this.upperDomainName = domainClass.getSimpleName();
		char[] domainNameTemp = upperDomainName.toCharArray();
		if (domainNameTemp.length > 0) {
			char tmp = StringUtil.charLowerCase(domainNameTemp[0]);
			domainNameTemp[0] = tmp;
		}
		this.lowerDomainName = new String(domainNameTemp);
	}

	@RequestMapping("/view/{id}")
	public String view(@PathVariable("id") int id, ModelMap model) {
		model.addAttribute("view" + this.upperDomainName, service.get(id)); // 사용자 등록 폼을 저장하는 목적으로 사용하는 '@SessionAttributes("domainName")' 와 구별을 두기위해 viewDomainName 라는 이름으로 model 에 등록
		return viewPath + "/" + this.lowerDomainName + "/view";
	}

	@RequestMapping("/list")
	public String list(ModelMap model) {
		List<T> list = service.getAll();
		model.put(this.lowerDomainName + "List", list);
		return viewPath + "/" + this.lowerDomainName + "/list";
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id, ModelMap model) {
		model.addAttribute("deleteResult", service.remove(id));
		return "redirect:./list";
	}

	@RequestMapping("/deleteAll")
	public String deleteAll(Model model) {
		model.addAttribute("deleteSize", service.removeAll());
		return "redirect:./list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(ModelMap model) throws InstantiationException, IllegalAccessException {
		T newDomainInstance = domainClass.newInstance();
		model.put(this.lowerDomainName, newDomainInstance);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute @Valid T entity, BindingResult memberBindingResult, SessionStatus sessionStatus) {
		if (memberBindingResult.hasErrors()) {
			return viewPath + "/" + this.lowerDomainName + "/add";
		}

		this.service.add(entity);
		sessionStatus.setComplete();
		return "redirect:./list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public void edit(@PathVariable int id, ModelMap model) {
		model.put(this.lowerDomainName, service.get(id));
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid T entity, BindingResult memberBindingResult, SessionStatus sessionStatus) {
		if (memberBindingResult.hasErrors()) {
			return viewPath + "/" + this.lowerDomainName + "/edit";
		}

		this.service.edit(entity);
		sessionStatus.setComplete(); /* 현재 컨트롤러에 의 해 세션에 저장된 (지금의 경우 @SessionAttributes 에 의해 저장된 "domain" attribute) 정보를 삭제 */
		return "redirect:./list";
	}
}
