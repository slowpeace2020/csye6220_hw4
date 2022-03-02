package edu.neu.csye6220.controller;

import edu.neu.csye6220.domain.Movie;
import edu.neu.csye6220.utils.JDBCUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MVC application to browse movies and add new movies
 */


public class MovieController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String method = httpServletRequest.getMethod();
        ModelAndView model = null;
        System.out.println("method:   "+method);
        if(method.equals("GET")){
            String pageName = httpServletRequest.getParameter("page_name");
            if(pageName==null){
                pageName="movie_navigate";
            }
//            src/main/webapp/WEB-IN    F/view/part4/movie_navigate.jsp
//                           /WEB-INF/view/part4/movie_navigate.jsp
            String url ="part4/"+pageName;
            model = new ModelAndView(url);

        }else if(method.equals("POST")){
            String action = httpServletRequest.getParameter("action");
            if(action.equals("add")){
                String title = httpServletRequest.getParameter("title");
                String actress = httpServletRequest.getParameter("actress");
                String actor = httpServletRequest.getParameter("actor");
                String genre = httpServletRequest.getParameter("genre");
                String year = httpServletRequest.getParameter("year");
                String sql = "insert into movies (title,actress,actor,genre,year) values (\""
                        +title+"\",\""+actress+"\",\""+actor+"\",\""+genre+"\",\""+year+"\")";
                JDBCUtil jdbcUtil = new JDBCUtil();
                jdbcUtil.getConn();
                jdbcUtil.insert(sql);
                jdbcUtil.closConn();
                model = new ModelAndView("part4/movie_add_success");
            }else if(action.equals("search")){
                String name = httpServletRequest.getParameter("keyword");
                String valueType = httpServletRequest.getParameter("search_type");
                try {
                    List<Movie> list = findListByQuery(valueType,name);
                    httpServletRequest.setAttribute("list",list);
                    httpServletRequest.setAttribute("keyword",name);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                model = new ModelAndView("part4/movie_list");

            }
        }


        return model;
    }

    private List<Movie> findListByQuery(String column,String value) throws SQLException {
        String sql = "select * from movies where "+column+" like \"%"+value+"%\"";
        System.out.println(sql);
        JDBCUtil jdbcUtil = new JDBCUtil();
        jdbcUtil.getConn();
        ResultSet resultSet = jdbcUtil.query(sql);
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()){
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("title"));
            movie.setActress(resultSet.getString("actress"));
            movie.setActor(resultSet.getString("actor"));
            movie.setGenre(resultSet.getString("genre"));
            movie.setYear(resultSet.getString("year"));
            movies.add(movie);
        }
        resultSet.close();
        return movies;
    }

}
