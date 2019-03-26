package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /**
     * 根据id查询
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    /**
     * 保存
     */
    public void save(Label label){
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }
    /**
     * 更新
     */
    public void update(Label label){
        labelDao.save(label);
    }
    /**
     * 根据id删除
     */
    public void delete(String id){
        labelDao.deleteById(id);
    }

    /**
     * 条件查询
     */
    public List<Label> findByCondition(Label label){
        Specification<Label> specification = generateCondition(label);
        return labelDao.findAll(specification);
    }
    /**
     * 条件查询带分页
     */
    public Page<Label> findPage(Label label,int page,int size){
        Specification<Label> specification = generateCondition(label);
        PageRequest of = PageRequest.of(page-1, size);

        return labelDao.findAll(specification,of);
    }

    /**
     * 生成查询条件
     */
    private Specification<Label> generateCondition(Label label){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder cb) {
                ArrayList<Predicate> labels = new ArrayList<>();
                // 是否提供了标签名查询
                if (!StringUtils.isEmpty(label.getLabelname())){
                    Predicate p1 = cb.like(root.get("labelname"), "%" + label.getLabelname() + "%");
                    labels.add(p1);

                }
                // 是否提供了标签状态查询
                if (!StringUtils.isEmpty(label.getState())){
                    Predicate p2 = cb.equal(root.get("state"), label.getState());
                    labels.add(p2);
                }
                //是否提供了推荐信息查询
                if (!StringUtils.isEmpty(label.getRecommend())){
                    Predicate p3 = cb.equal(root.get("recommend"), label.getRecommend());
                    labels.add(p3);
                }
                    int i = 0;
                return cb.and(labels.toArray(new Predicate[labels.size()]));
            }
        };


    }



}
