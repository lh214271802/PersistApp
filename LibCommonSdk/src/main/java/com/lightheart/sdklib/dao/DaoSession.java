package com.lightheart.sdklib.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lightheart.sdklib.dao.CommonDataBean;

import com.lightheart.sdklib.dao.CommonDataBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig commonDataBeanDaoConfig;

    private final CommonDataBeanDao commonDataBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        commonDataBeanDaoConfig = daoConfigMap.get(CommonDataBeanDao.class).clone();
        commonDataBeanDaoConfig.initIdentityScope(type);

        commonDataBeanDao = new CommonDataBeanDao(commonDataBeanDaoConfig, this);

        registerDao(CommonDataBean.class, commonDataBeanDao);
    }
    
    public void clear() {
        commonDataBeanDaoConfig.clearIdentityScope();
    }

    public CommonDataBeanDao getCommonDataBeanDao() {
        return commonDataBeanDao;
    }

}
