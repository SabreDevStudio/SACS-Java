package com.sabre.api.sacs.soap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sabre.api.sacs.contract.soap.Security;
import com.sabre.api.sacs.contract.transaction.IgnoreTransactionRS;
import com.sabre.api.sacs.soap.SoapApplicationConfiguration;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.soap.session.IgnoreTransactionWrapper;
import com.sabre.api.sacs.workflow.SharedContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SoapApplicationConfiguration.class })
public class SessionPoolTest {

    @Autowired
    @InjectMocks
    private SessionPool toBeTested;
    
    @Mock
    private IgnoreTransactionWrapper itWrapper;
    
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldReturnSession() {
        toBeTested.clear();
        Security security = new Security();
        security.setBinarySecurityToken("BST1");
        
        SharedContext context = new SharedContext();
        context.setConversationId("ConvId");
        toBeTested.addToPool(security);
        Security returned = toBeTested.getFromPool(context);
        Assert.assertEquals("BST1", returned.getBinarySecurityToken());
        Assert.assertEquals(1, toBeTested.getBusy().size());
        Assert.assertEquals(0, toBeTested.getAvailable().length);
    }
    
    @Test
    public void shouldGetSessionAndReturnItToThePool() {
        toBeTested.clear();
        Mockito.doReturn(new IgnoreTransactionRS()).when(itWrapper).executeRequest(Mockito.any(Security.class), Mockito.any(SharedContext.class));
        Security security = new Security();
        security.setBinarySecurityToken("BST1");
        
        SharedContext context = new SharedContext();
        context.setConversationId("ConvId");
        toBeTested.addToPool(security);
        Security returned = toBeTested.getFromPool(context);
        Assert.assertEquals("BST1", returned.getBinarySecurityToken());
        Assert.assertEquals(1, toBeTested.getBusy().size());
        Assert.assertEquals(0, toBeTested.getAvailable().length);
        toBeTested.returnToPool(context.getConversationId());
        Assert.assertEquals(0, toBeTested.getBusy().size());
        Assert.assertEquals(1, toBeTested.getAvailable().length);
    }
}
