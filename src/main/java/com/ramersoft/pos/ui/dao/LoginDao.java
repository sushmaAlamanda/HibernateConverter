package com.ramersoft.pos.ui.dao;

import java.util.List;

import com.ramersoft.pos.entities.ERPUSERS;

public interface LoginDao {
   String checkUserExistence(String email,String password);
   List getOutletsInfo();
   String getLocalDataInfo(String fieldName);
   ERPUSERS checkOtpUserDetails(String user_uuid);
}
