<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <!-- FileName: stringerr16 -->
  <!-- Document: http://www.w3.org/TR/xpath -->
  <!-- DocVersion: 19991116 -->
  <!-- Section: 4.2 String Functions  -->
  <!-- Creator: David Marston -->
  <!-- Purpose: Test of 'translate()' with 2 arguments -->
  <!-- ExpectedException: The translate() function takes three arguments -->
  <!-- ExpectedException: FuncTranslate only allows 3 arguments -->

<xsl:template match="/">
  <out>
    <xsl:value-of select="translate('bar','abc')"/>
  </out>
</xsl:template>

</xsl:stylesheet>