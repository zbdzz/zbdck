package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.pojo.backenduser;
import com.pojo.category;
import com.pojo.dictionary;
import com.pojo.info;
import com.pojo.version;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	@Override
	public backenduser selectNamepwd(String userCode, String userPassword) {
		return userMapper.selectNamepwd(userCode, userPassword);
	}

	@Override
	public int getInfoCount(String softwareName, int flatformId, String categoryLevel1, String categoryLevel2,
			String categoryLevel3,int status) {
		int num = 0;
		try {
			num = userMapper.getInfoCount(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3,status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
		
	}

	@Override
	public List<info> getInfoList(String softwareName, int flatformId, String categoryLevel1, String categoryLevel2,
			String categoryLevel3,int status, int currentPageNo, int pageSize) {
		return userMapper.getInfoList(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3,status, currentPageNo, pageSize);
	}

	@Override
	public List<dictionary> list(String typeCode) {
		return userMapper.list(typeCode);
	}

	@Override
	public List<category> listcate(Integer parentId) {
		return userMapper.listcate(parentId);
	}

	@Override
	public info getappinfo(Integer id) {
		info infos = null;
		try {
			infos = userMapper.getappinfo(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infos;
	}

	@Override
	public version getappversion(Integer id) {
		version versions = null;
		try {
			versions = userMapper.getappversion(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versions;
	}

	@Override
	public int update(Integer status, Integer id) {
		int info = 0;
		try {
			info=userMapper.update(status, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
