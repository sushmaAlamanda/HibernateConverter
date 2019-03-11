package com.ramersoft.pos.enums;

public enum ERP_Companies_Status {
	Active{
        @Override
        public String toString(){
            return "Active";
        }
    },
	InActive{
   	 @Override
	        public String toString(){
               return "InActive";
           }
   },
	Removed{
   	 @Override
	        public String toString(){
               return "Removed";
           }
   }
}
