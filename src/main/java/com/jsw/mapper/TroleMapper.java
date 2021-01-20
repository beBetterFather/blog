package com.jsw.mapper;

import com.jsw.entity.Trole;
import com.jsw.util.MyMapper;

import java.util.List;

public interface TroleMapper extends MyMapper<Trole> {

    List<Trole> selectRolesByUserId(Integer userid);

}