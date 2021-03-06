package cn.gnetop.dcs.console.system.linster;

import org.springframework.context.ApplicationContext;

public class ContextHolder
{
    private static ApplicationContext ctx = null;
    
    public static void setCtx(ApplicationContext ctx)
    {
        if (null != ctx)
        {
            ContextHolder.ctx = ctx;
        }
    }
    
    public static ApplicationContext getCtx()
    {
        return ctx;
    }
}
