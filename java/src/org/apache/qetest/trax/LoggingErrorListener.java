/*
 * The Apache Software License, Version 1.1
 *
 *
 * Copyright (c) 2000 The Apache Software Foundation.  All rights 
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
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:  
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xalan" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
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
 * individuals on behalf of the Apache Software Foundation and was
 * originally based on software copyright (c) 2000, Lotus
 * Development Corporation., http://www.lotus.com.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

/*
 *
 * LoggingErrorListener.java
 *
 */
package org.apache.qetest.trax;

import org.apache.qetest.*;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

//-------------------------------------------------------------------------

/**
 * Cheap-o ErrorListener for use by API tests.
 * <p>Implements javax.xml.transform.ErrorListener and dumps 
 * everything to a Reporter; is separately settable as 
 * to when it will throw an exception.</p>
 * @todo try calling getLocator() and asking it for info directly
 * @author shane_curcuru@lotus.com
 * @version $Id$
 */
public class LoggingErrorListener implements ErrorListener
{

    /** No-op ctor seems useful. */
    public LoggingErrorListener(){}

    /**
     * Ctor that calls setReporter automatically.  
     *
     * @param r Reporter we should log to
     * @todo this should really be a Logger, not Reporter
     */
    public LoggingErrorListener(Reporter r)
    {
        setReporter(r);
    }

    /** Our Reporter, who we tell all our secrets to. */
    private Reporter reporter;

    /**
     * Accesor methods for our Reporter.  
     *
     * NEEDSDOC @param r
     */
    public void setReporter(Reporter r)
    {
        if (r != null)
            reporter = r;
    }

    /**
     * Accesor methods for our Reporter.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public Reporter getReporter()
    {
        return (reporter);
    }

    /** Prefixed to all reporter msg output. */
    private final String prefix = "EH:";

    /** 
     * Constants determining when we should throw exceptions.
     * <ul>Flags are combineable like a bitfield.
     * <li>THROW_NEVER - never ever (always continue - note this 
     * have have unexpected effects when fatalErrors happen, see 
     * {@link javax.xml.transform.ErrorListener#fatalError(javax.xml.transform.TransformerException)}</li>
     * <li>THROW_ON_WARNING - throw only on warnings</li>
     * <li>THROW_ON_ERROR - throw only on errors</li>
     * <li>THROW_ON_FATAL - throw only on fatalErrors - default</li>
     * <li>THROW_ALWAYS - always throw exceptions</li>
     * </ul>
     */
    public static final int THROW_NEVER = 0;

    /** NEEDSDOC Field THROW_ON_WARNING          */
    public static final int THROW_ON_WARNING = 1;

    /** NEEDSDOC Field THROW_ON_ERROR          */
    public static final int THROW_ON_ERROR = 2;

    /** NEEDSDOC Field THROW_ON_FATAL          */
    public static final int THROW_ON_FATAL = 4;

    /** NEEDSDOC Field THROW_ALWAYS          */
    public static final int THROW_ALWAYS = THROW_ON_WARNING & THROW_ON_ERROR
                                           & THROW_ON_FATAL;

    /** If we should throw an exception for each message type. */
    private int throwWhen = THROW_ON_FATAL;

    /**
     * Accesor methods; don't think it needs to be synchronized.  
     *
     * NEEDSDOC @param t
     */
    public void setThrowWhen(int t)
    {
        throwWhen = t;
    }

    /**
     * Accesor methods; don't think it needs to be synchronized.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public int getThrowWhen()
    {
        return throwWhen;
    }

    /** Counters for how many problems or messages we've processed. */
    private int warningCtr = 0;

    /** NEEDSDOC Field errorCtr          */
    private int errorCtr = 0;

    /** NEEDSDOC Field fatalCtr          */
    private int fatalCtr = 0;

