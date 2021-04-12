package com.trace.service.article;

import com.trace.dao.entity.Popularize;
import com.trace.dao.repository.PopularizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/8
 */
@Service
public class PopService {

    @Autowired
    PopularizeMapper mapper;

    public List<Popularize> getAll() {
        List<Popularize> popularizes = mapper.selectAll();
        if(popularizes == null){
            return new ArrayList<>();
        }
        return popularizes;
    }
}
