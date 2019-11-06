package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService{
    @Autowired
    private BannerDAO bannerDAO;

    private Banner banner = new Banner();

    @Override
    public List<Banner> selectAll(Integer page,Integer rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Banner> banners = bannerDAO.selectByRowBounds(banner, rowBounds);
        return banners;
    }

    @Override
    public Integer selectCount() {
        int count = bannerDAO.selectCount(banner);
        return count;
    }

    @Override
    public void insert(Banner banner) {
          banner.setId(UUID.randomUUID().toString());
          banner.setCreateDate(new Date());
          banner.setLastUpdateDate(new Date());
          bannerDAO.insert(banner);
    }

    @Override
    public void delete(String id) {
        bannerDAO.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Banner banner) {
        if ("".equals(banner.getImage())){
            banner.setImage(null);
        }
        int i = bannerDAO.updateByPrimaryKeySelective(banner);
        if (i==0){
            throw new RuntimeException("修改轮播图失败");
        }
    }

    @Override
    public List<Banner> selectNew() {
        List<Banner> banners = bannerDAO.selectNew();

        return banners;
    }
}
