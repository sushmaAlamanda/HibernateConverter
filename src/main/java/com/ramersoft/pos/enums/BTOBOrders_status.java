package com.ramersoft.pos.enums;

public enum BTOBOrders_status {
	//pending,paid
	pending {
		@Override
		public String toString() {
			return "pending";
		}
	},
	paid {
		@Override
		public String toString() {
			return "paid";
		}
	}
}
