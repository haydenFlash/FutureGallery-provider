package com.futuregallery.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.futuregallery.mapper.DicTypeMapper;
import com.futuregallery.mapper.DicValueMapper;
import com.futuregallery.model.DicType;
import com.futuregallery.model.DicValue;
import com.futuregallery.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service(interfaceClass = DicService.class, version = "1.0.0", timeout = 15000)
public class DicServiceImpl implements DicService {
    @Autowired
    private DicTypeMapper dicTypeMapper;
    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public Map<String, List<DicValue>> getAll() {
        Map<String, List<DicValue>> map = new HashMap<>();

        List<DicType> dicTypeList = dicTypeMapper.getAll();
        for (DicType dicType : dicTypeList) {
            String code = dicType.getCode();

            List<DicValue> dicValueList = dicValueMapper.selectByTypeCode(code);
            map.put(code, dicValueList);
        }

        return map;
    }
}
