package com.ramersoft.pos.enums;

public enum Pos_Voucher_Discounts_Status {
	/*Active,InActive,Removed;*/	
		
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
