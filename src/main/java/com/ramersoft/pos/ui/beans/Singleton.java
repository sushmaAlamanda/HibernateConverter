package com.ramersoft.pos.ui.beans;

public class Singleton {
     
	  private static String user_name;
	  private static String user_uuid;
	  private static String outlet_uuid;
		
	  
	    public static String getUser_name() {
			return user_name;
		}
		public static void setUser_name(String user_name) {
			Singleton.user_name = user_name;
		}
		public static String getUser_uuid() {
			return user_uuid;
		}
		public static void setUser_uuid(String user_uuid) {
			Singleton.user_uuid = user_uuid;
		}
		public static String getOutlet_uuid() {
			return outlet_uuid;
		}
		public static void setOutlet_uuid(String outlet_uuid) {
			Singleton.outlet_uuid = outlet_uuid;
		}
	  
	  
	  
}
