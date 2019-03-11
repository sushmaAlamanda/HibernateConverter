package com.ramersoft.pos.enums;

public enum Pos_Outlet_Bills_Status {
//	Active,InActive,Removed,InProgress;
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
