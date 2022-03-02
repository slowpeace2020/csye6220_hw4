package edu.neu.csye6220.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * using a  session object to  store and retrieve selected items  from a simple shopping cart application. Shopping
 * cart applications typically allow users to select items from a catalog and place them in a virtual shopping car
 *
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @RequestMapping(value = "/store_view",method = RequestMethod.GET)
    public String get(HttpServletRequest req) throws IOException {
        HttpSession session = req.getSession();
        System.out.println("there");
        ObjectMapper objectMapper = new ObjectMapper();
        Object data = session.getAttribute("data");
        Map<String, List<String>> mapper = new HashMap<String, List<String>>();
        if(data!=null){
            mapper = objectMapper.readValue(String.valueOf(data), new TypeReference<Map<String,  List<String>>>(){});
            List<String> all = new ArrayList<>();
            for(String key:mapper.keySet()){
                all.addAll(mapper.get(key));
            }
            req.setAttribute("list",all);
        }

        return "part6/store";
    }

    @RequestMapping(value = "/store_act",method = RequestMethod.POST)
    public String post(HttpServletRequest req) throws IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        ObjectMapper objectMapper = new ObjectMapper();
        Object data = session.getAttribute("data");
        Map<String, List<String>> mapper = new HashMap<String, List<String>>();
        List<String> all = new ArrayList<>();
        if(action.equals("add")){
            String category = req.getParameter("category");
            System.out.println(category);
            String[] values = req.getParameterValues(category+"[]");

            List<String> list = Arrays.asList(values);
            if(data!=null){
                mapper = objectMapper.readValue(String.valueOf(data), new TypeReference<Map<String,  List<String>>>(){});
            }
            mapper.put(category,list);
            for(String key:mapper.keySet()){
                all.addAll(mapper.get(key));
            }
        }else if(action.equals("delete")){
            String item = req.getParameter("item");
            if(data!=null){
                mapper = objectMapper.readValue(String.valueOf(data), new TypeReference<Map<String,  List<String>>>(){});
                for(String key:mapper.keySet()){
                    mapper.get(key).remove(item);
                    all.addAll(mapper.get(key));
                }
            }
        }

        req.setAttribute("list",all);
        String json = objectMapper.writeValueAsString(mapper);
        session.setAttribute("data",json);

        return "part6/store";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String doGet(Model model) throws IOException {
        return "part6/store";
    }
}
