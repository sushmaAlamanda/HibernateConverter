package com.ramersoft.pos.ui.service;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface LoginService {
   
      String checkUserByLoginDetails(HttpServletRequest request, HttpServletResponse response);
      LinkedHashMap<String,String> getOutletInfo();
      String getLocalDataInfo(String fieldName);
      String checkUserValidOrNot(HttpServletRequest request,  HttpSession session);
      
}
