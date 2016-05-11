package org.springframework.samples.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//类级别匹配
@RequestMapping("/class-mapping/*")
public class ClasslevelMappingController {

	//在类级别匹配路径后加方法路径
	@RequestMapping("/path")
	public @ResponseBody String byPath() {
		return "Mapped by path!";
	}
	//可用正则*匹配路径名
	@RequestMapping(value="/path/*", method=RequestMethod.GET)
	public @ResponseBody String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	@RequestMapping(value="/method", method=RequestMethod.GET)
	public @ResponseBody String byMethod() {
		return "Mapped by path + method";
	}
	
	//通过请求参数匹配 (带 foo 参数则调用byParameter()方法)
	@RequestMapping(value="/parameter", method=RequestMethod.GET, params="foo")
	public @ResponseBody String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}
	//通过请求参数匹配 (不带 foo 参数则调用byParameterNegation()方法)
	@RequestMapping(value="/parameter", method=RequestMethod.GET, params="!foo")
	public @ResponseBody String byParameterNegation() {
		return "Mapped by path + method + not presence of query!";
	}

	@RequestMapping(value="/header", method=RequestMethod.GET, headers="FooHeader=foo")
	public @ResponseBody String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	@RequestMapping(value="/notheader", method=RequestMethod.GET, headers="!FooHeader")
	public @ResponseBody String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}


	@RequestMapping(value="/consumes", method=RequestMethod.POST, consumes="application/json")
	public @ResponseBody String byConsumes(@RequestBody JavaBean javaBean) {
		return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
	}

	@RequestMapping(value="/produces", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody JavaBean byProduces() {
		return new JavaBean();
	}

}
