/*
 * $Header: /home/cvs/jakarta-struts/src/test/org/apache/struts/taglib/bean/TestPageTag.java,v 1.9 2004/01/13 12:48:54 husted Exp $
 * $Revision: 1.9 $
 * $Date: 2004/01/13 12:48:54 $
 *
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Struts", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.struts.taglib.bean;

import javax.servlet.jsp.PageContext;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.JspTestCase;
import org.apache.cactus.WebResponse;
import org.apache.commons.lang.StringUtils;

/**
 * Suite of unit tests for the
 * <code>org.apache.struts.taglib.bean.PageTag</code> class.
 *
 */
public class TestPageTag extends JspTestCase {

    protected final static String PAGETAG_KEY = "PAGETAG_KEY";
    protected final static String PAGETAG_VAL = "PAGETAG_VAL";

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestPageTag(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[] {TestPageTag.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     *         starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestPageTag.class);
    }

    private void runMyTest(String whichTest) throws Exception {
        request.setAttribute("runTest", whichTest);
        pageContext.forward("/test/org/apache/struts/taglib/bean/TestPageTag.jsp");
    }

        private void formatAndTest(String compare, String output) {
                //fix for introduced carriage return / line feeds
                output = StringUtils.replace(output,"\r","");
                output = StringUtils.replace(output,"\n","");
                output = output.trim();
                //System.out.println("Testing [" + compare + "] == [" + output + "]");
            assertEquals(compare, output);
        }

    public void testPageTagApplication() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY, PAGETAG_VAL, PageContext.APPLICATION_SCOPE);
        runMyTest("testPageTagApplication");
        }
        public void endPageTagApplication(WebResponse response){
                formatAndTest(PAGETAG_VAL, response.getText());
        }

    public void testPageTagSession() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY, PAGETAG_VAL, PageContext.SESSION_SCOPE);
        runMyTest("testPageTagSession");
        }
        public void endPageTagSession(WebResponse response){
                formatAndTest(PAGETAG_VAL, response.getText());
        }

    public void testPageTagRequest() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY, PAGETAG_VAL, PageContext.REQUEST_SCOPE);
        runMyTest("testPageTagRequest");
        }
        public void endPageTagRequest(WebResponse response){
                formatAndTest(PAGETAG_VAL, response.getText());
        }

    public void testPageTagResponse() throws Exception {
        pageContext.setAttribute(PAGETAG_KEY, PAGETAG_VAL, PageContext.APPLICATION_SCOPE);
        runMyTest("testPageTagResponse");
        }
        public void endPageTagResponse(WebResponse response){
                formatAndTest(PAGETAG_VAL, response.getText());
        }

    public void testPageTagConfig() throws Exception {
        config.getServletContext().setAttribute(PAGETAG_KEY, PAGETAG_VAL);
        runMyTest("testPageTagConfig");
        }
        public void endPageTagConfig(WebResponse response){
                formatAndTest(PAGETAG_VAL, response.getText());
        }


}