    /**
     * Accesor methods for counters.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public int getWarningCtr()
    {
        return warningCtr;
    }

    /**
     * Accesor methods for counters.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public int getErrorCtr()
    {
        return errorCtr;
    }

    /**
     * Accesor methods for counters.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public int getFatalCtr()
    {
        return fatalCtr;
    }

    /**
     * Cheap-o string representation of our state.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public String getCounterString()
    {
        return (prefix + " Warnings: " + getWarningCtr() + ", Errors: "
                + getErrorCtr() + ", FatalErrors: " + getFatalCtr());
    }

    /** Cheap-o string representation of last warn/error/fatal we got. */
    private String lastError = null;

    /**
     * NEEDSDOC Method setLastError 
     *
     *
     * NEEDSDOC @param s
     */
    protected void setLastError(String s)
    {
        lastError = s;
    }

    /**
     * Accessor for string representation of last warn/error/fatal we got.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public String getLastError()
    {
        return lastError;
    }

    /** What loggingLevel to use for reporter.logMsg(). */
    private int level = Reporter.DEFAULT_LOGGINGLEVEL;

    /**
     * Accesor methods; don't think it needs to be synchronized.  
     *
     * NEEDSDOC @param l
     */
    public void setLoggingLevel(int l)
    {
        level = l;
    }

    /**
     * Accesor methods; don't think it needs to be synchronized.  
     *
     * NEEDSDOC ($objectName$) @return
     */
    public int getLoggingLevel()
    {
        return level;
    }

    /**
     * Grab basic info out of a TransformerException.
     *
     * @param exception to get information from
     * @return simple string describing the exception (getMessageAndLocation())
     */
    public String getTransformerExceptionInfo(TransformerException exception)
    {

        if (exception == null)
            return null;

        return exception.getMessageAndLocation();
    }

    /**
     * Implementation of warning; calls logMsg with info contained in exception.
     *
     * NEEDSDOC @param exception
     * @exception TransformerException thrown only if asked to or if reporters are bad
     */
    public void warning(TransformerException exception) throws TransformerException
    {

        // Increment counter, save the exception, and log what we got
        warningCtr++;

        String exInfo = getTransformerExceptionInfo(exception);

        setLastError(exInfo);

        if (reporter != null)
        {
            reporter.logMsg(level, prefix + " warning threw: " + exInfo);
            reporter.logMsg(level, getCounterString());
        }

        if ((throwWhen & THROW_ON_WARNING) == THROW_ON_WARNING)
        {
            throw new TransformerException(exception);
        }
    }

    /**
     * Implementation of error; calls logMsg with info contained in exception.
     * Only ever throws an exception itself if asked to or if reporters are bad.
     *
     * NEEDSDOC @param exception
     * @exception TransformerException thrown only if asked to or if reporters are bad
     */
    public void error(TransformerException exception) throws TransformerException
    {

        // Increment counter, save the exception, and log what we got
        errorCtr++;

        String exInfo = getTransformerExceptionInfo(exception);

        setLastError(exInfo);

        if (reporter != null)
        {
            reporter.logMsg(level, prefix + " error threw: " + exInfo);
            reporter.logMsg(level, getCounterString());
        }

        if ((throwWhen & THROW_ON_ERROR) == THROW_ON_ERROR)
        {
            throw new TransformerException(exception);
        }
    }

    /**
     * Implementation of error; calls logMsg with info contained in exception.
     * Only ever throws an exception itself if asked to or if reporters are bad.
     * Note that this may cause unusual behavior since we may not actually
     * re-throw the exception, even though it was 'fatal'.
     *
     * NEEDSDOC @param exception
     * @exception TransformerException thrown only if asked to or if reporters are bad
     */
    public void fatalError(TransformerException exception) throws TransformerException
    {

        // Increment counter, save the exception, and log what we got
        fatalCtr++;

        String exInfo = getTransformerExceptionInfo(exception);

        setLastError(exInfo);

        if (reporter != null)
        {
            reporter.logMsg(level, prefix + " fatal threw: " + exInfo);
            reporter.logMsg(level, getCounterString());
        }

        if ((throwWhen & THROW_ON_FATAL) == THROW_ON_FATAL)
        {
            throw new TransformerException(exception);
        }
    }
}