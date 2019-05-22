package com.rongke.web.shiro;//import com.rongke.redis.RedisClusterCache;import com.rongke.rediscluster.CacheUtil;import org.apache.shiro.session.Session;import org.apache.shiro.session.UnknownSessionException;import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import javax.annotation.Resource;import java.io.Serializable;import java.util.Collection;import java.util.Collections;/** * Created by cww on 2017/2/26. */public class RedisSessionDao  extends AbstractSessionDAO {    @Resource    private CacheUtil cacheUtil;    Logger log = LoggerFactory.getLogger(getClass());    @Override    public void update(Session session) throws UnknownSessionException {        log.info("更新seesion,id=[{}]", session.getId().toString());        try {            cacheUtil.set(session.getId().toString(), session,0);        } catch (Exception e) {            e.printStackTrace();        }    }    @Override    public void delete(Session session) {        log.info("删除seesion,id=[{}]", session.getId().toString());        try {            cacheUtil.delkey(session.getId().toString());        } catch (Exception e) {            e.printStackTrace();        }    }    @Override    public Collection<Session> getActiveSessions() {        log.info("获取存活的session");        return Collections.emptySet();    }    @Override    protected Serializable doCreate(Session session) {        Serializable sessionId = generateSessionId(session);        assignSessionId(session, sessionId);        log.info("创建seesion,id=[{}]", session.getId().toString());        try {            cacheUtil.set(sessionId.toString(), session,0);        } catch (Exception e) {            log.error(e.getMessage());        }        return sessionId;    }    @Override    protected Session doReadSession(Serializable sessionId) {        log.info("获取seesion,id=[{}]", sessionId.toString());        Session session = null;        try {            session = cacheUtil.getCache(sessionId.toString());        } catch (Exception e) {            log.error(e.getMessage());        }        return session;    }}