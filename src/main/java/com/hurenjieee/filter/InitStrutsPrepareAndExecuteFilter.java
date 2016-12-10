package com.hurenjieee.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.hurenjieee.entity.Dictionary;
import com.hurenjieee.service.DictionaryService;
import com.hurenjieee.util.SpringContextUtil;

/**
 * Servlet Filter implementation class InitStrutsPrepareAndExecuteFilter
 */
@WebFilter("/InitStrutsPrepareAndExecuteFilter")
public class InitStrutsPrepareAndExecuteFilter extends StrutsPrepareAndExecuteFilter implements Filter {
       
    /**
     * @see StrutsPrepareAndExecuteFilter#StrutsPrepareAndExecuteFilter()
     */
    public InitStrutsPrepareAndExecuteFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		ServletContext servletContext =  fConfig.getServletContext();
		Map<String,String> map=new TreeMap<String,String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		// FIXME 对数据库数据字典的访问，写到ServletContext中
		DictionaryService dictionaryService = (DictionaryService) SpringContextUtil.getBean("dictionaryService");
		List<Dictionary> list=dictionaryService.getList();
		for(Dictionary dictionary:list){
			map.put(dictionary.getKey(),dictionary.getValue());
		}
		servletContext.setAttribute("applicationMap", map);
	}

}
