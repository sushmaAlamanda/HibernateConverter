package com.ramersoft.pos.enums;

public enum Pos_Vouchers_Used_Status {
	used{
        @Override
        public String toString(){
            return "used";
        }
    },
	not_used{
	    	 @Override
		        public String toString(){
	                return "not_used";
	            }
	    }
}
