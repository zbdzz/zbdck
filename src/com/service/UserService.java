package com.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.backenduser;
import com.pojo.category;
import com.pojo.dictionary;
import com.pojo.info;
import com.pojo.version;

public interface UserService {
	
	public backenduser selectNamepwd(String userCode,String userPassword);
	
	public int getInfoCount(String softwareName,int flatformId,String categoryLevel1,String categoryLevel2
	,String categoryLevel3,int status);
	
	public List<info> getInfoList(String softwareName, int flatformId,String categoryLevel1,
	String categoryLevel2,String categoryLevel3,int status,int currentPageNo,int pageSize);

	public List<dictionary> list(String typeCode);
	//≤È—Ø≤Àµ•
	public List<category> listcate(Integer parentId);
	
	public info getappinfo(Integer id);
	
	public version getappversion(Integer id);
	
	public int update(Integer status,Integer id);
}
