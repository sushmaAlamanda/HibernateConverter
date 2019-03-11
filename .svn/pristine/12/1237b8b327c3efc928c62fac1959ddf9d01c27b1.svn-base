package com.ramersoft.pos.ui.service;

import java.util.List;

import com.ramersoft.pos.entities.Pos_Outlet_Bom_Items;
import com.ramersoft.pos.entities.Pos_Outlet_Bom_Transaction;

public interface BillsCommonFnsService {

	String companyDetails(String outlet_uuids);

	String ItemsDetails(List<Pos_Outlet_Bom_Items> bomItemsObjList, Long BillNo, String outlet_uuids,double returnAmount, int token_flag);

	String gstDetails(List<Pos_Outlet_Bom_Items> bomItemsObjList, int btobordresCount);

	String getpreOrderAmountDetails(double balance_amtcheck, List<Pos_Outlet_Bom_Transaction> bomTransactionList);
}
