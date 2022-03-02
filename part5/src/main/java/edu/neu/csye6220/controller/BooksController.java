package edu.neu.csye6220.controller;

import edu.neu.csye6220.utils.JDBCUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MVC application and use PreparedStatement to enter the books to the databas
 */


public class BooksController extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String method = httpServletRequest.getMethod();
        ModelAndView model = null;
        System.out.println("method:   "+method);
        if(method.equals("GET")){
            try {
                Integer num = Integer.valueOf(httpServletRequest.getParameter("num"));
                model = new ModelAndView("part5/book_add");
                model.addObject("num",num);
            } catch (NumberFormatException e) {
                model = new ModelAndView("part5/books");
            }
            
        }else if(method.equals("POST")){
            String[] isbns = httpServletRequest.getParameterValues("isbn[]");
            String[] titles = httpServletRequest.getParameterValues("title[]");
            String[] authors = httpServletRequest.getParameterValues("authors[]");
            String[] prices = httpServletRequest.getParameterValues("price[]");
            int len =isbns.length;

            JDBCUtil jdbcUtil = new JDBCUtil();
            jdbcUtil.getConn();
            for(int i=0;i<len;i++){
                String sql = "insert into books (isbn,title,authors,price) values (\""
                        +isbns[i]+"\",\""+titles[i]+"\",\""+authors[i]+"\",\""+prices[i]+"\")";
                jdbcUtil.insert(sql);
            }

            jdbcUtil.closConn();
            model = new ModelAndView("part5/book_add_success");
            model.addObject("num",len);
        }
        
        return model;
    }
}
