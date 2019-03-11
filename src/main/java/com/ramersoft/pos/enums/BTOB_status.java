package com.ramersoft.pos.enums;

public enum BTOB_status {
	Active {
		@Override
		public String toString() {
			return "Active";
		}
	},
	InActive {
		@Override
		public String toString() {
			return "InActive";
		}
	},
	Removed {
		@Override
		public String toString() {
			return "Removed";
		}
	}		
	
}
