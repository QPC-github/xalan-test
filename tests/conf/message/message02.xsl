<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <!-- FileName: MESSAGE02 -->
  <!-- Document: http://www.w3.org/TR/xslt -->
  <!-- DocVersion: 19991008 -->
  <!-- Section: 13 -->
  <!-- Creator: David Marston -->
  <!-- Purpose: Issue a message from a literal constant, "no" on terminate option -->                

<xsl:template match="/">
  <xsl:message terminate="no">This message came from the MESSAGE02 test.</xsl:message>
  If we got this far, we did not terminate.
</xsl:template>

</xsl:stylesheet>