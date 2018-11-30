package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.pojo.backenduser;
import com.pojo.category;
import com.pojo.dictionary;
import com.pojo.info;
import com.pojo.version;

public interface UserMapper {
	
	public info getappinfo(@Param("id")Integer id);
	
	public version getappversion(@Param("id")Integer id);
	//µÇÂ¼
	public backenduser selectNamepwd(@Param("userCode")String userCode,@Param("userPassword")String userPassword);
	//·ÖÒ³
	public int getInfoCount(@Param("softwareName")String softwareName,
	@Param("flatformId") Integer flatformId,@Param("categoryLevel1") String categoryLevel1,
	@Param("categoryLevel2") String categoryLevel2,@Param("categoryLevel3") String categoryLevel3
	,@Param("status")Integer status);
	
	public List<info> getInfoList(@Param("softwareName")String softwareName,@Param("flatformId") int flatformId,
	@Param("categoryLevel1") String categoryLevel1,@Param("categoryLevel2") String categoryLevel2,
	@Param("categoryLevel3") String categoryLevel3,@Param("status")Integer status,
	@Param("currentPageNo") int currentPageNo, @Param("pageSize")int pageSize);
	//²éÑ¯app×´Ì¬
	public List<dictionary> list(@Param("typeCode")String typeCode);
	//²éÑ¯²Ëµ¥
	public List<category> listcate(@Param("parentId")Integer parentId);
	
	public int update(@Param("status")Integer status,@Param("id")Integer id);
	
}
