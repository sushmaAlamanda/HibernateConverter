package com.ramersoft.pos.enums;

public enum Bom_Invoice_Status {
	//Active,InActive,Removed,InProgress;
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
	    },
	InProgress{
	    	 @Override
		        public String toString(){
	                return "InProgress";
	            }
	    }
	    

}
