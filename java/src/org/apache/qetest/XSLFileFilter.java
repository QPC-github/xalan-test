/*
 * Copyright 2000-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id$
 */
package org.apache.qetest;

/**
 * FilenameFilter supporting includes/excludes returning files
 * that match *.xsl.
 *
 * @author shane_curcuru@us.ibm.com
 * @version $Id$
 */
public class XSLFileFilter extends FilePatternFilter
{

    /** Default pattern we're looking for: *.xsl.  */
    public static final String PATTERN = "*.xsl";

    /** 
     * Initialize for default pattern.  
     */
    public XSLFileFilter() 
    {
        super(null, null, PATTERN);
    }

    /**
     * Initialize with some include(s)/exclude(s).  
     *
     * @param inc semicolon-delimited string of inclusion name(s)
     * @param exc semicolon-delimited string of exclusion name(s)
     */
    public XSLFileFilter(String inc, String exc)
    {
        super(inc, exc, PATTERN);
    }
}
