package com.wuxianggujun.aop;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * �����е���
 */
public class ProxyPointcut {

    //�е������
    private final PointcutParser pointcutParser;

 

    //AspectJ ���ʽ
    private String expression;

    //���ʽ������
    private PointcutExpression pointcutExpression;

    //AspectJ
    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public ProxyPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public ProxyPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        pointcutParser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    /**
     * Class �Ƿ�ƥ���е�
     * @param targetClass
     * @return
     */
    public boolean matches(Class<?> targetClass){
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    /**
     * Method �Ƿ�ƥ���е���ʽ
     * @param method
     * @return
     */
    public boolean matches(Method method){
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if (shadowMatch.alwaysMatches()){
            return true;
        }else if (shadowMatch.neverMatches()){
            return false;
        }
        // TODO: ���и�������ж��ˣ������ org.springframework.aop.aspectj.AspectJExpressionPointcut@matches() ����
        return false;
    }

    /**
     * ��ʼ���е������
     */
    private void checkReadyToMatch() {
        if (pointcutExpression == null){
            pointcutExpression = pointcutParser.parsePointcutExpression(expression);
        }
    }
    
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
