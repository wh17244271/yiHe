package yh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yh.dao.GFDao;
import yh.dao.IndexDao;
import yh.service.GFService;
import yh.service.IIndexService;

import java.util.List;

@Service("gFService")
public class GFServiceImpl implements GFService {
    @Autowired
    private GFDao gFDao;
}
