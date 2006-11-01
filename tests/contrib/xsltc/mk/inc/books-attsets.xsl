<xsl:stylesheet  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">



<xsl:attribute-set name="attset1" use-attribute-sets="attset2">

    <xsl:attribute name="LEFTMARGIN">150</xsl:attribute>

    <xsl:attribute name="RIGHTMARGIN">150</xsl:attribute>

    <xsl:attribute name="TOPMARGIN">190</xsl:attribute>

</xsl:attribute-set>



<xsl:variable name="x">attsets.xsl</xsl:variable>



<xsl:attribute-set name="attset2">

    <xsl:attribute name="TOPMARGIN">180</xsl:attribute>

    <xsl:attribute name="BOTTOMMARGIN">200</xsl:attribute>

</xsl:attribute-set>

  <!--
   * Licensed to the Apache Software Foundation (ASF) under one
   * or more contributor license agreements. See the NOTICE file
   * distributed with this work for additional information
   * regarding copyright ownership. The ASF licenses this file
   * to you under the Apache License, Version 2.0 (the  "License");
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
  -->

</xsl:stylesheet>
